package com.projects.carworkshop.mfApi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MfApiResponseDto {

    @JsonProperty("result")
    ResultDto result;
}
