package com.vass.reniec.pe.sede.administration.service;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.vass.reniec.pe.model.Ubigeo;

import javax.portlet.ActionRequest;
import java.util.Optional;

public interface ConsumerService {

	public JSONObject addOficina(
			JSONArray csvDataArray, ActionRequest actionRequest) throws Exception  ;

	public JSONObject addUbigeo(
			JSONArray csvDataArray, ActionRequest actionRequest) throws Exception  ;

	public Optional<Ubigeo> getUbigeoByfilter(String filter);

}