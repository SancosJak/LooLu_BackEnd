//package loolu.loolu_backend.controllers;
//
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import loolu.loolu_backend.models.Picture;
//import loolu.loolu_backend.models.Product;
//import loolu.loolu_backend.repositories.PictureRepository;
//import loolu.loolu_backend.services.ProductService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Set;
//
//@Tag(name = "Product Controller", description = "Controller for managing products")
//@RestController
//@RequestMapping("/api/products")
//@CrossOrigin(origins = "http://localhost:3000")
//public class ProductController {
//
//    private final ProductService productService;
//    private final PictureRepository pictureRepository;
//
//    @Autowired
//    public ProductController(ProductService productService, PictureRepository pictureRepository) {
//        this.productService = productService;
//        this.pictureRepository = pictureRepository;
//    }
//
//    @Operation(
//            summary = "Get all products",
//            description = "Retrieve all products available in the database"
//    )
//    @GetMapping
//    public ResponseEntity<List<Product>> getFilteredProducts(
//            @RequestParam(required = false) String title,
//            @RequestParam(required = false) Double price,
//            @RequestParam(required = false) Double price_min,
//            @RequestParam(required = false) Double price_max,
//            @RequestParam(required = false) Long categoryId) {
//
//        List<Product> filteredProducts = productService.filterProducts(title, price, price_min, price_max, categoryId);
//
//        if (price_min != null && price_max != null) {
//            filteredProducts = productService.filterProducts(title, null, price_min, price_max, categoryId);
//        } else if (price != null) {
//            filteredProducts = productService.filterProducts(title, price, null, null, categoryId);
//        } else {
//            filteredProducts = productService.filterProducts(title, null, null, null, categoryId);
//        }
//
//        return new ResponseEntity<>(filteredProducts, HttpStatus.OK);
//    }
//
//    @Operation(
//            summary = "Get a product by ID",
//            description = "Retrieve a single product by its ID"
//    )
//    @GetMapping("/{id}")
//    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
//        Product product = productService.getProductById(id);
//
//        if (product == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//        Set<Picture> pictures = pictureRepository.findByProduct(product);
//
//        // Пройти по найденным изображениям и сбросить ссылку на продукт
//        for (Picture picture : pictures) {
//            picture.setProduct(null);
//        }
//
//        product.setPicture(pictures);
//
//        return new ResponseEntity<>(product, HttpStatus.OK);
//    }
//
//
//    @Operation(
//            summary = "Add a new product",
//            description = "Add a new product to the database"
//    )
//    @PostMapping
//    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
//        Product savedProduct = productService.saveProduct(product);
//        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
//    }
//
//    @Operation(
//            summary = "Update an existing product",
//            description = "Update an existing product by its ID"
//    )
////    @PutMapping("/{id}")
////    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
////        Product existingProduct = productService.getProductById(id);
////        if (existingProduct != null) {
////            product.setId(id);
////            Product updatedProduct = productService.saveProduct(product);
////            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
////        } else {
////            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
////        }
////    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
//        Product existingProduct = productService.getProductById(id);
//        if (existingProduct != null) {
//            // Проверяем каждое поле и обновляем только то, что изменилось
//            if (updatedProduct.getTitle() != null) {
//                existingProduct.setTitle(updatedProduct.getTitle());
//            }
//            if (updatedProduct.getDescription() != null) {
//                existingProduct.setDescription(updatedProduct.getDescription());
//            }
//            if (updatedProduct.getPrice() != null) {
//                existingProduct.setPrice(updatedProduct.getPrice());
//            }
//            if (updatedProduct.getCategory() != null) {
//                existingProduct.setCategory(updatedProduct.getCategory());
//            }
//
//            // Сохраняем обновленный продукт
//            Product savedProduct = productService.saveProduct(existingProduct);
//            return new ResponseEntity<>(savedProduct, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//
//    @Operation(
//            summary = "Delete a product",
//            description = "Delete a product by its ID"
//    )
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
//        Product existingProduct = productService.getProductById(id);
//        if (existingProduct != null) {
//            productService.deleteProduct(id);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @GetMapping("/products/min-price")
//    public Double getMinPrice() {
//        return productService.findMinPrice();
//    }
//
//    @GetMapping("/products/max-price")
//    public Double getMaxPrice() {
//        return productService.findMaxPrice();
//    }
//
//    @GetMapping("/products/price-greater-than")
//    public List<Product> getProductsByPriceGreaterThanEqual(@RequestParam Double minPrice) {
//        return productService.findByPriceGreaterThanEqual(minPrice);
//    }
//
//}
//-------------------------------------------------------

