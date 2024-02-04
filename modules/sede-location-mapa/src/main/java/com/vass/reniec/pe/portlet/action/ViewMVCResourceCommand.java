package com.vass.reniec.pe.portlet.action;


import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.JSONPortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.vass.reniec.pe.constants.GooglesedeMapaPortletKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.io.IOException;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + GooglesedeMapaPortletKeys.GOOGLESEDEMAPA,
                "mvc.command.name=getDepartamento"
        },
        service = MVCResourceCommand.class
)
public class ViewMVCResourceCommand implements MVCResourceCommand{
    @Override
    public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws PortletException {
        String command = ParamUtil.getString(resourceRequest, "cmd");
        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

        if (command.equals("")) {
            //jsonObj =" ";
        }else if (command.equals("")) {
            //jsonObj =" ";
        }else if (command.equals("")) {
            //jsonObj =" ";
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
}
