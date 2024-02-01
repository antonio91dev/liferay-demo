package com.vass.reniec.pe.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.util.PropsUtil;
import com.vass.reniec.pe.constants.SedeAdministrationPortletKeys;
import com.vass.reniec.pe.service.Invoker;
import com.vass.reniec.pe.service.api.Credentials;
import com.vass.reniec.pe.service.RestConsumer;
import com.vass.reniec.pe.service.api.model.request.UbigeoRequest;
import com.vass.reniec.pe.util.CSVImportFileUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, service = Invoker.class)
public class InvokerImpl implements Invoker {
    private static Log log = LogFactoryUtil.getLog(CSVImportFileUtil.class);

    @Reference
    private RestConsumer restConsumer;

    protected ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    private Credentials credentials = new Credentials(PropsUtil.get(SedeAdministrationPortletKeys.USER_LOGIN),
            PropsUtil.get(SedeAdministrationPortletKeys.PWD_LOGIN));

    @Override
    public String addUbigeo(String sku) {
        return null;
    }

    @Override
    public String addOficina(String sku) {
        return null;
    }

    private String postUbigeo(UbigeoRequest ubigeoRequest) {
        String json = getJson(ubigeoRequest);

        String response = restConsumer.post(
                PropsUtil.get(SedeAdministrationPortletKeys.UBIGEO_HOST)+"/o/headless-commerce-admin-order/orders", credentials,
                json);

        log.debug(response);

        return response;
    }


    private String postOficina(UbigeoRequest ubigeoRequest) {
        String json = getJson(ubigeoRequest);

        String response = restConsumer.post(
                PropsUtil.get(SedeAdministrationPortletKeys.UBIGEO_HOST)+"/o/headless-commerce-admin-order/orders", credentials,
                json);

        log.debug(response);

        return response;
    }

    protected String getJson(Object object) {
        String json = "";
        try {
            json = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error(e);
        }

        return json;
    }



}
