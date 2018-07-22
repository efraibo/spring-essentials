package br.com.sourceinformation.error;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ValidationErrorDetails extends ErrorDetails {
    private String field;
    private String fieldMessage;
}
