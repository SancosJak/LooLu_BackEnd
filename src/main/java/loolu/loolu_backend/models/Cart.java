package loolu.loolu_backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Cart {
    // Hibernate
    public enum State{
        DRAFT,PUBLISHED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 25)
    private String title;

    @Column(nullable = false,length = 500)
    private String description;

    private LocalDate beginDate;
    private LocalDate endDate;

    @Column(nullable = false)
    private Double price;

    @Enumerated(value = EnumType.STRING)
    private State state;

    @ManyToMany(mappedBy = "carts_products")
    private Set<User> products;

    @OneToMany(mappedBy = "cart")
    private Set<Cart> product;
}
