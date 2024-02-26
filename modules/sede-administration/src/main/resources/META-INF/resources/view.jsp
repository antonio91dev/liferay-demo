<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %><%@ page
import="com.liferay.portal.kernel.util.Validator" %> <%@ page
import="javax.portlet.PortletURL" %> <%@ include file="/init.jsp" %> <% String
redirect = ParamUtil.getString(request, "redirect"); if
(Validator.isNull(redirect)) { PortletURL portletURL =
renderResponse.createRenderURL(); redirect = portletURL.toString(); }
portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(redirect);
 %>

<portlet:actionURL
  name="CSVDataUpload"
  var="CSVDataUploadURL"
></portlet:actionURL>

<liferay-frontend:edit-form
  action="<%= CSVDataUploadURL %>"
  method="post"
  name="fm"
>
  <aui:input name="redirect" type="hidden" value="<%= redirect %>" />

  <liferay-frontend:edit-form-body>
    <liferay-frontend:fieldset>

      <aui:select required="<%= true %>" label="Seleccionar Objeto a importar" name="objetoImportar">
        <aui:option value="ubigeo"><liferay-ui:message key="Ubigeo" /></aui:option>
        <aui:option value="oficina"><liferay-ui:message key="Oficina" /></aui:option>
      </aui:select>

      <aui:input
        id="csvDataFile"
        label="select-file"
        name="csvDataFile"
        required="<%= true %>"
        type="file"
      >
        <aui:validator name="acceptFiles"> 'csv' </aui:validator>
      </aui:input>
    </liferay-frontend:fieldset>
  </liferay-frontend:edit-form-body>

  <liferay-frontend:edit-form-footer>
    <liferay-frontend:edit-form-buttons redirect="<%= redirect %>" />
  </liferay-frontend:edit-form-footer>
</liferay-frontend:edit-form>
<liferay-ui:success key="onlinetaskactivity.updating" message="onlinetaskactivity.updating" />