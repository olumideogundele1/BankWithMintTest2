package com.test.mintbank.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MintResponse {

    private String scheme;
    private String type;
    private String bank;
    private Boolean prepaid;
    private String brand;


}
