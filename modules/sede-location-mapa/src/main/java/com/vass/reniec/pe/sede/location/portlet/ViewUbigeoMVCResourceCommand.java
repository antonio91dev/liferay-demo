package com.vass.reniec.pe.sede.location.portlet;


import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.JSONPortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ParamUtil;

import com.vass.reniec.pe.api.enums.Filters;
import com.vass.reniec.pe.api.enums.Operadores;
import com.vass.reniec.pe.model.Ubigeo;
import com.vass.reniec.pe.sede.location.constants.SedeLocationMapaPortletKeys;
import com.vass.reniec.pe.sede.location.service.ConsumerService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name="+ SedeLocationMapaPortletKeys.SEDELOCATIONMAPA,
                "mvc.command.name=getDepartamento"
        },
        service = MVCResourceCommand.class
)
public class ViewUbigeoMVCResourceCommand implements MVCResourceCommand{

    private Log _log = LogFactoryUtil.getLog(ViewUbigeoMVCResourceCommand.class);

    @Override
    public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws PortletException {

        if (_log.isInfoEnabled()) {
            _log.info("serveResource ");
        }

        String command = ParamUtil.getString(resourceRequest, "cmd");

        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

        _log.info("serveResource === command " + command);

        if (command.equals("departamento")) {

            String filter = Operadores.addPageSizeAndSort(-1, Filters.NAME_DEPARTAMENTO.getFilter(), Filters.ASC.getFilter());

            List<Ubigeo> optionalOrderItem = consumerService.getUbigeo(filter);

            optionalOrderItem = optionalOrderItem.stream()
                    .filter(c -> c.getCodigo().endsWith("0101")).collect(Collectors.toList());

            jsonObj.put("searchResults",  optionalOrderItem);

        }else if (command.equals("provincia")) {

            String filter = Operadores.addPageSizeAndSort(-1, Filters.NAME_PROVINCIA.getFilter(), Filters.ASC.getFilter());

            String codDepartamento = ParamUtil.getString(resourceRequest, "codDepartamento");

            _log.info("codDepartamento " + codDepartamento);
            _log.info("codDepartamento " + codDepartamento.substring(0,2));

            List<Ubigeo> optionalOrderItem = consumerService.getUbigeo(filter);

            optionalOrderItem = optionalOrderItem.stream()
                    .filter(c -> c.getCodigo().startsWith(codDepartamento.substring(0,2)))
                    .filter(c -> c.getCodigo().endsWith("01")).collect(Collectors.toList());

            _log.info(optionalOrderItem);

            jsonObj.put("searchResults",  optionalOrderItem);
            //jsonObj =" ";
        }else if (command.equals("distrito")) {

            String filter = Operadores.addPageSizeAndSort(-1, Filters.NAME_DISTRITO.getFilter(), Filters.ASC.getFilter());

            String codProvincia = ParamUtil.getString(resourceRequest, "codProvincia");

            _log.info("codProvincia " + codProvincia);
            _log.info("filter " + filter);

            List<Ubigeo> optionalOrderItem = consumerService.getUbigeo(filter);

            optionalOrderItem = optionalOrderItem.stream()
                    .filter(c -> c.getCodigo().startsWith(codProvincia.substring(0,4))).collect(Collectors.toList());

            jsonObj.put("searchResults",  optionalOrderItem);

        }else if (command.equals("listaautocompletado")) {
            String filter = Operadores.addPageSizeAndSort(-1, Filters.NAME_DISTRITO.getFilter(), Filters.ASC.getFilter());

            List<Ubigeo> optionalOrderItem = consumerService.getUbigeo(filter);
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
