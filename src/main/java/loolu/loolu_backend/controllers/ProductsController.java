package loolu.loolu_backend.controllers;

import lombok.AllArgsConstructor;
import loolu.loolu_backend.models.Products;

import loolu.loolu_backend.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
@AllArgsConstructor
public class ProductsController {

    private final ProductService productService;

    @GetMapping
    public List<Products> findAllProducts(){

        return productService.findAllProducts();
    }

    @PostMapping("save_product")
    public String saveProduct(@RequestBody Products products){
        productService.saveProduct(products);
        return "Product saved successfully";
    }

    @GetMapping("/{id}")
    public Products findProductById(@PathVariable long id){
        return productService.findProductById(id);
    }

    @PutMapping("update_product")
    public Products updateProduct(@RequestBody Products products){
        return productService.updateProduct(products);
    }

    @DeleteMapping("delete_product/{id}")
    public void deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);

    }
}
