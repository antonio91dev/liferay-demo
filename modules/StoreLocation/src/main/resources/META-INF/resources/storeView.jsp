<%@page import="com.liferay.portal.kernel.model.PortletPreferences"%>
<%@ include file="/init.jsp" %>
<%@page import="com.liferay.portal.kernel.util.Constants"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<script src="<%=request.getContextPath()%>/js/jquery.custom-scrollbar.js"></script>
<script src="<%=request.getContextPath()%>/js/maps.js"></script>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery.custom-scrollbar.css">

<div class="view_store_search">

		<aui:form name="searchStoreLocationForm">
			<aui:select name="distanceUnit" label="Select Distance Unit">
				<aui:option value="kilometer"><liferay-ui:message key="kilometer" /></aui:option>
				<aui:option value="miles"><liferay-ui:message key="miles" /></aui:option>
			</aui:select>

			<aui:input id="distance" name="distance" label="Distance" helpMessage="storeSearchToolTip" >
				<aui:validator name="digits"></aui:validator>
			</aui:input>

			<button  class="btn btn-primary custBtn" type="button" onclick="<portlet:namespace />getNearbyStoreLocation()"><liferay-ui:message key="submit" /></button>
		</aui:form>
	<div class="clearfix"></div>
	<c:if test="${empty googleAPIKey}">
		<p class="pull-right"><font color="red" ><liferay-ui:message key="add-google-api-key-message" /></font></p>
	</c:if>
	<div class="container">
		<div id="noRecords" style="display:none"><liferay-ui:message key="no-records-found" /></div>
		<div class="row"><label id="noOfRecords"> </label> </div>
	</div>
	<div class="row default-skin" id="addressInformation"></div>

	<div id="map"></div>
</div>
<portlet:resourceURL var="getNearbyStoreInformationURL" id="getNearbyStoreInformation" />

${googleAPIKey}
<c:if test="${not empty googleAPIKey}">
	<script src="https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/markerclusterer.js"></script>
	<script async defer src="https://maps.googleapis.com/maps/api/js?key=${googleAPIKey}&callback=initMap"></script>
</c:if>




<script type="text/javascript">
	var lat = Number(0.0); var lng = Number(0.0) ;

	$( document ).ready(function() {
		setTimeout(function() {
			<portlet:namespace/>getLocationLatLng();
		}, 1000)
		var latitude='';
		var longitude='';
		<portlet:namespace/>getNearbyStores(latitude, longitude, null, null);
	});

	function <portlet:namespace/>getLocationLatLng(){
		if (navigator.geolocation){
			console.log('window.navigator.geolocation : '+window.navigator.geolocation);
			window.navigator.geolocation.getCurrentPosition(userLocationFound, userLocationNotFound, geoOptions);
		}else{
			viewSearchResultServices
			alert("Geolocation is not supported by this browser.");
		}
	}


	var latLng,
			geoOptions = {
				enableHighAccuracy: false,
				timeout: 5000, // Wait 5 seconds
				maximumAge: 300000 //  Valid for 5 minutes
			};

	var userLocationFound = function(position){
		latLng = {
			lat: position.coords.latitude,
			lng: position.coords.longitude
		};

		var latlng = null;
		lat = parseFloat(latLng.lat);
		lng = parseFloat(latLng.lng);
	}

	var userLocationNotFound = function(){

		var lats='';
		var lngs='';
		$.get("http://ipinfo.io", function (response)
		{
			lats = response.loc.split(',')[0];
			lngs = response.loc.split(',')[1];

			lat = parseFloat(lats);
			lng = parseFloat(lngs);

		}, "jsonp");
	}



	setTimeout(function () {
		if(!latLng){
			userLocationNotFound();
		}else{
			// alert("Location was set");
		}
	}, geoOptions.timeout + 1000); // Wait extra second

	function <portlet:namespace />getNearbyStoreLocation(){
		var unit = $("#<portlet:namespace />distanceUnit option:selected").val();
		var distance = $("#<portlet:namespace />distance").val();
		<portlet:namespace/>getNearbyStores(lat, lng, unit, distance);
	}

	function <portlet:namespace/>getNearbyStores(lat, lng, unit, distance){

		$.ajax({
			url :"<%=getNearbyStoreInformationURL%>",
			data:{
				<portlet:namespace />lattitude: lat,
				<portlet:namespace />longitude: lng,
				<portlet:namespace />unit: unit,
				<portlet:namespace />distance: distance
			},
			success: function(data){
				var content= JSON.parse(data);

				if(content.storeLocationResult.length>0){
					$("#map").show();
					$("#noOfRecords").text(content.storeLocationResult.length +' records found !!!');
					resultAppear(content.lats, content.lngs, content.storeLocationResult);
					$( "#addressInformation" ).empty();
					$.each( content.storeLocationResult, function( key, value ) {

						var addressInformation = document.createElement("div");
						addressInformation.setAttribute("class", "col-md-6");

						var storeName = document.createElement("div");  // Create with DOM
						storeName.innerHTML = value.storeName.bold();
						addressInformation.append(storeName);

						var address1 = document.createElement("div");  // Create with DOM
						address1.innerHTML = value.address1;
						addressInformation.append(address1);

						var city = document.createElement("div");  // Create with DOM
						city.innerHTML = value.city;
						addressInformation.append(city);

						var state = document.createElement("div");  // Create with DOM
						state.innerHTML = value.state;
						addressInformation.append(state);

						var country = document.createElement("div");  // Create with DOM
						country.innerHTML = value.country;
						addressInformation.append(country);

						var zip = document.createElement("div");  // Create with DOM
						zip.innerHTML = value.zip;
						addressInformation.append(zip);

						$("#addressInformation").append(addressInformation);
					});
					$("#addressInformation").customScrollbar({
						skin: "default-skin",
						hScroll: false,
						updateOnWindowResize: true
					});
					$("#noRecords").hide();
					return false;
				}else{
					$("#noOfRecords").empty();
					$("#addressInformation").empty();
					$("#noRecords").show();
					$("#map").hide();
				}
			}
		});
	}

	function resultAppear(latitude, longitude, searchLocationResult){

		var locations = [];
		$.each( searchLocationResult, function( key, value ) {
			locations.push({
				lat:Number(value.latitude),
				lng:Number(value.longitude),
				storeName:value.storeName,
				address1:value.address1,
				city:value.city,
				state:value.state,
				country:value.country,
				zip:value.zip,
				phone:value.phone
			});
		});

		setTimeout(function() {
			initMap(latitude, longitude, searchLocationResult, locations);
		}, 1000)

	}

	function initMap(latitude, longitude, searchLocationResult, locations) {

		if(latitude === 0 && longitude === 0){
			latitude = locations[0].lat;
			longitude = locations[0].lng;
		}

		var map = new google.maps.Map(document.getElementById('map'), {
			zoom: 20,
			center: {lat: Number(latitude), lng: Number(longitude)}
		});

		if(map == 'undefined'){
			alert('map is undefined');
		}

		// Create an array of alphabetical characters used to label the markers.
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

		// Add a marker clusterer to manage the markers.
		var markerCluster = new MarkerClusterer(map, markers,
				{imagePath: 'https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/m'});
	}

</script>