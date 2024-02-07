package com.vass.reniec.pe.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Ubigeo {

    @JsonProperty("id")
    private String id;

    @JsonProperty("distrito")
    private String distrito;
    @JsonProperty("codigo")
    private String codigo;
    @JsonProperty("departamento")
    private String departamento;
    @JsonProperty("provincia")
    private String provincia;

    @JsonProperty("distrito")
    public String getDistrito() {
        return distrito;
    }

    @JsonProperty("distrito")
    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    @JsonProperty("codigo")
    public String getCodigo() {
        return codigo;
    }

    @JsonProperty("codigo")
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @JsonProperty("departamento")
    public String getDepartamento() {
        return departamento;
    }

    @JsonProperty("departamento")
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }


    @JsonProperty("provincia")
    public String getProvincia() {
        return provincia;
    }

    @JsonProperty("provincia")
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }
}
