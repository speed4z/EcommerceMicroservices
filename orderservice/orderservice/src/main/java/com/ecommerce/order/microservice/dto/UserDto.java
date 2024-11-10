package com.ecommerce.order.microservice.dto;

import com.ecommerce.order.microservice.configuration.Role;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class UserDto {

    private String userId;
    private Role role;

}

