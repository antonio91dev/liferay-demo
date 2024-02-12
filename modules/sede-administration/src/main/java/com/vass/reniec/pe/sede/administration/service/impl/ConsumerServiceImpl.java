package com.vass.reniec.pe.sede.administration.service.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import com.vass.reniec.pe.api.RestConsumer;
import com.vass.reniec.pe.api.enums.Filters;
import com.vass.reniec.pe.api.enums.Operadores;
import com.vass.reniec.pe.model.*;
import com.vass.reniec.pe.sede.administration.constants.SedeAdministrationPortletKeys;
import com.vass.reniec.pe.sede.administration.service.ConsumerService;
import com.vass.reniec.pe.sede.administration.service.api.model.request.UbigeoRequest;

import javax.portlet.ActionRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@Component(immediate = true, service = ConsumerService.class)
public class ConsumerServiceImpl implements ConsumerService {

	@Override
	public JSONObject addOficina(
			JSONArray csvDataArray) throws Exception  {

		String retorno = "";
		JSONObject resultsObject = JSONFactoryUtil.createJSONObject();
		String filterS = Operadores.addPageSize(-1);

		List<Oficina> lstOficina = getOficina(filterS);

		JSONArray jsonList = JSONFactoryUtil.createJSONArray ();
		for (Oficina oficina:lstOficina) {
			JSONObject result = JSONFactoryUtil.createJSONObject();
			result.put("id",oficina.getOficinaId());
			jsonList.put(result);
		}

		deleteBatch(jsonList);

		if (Validator.isNotNull(csvDataArray)) {
			JSONArray csvData = JSONFactoryUtil.createJSONArray();

			for (int i = 1; i < csvDataArray.length(); i++) {
				Oficina oficina = new Oficina();

				oficina.setUbigeoId(GetterUtil.getInteger(csvDataArray.getJSONObject(i).get("r_ubigeo_c_ubigeoId")));
				String codigoUbigeo = String.valueOf(oficina.getUbigeoId());
				String filter = Operadores.addFilter(Filters.CODIGO_UBIGEO.getFilter(), Operadores.EQUAL, codigoUbigeo);
				Optional<Ubigeo> optionalUbigeoItem = getUbigeoByfilter(filter);
				oficina.setUbigeoId(GetterUtil.getInteger(optionalUbigeoItem.get().getId()));

				oficina.setCentralTelefonico(GetterUtil.getString(csvDataArray.getJSONObject(i).get("centralTelefonico")));
				oficina.setRepresentante(GetterUtil.getString(csvDataArray.getJSONObject(i).get("representante")));
				oficina.setCorreo(GetterUtil.getString(csvDataArray.getJSONObject(i).get("correo")));
				oficina.setHorarioDeAtencion(GetterUtil.getString(csvDataArray.getJSONObject(i).get("horarioDeAtencion")));
				oficina.setCentroDeAtencion(GetterUtil.getString(csvDataArray.getJSONObject(i).get("centroDeAtencion")));
				oficina.setTipoDeVia(GetterUtil.getString(csvDataArray.getJSONObject(i).get("tipoDeVia")));
				oficina.setNombreDeVia(GetterUtil.getString(csvDataArray.getJSONObject(i).get("nombreDeVia")));
				oficina.setTramiteFotografia(GetterUtil.getBoolean(csvDataArray.getJSONObject(i).get("tramiteFotografia")));
				oficina.setTramiteElectronico(GetterUtil.getBoolean(csvDataArray.getJSONObject(i).get("tramiteElectronico")));
				oficina.setTramiteDNIMayor(GetterUtil.getBoolean(csvDataArray.getJSONObject(i).get("tramiteDNIMayor")));
				oficina.setTramiteDNIMenor(GetterUtil.getBoolean(csvDataArray.getJSONObject(i).get("tramiteDNIMenor")));
				oficina.setTramiteDNIEntrega(GetterUtil.getBoolean(csvDataArray.getJSONObject(i).get("tramiteDNIEntrega")));
				oficina.setTramiteINSRciviles(GetterUtil.getBoolean(csvDataArray.getJSONObject(i).get("tramiteINSRciviles")));
				oficina.setTramiteCERRciviles(GetterUtil.getBoolean(csvDataArray.getJSONObject(i).get("tramiteCERRciviles")));
				oficina.setTramiteCERRuipn(GetterUtil.getBoolean(csvDataArray.getJSONObject(i).get("tramiteCERRuipn")));
				oficina.setTramiteEREP(GetterUtil.getBoolean(csvDataArray.getJSONObject(i).get("tramiteEREP")));

				GeocodingResult[] results = getUbicacion(oficina.getNombreDeVia() + "RENIEC");
				if (results.length == 1) {
					oficina.setLatitud(String.valueOf(results[0].geometry.location.lat));
					oficina.setLongitud(String.valueOf(results[0].geometry.location.lng));
					csvData.put(JSONFactoryUtil.createJSONObject(getJson(oficina)));
				}else{
					log.warn("La Oficina no ha sido encontrada por la api de google " + oficina.getCentroDeAtencion());

				}

			}
			retorno = postOficina(csvData);
		}
		resultsObject = JSONFactoryUtil.createJSONObject(retorno);
		return resultsObject;
	}

