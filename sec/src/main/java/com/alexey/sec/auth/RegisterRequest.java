package com.alexey.sec.auth;

import com.alexey.sec.store.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private Role role;
    private String name;
    private String email;
    private String password;
}
