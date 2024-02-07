package com.vass.reniec.pe.service;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;

import javax.portlet.ActionRequest;

public interface ConsumerService {

	public JSONObject addOficina(
			JSONArray csvDataArray, ActionRequest actionRequest) throws Exception  ;

	public JSONObject addUbigeo(
			JSONArray csvDataArray, ActionRequest actionRequest) throws Exception  ;

}