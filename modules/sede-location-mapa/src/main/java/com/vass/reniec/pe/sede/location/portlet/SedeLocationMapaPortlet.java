package com.vass.reniec.pe.sede.location.portlet;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.vass.reniec.pe.sede.location.configuracion.SedeLocationMapaDisplayConfiguration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Modified;
import com.vass.reniec.pe.sede.location.constants.SedeLocationMapaPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.*;
import javax.servlet.http.HttpServletRequest;

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
			_log.info("Portlet render Location Maps");
		}
		/*
		ThemeDisplay themeDisplay =
				(ThemeDisplay)renderRequest.getAttribute(
						WebKeys.THEME_DISPLAY);

		PortletPreferences companyPortletPreferences =
				PrefsPropsUtil.getPreferences(themeDisplay.getCompanyId());

		String googleMapsApiKey = GetterUtil.getString(companyPortletPreferences.getValue("googleMapsAPIKey", null));

		renderRequest.setAttribute("googleMapsApiKey", googleMapsApiKey);*/

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