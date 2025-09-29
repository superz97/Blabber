package com.github.superz97.core.web.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {

    private String message;

}
