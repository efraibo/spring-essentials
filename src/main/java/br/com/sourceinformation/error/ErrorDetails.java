package br.com.sourceinformation.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderMethodName = "newBuilder")
public class ErrorDetails {
    private String title;
    private int status;
    private String detail;
    private long timestamp;
    private String developerMessage;
}
