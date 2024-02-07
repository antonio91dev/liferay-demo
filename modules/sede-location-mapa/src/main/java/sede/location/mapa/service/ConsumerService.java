package sede.location.mapa.service;


import com.vass.reniec.pe.model.Oficina;
import com.vass.reniec.pe.model.Ubigeo;

import java.util.List;

public interface ConsumerService {

    public List<Ubigeo> getUbigeo(String filter);

    public List<Oficina> getOficina(String filter);

}
