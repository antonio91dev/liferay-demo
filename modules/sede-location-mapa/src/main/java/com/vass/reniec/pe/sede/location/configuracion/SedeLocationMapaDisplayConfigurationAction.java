package com.vass.reniec.pe.sede.location.configuracion;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.util.ParamUtil;
import com.vass.reniec.pe.sede.location.constants.SedeLocationMapaPortletKeys;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


@Component(
        property = "javax.portlet.name="+ SedeLocationMapaPortletKeys.SEDELOCATIONMAPA,
        service = ConfigurationAction.class
)public class SedeLocationMapaDisplayConfigurationAction extends DefaultConfigurationAction {

    @Override
    public void include(final PortletConfig portletConfig, final HttpServletRequest httpServletRequest,
                        final HttpServletResponse httpServletResponse) throws Exception {

        final String portletName = ParamUtil.getString(httpServletRequest, "portletResource");
        httpServletRequest.setAttribute("mortgage", Boolean.TRUE);
        httpServletRequest.setAttribute("mortgage", Boolean.TRUE);
        httpServletRequest.setAttribute("mortgage", Boolean.TRUE);
        httpServletRequest.setAttribute("mortgage", Boolean.TRUE);

        super.include(portletConfig, httpServletRequest, httpServletResponse);
    }
}
