package sede.location.mapa.configuracion;

import aQute.bnd.annotation.metatype.Meta;


@Meta.OCD(
        id = "sede.location.mapa.configuracion.SedeLocationMapaDisplayConfiguration"
)
public interface SedeLocationMapaDisplayConfiguration {

    @Meta.AD(required = false)
    public String sedePrincipal();

    @Meta.AD(required = false)
    public String tipoForm();

}
