package sede.location.mapa.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.vass.reniec.pe.api.RestConsumer;
import com.vass.reniec.pe.model.*;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import sede.location.mapa.service.ConsumerService;

import java.util.List;

@Component(immediate = true, service = ConsumerService.class)
public class ConsumerServiceImpl implements ConsumerService {


    @Reference
    private  RestConsumer restConsumer;

    private  Credentials credentials = new Credentials("test@liferay.com","123");


    private  Log _log = LogFactoryUtil.getLog(ConsumerServiceImpl.class);


    protected  ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public List<Ubigeo> getUbigeo(String filter) {

        String response = restConsumer.get("http://localhost:8080/o/c/ubigeos", credentials,  null,filter);

        UbigeoSearchResponse ubigeoSearchResponse = new UbigeoSearchResponse();
        try {
            ubigeoSearchResponse = objectMapper.readValue(response, UbigeoSearchResponse.class);
        } catch (JsonProcessingException e) {
            _log.error(e);
        }

        return ubigeoSearchResponse.getItems();
    }

    @Override
    public List<Oficina> getOficina(String filter) {


        String response = restConsumer.get("http://localhost:8080/o/c/oficinas?pageSize=-1", credentials,
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
