package com.vass.reniec.pe.util;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;

public class CSVImportFileUtil {
    private static Log log = LogFactoryUtil.getLog(CSVImportFileUtil.class);

    public static JSONArray readCSVFile(File csvfile) throws IOException {

        JSONArray csvData = JSONFactoryUtil.createJSONArray();

        try (InputStream targetStream = new FileInputStream(csvfile);
             InputStreamReader isr = new InputStreamReader(targetStream);) {

            CSVFormat csvFormat = CSVFormat.newFormat(',').withIgnoreEmptyLines().withTrim(true);

            CSVParser csvParser = csvFormat.parse(isr);
            if (csvParser != null) {
                JSONObject rowObject = null;
                for (CSVRecord record : csvParser) {
                    rowObject = JSONFactoryUtil.createJSONObject();
                    for (int j = 0; j < record.size(); j++) {
                        rowObject.put(String.valueOf(j), record.get(j));
                    }
                    csvData.put(rowObject);
                }
                log.info("CSV Data : " + csvData.toString());
            }

        } catch (IOException e) {
            log.error("Exception while reading file : ", e);
            throw e;
        }

        return csvData;
    }

}
