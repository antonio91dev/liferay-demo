<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.liferay.petra.string.StringPool" %>
<%@ page import="com.vass.reniec.pe.sede.location.configuracion.SedeLocationMapaDisplayConfiguration" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
        taglib uri="http://liferay.com/tld/clay" prefix="clay" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %><%@
        taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<liferay-theme:defineObjects />

<portlet:defineObjects />

<%
    String redirect = ParamUtil.getString(request, "redirect");
%>

<%
    SedeLocationMapaDisplayConfiguration messageDisplayConfiguration =
            (SedeLocationMapaDisplayConfiguration)
                    renderRequest.getAttribute(SedeLocationMapaDisplayConfiguration.class.getName());

    String tipoForm = StringPool.BLANK;
    String sedePrincipal = StringPool.BLANK;
    String googleAPIKey= StringPool.BLANK;
    String ubicacionPrincipal= StringPool.BLANK;
    String longitud= StringPool.BLANK;
    String latitud= StringPool.BLANK;



    if (messageDisplayConfiguration != null) {
        tipoForm = portletPreferences.getValue("tipoForm", messageDisplayConfiguration.tipoForm());

        sedePrincipal = portletPreferences.getValue("sedePrincipal", messageDisplayConfiguration.sedePrincipal());

        googleAPIKey = portletPreferences.getValue("googleAPIKey", messageDisplayConfiguration.googleAPIKey());

        ubicacionPrincipal = portletPreferences.getValue("ubicacionPrincipal", messageDisplayConfiguration.ubicacionPrincipal());

        longitud = portletPreferences.getValue("longitud", messageDisplayConfiguration.longitud());

        latitud = portletPreferences.getValue("latitud", messageDisplayConfiguration.latitud());


    }
%>