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

	public static JSONArray readCSVFile(File csvfile) throws PortalException, IOException {
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

					if (i > 1)
						csvData.put(rowObject);
				}

				//log.info("CSV Data : " + csvData.toString());
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