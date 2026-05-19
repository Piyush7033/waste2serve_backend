package com.fooddonation.system.dto;

import com.fooddonation.system.entity.Role;
import lombok.Getter;
import lombok.Setter;

public class RegisterRequestDto {

    @Getter
    private String name;
    @Getter
    private String email;
    @Setter
    @Getter
    private String password;
    @Setter
    private Role role;

    public void setName(String name) {
        this.name = name != null ? name.trim() : null;
    }

    public void setEmail(String email) {
        this.email = email != null ? email.trim().toLowerCase() : null;
    }

    public Role getRole() {
        return role != null ? role : Role.RECEIVER; // ✅ default role safety
    }

}