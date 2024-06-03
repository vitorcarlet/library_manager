package com.ifc.work.entities;

public record UserPermissions (
        boolean isAdmin,
        boolean isOperator,
        boolean isAssistant
){
}
