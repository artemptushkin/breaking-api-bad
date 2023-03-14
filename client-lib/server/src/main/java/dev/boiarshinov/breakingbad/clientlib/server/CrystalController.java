package dev.boiarshinov.breakingbad.clientlib.server;

import dev.boiarshinov.breakingbad.clientlib.datamodel.CrystalUpdateRequestDto;
import dev.boiarshinov.breakingbad.clientlib.datamodel.CrystalUpdatedDto;
import dev.boiarshinov.breakingbad.clientlib.datamodel.ResponseDto;
import org.springframework.core.MethodParameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/v1/crystals")
public class CrystalController {
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<CrystalUpdatedDto>> updateCrystal(
            @RequestHeader("X-CUSTOM-TOKEN") String token,
            @PathVariable("id") String id,
            @RequestBody CrystalUpdateRequestDto request
    ) {
        var crystalUpdatedDto = new CrystalUpdatedDto(UUID.randomUUID().toString(), request.type(), request.price());
        return ResponseEntity.ok(new ResponseDto<>("S000", "Success", crystalUpdatedDto));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<ResponseDto> handleInvalidArguments(MethodArgumentNotValidException ex) {
        Map<String, MethodParameter> responseMap = Map.of("param", ex.getParameter());

        return ResponseEntity.badRequest()
                .body(new ResponseDto("E802", "Invalid Request Params", responseMap));
    }
}
