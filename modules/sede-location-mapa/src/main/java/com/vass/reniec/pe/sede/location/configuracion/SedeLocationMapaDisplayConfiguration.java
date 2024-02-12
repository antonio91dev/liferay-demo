package com.vass.reniec.pe.sede.location.configuracion;

import aQute.bnd.annotation.metatype.Meta;


@Meta.OCD(
        id = "sede.location.mapa.configuracion.SedeLocationMapaDisplayConfiguration"
)
public interface SedeLocationMapaDisplayConfiguration {

    @Meta.AD(required = false)
    public String sedePrincipal();

    @Meta.AD(required = false)
    public String tipoForm();

    @Meta.AD(required = false)
    public String ubicacionPrincipal();

    @Meta.AD(required = false)
    public String googleAPIKey();


    @Meta.AD(required = false)
    public String latitud();

    @Meta.AD(required = false)
    public String longitud();








}
