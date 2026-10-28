package org.techie.microservices.product.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

//@Document(value = "product") // for mongodb -> I am using postgres to not this for now
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data // for @Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Column(nullable = false)
    private BigDecimal price;
}