	@Override
	public JSONObject addUbigeo(
			JSONArray csvDataArray, ActionRequest actionRequest) throws Exception  {

		String retorno = "";
		JSONObject resultsObject = JSONFactoryUtil.createJSONObject();

		if (Validator.isNotNull(csvDataArray)) {
			log.info("Data Array Length ===> " + csvDataArray.length());

		  	JSONArray csvData = JSONFactoryUtil.createJSONArray();

			retorno = postUbigeo(csvData);
		}


		resultsObject = JSONFactoryUtil.createJSONObject(retorno);

		return resultsObject;
	}

	@Override
	public Optional<Ubicacion> getLocation(String filter) {


		String response = restConsumer.get("https://maps.googleapis.com/maps/api/geocode/json"+filter, new Credentials("",""),
				null,"");

		UbicacionSearchResponse ubicacionSearchResponse = new UbicacionSearchResponse();
		try {
			ubicacionSearchResponse = objectMapper.readValue(response, UbicacionSearchResponse.class);
		} catch (JsonProcessingException e) {
			log.error(e);
		}

		return ubicacionSearchResponse.getResults().stream().findFirst();
	}


	@Override
	public Optional<ResponseBatch> deleteBatch(JSONArray jsonList) {

		log.info(jsonList.toString());
		String response = restConsumer.delete(SedeAdministrationPortletKeys.OFICINA_HOST_BATCH,credentials,jsonList.toString());

		ResponseBatch batchSearchResponse = new ResponseBatch();
		try {
			batchSearchResponse = objectMapper.readValue(response, ResponseBatch.class);
		} catch (JsonProcessingException e) {
			log.error(e);
		}

		return Optional.ofNullable(batchSearchResponse);
	}

	@Override
	public List<Ubigeo> getUbigeo(String filter) {
		String response = restConsumer.get(SedeAdministrationPortletKeys.UBIGEO_HOST_GET, credentials,  null,filter);

		UbigeoSearchResponse ubigeoSearchResponse = new UbigeoSearchResponse();
		try {
			ubigeoSearchResponse = objectMapper.readValue(response, UbigeoSearchResponse.class);
		} catch (JsonProcessingException e) {
			log.error(e);
		}

		return ubigeoSearchResponse.getItems();
	}

	@Override
	public List<Oficina> getOficina(String filter) {
		String response = restConsumer.get(SedeAdministrationPortletKeys.OFICINA_HOST_GET, credentials,
				null,filter);

		OficinaSearchResponse oficinaSearchResponse = new OficinaSearchResponse();
		try {
			oficinaSearchResponse = objectMapper.readValue(response, OficinaSearchResponse.class);
		} catch (JsonProcessingException e) {
			log.error(e);
		}

		return oficinaSearchResponse.getItems();
	}

	public Optional<Ubigeo> getUbigeoByfilter(String filter) {

		String response = restConsumer.get(SedeAdministrationPortletKeys.UBIGEO_HOST_GET, credentials,  null,filter);

		UbigeoSearchResponse ubigeoSearchResponse = new UbigeoSearchResponse();
		try {
			ubigeoSearchResponse = objectMapper.readValue(response, UbigeoSearchResponse.class);
		} catch (JsonProcessingException e) {
			log.error(e);
		}

		return ubigeoSearchResponse.getItems().stream().findFirst();

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


		String json = ubigeoRequest.toString();

		String response = restConsumer.post(
				SedeAdministrationPortletKeys.OFICINA_HOST_BATCH, credentials,
				json);

		log.info(response);

		return response;
	}

	private String postUbigeo(JSONArray ubigeoRequest) {

		String json = ubigeoRequest.toString();

        String response = restConsumer.post(
				SedeAdministrationPortletKeys.UBIGEO_HOST_BATCH, credentials,
                json);

		log.info(response);

		return response;
	}

	public GeocodingResult[] getUbicacion(String direccion) {
		GeocodingResult[] results = new GeocodingResult[0];
		try {

			GeoApiContext context = new GeoApiContext.Builder()
					.apiKey("AIzaSyAbJ6UwYW0ofJ7BdbNPwjRrZANK8E1tg0A")
					.build();
			results = new GeocodingResult[0];

			results = GeocodingApi.geocode(context,direccion).await();

			System.out.println(results.length);
			context.shutdown();

		} catch (ApiException | IOException | InterruptedException ex) {
			System.out.println("Exception ->" + ex.getMessage());
		}
		return results;
	}


	private  Credentials credentials = new Credentials("test@liferay.com","123");

	private static Log log = LogFactoryUtil.getLog(ConsumerServiceImpl.class);

	@Reference
	private RestConsumer restConsumer;

}