package com.example.rest2.service;

import com.example.rest2.DTO.ProductDto;
import com.example.rest2.models.Product;
import com.example.rest2.repositories.ProductRepository;
import com.example.rest2.repositories.specification.ProductSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;

    public Page<Product> findAll(Integer minPrice, Integer maxPrice, String partTitle, Integer page) {
        Specification<Product> spec = Specification.where(null);
        if (minPrice != null) {
            spec = spec.and(ProductSpecification.priceGreaterOrEqualsThan(minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and(ProductSpecification.priceLessOrEqualsThan(maxPrice));
        }
        if (partTitle != null) {
            spec = spec.and(ProductSpecification.likeTitle(partTitle));
        }
        return repository.findAll(spec, PageRequest.of(0, 5));
    }

    public Product findOneById(Long id) {
        Product product = null;
        Optional<Product> byId = repository.findById(id);
        if (byId.isPresent()) {
            product = byId.get();
        }
        assert product != null;
        return product;
    }

    @Transactional
    public Product update(ProductDto productDto) {
        Product product = repository.findById(productDto.getId()).orElseThrow(() -> new RuntimeException("Unreachable update"));
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        return product;
    }

    public Product addProduct(Product product) {
        product.setId(null);
        return repository.save(product);
    }

    public void delete(long id) {
        repository.deleteById(id);
    }
}
