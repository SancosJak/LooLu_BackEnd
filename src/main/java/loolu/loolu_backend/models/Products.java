package loolu.loolu_backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data

@Entity
public class Products {

    @Id                         //primary key
    @GeneratedValue             //автоматическая генерация
    private Long productId;

    private String productName;
    private String productDescription;
    private Double productPrice;
    private String productCategory;

}
