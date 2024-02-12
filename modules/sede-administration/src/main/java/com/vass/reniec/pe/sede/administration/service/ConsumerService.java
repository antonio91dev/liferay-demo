package com.vass.reniec.pe.sede.administration.service;

import com.google.maps.model.GeocodingResult;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.vass.reniec.pe.model.Oficina;
import com.vass.reniec.pe.model.ResponseBatch;
import com.vass.reniec.pe.model.Ubicacion;
import com.vass.reniec.pe.model.Ubigeo;

import javax.portlet.ActionRequest;
import java.util.List;
import java.util.Optional;

public interface ConsumerService {

	public JSONObject addOficina(
			JSONArray lstOficina) throws Exception  ;

	public JSONObject addUbigeo(
			JSONArray csvDataArray, ActionRequest actionRequest) throws Exception  ;

	public Optional<Ubigeo> getUbigeoByfilter(String filter);

	Optional<Ubicacion> getLocation(String filter);

	Optional<ResponseBatch> deleteBatch(JSONArray jsonList);

	public List<Ubigeo> getUbigeo(String filter);

	public List<Oficina> getOficina(String filter);

	public GeocodingResult[] getUbicacion(String direccion);

}