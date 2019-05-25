package co.uk.lambda.handler;

import lombok.Data;

@Data
public class ErrorResponse {
    private String code;
    private String message;
}
