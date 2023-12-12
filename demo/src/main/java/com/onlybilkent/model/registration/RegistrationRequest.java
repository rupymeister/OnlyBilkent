package com.onlybilkent.model.registration;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {

    private final String email;
    private final String password;
    private final String name;
    private final String surname;
    private final String bio;
    private final byte[] imageData;
}
