package com.vass.reniec.pe.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GeometryResponse {

    @JsonProperty("location")
    private LocationResponse locationResponse;

    public LocationResponse getLocationResponse() {
        return locationResponse;
    }

    public void setLocationResponse(LocationResponse locationResponse) {
        this.locationResponse = locationResponse;
    }
}