package loolu.loolu_backend.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import loolu.loolu_backend.models.Picture;
import loolu.loolu_backend.models.Product;
import loolu.loolu_backend.repositories.PictureRepository;
import loolu.loolu_backend.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Tag(name = "Product Controller", description = "Controller for managing products")
@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

    private final ProductService productService;
    private final PictureRepository pictureRepository;

    @Autowired
    public ProductController(ProductService productService, PictureRepository pictureRepository) {
        this.productService = productService;
        this.pictureRepository = pictureRepository;
    }

    @Operation(
            summary = "Get all products",
            description = "Retrieve all products available in the database, optionally filtered by title, price range, or category ID"
    )
    @GetMapping
    public ResponseEntity<List<Product>> getFilteredProducts(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Double price,
            @RequestParam(required = false) Double price_min,
            @RequestParam(required = false) Double price_max,
            @RequestParam(required = false) Long categoryId) {

        List<Product> filteredProducts;

        // Если указаны price_min и price_max, фильтруем по диапазону цен
        if (price_min != null && price_max != null) {
            filteredProducts = productService.filterProducts(title, null, price_min, price_max, categoryId);
        }
        // Если указана только одна цена (параметр price), фильтруем по точной цене
        else if (price != null) {
            filteredProducts = productService.filterProducts(title, price, null, null, categoryId);
        }
        // Если не указаны цены, просто фильтруем по названию и/или категории
        else {
            filteredProducts = productService.filterProducts(title, null, null, null, categoryId);
        }

        return new ResponseEntity<>(filteredProducts, HttpStatus.OK);
    }

    @Operation(
            summary = "Get a product by ID",
            description = "Retrieve a single product by its ID"
    )
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);

        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Set<Picture> pictures = pictureRepository.findByProduct(product);

        // Пройти по найденным изображениям и сбросить ссылку на продукт
        for (Picture picture : pictures) {
            picture.setProduct(null);
        }

        product.setPicture(pictures);

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @Operation(
            summary = "Add a new product",
            description = "Add a new product to the database"
    )
    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product savedProduct = productService.saveProduct(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Update an existing product",
            description = "Update an existing product by its ID"
    )
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        Product existingProduct = productService.getProductById(id);
        if (existingProduct != null) {
            // Проверяем каждое поле и обновляем только то, что изменилось
            if (updatedProduct.getTitle() != null) {
                existingProduct.setTitle(updatedProduct.getTitle());
            }
            if (updatedProduct.getDescription() != null) {
                existingProduct.setDescription(updatedProduct.getDescription());
            }
            if (updatedProduct.getPrice() != null) {
                existingProduct.setPrice(updatedProduct.getPrice());
            }
            if (updatedProduct.getCategory() != null) {
                existingProduct.setCategory(updatedProduct.getCategory());
            }

            // Сохраняем обновленный продукт
            Product savedProduct = productService.saveProduct(existingProduct);
            return new ResponseEntity<>(savedProduct, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(
            summary = "Delete a product",
            description = "Delete a product by its ID"
    )
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



    @Operation(
            summary = "Get the minimum price of all products",
            description = "Retrieve the minimum price of all products in the database"
    )
    @GetMapping("/min-price")
    public ResponseEntity<Double> getMinPrice() {
        Double minPrice = productService.findMinPrice();
        return new ResponseEntity<>(minPrice, HttpStatus.OK);
    }

    @Operation(
            summary = "Get the maximum price of all products",
            description = "Retrieve the maximum price of all products in the database"
    )
    @GetMapping("/max-price")
    public ResponseEntity<Double> getMaxPrice() {
        Double maxPrice = productService.findMaxPrice();
        return new ResponseEntity<>(maxPrice, HttpStatus.OK);
    }


    @Operation(
            summary = "Get products with a price greater than or equal to the specified value",
            description = "Retrieve products with a price greater than or equal to the specified value"
    )
    @GetMapping("/price-greater-than")
    public ResponseEntity<List<Product>> getProductsByPriceGreaterThanEqual(@RequestParam Double minPrice) {
        List<Product> products = productService.findByPriceGreaterThanEqual(minPrice);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

}

