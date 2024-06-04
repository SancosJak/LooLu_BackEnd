package loolu.loolu_backend.services.impl;

import lombok.AllArgsConstructor;
import loolu.loolu_backend.models.Products;
import loolu.loolu_backend.repository.InMemoryProductsDAO;

import loolu.loolu_backend.services.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InMemoryProductServiceImpl implements ProductService {

    private final InMemoryProductsDAO repositoryProductsDAO;

    @Override
    public List<Products> findAllProducts() {
        return repositoryProductsDAO.findAllProducts();
    }

    @Override
    public Products saveProduct(Products product) {
        return repositoryProductsDAO.saveProduct(product);
    }

    @Override
    public Products findProductById(Long id) {
        return repositoryProductsDAO.findProductById(id);
    }

    @Override
    public Products updateProduct(Products product) {
        return repositoryProductsDAO.updateProduct(product);
    }

    @Override
    public void deleteProduct(Long id) {
        repositoryProductsDAO.deleteProduct(id);
    }
}
