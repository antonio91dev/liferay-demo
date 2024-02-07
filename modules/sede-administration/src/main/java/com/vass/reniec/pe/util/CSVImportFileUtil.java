package com.vass.reniec.pe.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.io.*;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class CSVImportFileUtil {

	public static JSONArray readCSVFile(File csvfile,String objetoImportar) throws PortalException, IOException {
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

			if (csvParser != null) {
				JSONObject rowObject = null;
				int i = 0;

				for (CSVRecord record : csvParser) {
					i++;
					rowObject = JSONFactoryUtil.createJSONObject();

					if(objetoImportar.equalsIgnoreCase("UBIGEO")){
						for (int j = 0; j < record.size(); j++) {
							if (j == 0) {
								rowObject.put("codigo", record.get(j));
							}

							if (j == 1) {
								rowObject.put("departamento", record.get(j));
							}

							if (j == 2) {
								rowObject.put("provincia", record.get(j));
							}

							if (j == 3) {
								rowObject.put("distrito", record.get(j));
							}
						}
					}else if(objetoImportar.equalsIgnoreCase("OFICINA")){

						for (int j = 0; j < record.size(); j++) {
							if (j == 0) {
								rowObject.put("r_ubigeoOficina_c_ubigeoId", record.get(j));
							}

							if (j == 1) {
								rowObject.put("latitud", record.get(j));
							}

							if (j == 2) {
								rowObject.put("longitud", record.get(j));
							}

							if (j == 3) {
								rowObject.put("centroDeAtencion", record.get(j));
							}

							if (j == 4) {
								rowObject.put("horarioDeAtencion", record.get(j));
							}

							if (j == 5) {
								rowObject.put("tipoDeVia", record.get(j));
							}
							if (j == 6) {
								rowObject.put("nombreDeVia", record.get(j));
							}
							/*if (j == 7) {
								rowObject.put("campo5", record.get(j));
							}*/
							if (j == 8) {
								rowObject.put("tramiteFotografia", record.get(j));
							}
							if (j == 9) {
								rowObject.put("tramiteElectronico", record.get(j));
							}
							if (j == 10) {
								rowObject.put("tramiteDNIMayor", record.get(j));
							}
							if (j == 11) {
								rowObject.put("tramiteDNIMenor", record.get(j));
							}
							if (j == 12) {
								rowObject.put("tramiteDNIEntrega", record.get(j));
							}
							if (j == 13) {
								rowObject.put("tramiteINSRciviles", record.get(j));
							}
							if (j == 14) {
								rowObject.put("tramiteCERRciviles", record.get(j));
							}
							if (j == 15) {
								rowObject.put("tramiteCERRuipn", record.get(j));
							}
							if (j == 16) {
								rowObject.put("tramiteEREP", record.get(j));
							}
						}
						log.info("Se lee el objeto  : " + rowObject);
					}


					if (i > 1)
						csvData.put(rowObject);
				}

				log.info("CSV Data : " + csvData.toString());
			}
		}
		catch (IOException e) {
			log.error("Exception while reading file : ", e);

			throw e;
		}

		return csvData;
	}

	private static Log log = LogFactoryUtil.getLog(CSVImportFileUtil.class);

}