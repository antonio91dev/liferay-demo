package sede.location.mapa.configuracion;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.util.ParamUtil;
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
        property = "javax.portlet.name=sede_location_mapa_SedeLocationMapaPortlet",
        service = ConfigurationAction.class
)public class SedeLocationMapaDisplayConfigurationAction extends DefaultConfigurationAction {

    @Override
    public void include(
            PortletConfig portletConfig, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse)
            throws Exception {

        if (_log.isInfoEnabled()) {
            _log.info("Blade Message Portlet configuration include");
        }

        httpServletRequest.setAttribute(
                SedeLocationMapaDisplayConfiguration.class.getName(),
                _sedeLocationMapaDisplayConfiguration);

        super.include(portletConfig, httpServletRequest, httpServletResponse);
    }

    @Override
    public void processAction(
            PortletConfig portletConfig, ActionRequest actionRequest,
            ActionResponse actionResponse)
            throws Exception {

        if (_log.isInfoEnabled()) {
            _log.info("Blade Message Portlet configuration action");
        }

        String sedePrincipal = ParamUtil.getString(actionRequest, "sedePrincipal");
        String tipoForm = ParamUtil.getString(actionRequest, "tipoForm");

        if (_log.isInfoEnabled()) {
            _log.info(
                    "Message Display Configuration: tipoForm: " + tipoForm);

            _log.info("Message Display Configuration:sedePrincipal: " + sedePrincipal);

        }

        setPreference(actionRequest, "sedePrincipal", sedePrincipal);
        setPreference(actionRequest, "tipoForm", tipoForm);

        super.processAction(portletConfig, actionRequest, actionResponse);
    }

    @Activate
    @Modified
    protected void activate(Map<Object, Object> properties) {
        _sedeLocationMapaDisplayConfiguration = ConfigurableUtil.createConfigurable(
                SedeLocationMapaDisplayConfiguration.class, properties);
    }

    private static final Log _log = LogFactoryUtil.getLog(
            SedeLocationMapaDisplayConfigurationAction.class);

    private volatile SedeLocationMapaDisplayConfiguration _sedeLocationMapaDisplayConfiguration;

}
