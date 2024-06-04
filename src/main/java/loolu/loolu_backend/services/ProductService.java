package loolu.loolu_backend.services;

import loolu.loolu_backend.models.Products;

import java.util.List;


public interface ProductService {

    List<Products> findAllProducts();

    Products saveProduct(Products product);

    Products findProductById(Long id);

    Products updateProduct(Products product);

    void deleteProduct(Long id);

}

