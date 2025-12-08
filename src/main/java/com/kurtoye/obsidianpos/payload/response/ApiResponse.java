package com.kurtoye.obsidianpos.payload.response;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@NotNull
public class ApiResponse {
    @NotNull
    String message;
}
