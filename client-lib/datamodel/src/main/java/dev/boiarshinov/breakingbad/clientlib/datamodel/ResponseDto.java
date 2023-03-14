package dev.boiarshinov.breakingbad.clientlib.datamodel;

// Inspired by RFC 7807
public record ResponseDto<T>(
        String code,
        String detail,
        T data
) { }
