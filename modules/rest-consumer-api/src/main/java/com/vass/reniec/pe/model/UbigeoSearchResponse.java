package com.vass.reniec.pe.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "items" })
public class UbigeoSearchResponse {

    @JsonProperty("items")
    private List<Ubigeo> items = null;


    @JsonProperty("items")
    public List<Ubigeo> getItems() {
        return items;
    }

    @JsonProperty("items")
    public void setItems(List<Ubigeo> items) {
        this.items = items;
    }
}
