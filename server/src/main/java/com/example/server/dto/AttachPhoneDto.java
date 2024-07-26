package com.example.server.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttachPhoneDto {
    @NotNull
    private Long employeeId;

    @NotNull
    private Long phoneId;
}
