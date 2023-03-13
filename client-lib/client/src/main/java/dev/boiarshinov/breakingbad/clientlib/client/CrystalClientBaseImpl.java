package dev.boiarshinov.breakingbad.clientlib.client;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dev.boiarshinov.breakingbad.clientlib.datamodel.CrystalUpdateRequestDto;
import dev.boiarshinov.breakingbad.clientlib.datamodel.CrystalUpdatedDto;
import dev.boiarshinov.breakingbad.clientlib.datamodel.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.cbor.MappingJackson2CborHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.Duration;
import java.util.Map;

//Dumbest implementation
//Do not use it in production. Only for inspiration purposes
@Slf4j
@RequiredArgsConstructor
public class CrystalClientBaseImpl implements CrystalClient {


    public static final String ENDPOINT_PATH = "/v1/crystal/{id}";

    private final ObjectMapper jsonMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .registerModule(new JavaTimeModule());

    private final CrystalClientProperties props;
    private final RestTemplate restTemplate = new RestTemplateBuilder()
            .setConnectTimeout(Duration.ofMillis(props.getTimeout()))
            .messageConverters(new MappingJackson2CborHttpMessageConverter(jsonMapper))
            .build();

    @Override
    public CrystalUpdatedDto updateCrystalInfo(String id, CrystalUpdateRequestDto crystalUpdateInfo) {
        URI uri = UriComponentsBuilder.fromUriString(props.getUri())
                .path(ENDPOINT_PATH)
                .build(Map.of("id", id));

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-CUSTOM-TOKEN", props.getToken());
        HttpEntity<CrystalUpdateRequestDto> requestEntity = new HttpEntity<>(crystalUpdateInfo, httpHeaders);
        ResponseEntity<ResponseDto> responseEntity = restTemplate.exchange(
                uri,
                HttpMethod.PUT,
                requestEntity,
                ResponseDto.class);

        if (responseEntity.getStatusCodeValue() != 200
                || responseEntity.getBody() == null
                || !responseEntity.getBody().code().equals("S000")
        ) {
            log.warn("Unexpected response during crystal server invocation. Http code: {}. Business code: {}",
                    responseEntity.getStatusCodeValue(),
                    responseEntity.getBody() != null ? responseEntity.getBody().code() : null);
        }

        ResponseDto<CrystalUpdatedDto> responseDto = responseEntity.getBody();
        return responseDto.data();
    }
}
