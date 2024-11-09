package com.ecommerce.order.microservice.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ModelMapperEx extends ModelMapper {
    public <T, U> List<U> mapList(List<T> source, Class<U> targetClass) {
        return source.stream()
                .map(element -> map(element, targetClass))
                .toList();
    }
}