package com.vass.reniec.pe.portlet;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.vass.reniec.pe.constants.SedeAdministrationPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;

import com.vass.reniec.pe.util.CSVImportFileUtil;
import org.osgi.service.component.annotations.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author jantonio
 */
@Component(
	property = {
		"com.liferay.portlet.add-default-resource=true",
		"com.liferay.portlet.display-category=category.hidden",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.layout-cacheable=true",
		"com.liferay.portlet.private-request-attributes=false",
		"com.liferay.portlet.private-session-attributes=false",
		"com.liferay.portlet.render-weight=50",
		"com.liferay.portlet.use-default-template=true",
		"javax.portlet.display-name=SedeAdministration",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + SedeAdministrationPortletKeys.SEDEADMINISTRATION,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user",

	},
	service = Portlet.class
)
public class SedeAdministrationPortlet extends MVCPortlet {
	private Log log = LogFactoryUtil.getLog(SedeAdministrationPortlet.class);

	public void CSVDataUpload(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {

		log.info("******************** User CSV Data Upload ***************************");

		String filePath = "D:\\CSV\\Import\\User.csv";

		try (FileOutputStream fOut = new FileOutputStream(filePath);) {
			UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(actionRequest);
			InputStream is = uploadRequest.getFileAsStream("csvDataFile");
			int i;
			while ((i = is.read()) != -1) {
				fOut.write(i);
			}

			File csvFile = new File(filePath);
			log.info("CSV File ===> " + csvFile);

			if (Validator.isNotNull(csvFile)) {
				if (csvFile.getName().contains(".csv")) {
					JSONArray csvDataArray = CSVImportFileUtil.readCSVFile(csvFile);
					log.info(csvDataArray);

					//UserCSVImportUtil.addUserToDatabase(csvDataArray, actionRequest);

				} else {
					log.error("Uploaded File is not CSV file.Your file name is ----> " + csvFile.getName());
				}
			}
		} catch (Exception e) {
			log.error("Exception in CSV File Reading Process :: ", e);
		}
	}


}