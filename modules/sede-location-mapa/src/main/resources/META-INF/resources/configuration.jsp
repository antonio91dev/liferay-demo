<%--
/**
 * Copyright 2000-present Liferay, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
--%>

<%@ page import="com.liferay.portal.kernel.util.Constants" %>
<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %>
<%@ page import="com.liferay.google.places.constants.GooglePlacesWebKeys" %>

<%@ include file="/init.jsp" %>

<liferay-portlet:actionURL
        portletConfiguration="<%= true %>"
        var="configurationActionURL"
/>

<liferay-portlet:renderURL
        portletConfiguration="<%= true %>"
        var="configurationRenderURL"
/>

<clay:container-fluid
        cssClass="add-group-container"
>
    <div class="add-group-content">
        <div class="lfr-form-content">
        </div>
    </div>

    <liferay-frontend:edit-form action="<%= configurationActionURL %>"  method="post" name="fm" fluid="true">
        <aui:input name="longitud" type="hidden" value="<%= longitud %>"/>
        <aui:input name="latitud" type="hidden" value="<%= latitud %>"/>
        <aui:input
                name="<%= Constants.CMD %>"
                type="hidden"
                value="<%= Constants.UPDATE %>"
        />
        <aui:input
                name="redirect"
                type="hidden"
                value="<%= configurationRenderURL %>"
        />
        <liferay-frontend:edit-form-body>
            <liferay-frontend:fieldset collapsed="<%= false %>" collapsible="true" label="Buscador">
                <aui:row>
                    <aui:col width="50">
                        <aui:select
                                label="Tipo de Formulario"
                                name="tipoForm"
                                value="<%= tipoForm %>"
                        >
                            <aui:option value="mapacontacto">Mapa Contacto</aui:option>
                            <aui:option value="mapaoficina">Mapa Oficinas</aui:option>
                        </aui:select>

                    </aui:col>
                    <aui:col width="50">

                    </aui:col>
                </aui:row>
                <aui:row>
                    <aui:col width="50">
                        <aui:input class="form-control" label="Ubicacion Principal" name="ubicacionPrincipal" required="true" type="text" value="<%= ubicacionPrincipal %>">
                            <aui:validator name="maxLength">256</aui:validator>
                        </aui:input>
                    </aui:col>
                    <aui:col width="50">
                        <aui:input helpMessage='<%= LanguageUtil.get(request, "set-the-google-places-api-key-that-is-used-for-this-set-of-pages") %>'
                                   label='<%= LanguageUtil.get(request, "google-places-api-key") %>'
                                   name='googleAPIKey'
                                   value="<%= googleAPIKey %>" />
                    </aui:col>
                </aui:row>
                <aui:row>
                    <aui:col width="50">
                        <aui:select
                                label="Estilo del Tipo del Boton busqueda"
                                name="tipoForm"
                        >
                            <aui:option value="mapacontacto">Boton Primario</aui:option>
                            <aui:option value="mapaoficina">Boton Secundario</aui:option>
                        </aui:select>
                    </aui:col>
                    <aui:col width="50">
                        <aui:input type="text" name="preferences--heroAgente-xs--" cssClass="span6"
                                   label="Texto de Boton busqueda" />
                    </aui:col>
                </aui:row>

                <aui:row>
                    <aui:col width="50">
                        <aui:input type="text" name="preferences--heroAgente-xs--" cssClass="span6"
                                   label="URL imagen Icon Contacto" />
                    </aui:col>
                    <aui:col width="50">
                        <aui:input type="text" name="preferences--heroAgente-sm--" cssClass="span6"
                                   label="URL imagen Icon Horario" />
                    </aui:col>
                </aui:row>
                <aui:row>
                    <aui:col width="50">
                            <aui:input type="text" name="preferences--heroAgente-sm--" cssClass="span6"
                                       label="URL imagen Icon Busqueda" />
                    </aui:col>
                    <aui:col width="50">
                            <aui:input type="text" name="preferences--heroAgente-xs--" cssClass="span6"
                               label="URL imagen Icon Direccion" />
                    </aui:col>
                </aui:row>
            </liferay-frontend:fieldset>
            <liferay-frontend:fieldset collapsed="<%= false %>" collapsible="true" label="Seccion Call to action">
            </liferay-frontend:fieldset>
        </liferay-frontend:edit-form-body>
        <liferay-frontend:edit-form-footer>
            <aui:button type="submit" />
            <aui:button type="cancel" />
        </liferay-frontend:edit-form-footer>
    </liferay-frontend:edit-form>

    <div class="add-group-loading align-items-center d-none flex-column justify-content-center">
        <span aria-hidden="true" class="loading-animation mb-4"></span>

        <p class="text-3 text-center text-secondary"><liferay-ui:message key="the-creation-of-the-site-may-take-some-time-.closing-the-window-will-not-cancel-the-process" /></p>
    </div>
</clay:container-fluid>

    <script
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAbJ6UwYW0ofJ7BdbNPwjRrZANK8E1tg0A&callback=initAutocomplete&libraries=places&v=weekly"
        defer
></script>

<script type="text/javascript">

    function initAutocomplete() {
        /*const map = new google.maps.Map(document.getElementById("map"), {
            mapTypeId: "roadmap",
        });*/
        // Create the search box and link it to the UI element.
        const input = document.getElementById("<portlet:namespace />ubicacionPrincipal");
        const searchBox = new google.maps.places.SearchBox(input);

        //map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);
        // Bias the SearchBox results towards current map's viewport.
        /* map.addListener("bounds_changed", () => {
             console.log(map.getBounds());
             searchBox.setBounds(map.getBounds());
         });*/

        //let markers = [];

        // [START maps_places_searchbox_getplaces]
        // Listen for the event fired when the user selects a prediction and retrieve
        // more details for that place.
        searchBox.addListener("places_changed", () => {
            const places = searchBox.getPlaces();

            if (places.length == 0) {
                return;
            }

            // Clear out the old markers.
            //markers.forEach((marker) => {
            //    marker.setMap(null);
            //});
            //markers = [];

            // For each place, get the icon, name and location.
            const bounds = new google.maps.LatLngBounds();

            places.forEach((place) => {
                console.log(place);
                if (!place.geometry || !place.geometry.location) {
                    console.log("Returned place contains no geometry");
                    return;
                }

                const icon = {
                    url: place.icon,
                    size: new google.maps.Size(71, 71),
                    origin: new google.maps.Point(0, 0),
                    anchor: new google.maps.Point(17, 34),
                    scaledSize: new google.maps.Size(25, 25),
                };

                // Create a marker for each place.
                /*markers.push(
                    new google.maps.Marker({
                        map,
                        icon,
                        title: place.name,
                        position: place.geometry.location,
                    }),
                );*/

                console.log("" + place.geometry.location);
                console.log("" + place.geometry.location.lat);
                $('#<portlet:namespace />latitud').val(place.geometry.location.lat);
                console.log("" + place.geometry.location.lng);
                $('#<portlet:namespace />longitud').val(place.geometry.location.lng);

                console.log("" + place.geometry.viewport);
                if (place.geometry.viewport) {
                    // Only geocodes have viewport.
                    bounds.union(place.geometry.viewport);
                } else {
                    bounds.extend(place.geometry.location);
                }
            });
            //map.fitBounds(bounds);
        });
    }
</script>