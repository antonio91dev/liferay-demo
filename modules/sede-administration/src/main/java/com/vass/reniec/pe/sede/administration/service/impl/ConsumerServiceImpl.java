package com.vass.reniec.pe.sede.administration.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

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
import com.vass.reniec.pe.sede.administration.constants.SedeAdministrationPortletKeys;
import com.vass.reniec.pe.model.Credentials;
import com.vass.reniec.pe.model.Oficina;
import com.vass.reniec.pe.model.Ubigeo;
import com.vass.reniec.pe.model.UbigeoSearchResponse;
import com.vass.reniec.pe.sede.administration.service.ConsumerService;
import com.vass.reniec.pe.sede.administration.service.api.model.request.UbigeoRequest;

import javax.portlet.ActionRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.Optional;

@Component(immediate = true, service = ConsumerService.class)
public class ConsumerServiceImpl implements ConsumerService {

	@Override
	public JSONObject addOficina(
		JSONArray csvDataArray, ActionRequest actionRequest) throws Exception  {

		String retorno = "";
		JSONObject resultsObject = JSONFactoryUtil.createJSONObject();

		if (Validator.isNotNull(csvDataArray)) {
			log.info("Data Array Length ===> " + csvDataArray.length());

			JSONArray csvData = JSONFactoryUtil.createJSONArray();

			for (int i = 1; i < csvDataArray.length(); i++) {

				JSONObject jsonObject = csvDataArray.getJSONObject(i);

				Oficina csvToBean = new Oficina();

				csvToBean.setCentroDeAtencion(GetterUtil.getString(jsonObject.get("centroDeAtencion")));
				csvToBean.setHorarioDeAtencion(GetterUtil.getString(jsonObject.get("horarioDeAtencion")));
				csvToBean.setLatitud(GetterUtil.getString(jsonObject.get("latitud")));
				csvToBean.setLongitud(GetterUtil.getString(jsonObject.get("longitud")));
				csvToBean.setNombreDeVia(GetterUtil.getString(jsonObject.get("nombreDeVia")));

				csvToBean.setCentralTelefonico("");
				csvToBean.setRepresentante("");
				csvToBean.setCorreo("");

				String ubigeoId = GetterUtil.getString(jsonObject.get("ubigeoId"));
				String filter = Operadores.addFilter(Filters.CODIGO_UBIGEO.getFilter(), Operadores.EQUAL, ubigeoId);

				Optional<Ubigeo> optionalUbigeoItem = getUbigeoByfilter(filter);

				log.info(optionalUbigeoItem.get().getId());
				log.info(GetterUtil.getLong(optionalUbigeoItem.get().getId()));

				csvToBean.setUbigeoId(GetterUtil.getLong(optionalUbigeoItem.get().getId()));
				csvToBean.setTipoDeVia(GetterUtil.getString(jsonObject.get("tipoDeVia")));

				csvToBean.setTramiteCERRciviles(GetterUtil.getBoolean(jsonObject.get("tramiteCERRciviles") == "SI"));
				csvToBean.setTramiteCERRuipn(GetterUtil.getBoolean(jsonObject.get("tramiteCERRuipn") == "SI"));
				csvToBean.setTramiteDNIEntrega(GetterUtil.getBoolean(jsonObject.get("tramiteDNIEntrega") == "SI"));
				csvToBean.setTramiteDNIMayor(GetterUtil.getBoolean(jsonObject.get("tramiteDNIMayor") == "SI"));
				csvToBean.setTramiteDNIMenor(GetterUtil.getBoolean(jsonObject.get("tramiteDNIMenor") == "SI"));
				csvToBean.setTramiteElectronico(GetterUtil.getBoolean(jsonObject.get("tramiteElectronico") == "SI"));
				csvToBean.setTramiteEREP(GetterUtil.getBoolean(jsonObject.get("tramiteEREP") == "SI"));
				csvToBean.setTramiteFotografia(GetterUtil.getBoolean(jsonObject.get("tramiteFotografia") == "SI"));
				csvToBean.setTramiteINSRciviles(GetterUtil.getBoolean(jsonObject.get("tramiteINSRciviles") == "SI"));

				csvData.put(JSONFactoryUtil.createJSONObject(getJson(csvToBean)));
			}
			log.info("User Bean" + csvData);

			//retorno = postOficina(csvData);
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

				csvData.put(JSONFactoryUtil.createJSONObject(getJson(csvToBean)));

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


	private static Log log = LogFactoryUtil.getLog(ConsumerServiceImpl.class);

	private Credentials credentials = new Credentials("test@liferay.com","123");


	@Reference
	private RestConsumer restConsumer;

}