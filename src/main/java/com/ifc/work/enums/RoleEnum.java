package com.ifc.work.enums;

import lombok.Getter;

@Getter
public enum RoleEnum {
    ADMIN("admin"),
    USER("user"),
    ASSISTANT("assistant"),
    OPERATOR("operator");

    private String role;

    RoleEnum(String role) {
        this.role = role;
    }
}
