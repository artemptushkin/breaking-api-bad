package dev.boiarshinov.breakingbad.openapi.codefirst.api;

import dev.boiarshinov.breakingbad.openapi.codefirst.api.util.BadRequestApiResponse;
import dev.boiarshinov.breakingbad.openapi.codefirst.api.util.InternalServerErrorApiResponse;
import dev.boiarshinov.breakingbad.openapi.codefirst.api.util.UnauthorizedApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/v1/crystals")
public class CodefirstController {

    @Operation(
            summary = "Update crystal info",
            tags = OpenApiConfig.CRYSTAL_TAG,
            operationId = "updateCrystalInfo"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Success",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = CrystalUpdatedBody.class)
            )
    )
    @BadRequestApiResponse
    @UnauthorizedApiResponse
    @InternalServerErrorApiResponse
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<CrystalUpdatedDto>> updateCrystal(
            @Parameter(description = "Subscribe to receive an access to our Breaking Bad API",
                    schema = @Schema(minLength = 32, maxLength = 32),
                    example = "5f894480bfe911edafa10242ac120002")
            @RequestHeader("X-CUSTOM-TOKEN") String token,

            @Parameter(description = "identifier of the crystal",
                    schema = @Schema(minLength = 16, maxLength = 16),
                    example = "0123456789ABCDEF")
            @PathVariable("id") String id,

            @RequestBody CrystalUpdateRequestDto request
    ) {
        CrystalUpdatedDto crystalUpdatedDto = new CrystalUpdatedDto(UUID.randomUUID().toString(), request.type, request.price);
        return ResponseEntity.ok(new ResponseDto<>("S000", "Success", crystalUpdatedDto));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<ResponseDto> handleInvalidArguments(MethodArgumentNotValidException ex) {
        Map<String, MethodParameter> responseMap = Map.of("param", ex.getParameter());

        return ResponseEntity.badRequest()
                .body(new ResponseDto("E802", "Invalid Request Params", responseMap));
    }

    @Schema(title = "New parameters of the crystal")
    private record CrystalUpdateRequestDto(
            @Schema(description = "Type of the crystal", example = "BLUE") String type,
            @Schema(description = "Price of the crystal", example = "300") long price) { }

    @Schema(title = "Updated parameters of the crystal")
    private record CrystalUpdatedDto(
            @Schema(description = "Identifier", example = "0123456789ABCDEF", minLength = 16, maxLength = 16) String id,
            @Schema(description = "Type of the crystal", example = "BLUE") String type,
            @Schema(description = "Price of the crystal", example = "300") long price) { }

    //workaround for springdoc, because it not supports generics
    @Schema(title = "Response body for the crystal update")
    private static class CrystalUpdatedBody extends ResponseDto<CrystalUpdatedDto> {
        public CrystalUpdatedBody(String code, String detail, CrystalUpdatedDto data) {
            super(code, detail, data);
        }
    }
}
