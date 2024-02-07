package com.vass.reniec.pe.sede.administration.application.list;

import com.liferay.application.list.BasePanelApp;
import com.liferay.application.list.PanelApp;
import com.liferay.portal.kernel.model.Portlet;

import com.vass.reniec.pe.sede.administration.constants.SedeAdministrationPanelCategoryKeys;
import com.vass.reniec.pe.sede.administration.constants.SedeAdministrationPortletKeys;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author jantonio
 */
@Component(
	property = {
		"panel.app.order:Integer=100",
		"panel.category.key=" + SedeAdministrationPanelCategoryKeys.CONTROL_PANEL_CATEGORY
	},
	service = PanelApp.class
)
public class SedeAdministrationPanelApp extends BasePanelApp {

	@Override
	public Portlet getPortlet() {
		return _portlet;
	}

	@Override
	public String getPortletId() {
		return SedeAdministrationPortletKeys.SEDEADMINISTRATION;
	}

	@Reference(
		target = "(javax.portlet.name=" + SedeAdministrationPortletKeys.SEDEADMINISTRATION + ")"
	)
	private Portlet _portlet;

}