package com.vass.reniec.pe.sede.administration.portlet.context;

import com.liferay.asset.kernel.model.AssetTag;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.List;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

public class AssetTagsDisplayContext {

	public AssetTagsDisplayContext(
		HttpServletRequest httpServletRequest, RenderRequest renderRequest,
		RenderResponse renderResponse) {

		_httpServletRequest = httpServletRequest;
		_renderRequest = renderRequest;
		_renderResponse = renderResponse;

		_themeDisplay = (ThemeDisplay)httpServletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);
	}

	private String _displayStyle;
	private final HttpServletRequest _httpServletRequest;
	private String _keywords;
	private List<String> _mergeTagNames;
	private String _orderByCol;
	private String _orderByType;
	private final RenderRequest _renderRequest;
	private final RenderResponse _renderResponse;
	private Boolean _showTagsActions;
	private AssetTag _tag;
	private Long _tagId;
	private SearchContainer<AssetTag> _tagsSearchContainer;
	private final ThemeDisplay _themeDisplay;

}