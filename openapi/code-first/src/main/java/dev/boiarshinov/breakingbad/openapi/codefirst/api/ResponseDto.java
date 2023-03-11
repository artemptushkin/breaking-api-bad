package dev.boiarshinov.breakingbad.openapi.codefirst.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

// Inspired by RFC 7807
@Data
@AllArgsConstructor
@Schema(title = "Common response structure")
public class ResponseDto<T> {
    @Schema(description = "Code of the response", example = "S000") private String code;
    @Schema(description = "Detailed message for the response code", example = "Success") private String detail;
    @Schema(description = "Response or error data", nullable = true) private T data;
}
