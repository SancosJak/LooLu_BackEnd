package loolu.loolu_backend.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import loolu.loolu_backend.dto.ProductDTO;
import loolu.loolu_backend.models.Picture;
import loolu.loolu_backend.models.Product;
import loolu.loolu_backend.repositories.PictureRepository;
import loolu.loolu_backend.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:3000")
@Api(value = "Product Management System", tags = "Product Controller")
public class ProductController {

    private final ProductService productService;
    private final PictureRepository pictureRepository;

    @Autowired
    public ProductController(ProductService productService, PictureRepository pictureRepository) {
        this.productService = productService;
        this.pictureRepository = pictureRepository;
    }

    @ApiOperation(value = "Get all products with optional filtering and sorting", response = Page.class)
    @GetMapping
    public ResponseEntity<Page<ProductDTO>> getFilteredProducts(
            @ApiParam(value = "Filter by title") @RequestParam(required = false) String title,
            @ApiParam(value = "Filter by price") @RequestParam(required = false) Double price,
            @ApiParam(value = "Filter by minimum price") @RequestParam(required = false) Double price_min,
            @ApiParam(value = "Filter by maximum price") @RequestParam(required = false) Double price_max,
            @ApiParam(value = "Filter by category ID") @RequestParam(required = false) Long categoryId,
            @ApiParam(value = "Page number") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "Page size") @RequestParam(defaultValue = "10") int size,
            @ApiParam(value = "Sort by field") @RequestParam(defaultValue = "categoryId") String sortBy,
            @ApiParam(value = "Sort direction") @RequestParam(defaultValue = "asc") String sortDir) {

        Sort.Direction direction = sortDir.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));

        List<Product> filteredProducts = productService.filterProducts(title, price, price_min, price_max, categoryId);

        // Создаем подстраницу из всего списка продуктов
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), filteredProducts.size());
        Page<Product> pagedProducts = new PageImpl<>(filteredProducts.subList(start, end), pageable,
                filteredProducts.size());

        // Преобразуем продукты в DTO
        List<ProductDTO> productDTOs = pagedProducts.stream()
                .map(product -> {
                    ProductDTO productDTO = new ProductDTO(
                            product.getId(),
                            product.getTitle(),
                            product.getPrice(),
                            product.getDescription(),
                            product.getCategory().getId(),
                            product.getCategory().getName(),
                            new ArrayList<>()
                    );

                    Set<Picture> pictures = pictureRepository.findByProduct(product);
                    for (Picture picture : pictures) {
                        picture.setProduct(null);
                        productDTO.getImageUrls().add(picture.getUrl());
                    }

                    product.setPicture(pictures);
                    return productDTO;
                }).collect(Collectors.toList());

        Page<ProductDTO> productDTOPage = new PageImpl<>(productDTOs, pageable, filteredProducts.size());

        return new ResponseEntity<>(productDTOPage, HttpStatus.OK);
    }

    @ApiOperation(value = "Get product by ID", response = ProductDTO.class)
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);

        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ProductDTO productDTO = new ProductDTO(
                product.getId(),
                product.getTitle(),
                product.getPrice(),
                product.getDescription(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                new ArrayList<>()
        );

        Set<Picture> pictures = pictureRepository.findByProduct(product);
        for (Picture picture : pictures) {
            picture.setProduct(null);
            productDTO.getImageUrls().add(picture.getUrl());
        }

        product.setPicture(pictures);
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    @ApiOperation(value = "Add a new product")
    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product savedProduct = productService.saveProduct(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update an existing product")
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        Product existingProduct = productService.getProductById(id);
        if (existingProduct != null) {
            updatedProduct.setId(id);
            Product savedProduct = productService.saveProduct(updatedProduct);
            return new ResponseEntity<>(savedProduct, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Delete a product")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        Product existingProduct = productService.getProductById(id);
        if (existingProduct != null) {
            productService.deleteProduct(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
