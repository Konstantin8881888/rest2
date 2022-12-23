package com.example.rest2.controllers;

import com.example.rest2.models.Product;
import com.example.rest2.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/basket")
public class BasketController {

    private final BasketService basketService;

    @GetMapping("/get")
    public List<Product> productList() {
        return basketService.getProducts();
    }

    @PostMapping("/addOne")
    public void addProduct(@RequestBody Product product) {
        basketService.addProduct(product);
    }

    @PostMapping("/addMore")
    public void addProducts(@RequestBody Product... products) {
        basketService.addProducts(products);
    }

    @DeleteMapping("/delete")
    public void deleteProductFromBasket(@RequestBody Product product) {
        basketService.deleteProductFromBasket(product);
    }
}
