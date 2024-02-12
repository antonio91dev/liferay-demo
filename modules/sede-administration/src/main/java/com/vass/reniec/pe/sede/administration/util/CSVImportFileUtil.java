package com.vass.reniec.pe.sede.administration.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.io.*;
import java.util.Optional;

import com.liferay.portal.kernel.util.GetterUtil;
import com.vass.reniec.pe.api.enums.Filters;
import com.vass.reniec.pe.api.enums.Operadores;
import com.vass.reniec.pe.model.Oficina;
import com.vass.reniec.pe.model.Ubigeo;
import com.vass.reniec.pe.sede.administration.service.ConsumerService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.osgi.service.component.annotations.Reference;

public class CSVImportFileUtil {

	public static JSONArray readCSVFile(File csvfile, String objetoImportar) throws PortalException, IOException {
		JSONArray csvData = JSONFactoryUtil.createJSONArray();

		try (InputStream targetStream = new FileInputStream(csvfile);
			InputStreamReader isr = new InputStreamReader(targetStream)) {

			CSVFormat csvFormat = CSVFormat.newFormat(
				','
			).withIgnoreEmptyLines(
			).withTrim(
				true
			);



			CSVParser csvParser = csvFormat.parse(isr);
			int i = 0;
			for (CSVRecord csvRecord: csvParser.getRecords()) {
				if(i > 0){
					if(objetoImportar.equalsIgnoreCase("OFICINA")) {
						log.info(csvParser.getRecordNumber());
						Oficina oficina = new Oficina();
						oficina.setUbigeoId(GetterUtil.getInteger(csvRecord.get(0)));
						oficina.setCentralTelefonico(GetterUtil.getString(csvRecord.get(1)));
						oficina.setRepresentante(GetterUtil.getString(csvRecord.get(2)));
						oficina.setCorreo(GetterUtil.getString(csvRecord.get(3)));
						oficina.setLatitud(GetterUtil.getString(csvRecord.get(4)));
						oficina.setLongitud(GetterUtil.getString(csvRecord.get(5)));
						oficina.setCentroDeAtencion(GetterUtil.getString(csvRecord.get(6)));
						oficina.setHorarioDeAtencion(GetterUtil.getString(csvRecord.get(7)));
						oficina.setTipoDeVia(GetterUtil.getString(csvRecord.get(8)));
						oficina.setNombreDeVia(GetterUtil.getString(csvRecord.get(9)));
						//log.info(csvRecord.get(10));
						oficina.setTramiteFotografia(GetterUtil.getBoolean(csvRecord.get(11).equals("SI")));
						oficina.setTramiteElectronico(GetterUtil.getBoolean(csvRecord.get(12).equals("SI")));
						oficina.setTramiteDNIMayor(GetterUtil.getBoolean(csvRecord.get(13).equals("SI")));
						oficina.setTramiteDNIMenor(GetterUtil.getBoolean(csvRecord.get(14).equals("SI")));
						oficina.setTramiteDNIEntrega(GetterUtil.getBoolean(csvRecord.get(15).equals("SI")));
						oficina.setTramiteINSRciviles(GetterUtil.getBoolean(csvRecord.get(16).equals("SI")));
						oficina.setTramiteCERRciviles(GetterUtil.getBoolean(csvRecord.get(17).equals("SI")));
						oficina.setTramiteCERRuipn(GetterUtil.getBoolean(csvRecord.get(18).equals("SI")));
						oficina.setTramiteEREP(GetterUtil.getBoolean(csvRecord.get(19).equals("SI")));
						//JSONFactoryUtil.createJSONObject(
						csvData.put(JSONFactoryUtil.createJSONObject(getJson(oficina)));
					} else if (objetoImportar.equalsIgnoreCase("UBIGEO")) {
						Ubigeo ubigeo = new Ubigeo();
						ubigeo.setCodigo(GetterUtil.getString(csvRecord.get(0)));
						ubigeo.setDepartamento(GetterUtil.getString(csvRecord.get(1)));
						ubigeo.setProvincia(GetterUtil.getString(csvRecord.get(2)));
						ubigeo.setDistrito(GetterUtil.getString(csvRecord.get(3)));
						csvData.put(JSONFactoryUtil.createJSONObject(getJson(ubigeo)));
					}
				}
				i++;
			}
		}
		catch (IOException e) {
			log.error("Exception while reading file : ", e);
			throw e;
		}
		return csvData;
	}

	 static ObjectMapper objectMapper = new ObjectMapper(
	).configure(
			DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false
	);
	private static String getJson(Object object) {
		String json = "";

		try {
			json = objectMapper.writeValueAsString(object);
		}
		catch (JsonProcessingException e) {
			log.error(e);
		}

		return json;
	}

	private static Log log = LogFactoryUtil.getLog(CSVImportFileUtil.class);

	@Reference
	private ConsumerService consumerService;

}