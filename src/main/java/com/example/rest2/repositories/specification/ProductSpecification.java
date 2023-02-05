package com.example.rest2.repositories.specification;

import com.example.rest2.models.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification
{
    public static Specification<Product> priceGreaterOrEqualsThan(Integer price)
    {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), price));
    }

    public static Specification<Product> priceLessOrEqualsThan(Integer price)
    {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), price));
    }

    public static Specification<Product> likeTitle(String partTitle)
    {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), String.format("%%%s%%", partTitle)));
    }
}
