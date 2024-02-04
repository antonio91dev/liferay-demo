package com.vass.reniec.pe.service.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.util.PropsUtil;

import com.vass.reniec.pe.constants.SedeAdministrationPortletKeys;
import com.vass.reniec.pe.service.Invoker;
import com.vass.reniec.pe.service.RestConsumer;
import com.vass.reniec.pe.service.api.Credentials;
import com.vass.reniec.pe.service.api.model.request.UbigeoRequest;
import com.vass.reniec.pe.util.CSVImportFileUtil;

import javax.portlet.ActionRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, service = Invoker.class)
public class InvokerImpl implements Invoker {

	@Override
	public JSONObject addOficina(
		JSONArray csvDataArray, ActionRequest actionRequest) {

		return JSONFactoryUtil.createJSONObject();
	}

	@Override
	public JSONObject addUbigeo(
			JSONArray csvDataArray, ActionRequest actionRequest) throws Exception  {

		String retorno = "";
		JSONObject resultsObject = JSONFactoryUtil.createJSONObject();

		if (Validator.isNotNull(csvDataArray)) {
			log.info("Data Array Length ===> " + csvDataArray.length());

		  	JSONArray csvData = JSONFactoryUtil.createJSONArray();

			for (int i = 1; i < csvDataArray.length(); i++) {
				JSONObject jsonObject = csvDataArray.getJSONObject(i);

				log.info("Json Object ===> " + jsonObject);

				UbigeoRequest csvToBean = new UbigeoRequest();

				csvToBean.setCodigo(
					GetterUtil.getString(jsonObject.get("codigo")));
				csvToBean.setDepartamento(
					GetterUtil.getString(jsonObject.get("departamento")));
				csvToBean.setProvincia(
					GetterUtil.getString(jsonObject.get("provincia")));
				csvToBean.setDistrito(
					GetterUtil.getString(jsonObject.get("distrito")));
				
				csvData.put(jsonObject);
			}
			retorno = postUbigeo(csvData);
		}


		resultsObject = JSONFactoryUtil.createJSONObject(retorno);

		return resultsObject;
	}

	protected String getJson(Object object) {
		String json = "";

		try {
			json = objectMapper.writeValueAsString(object);
		}
		catch (JsonProcessingException e) {
			log.error(e);
		}

		return json;
	}

	protected ObjectMapper objectMapper = new ObjectMapper(
	).configure(
		DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false
	);

	private String postOficina(JSONArray ubigeoRequest) {
		String json = getJson(ubigeoRequest);

		String response = restConsumer.post(
			PropsUtil.get(SedeAdministrationPortletKeys.UBIGEO_HOST) +
				"/o/headless-commerce-admin-order/orders",
			credentials, json);

		log.debug(response);

		return response;
	}

	private String postUbigeo(JSONArray ubigeoRequest) {

		String json = ubigeoRequest.toString();

        String response = restConsumer.post(
               SedeAdministrationPortletKeys.UBIGEO_HOST, credentials,
                json);

		log.debug(response);

		return response;
	}

	private static Log log = LogFactoryUtil.getLog(CSVImportFileUtil.class);

	private Credentials credentials = new Credentials(
		SedeAdministrationPortletKeys.USER_LOGIN,
		SedeAdministrationPortletKeys.PWD_LOGIN);

	@Reference
	private RestConsumer restConsumer;

}