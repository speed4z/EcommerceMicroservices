package com.ecommerce.inventory_service.dto;

import com.ecommerce.inventory_service.configuration.Role;
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

