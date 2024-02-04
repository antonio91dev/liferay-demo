package sede.location.mapa.portlet;


import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.JSONPortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ParamUtil;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import sede.location.mapa.model.Ubigeo;
import sede.location.mapa.service.ConsumerService;

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
                "javax.portlet.name=sede_location_mapa_SedeLocationMapaPortlet",
                "mvc.command.name=getDepartamento"
        },
        service = MVCResourceCommand.class
)
public class ViewMVCResourceCommand implements MVCResourceCommand{

    private Log _log = LogFactoryUtil.getLog(ViewMVCResourceCommand.class);




    @Override
    public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws PortletException {

        if (_log.isInfoEnabled()) {
            _log.info("get captcha resource ");
        }

        String command = ParamUtil.getString(resourceRequest, "cmd");

        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

        if (command.equals("departamento")) {
            List<Ubigeo> optionalOrderItem = consumerService.getUbigeo();

            optionalOrderItem = optionalOrderItem.stream().filter(c -> c.getCodigo().contains("0101")).collect(Collectors.toList());

        }else if (command.equals("")) {
            //jsonObj =" ";
        }else if (command.equals("")) {
            //jsonObj =" ";
        }

        _log.info("HOLA 23455");
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
