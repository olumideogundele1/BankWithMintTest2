package com.test.mintbank.dtos;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardDetails {

    private String scheme;
    private String type;
    private Bank bank;
    private Boolean prepaid;
    private String brand;


}
