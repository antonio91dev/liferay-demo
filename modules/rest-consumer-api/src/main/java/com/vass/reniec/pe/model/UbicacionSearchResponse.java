package com.vass.reniec.pe.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "results" })
public class UbicacionSearchResponse {

    @JsonProperty("results")
    private List<Ubicacion> results = null;



    @JsonProperty("results")
    public List<Ubicacion> getResults() {
        return results;
    }

    @JsonProperty("results")
    public void setResults(List<Ubicacion> items) {
        this.results = items;
    }
}
