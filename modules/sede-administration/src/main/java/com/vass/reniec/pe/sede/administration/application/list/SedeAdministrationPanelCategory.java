package com.vass.reniec.pe.sede.administration.application.list;

import com.liferay.application.list.BasePanelCategory;
import com.liferay.application.list.PanelCategory;
import com.liferay.application.list.constants.PanelCategoryKeys;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;

import com.vass.reniec.pe.sede.administration.constants.SedeAdministrationPanelCategoryKeys;

import java.util.Locale;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;

/**
 * @author jantonio
 */
@Component(
	property = {
		"panel.category.key=" + PanelCategoryKeys.SITE_ADMINISTRATION,
		"panel.category.order:Integer=100"
	},
	service = PanelCategory.class
)
public class SedeAdministrationPanelCategory extends BasePanelCategory {

	@Override
	public String getKey() {
		return SedeAdministrationPanelCategoryKeys.CONTROL_PANEL_CATEGORY;
	}

	@Override
	public String getLabel(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		return LanguageUtil.get(resourceBundle, "category.custom.label");
	}

}