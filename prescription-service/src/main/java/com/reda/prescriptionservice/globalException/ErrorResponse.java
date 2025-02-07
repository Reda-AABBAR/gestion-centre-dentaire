package com.reda.prescriptionservice.globalException;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
class ErrorResponse {
    private LocalDateTime timestamp;
    private String message;
}
