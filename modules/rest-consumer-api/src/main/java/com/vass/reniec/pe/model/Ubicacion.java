package com.vass.reniec.pe.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Ubicacion {

    @JsonProperty("place_id")
    private String placeId;

    @JsonProperty("geometry")
    private GeometryResponse geometryResponse;


    @JsonProperty("geometry")
    public GeometryResponse getGeometryResponse() {
        return geometryResponse;
    }

    @JsonProperty("geometry")
    public void setGeometryResponse(GeometryResponse geometryResponse) {
        this.geometryResponse = geometryResponse;
    }


    @JsonProperty("place_id")
    public String getPlaceId() {
        return placeId;
    }

    @JsonProperty("place_id")
    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }
}
