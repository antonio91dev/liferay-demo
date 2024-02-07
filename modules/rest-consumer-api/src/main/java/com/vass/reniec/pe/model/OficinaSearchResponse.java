package com.vass.reniec.pe.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "items" })
public class OficinaSearchResponse {

    @JsonProperty("items")
    private List<Oficina> items = null;


    @JsonProperty("items")
    public List<Oficina> getItems() {
        return items;
    }

    @JsonProperty("items")
    public void setItems(List<Oficina> items) {
        this.items = items;
    }
}
