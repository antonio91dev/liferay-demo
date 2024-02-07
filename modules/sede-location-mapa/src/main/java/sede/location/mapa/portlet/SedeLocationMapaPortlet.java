package sede.location.mapa.portlet;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Modified;
import sede.location.mapa.configuracion.SedeLocationMapaDisplayConfiguration;
import sede.location.mapa.constants.SedeLocationMapaPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import java.io.IOException;
import java.util.Map;

/**
 * @author jantonio
 */
@Component(
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=SedeLocationMapa",
		"javax.portlet.init-param.config-template=/configuration.jsp",
			"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + SedeLocationMapaPortletKeys.SEDELOCATIONMAPA,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class SedeLocationMapaPortlet extends MVCPortlet {

	@Override
	public void doView(
			RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {

		if (_log.isInfoEnabled()) {
			_log.info("Portlet render");
		}

		renderRequest.setAttribute(
				SedeLocationMapaDisplayConfiguration.class.getName(),
				_sedeLocationMapaDisplayConfiguration);

		super.doView(renderRequest, renderResponse);
	}

	@Activate
	@Modified
	protected void activate(Map<Object, Object> properties) {
		_sedeLocationMapaDisplayConfiguration = ConfigurableUtil.createConfigurable(
				SedeLocationMapaDisplayConfiguration.class, properties);
	}

	private static final Log _log = LogFactoryUtil.getLog(
			SedeLocationMapaPortlet.class);

	private volatile SedeLocationMapaDisplayConfiguration _sedeLocationMapaDisplayConfiguration;
}