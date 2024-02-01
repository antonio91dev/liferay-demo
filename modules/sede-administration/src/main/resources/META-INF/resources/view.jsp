<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="javax.portlet.PortletURL" %>
<%@ include file="/init.jsp" %>


<%
	String redirect = ParamUtil.getString(request, "redirect");

	if (Validator.isNull(redirect)) {
		PortletURL portletURL = renderResponse.createRenderURL();

		redirect = portletURL.toString();
	}

	portletDisplay.setShowBackIcon(true);
	portletDisplay.setURLBack(redirect);

	renderResponse.setTitle("sdasd");
%>


<portlet:actionURL var="CSVDataUploadURL" name="CSVDataUpload"></portlet:actionURL>


<liferay-frontend:edit-form
		action="<%= CSVDataUploadURL %>"
		method="post"
		name="fm"
>
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />

	<liferay-frontend:edit-form-body>

		<liferay-frontend:fieldset>


			<aui:input cssClass="tag-name" name="name" placeholder="name" />
		</liferay-frontend:fieldset>

		<liferay-frontend:fieldset>
			<aui:input label="select-file" name='<portlet:namespace/>csvDataFile' id="csvDataFile" required="<%= true %>" type="file">
				<aui:validator name="acceptFiles">
					'xlsx'
				</aui:validator>
			</aui:input>

			<aui:input checked="<%= true %>" label="overwrite-existing-style-books" name="overwrite" type="checkbox" />
		</liferay-frontend:fieldset>

	</liferay-frontend:edit-form-body>

	<liferay-frontend:edit-form-footer>
		<liferay-frontend:edit-form-buttons
				redirect="<%= redirect %>"
		/>
	</liferay-frontend:edit-form-footer>
</liferay-frontend:edit-form>