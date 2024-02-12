package com.vass.reniec.pe.sede.location.service;


import com.google.maps.model.GeocodingResult;
import com.vass.reniec.pe.model.Oficina;
import com.vass.reniec.pe.model.Ubigeo;

import java.util.List;
import java.util.Optional;

public interface ConsumerService {

    public List<Ubigeo> getUbigeo(String filter);

    public List<Oficina> getOficina(String filter);

    public Optional<Ubigeo> getUbigeoByfilter(String filter);

    public GeocodingResult[] getUbicacion();

}
