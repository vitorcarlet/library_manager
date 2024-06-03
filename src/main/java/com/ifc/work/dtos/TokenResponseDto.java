package com.ifc.work.dtos;


import lombok.Builder;

@Builder
public record TokenResponseDto(String token, String refreshToken) {
}
