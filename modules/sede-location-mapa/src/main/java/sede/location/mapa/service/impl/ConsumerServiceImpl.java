package sede.location.mapa.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.vass.reniec.pe.api.RestConsumer;
import com.vass.reniec.pe.model.Credentials;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import sede.location.mapa.model.Ubigeo;
import sede.location.mapa.model.UbigeoSearchResponse;
import sede.location.mapa.service.ConsumerService;

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

    public List<Ubigeo> getUbigeo() {

        String response = restConsumer.get("http://localhost:8080/o/c/ubigeos?pageSize=-1", credentials,
                null);

        UbigeoSearchResponse ubigeoSearchResponse = new UbigeoSearchResponse();
        try {
            ubigeoSearchResponse = objectMapper.readValue(response, UbigeoSearchResponse.class);
        } catch (JsonProcessingException e) {
            _log.error(e);
        }

        return ubigeoSearchResponse.getItems();
    }

}
