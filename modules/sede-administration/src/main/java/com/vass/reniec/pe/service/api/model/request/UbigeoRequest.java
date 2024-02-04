package com.vass.reniec.pe.service.api.model.request;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"codigo", "departamento", "provincia", "distrito"})
public class UbigeoRequest {

	@JsonProperty("departamento")
	public String getDepartamento() {
		return departamento;
	}

	@JsonProperty("distrito")
	public String getDistrito() {
		return distrito;
	}

	@JsonProperty("provincia")
	public String getProvincia() {
		return provincia;
	}

	@JsonProperty("codigo")
	public String getCodigo() {
		return codigo;
	}

	@JsonProperty("departamento")
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	@JsonProperty("distrito")
	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}

	@JsonProperty("provincia")
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	@JsonProperty("codigo")
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<>();

	@JsonProperty("departamento")
	private String departamento;

	@JsonProperty("distrito")
	private String distrito;

	@JsonProperty("provincia")
	private String provincia;

	@JsonProperty("codigo")
	private String codigo;

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