package com.vass.reniec.pe.portlet;

import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import com.vass.reniec.pe.constants.GooglesedeMapaPortletKeys;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

/**
 * @author jantonio
 */
@Component(
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=GooglesedeMapa",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + GooglesedeMapaPortletKeys.GOOGLESEDEMAPA,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class GooglesedeMapaPortlet extends MVCPortlet {

	public JSONObject getListOficinas(String cep) throws ActionException {
		String data = "";

		//JSONObject(data);

		return JSONFactoryUtil.createJSONObject();
	}

	//_log.info("");
	//_log.debug("");

	private static final Log _log = LogFactoryUtil.getLog(
		GooglesedeMapaPortlet.class);

}