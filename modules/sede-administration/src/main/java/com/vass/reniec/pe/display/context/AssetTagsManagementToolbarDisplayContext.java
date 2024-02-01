package com.vass.reniec.pe.display.context;

import com.liferay.frontend.taglib.clay.servlet.taglib.display.context.SearchContainerManagementToolbarDisplayContext;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;

import javax.servlet.http.HttpServletRequest;

public class AssetTagsManagementToolbarDisplayContext extends SearchContainerManagementToolbarDisplayContext {

    public AssetTagsManagementToolbarDisplayContext(HttpServletRequest httpServletRequest, LiferayPortletRequest liferayPortletRequest, LiferayPortletResponse liferayPortletResponse, SearchContainer<?> searchContainer, AssetTagsDisplayContext assetTagsDisplayContext) {
        super(httpServletRequest, liferayPortletRequest, liferayPortletResponse, searchContainer);
        _assetTagsDisplayContext = assetTagsDisplayContext;
    }

    private final AssetTagsDisplayContext _assetTagsDisplayContext;
}


    //private final AssetTagsDisplayContext _assetTagsDisplayContext;
