package com.vass.reniec.pe.model;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"centroDeAtencion", "horarioDeAtencion", "latitud", "longitud", "nombreDeVia", "r_ubigeo_c_ubigeoId", "tipoDeVia",
        "tramiteCERRciviles", "tramiteCERRuipn", "tramiteDNIEntrega"
        , "tramiteDNIMayor", "tramiteDNIMenor", "tramiteElectronico"
        , "tramiteEREP", "tramiteFotografia", "tramiteINSRciviles","id"})
public class Oficina {


    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    @JsonProperty("id")
    private Long OficinaId;

    @JsonProperty("departamento")
    private String departamento;

    @JsonProperty("provincia")
    private String provincia;

    @JsonProperty("distrito")
    private String distrito;

    @JsonProperty("tipoDeLocal")
    private String tipoDeLocal;


    @JsonProperty("centroDeAtencion")
    private String centroDeAtencion;

    @JsonProperty("horarioDeAtencion")
    private String horarioDeAtencion;

    @JsonProperty("centralTelefonico")
    private String centralTelefonico;

    @JsonProperty("representante")
    private String representante;

    @JsonProperty("correo")
    private String correo;


    @JsonProperty("tipoDeVia")
    private String tipoDeVia;

    @JsonProperty("nombreDeVia")
    private String nombreDeVia;


    @JsonProperty("latitud")
    private String latitud;
    @JsonProperty("longitud")
    private String longitud;


    @JsonProperty("r_ubigeo_c_ubigeoId")
    private int ubigeoId;




    @JsonProperty("tramiteCERRciviles")
    private Boolean tramiteCERRciviles;
    @JsonProperty("tramiteCERRuipn")
    private Boolean tramiteCERRuipn;
    @JsonProperty("tramiteDNIEntrega")
    private Boolean tramiteDNIEntrega;
    @JsonProperty("tramiteDNIMayor")
    private Boolean tramiteDNIMayor;
    @JsonProperty("tramiteDNIMenor")
    private Boolean tramiteDNIMenor;
    @JsonProperty("tramiteElectronico")
    private Boolean tramiteElectronico;
    @JsonProperty("tramiteEREP")
    private Boolean tramiteEREP;
    @JsonProperty("tramiteFotografia")
    private Boolean tramiteFotografia;
    @JsonProperty("tramiteINSRciviles")
    private Boolean tramiteINSRciviles;

    public String getTipoDeLocal() {
        return tipoDeLocal;
    }

