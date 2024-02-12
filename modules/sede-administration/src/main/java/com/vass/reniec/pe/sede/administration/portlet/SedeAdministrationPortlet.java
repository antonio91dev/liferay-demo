package com.vass.reniec.pe.sede.administration.portlet;

import com.liferay.petra.string.CharPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.*;

import com.vass.reniec.pe.model.Oficina;
import com.vass.reniec.pe.sede.administration.constants.SedeAdministrationPortletKeys;
import com.vass.reniec.pe.sede.administration.service.ConsumerService;
import com.vass.reniec.pe.sede.administration.util.CSVImportFileUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.*;

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
						JSONObject JSONObject = consumerService.addOficina(csvDataArray);

					}

/*					StringBundler sb = new StringBundler();
					sb.setIndex(sb.index() - 1);
					sb.append(CharPool.NEW_LINE);

					byte[] bytes = sb.toString().getBytes();
					String contentType = ContentTypes.APPLICATION_TEXT;
					PortletResponseUtil.sendFile((PortletRequest) actionRequest, (MimeResponse) actionResponse,"FILENAME", bytes, contentType);
*/
				}
				else {
					log.error(
							"Uploaded File is not CSV file.Your file name is ----> " +
									csvFile.getName());
					throw new Exception("Uploaded File is not CSV file.Your file name is");
				}
			}


		}
		catch (Exception e) {
			log.error(e);
			SessionErrors.add(actionRequest, e.getClass());
		}
	}

	private Log log = LogFactoryUtil.getLog(SedeAdministrationPortlet.class);

	@Reference
	private ConsumerService consumerService;

}