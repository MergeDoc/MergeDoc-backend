package com.mergedoc.backend.auth.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpForm {
    @NotNull
    private String email;
    @NotNull
    private String username;
    @NotNull
    private String password;
}
