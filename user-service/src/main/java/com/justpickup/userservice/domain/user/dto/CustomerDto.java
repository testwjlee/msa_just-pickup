package com.justpickup.userservice.domain.user.dto;

import com.justpickup.userservice.domain.user.entity.AuthType;
import com.justpickup.userservice.domain.user.entity.Customer;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CustomerDto extends UserDto {

    private AuthType authType;

    public CustomerDto(Customer customer) {
        super(customer);
        this.authType = customer.getOauthType();
    }

    @Builder
    public CustomerDto(Long id, String email, String password, String name,
                       String phoneNumber, String dtype, String refreshTokenId) {
        super(id, email, password, name, phoneNumber, dtype, refreshTokenId);
    }

}