    public void setTipoDeLocal(String tipoDeLocal) {
        this.tipoDeLocal = tipoDeLocal;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }

    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }

    @JsonProperty("centroDeAtencion")
    public String getCentroDeAtencion() {
        return centroDeAtencion;
    }

    @JsonProperty("centroDeAtencion")
    public void setCentroDeAtencion(String centroDeAtencion) {
        this.centroDeAtencion = centroDeAtencion;
    }




    @JsonProperty("horarioDeAtencion")
    public String getHorarioDeAtencion() {
        return horarioDeAtencion;
    }

    @JsonProperty("horarioDeAtencion")
    public void setHorarioDeAtencion(String horarioDeAtencion) {
        this.horarioDeAtencion = horarioDeAtencion;
    }

    @JsonProperty("tipoDeVia")
    public String getTipoDeVia() {
        return tipoDeVia;
    }

    @JsonProperty("tipoDeVia")
    public void setTipoDeVia(String tipoDeVia) {
        this.tipoDeVia = tipoDeVia;
    }

    @JsonProperty("nombreDeVia")
    public String getNombreDeVia() {
        return nombreDeVia;
    }

    @JsonProperty("nombreDeVia")
    public void setNombreDeVia(String nombreDeVia) {
        this.nombreDeVia = nombreDeVia;
    }

    @JsonProperty("latitud")
    public String getLatitud() {
        return latitud;
    }

    @JsonProperty("latitud")
    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    @JsonProperty("longitud")
    public String getLongitud() {
        return longitud;
    }

    @JsonProperty("longitud")
    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    @JsonProperty("r_ubigeo_c_ubigeoId")
    public int getUbigeoId() {
        return ubigeoId;
    }

    @JsonProperty("r_ubigeo_c_ubigeoId")
    public void setUbigeoId(int ubigeoId) {
        this.ubigeoId = ubigeoId;
    }

    @JsonProperty("tramiteCERRciviles")
    public Boolean getTramiteCERRciviles() {
        return tramiteCERRciviles;
    }

    @JsonProperty("tramiteCERRciviles")
    public void setTramiteCERRciviles(Boolean tramiteCERRciviles) {
        this.tramiteCERRciviles = tramiteCERRciviles;
    }

    @JsonProperty("tramiteCERRuipn")    public Boolean getTramiteCERRuipn() {
        return tramiteCERRuipn;
    }

    @JsonProperty("tramiteCERRuipn")    public void setTramiteCERRuipn(Boolean tramiteCERRuipn) {
        this.tramiteCERRuipn = tramiteCERRuipn;
    }

    @JsonProperty("tramiteDNIEntrega")
    public Boolean getTramiteDNIEntrega() {
        return tramiteDNIEntrega;
    }

    @JsonProperty("tramiteDNIEntrega")
    public void setTramiteDNIEntrega(Boolean tramiteDNIEntrega) {
        this.tramiteDNIEntrega = tramiteDNIEntrega;
    }

    @JsonProperty("tramiteDNIMayor")
    public Boolean getTramiteDNIMayor() {
        return tramiteDNIMayor;
    }

    @JsonProperty("tramiteDNIMayor")
    public void setTramiteDNIMayor(Boolean tramiteDNIMayor) {
        this.tramiteDNIMayor = tramiteDNIMayor;
    }

    @JsonProperty("tramiteDNIMenor")
    public Boolean getTramiteDNIMenor() {
        return tramiteDNIMenor;
    }

    @JsonProperty("tramiteDNIMenor")
    public void setTramiteDNIMenor(Boolean tramiteDNIMenor) {
        this.tramiteDNIMenor = tramiteDNIMenor;
    }

    @JsonProperty("tramiteElectronico")    public Boolean getTramiteElectronico() {
        return tramiteElectronico;
    }

    @JsonProperty("tramiteElectronico")    public void setTramiteElectronico(Boolean tramiteElectronico) {
        this.tramiteElectronico = tramiteElectronico;
    }

    @JsonProperty("tramiteEREP")
    public Boolean getTramiteEREP() {
        return tramiteEREP;
    }

    @JsonProperty("tramiteEREP")
    public void setTramiteEREP(Boolean tramiteEREP) {
        this.tramiteEREP = tramiteEREP;
    }

    @JsonProperty("tramiteFotografia")
    public Boolean getTramiteFotografia() {
        return tramiteFotografia;
    }

    @JsonProperty("tramiteFotografia")
    public void setTramiteFotografia(Boolean tramiteFotografia) {
        this.tramiteFotografia = tramiteFotografia;
    }

    @JsonProperty("tramiteINSRciviles")
    public Boolean getTramiteINSRciviles() {
        return tramiteINSRciviles;
    }

    @JsonProperty("tramiteINSRciviles")
    public void setTramiteINSRciviles(Boolean tramiteINSRciviles) {
        this.tramiteINSRciviles = tramiteINSRciviles;
    }


    @JsonProperty("centralTelefonico")
    public String getCentralTelefonico() {
        return centralTelefonico;
    }


    @JsonProperty("centralTelefonico")
    public void setCentralTelefonico(String centralTelefonico) {
        this.centralTelefonico = centralTelefonico;
    }

    @JsonProperty("representante")
    public String getRepresentante() {
        return representante;
    }

    @JsonProperty("representante")
    public void setRepresentante(String representante) {
        this.representante = representante;
    }

    @JsonProperty("correo")
    public String getCorreo() {
        return correo;
    }

    @JsonProperty("correo")
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @JsonProperty("id")
    public Long getOficinaId() {
        return OficinaId;
    }
    @JsonProperty("id")
    public void setOficinaId(Long oficinaId) {
        OficinaId = oficinaId;
    }
}

