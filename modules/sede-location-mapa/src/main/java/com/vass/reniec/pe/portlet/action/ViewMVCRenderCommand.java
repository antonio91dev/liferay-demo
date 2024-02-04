package com.vass.reniec.pe.portlet.action;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;

import com.vass.reniec.pe.constants.GooglesedeMapaPortletKeys;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + GooglesedeMapaPortletKeys.GOOGLESEDEMAPA,
		"mvc.command.name=/"
	},
	service = MVCRenderCommand.class
)
public class ViewMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		if (_log.isDebugEnabled()) {
			_log.debug("ViewMVCRenderCommand.render()");
		}

		renderRequest.setAttribute("mainRequire", "hola");

		try {
			String mapa = "";
			//renderRequest.setAttribute("configuration",  getConfiguration(renderRequest, renderResponse));
		}
		catch (Exception e) {
			_log.error("UI configuration object could not be set.");

			throw new RuntimeException(e);
		}

		return "/view.jsp";
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ViewMVCRenderCommand.class);

}