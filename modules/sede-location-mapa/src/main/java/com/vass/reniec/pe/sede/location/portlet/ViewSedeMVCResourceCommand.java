package com.vass.reniec.pe.sede.location.portlet;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.JSONPortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.vass.reniec.pe.api.enums.Filters;
import com.vass.reniec.pe.api.enums.Operadores;
import com.vass.reniec.pe.model.Oficina;
import com.vass.reniec.pe.model.Ubigeo;
import com.vass.reniec.pe.model.UbigeoSearchResponse;
import com.vass.reniec.pe.sede.location.constants.SedeLocationMapaPortletKeys;
import com.vass.reniec.pe.sede.location.service.ConsumerService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name="+ SedeLocationMapaPortletKeys.SEDELOCATIONMAPA,
                "mvc.command.name=getSedes"
        },
        service = MVCResourceCommand.class
)
public class ViewSedeMVCResourceCommand implements MVCResourceCommand{

    private Log _log = LogFactoryUtil.getLog(ViewSedeMVCResourceCommand.class);

    @Override
    public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws PortletException {

        if (_log.isInfoEnabled()) {
            _log.info("serveResource ");
        }

        String command = ParamUtil.getString(resourceRequest, "cmd");
        String codigoUbigeo = ParamUtil.getString(resourceRequest, "codigoUbigeo");
        String nombredistrito = ParamUtil.getString(resourceRequest, "nombredistrito");
        String direccion = ParamUtil.getString(resourceRequest, "direccion");

        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

        _log.info("serveResource === command " + command);
        if (command.equals("sede")) {

            _log.info("filtrar por codigo de ubigeo === command " + codigoUbigeo);
            //String filter = Operadores.addPageSizeAndSort(-1, Filters.CENTRO_ATENCION.getFilter(), Filters.ASC.getFilter());

            String filter = Operadores.addFilter(Filters.CODIGO_UBIGEO.getFilter(), Operadores.EQUAL, codigoUbigeo);
            Optional<Ubigeo> optionalUbigeoItem = consumerService.getUbigeoByfilter(filter);

            filter = Operadores.addFilter(Filters.ID_UBIGEO_OFICINA.getFilter(), Operadores.EQUAL, optionalUbigeoItem.get().getId());

            List<Oficina> optionalOrderItem = consumerService.getOficina(filter);
            _log.info(optionalOrderItem.size());

            //optionalOrderItem = optionalOrderItem.stream()
                   // .filter(c -> c.getUbigeoId() == GetterUtil.getLong(codigoUbigeo) ).collect(Collectors.toList());//
            //_log.info(optionalOrderItem.size());

            jsonObj.put("searchResults",  optionalOrderItem);

        }else if (command.equals("distrito")) {

            String filter = Operadores.addPageSizeAndSort(-1, Filters.NAME_DISTRITO.getFilter(), Filters.ASC.getFilter());

            List<Ubigeo> optionalOrderItem = consumerService.getUbigeo(filter);
            optionalOrderItem = optionalOrderItem.stream().filter(c -> c.getDistrito().equals(nombredistrito)).collect(Collectors.toList());


            jsonObj.put("searchResults",  optionalOrderItem);

        }else if (command.equals("direccion")) {

            _log.info("Direccion a encontrar es " +  direccion);
            String filter = Operadores.addFilter(Filters.CENTRO_ATENCION.getFilter(), Operadores.EQUAL, direccion);

            List<Oficina> optionalOrderItem = consumerService.getOficina(filter);
            jsonObj.put("searchResults",  optionalOrderItem);

        }
        try {
            JSONPortletResponseUtil.writeJSON(
                    resourceRequest, resourceResponse, jsonObj);
            return false;
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Reference
    private ConsumerService consumerService;

}
