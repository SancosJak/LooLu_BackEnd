package loolu.loolu_backend.services;

import loolu.loolu_backend.models.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(Long id);
    Product saveProduct(Product product);
    void deleteProduct(Long id);

    List<Product> findProductsByTitle(String title);

    List<Product> findProductsByCategory(Long categoryId);

    List<Product> findProductsByPriceBetween(Double minPrice, Double maxPrice);

    List<Product> findProductsByNameAndPriceBetween(String name, Double minPrice, Double maxPrice);
}
