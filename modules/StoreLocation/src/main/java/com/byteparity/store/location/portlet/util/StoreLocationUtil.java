package com.byteparity.store.location.portlet.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.*;
import com.liferay.portal.kernel.theme.ThemeDisplay;

import javax.portlet.PortletRequest;
import java.util.ArrayList;
import java.util.List;

public class StoreLocationUtil {

	public static List<SearchResult> getStores(PortletRequest portletRequest, SearchCriteria searchCriteria, double currentLocationLat, double currentLocationLng) {

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute("LIFERAY_SHARED_THEME_DISPLAY");


		List<SearchResult> recordList = new ArrayList();

		//   Hits results = indexer.search(searchContext);
		SearchResult result = null;


		for (int i = 0; i < 5 ; i++) {
			result = new SearchResult();
			result.setStoreId(i);
			result.setStoreName("");
			result.setAddress1("");
			result.setCity("");
			result.setState("");
			result.setCountry("");
			result.setZip("");
			result.setPhone("");
			result.setLatitude("-12.04318");
			result.setLongitude("-77.02824");
			recordList.add(result);
		}


		return recordList;
	}
	


	
	private static Log _log = LogFactoryUtil.getLog(StoreLocationUtil.class);
}
