package com.vass.reniec.pe.portlet;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;

import com.vass.reniec.pe.constants.SedeAdministrationPortletKeys;
import com.vass.reniec.pe.service.ConsumerService;
import com.vass.reniec.pe.util.CSVImportFileUtil;

import java.io.File;
import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

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
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class SedeAdministrationPortlet extends MVCPortlet {

	public void CSVDataUpload(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws IOException, PortletException, Exception{

		log.info(
			"******************** User CSV Data Upload ***************************");
		try {
			String objetoImportar = ParamUtil.getString(actionRequest, "objetoImportar");

			log.info("El objeto seleccionado a importar es :" + objetoImportar);

			UploadPortletRequest uploadRequest =
					PortalUtil.getUploadPortletRequest(actionRequest);

			File csvFile = uploadRequest.getFile("csvDataFile");

			if (Validator.isNotNull(csvFile)) {
				if (csvFile.getName(
				).contains(
						".csv"
				)) {

					JSONArray csvDataArray = CSVImportFileUtil.readCSVFile(
							csvFile,objetoImportar);

					if(objetoImportar.equalsIgnoreCase("UBIGEO")){
						log.info("Se procedera a importar el objeto : " + objetoImportar);
						JSONObject JSONObject = consumerService.addUbigeo(csvDataArray, actionRequest);
					}else if(objetoImportar.equalsIgnoreCase("OFICINA")){
						log.info("Se procedera a importar el objeto : " + objetoImportar);
						JSONObject JSONObject = consumerService.addOficina(csvDataArray, actionRequest);
					}

					/*if (Validator.isNotNull(csvDataArray)) {
						JSONObject JSONObject = serviceInvoker.addUbigeo(csvDataArray, actionRequest);
					}*/
				}
				else {
					log.error(
							"Uploaded File is not CSV file.Your file name is ----> " +
									csvFile.getName());
					throw new Exception("Uploaded File is not CSV file.Your file name is");
				}
			}


		}
		catch (Exception exception) {
			log.error(exception,exception);

			SessionErrors.add(actionRequest, exception.getClass());

		}
	}

	private Log log = LogFactoryUtil.getLog(SedeAdministrationPortlet.class);

	@Reference
	private ConsumerService consumerService;

}