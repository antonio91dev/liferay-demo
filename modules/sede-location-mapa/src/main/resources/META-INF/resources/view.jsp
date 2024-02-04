<%@ include file="/init.jsp" %>
<link href="<%= request.getContextPath() %>/css/custom.css" rel="stylesheet" />
<script src="<%= request.getContextPath() %>/js/jquery.custom-scrollbar.js"></script>
<link href="<%= request.getContextPath() %>/css/jquery.custom-scrollbar.css" rel="stylesheet">


<portlet:resourceURL id="getDepartamento" var="getDepartamentoURL" />

<div class="view_store_search">
    <aui:form name="searchStoreLocationForm">
        <aui:fieldset>
            <aui:input autoFocus="<%= true %>" label="name" name="name" placeholder="name" />
            <aui:input autoFocus="<%= true %>" label="name2" name="name2" placeholder="name2" />

            <aui:input autoFocus="<%= true %>" label="name3" name="name3" placeholder="name3" />

            <aui:input autoFocus="<%= true %>" label="name4" name="name4" placeholder="name4" />

        </aui:fieldset>

        <aui:button-row>
            <aui:button type="submit" />


        </aui:button-row>


        <aui:select label="Select Distance Unit" name="distanceUnit">
            <aui:option value="kilometer"><liferay-ui:message key="kilometer" /></aui:option>
            <aui:option value="miles"><liferay-ui:message key="miles" /></aui:option>
        </aui:select>

        <aui:select label="Select Distance Unit" name="distanceUnit1">
            <aui:option value="kilometer"><liferay-ui:message key="kilometer" /></aui:option>
            <aui:option value="miles"><liferay-ui:message key="miles" /></aui:option>
        </aui:select>

        <aui:select label="Select Distance Unit" name="distanceUnit2">
            <aui:option value="kilometer"><liferay-ui:message key="kilometer" /></aui:option>
            <aui:option value="miles"><liferay-ui:message key="miles" /></aui:option>
        </aui:select>

        <aui:input helpMessage="storeSearchToolTip" id="distance" label="Distance" name="distance">
            <aui:validator name="digits"></aui:validator>
        </aui:input>

        <button class="btn btn-primary custBtn" onclick="<portlet:namespace />getNearbyStoreLocation();" type="button"><liferay-ui:message key="submit" /></button>

    </aui:form>
    <div class="clearfix"></div>
    <div class="container">
        <div id="noRecords" style="display: none;"><liferay-ui:message key="no-records-found" /></div>
        <div class="row"><label id="noOfRecords"> </label> </div>
    </div>

    <div class="default-skin row" id="addressInformation"></div>

    <div id="map"></div>
</div>

<script src="https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/markerclusterer.js"></script>
<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAbJ6UwYW0ofJ7BdbNPwjRrZANK8E1tg0A&callback=initMap"></script>


<script type="text/javascript">

    $( document ).ready(function() {
        <portlet:namespace />getCargaDepartamento();

        $("#map").show();
        var locations = [];
        locations.push({
            lat:Number(-12.068352795386877),
            lng:Number(-77.04771625774335),
            storeName:"value.storeName",
            address1:"value.address1",
            city:"value.city",
            state:"value.state",
            country:"value.country",
            zip:"value.zip",
            phone:"value.phone"
        });
        initMap(locations);




    });

    function initMap(locations) {
        var map = new google.maps.Map(document.getElementById('map'), {
            zoom: 13,
            center: {lat: Number(-12.082485636330079), lng: Number(-77.05031392249481)}
        });


        if(map == 'undefined') {
            alert('map is undefined');
        }
        var labels = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';

        var infowindow = new google.maps.InfoWindow();
        var marker, i;
        var markers = locations.map(function(location, i) {
            marker = new google.maps.Marker({
                position: location,
                label: labels[i % labels.length],
                title: location.storeName
            });

            google.maps.event.addListener(marker, 'click', (function(marker, i) {
                return function() {
                    infowindow.setContent('<div><strong>'+location.storeName+'</div></strong><div>'+location.address1+','+'</div><div>'+location.city+','+'</div><div>'+location.state+','+'</div><div>'+location.country+','+'</div><div>'+location.zip+'</div><div> Phone : '+location.phone+'</div>' );
                    infowindow.open(map, marker);
                }
            })(marker, i));

            return marker;
        });
        var markerCluster = new MarkerClusterer(map, markers,
            {imagePath: 'https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/m'});
    }



    function <portlet:namespace />getCargaDepartamento() {
        //alert("PRIMER AJAX");
        $.ajax({
            url :"<%= getDepartamentoURL %>",
            data:{
                <portlet:namespace />cmd: "departamento"
            },
            success: function(data) {
                var content= JSON.parse(data);
                alert("PRIMER AJAX-52");
            }
        });
    }

    function <portlet:namespace />getCargaProvincia(departamento) {
        $.ajax({
            url :"<%= getDepartamentoURL %>",
            data:{
                <portlet:namespace />departamento: departamento,
            },
            success: function(data) {
                var content= JSON.parse(data);

            }
        });
    }

    function <portlet:namespace />getCargaDistrito(distrito) {
        $.ajax({
            url :"<%= getDepartamentoURL %>",
            data:{
                <portlet:namespace />distrito: distrito,
            },
            success: function(data) {
                var content= JSON.parse(data);

            }
        });
    }









</script>