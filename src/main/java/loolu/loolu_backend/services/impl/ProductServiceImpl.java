package loolu.loolu_backend.services.impl;

import loolu.loolu_backend.models.Product;
import loolu.loolu_backend.repositories.PicturesRepository;
import loolu.loolu_backend.repositories.ProductRepository;
import loolu.loolu_backend.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final PicturesRepository picturesRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, PicturesRepository picturesRepository) {
        this.productRepository = productRepository;
        this.picturesRepository = picturesRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        Object obj = picturesRepository.findById(1L);
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }
    @Override
    public Product saveProduct(Product product) {
        if (product.getTitle() == null || product.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Product title is required");
        }

        if (product.getPrice() == null || product.getPrice() <= 0) {
            throw new IllegalArgumentException("Product price must be greater than zero");
        }

        if (product.getCategory() == null) {
            throw new IllegalArgumentException("Product category is required");
        }

        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> filterProducts(String title, Double price, Double price_min, Double price_max, Long categoryId) {
        if (title != null && price_min != null && price_max != null) {
            if (price_min > price_max) {
                throw new IllegalArgumentException("Minimum price cannot be greater than maximum price");
            }
            return productRepository.findProductsByTitleAndPriceBetween(title, price_min, price_max);
        } else if (title != null && price != null) {
            return productRepository.findProductsByTitleAndPriceBetween(title, price, price);
        } else if (title != null) {
            return productRepository.findByTitleContainingIgnoreCase(title);
        } else if (price_min != null && price_max != null) {
            if (price_min > price_max) {
                throw new IllegalArgumentException("Minimum price cannot be greater than maximum price");
            }
            return productRepository.findByPriceBetween(price_min, price_max);
        } else if (categoryId != null) {
            return productRepository.findByCategory_Id(categoryId);
        } else {
            return productRepository.findAll();
        }
    }
}