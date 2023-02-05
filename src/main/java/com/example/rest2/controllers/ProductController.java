package com.example.rest2.controllers;

import com.example.rest2.DTO.ProductDto;
import com.example.rest2.converters.ProductConverter;
import com.example.rest2.models.Product;
import com.example.rest2.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController
{

    private static final String ADD_URL = "/add";
    private static final String DELETE_URL = "/delete/{id}";
    private final ProductService service;
    private final ProductConverter converter;

    @GetMapping
    public Page<ProductDto> all(
            @PageableDefault(page = 1)
            @RequestParam(name = "p", defaultValue = "1") Integer page,
            @RequestParam(name = "min_price", required = false) Integer minPrice,
            @RequestParam(name = "max_price", required = false) Integer maxPrice,
            @RequestParam(name = "part_title", required = false) String namePart
    )
    {
        return service.findAll(minPrice, maxPrice, namePart, page)
                .map(converter::entityToDto);
    }

    @GetMapping("/{id}")
    public ProductDto findOneById(@PathVariable Long id)
    {
        Product oneById = service.findOneById(id);
        return converter.entityToDto(oneById);
    }

    @PutMapping
    public ProductDto update(@RequestBody ProductDto productDto)
    {
        Product update = service.update(productDto);
        return converter.entityToDto(update);
    }

    @PostMapping(ADD_URL)
    public ProductDto addProduct(@RequestBody ProductDto productDto)
    {
        Product product = converter.dtoToEntity(productDto);
        service.addProduct(product);
        return converter.entityToDto(product);
    }

    @DeleteMapping(DELETE_URL)
    public void delete(@PathVariable long id)
    {
        service.delete(id);
    }
}
