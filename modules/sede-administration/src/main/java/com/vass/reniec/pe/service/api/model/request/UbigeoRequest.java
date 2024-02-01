package com.vass.reniec.pe.service.api.model.request;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "ubigeo", "departamento", "provincia", "distrito"})
public class UbigeoRequest {

    @JsonProperty("ubigeo")
    private long ubigeo;
    @JsonProperty("departamento")
    private String departamento;

    @JsonProperty("provincia")
    private String provincia;

    @JsonProperty("distrito")
    private String distrito;

}


/*
* {
  "externalReferenceCode": "string",
  "keywords": [
    "string"
  ],
  "taxonomyCategoryIds": [
    0
  ],
  "departamento": "string",
  "distrito": "string",
  "provincia": "string",
  "ubigeo": 0
}
* */