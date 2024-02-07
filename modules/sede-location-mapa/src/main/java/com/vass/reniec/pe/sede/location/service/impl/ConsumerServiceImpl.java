package com.vass.reniec.pe.sede.location.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.vass.reniec.pe.api.RestConsumer;
import com.vass.reniec.pe.model.*;

import com.vass.reniec.pe.sede.location.constants.SedeLocationMapaPortletKeys;
import com.vass.reniec.pe.sede.location.service.ConsumerService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.List;
import java.util.Optional;

@Component(immediate = true, service = ConsumerService.class)
public class ConsumerServiceImpl implements ConsumerService {


    @Reference
    private  RestConsumer restConsumer;

    private  Credentials credentials = new Credentials("test@liferay.com","123");


    private  Log _log = LogFactoryUtil.getLog(ConsumerServiceImpl.class);


    protected  ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public List<Ubigeo> getUbigeo(String filter) {

        String response = restConsumer.get(SedeLocationMapaPortletKeys.UBIGEO_HOST_GET, credentials,  null,filter);

        UbigeoSearchResponse ubigeoSearchResponse = new UbigeoSearchResponse();
        try {
            ubigeoSearchResponse = objectMapper.readValue(response, UbigeoSearchResponse.class);
        } catch (JsonProcessingException e) {
            _log.error(e);
        }

        return ubigeoSearchResponse.getItems();
    }

    public Optional<Ubigeo> getUbigeoByfilter(String filter) {

        String response = restConsumer.get(SedeLocationMapaPortletKeys.UBIGEO_HOST_GET, credentials,  null,filter);

        UbigeoSearchResponse ubigeoSearchResponse = new UbigeoSearchResponse();
        try {
            ubigeoSearchResponse = objectMapper.readValue(response, UbigeoSearchResponse.class);
        } catch (JsonProcessingException e) {
            _log.error(e);
        }

        return ubigeoSearchResponse.getItems().stream().findFirst();

    }

    @Override
    public List<Oficina> getOficina(String filter) {

        String response = restConsumer.get(SedeLocationMapaPortletKeys.OFICINA_HOST_GET, credentials,
                null,filter);

        OficinaSearchResponse oficinaSearchResponse = new OficinaSearchResponse();
        try {
            oficinaSearchResponse = objectMapper.readValue(response, OficinaSearchResponse.class);
        } catch (JsonProcessingException e) {
            _log.error(e);
        }

        return oficinaSearchResponse.getItems();
    }

}
