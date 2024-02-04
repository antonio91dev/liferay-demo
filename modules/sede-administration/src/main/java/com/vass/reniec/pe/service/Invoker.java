package com.vass.reniec.pe.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONObject;

import javax.portlet.ActionRequest;

public interface Invoker {

	public JSONObject addOficina(
		JSONArray csvDataArray, ActionRequest actionRequest);

	public JSONObject addUbigeo(
			JSONArray csvDataArray, ActionRequest actionRequest) throws Exception  ;

}