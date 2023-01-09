package com.example.rest2.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Product
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String title;

    private double price;

    private String secretKey;

    public Product(Long id, String title, double price)
    {
        this.id = id;
        this.title = title;
        this.price = price;
    }
}
