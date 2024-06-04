package loolu.loolu_backend.repository;

import loolu.loolu_backend.models.Products;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Repository
public class InMemoryProductsDAO {
    private final List<Products> PRODUCTS = new ArrayList<>();



    public List<Products> findAllProducts() {          //для чтения всех продуктов
        return PRODUCTS;
    }


    public Products saveProduct(Products product) {    //сохраняем продукт
        PRODUCTS.add(product);
        return product;
    }


    public Products findProductById(Long id) {
        return PRODUCTS.stream()
                .filter(el -> el.getProductId().equals(id))
                .findFirst()
                .orElse(null);
    }


    public Products updateProduct(Products product) {
        var productIndex = IntStream.range(0, PRODUCTS.size())
                .filter(index -> PRODUCTS.get(index).getProductId().equals(product.getProductId()))
                .findFirst()
                .orElse(-1);
        if (productIndex > -1) {                         //есои продукт найден
            PRODUCTS.set(productIndex, product);         //заменяем значения на новые
            return product;
        }
        return null;
    }


    public void deleteProduct(Long id) {
        var delete = findProductById(id);
        if (delete != null){
            PRODUCTS.remove(delete);
        }

    }
}
