<%@ page import="com.liferay.portal.kernel.util.Constants" %>
<%@ include file="/init.jsp" %>

<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>

<portlet:resourceURL id="getDepartamento" var="getDepartamentoURL" />
<portlet:resourceURL id="getSedes" var="getSedesURL" />
<link href="<%= request.getContextPath() %>/css/custom.css" rel="stylesheet" />
<script src="<%= request.getContextPath() %>/js/jquery.custom-scrollbar.js"></script>
<link href="<%= request.getContextPath() %>/css/jquery.custom-scrollbar.css" rel="stylesheet">

<section class="container bg-color-blanco">
    <div class="r-c-<%= tipoForm %>">


        <%
            // Set balance and formatted balance as session attributes.
            if(tipoForm.equals("mapaoficina"))
            {
        %>

        <div class="r-c-<%= tipoForm %>-search">
            <div class="r-c-mapaoficina-search__input">
                <input
                        type="text"
                        id="distrito-input"
                        name="distrito"
                        class="r-j-input-distrito"
                        placeholder="Ingresa el Distrito en el que te encuentras"
                        aria-label="Distrito"
                />
                <img
                        src="<%= request.getContextPath() %>/img/computador.svg"
                        alt="Ícono de computadora"
                        loading="lazy"
                />
                <ul class="autocomplete-list"></ul>

            </div>

            <button onclick="getSede();" class="r-c-mapaoficina-search__button btn r-o-button r-o-button__primary">
                Buscar
            </button>
        </div>
        <div class="r-c-<%= tipoForm %>-filter">
            <div class="dropdown r-o-dropdown">
                <button
                        class="btn btn-secondary dropdown-toggle"
                        type="button"
                        id="departamento"
                        data-toggle="dropdown"
                        aria-haspopup="true"
                        aria-expanded="false"
                >
                    <div class="text-container bg-color-blanco">
                        <p class="selected-option">Departamento</p>
                    </div>

                    <div class="dropdown-icon bg-color-blanco">
                        <img
                                src="<%= request.getContextPath() %>/img/selectarrow.svg"
                                alt="Ícono de computadora"
                                loading="lazy"
                        />
                    </div>
                </button>
                <div class="dropdown-menu" aria-labelledby="departamento" id="selectorDepartamento">
                </div>
            </div>


            <div class="dropdown r-o-dropdown">
                <button
                        class="btn btn-secondary dropdown-toggle"
                        type="button"
                        id="departamento2"
                        data-toggle="dropdown"
                        aria-haspopup="true"
                        aria-expanded="false"
                >
                    <div class="text-container bg-color-blanco">
                        <p class="selected-option">Provincia</p>
                    </div>

                    <div class="dropdown-icon bg-color-blanco">
                        <img
                                src="<%= request.getContextPath() %>/img/selectarrow.svg"
                                alt="Ícono de computadora"
                                loading="lazy"
                        />
                    </div>
                </button>
                <div class="dropdown-menu" aria-labelledby="departamento2" id="selectorProvincia">
                </div>
            </div>


            <div class="dropdown r-o-dropdown">
                <button
                        class="btn btn-secondary dropdown-toggle"
                        type="button"
                        id="departamento3"
                        data-toggle="dropdown"
                        aria-haspopup="true"
                        aria-expanded="false"
                >
                    <div class="text-container bg-color-blanco">
                        <p class="selected-option">Distrito</p>
                    </div>

                    <div class="dropdown-icon bg-color-blanco">
                        <img
                                src="<%= request.getContextPath() %>/img/selectarrow.svg"
                                alt="Ícono de computadora"
                                loading="lazy"
                        />
                    </div>
                </button>
                <div class="dropdown-menu" aria-labelledby="departamento3" id="selectorDistrito">
                </div>
            </div>

            <div class="dropdown r-o-dropdown">
                <button class="btn btn-secondary dropdown-toggle" type="button" id="tramite"
                        data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <div class="text-container bg-color-blanco" >
                        <p class="selected-option">Tramite</p>
                    </div>

                    <div class="dropdown-icon bg-color-blanco">
                        <img src="<%= request.getContextPath() %>/img/selectarrow.svg" alt="Ícono de computadora" loading="lazy">
                    </div>

                </button>
                <div class="dropdown-menu" aria-labelledby="tramite">
                    <button class="dropdown-item" href="#">Seleccionar</button>
                    <button class="dropdown-item" href="#">Toma Fotografia</button>
                    <button class="dropdown-item" href="#">Entrega DNI Electronico</button>
                    <button class="dropdown-item" href="#">Tramite de DNI mayor</button>
                    <button class="dropdown-item" href="#">Tramite de DNI menor</button>
                    <button class="dropdown-item" href="#">Entregas DNI</button>
                    <button class="dropdown-item" href="#">INSCRIPCION DE REGISTROS CIVILES</button>
                    <button class="dropdown-item" href="#">CERTIFICACION DE REGISTROS CIVILES</button>
                    <button class="dropdown-item" href="#">CERTIFICACION DEL RUIPN</button>
                    <button class="dropdown-item" href="#">EREP</button>
                </div>

            </div>
        </div>

        <%   }
        %>

        <div class="r-c-<%= tipoForm %>-cuerpo">
            <div class="r-c-<%= tipoForm %>-mapa">
                <div id="map"></div>
            </div>
            <div class="r-c-<%= tipoForm %>-cards r-j-<%= tipoForm %>-cards" id="resultadoInformacion">

            </div>
        </div>
    </div>
</section>


<%= googleAPIKey %>

<script src="https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/markerclusterer.js"></script>
<script async defer src="https://maps.googleapis.com/maps/api/js?key=<%= googleAPIKey %>&callback=initMap"></script>

<script type="text/javascript">

    $( document ).ready(function() {

        $(".r-j-input-distrito").on("input", function() {
            let inputValue = $(this).val().toLowerCase();
            let autocompleteList = $(".autocomplete-list");
            autocompleteList.empty();

            districts.forEach(function(district) {
                if (district.toLowerCase().startsWith(inputValue)) {
                    $("<li>").text(district).appendTo(autocompleteList);
                }
            });

            if (inputValue.length > 0) {
                autocompleteList.show();
            } else {
                autocompleteList.hide();
            }
        });

        $("#map").show();





        var locations = [];
        initMap(<%= latitud %>,<%= longitud %>,locations);
        <portlet:namespace />getCargaDepartamento();
        <portlet:namespace />getListaAutocomplete();
    });

    let districts = [];

    function <portlet:namespace />getListaAutocomplete(){
        $.ajax({
            url :"<%= getDepartamentoURL %>",
            data:{
                <portlet:namespace />cmd: "listaautocompletado"
            },
            success: function(data) {
                districts = [];
                //console.log(data.searchResults);
                $.each(data.searchResults, function (key, value) {
                    districts.push(value.distrito);
                });
            }
        });
    }

    function <portlet:namespace />getCargaDepartamento() {
        $.ajax({
            url :"<%= getDepartamentoURL %>",
            data:{
                <portlet:namespace />cmd: "departamento"
            },
            success: function(data) {
                //console.log(data.searchResults);

                $.each(data.searchResults, function (key, value) {

                    $('#selectorDepartamento').append($("<button/>", {
                        class: 'dropdown-item departamento',
                        href: '#',
                        value: value.codigo,
                        text: value.departamento
                    }));

                });

                $('.departamento').on('click', function() {
                    let selectedOption = $(this).text();
                    let dropdownButton = $(this).closest('.r-o-dropdown').find('.dropdown-toggle');
                    $(this).siblings().removeClass('selected');

                    dropdownButton.find('.selected-option').text(selectedOption);
                    $(this).addClass('selected');
                    <portlet:namespace />getCargaProvincia($(this).val());
                });

            }
        });
    }

    function <portlet:namespace />getCargaProvincia(codDepartamento) {
        //console.log(codDepartamento);

        $.ajax({
            url :"<%= getDepartamentoURL %>",
            data:{
                <portlet:namespace />cmd: "provincia",
                <portlet:namespace />codDepartamento: codDepartamento
            },
            success: function(data) {
                //console.log(data.searchResults);
                $('#selectorProvincia').empty();
                $.each(data.searchResults, function (key, value) {

                    $('#selectorProvincia').append($("<button/>", {
                        class: 'dropdown-item provincia',
                        href: '#',
                        value: value.codigo,
                        text: value.provincia
                    }));
                });

                $('.provincia').on('click', function() {
                    let selectedOption = $(this).text();
                    let dropdownButton = $(this).closest('.r-o-dropdown').find('.dropdown-toggle');
                    $(this).siblings().removeClass('selected');

                    dropdownButton.find('.selected-option').text(selectedOption);
                    $(this).addClass('selected');
                    <portlet:namespace />getCargaDistrito($(this).val());


                });

            }
        });
    }

    function <portlet:namespace />getCargaDistrito(codProvincia) {
        console.log(codProvincia);

        $.ajax({
            url :"<%= getDepartamentoURL %>",
            data:{
                <portlet:namespace />cmd: "distrito",
                <portlet:namespace />codProvincia: codProvincia
            },
            success: function(data) {
                console.log(data.searchResults);

                $('#selectorDistrito').empty();

                $.each(data.searchResults, function (key, value) {

                    $('#selectorDistrito').append($("<button/>", {
                        class: 'dropdown-item distrito',
                        href: '#',
                        value: value.codigo,
                        text: value.distrito
                    }));
                });

                $('.distrito').on('click', function() {
                    let selectedOption = $(this).text();
                    let dropdownButton = $(this).closest('.r-o-dropdown').find('.dropdown-toggle');
                    $(this).siblings().removeClass('selected');

                    dropdownButton.find('.selected-option').text(selectedOption);
                    $(this).addClass('selected');
                });

            }
        });
    }

    function initMap(latitude, longitude,locations) {




        if(locations.length > 0){
            $('#resultadoInformacion').empty();
            $('#resultadoInformacion').append("<div class='card-item bg-color-blanco'>" +
                " <div class='card-item__header'>" +
                "<img src='<%= request.getContextPath() %>/img/computador.svg' alt='' loading='lazy'>" +
                "<h4 class='card-item__h-principal color-titulo-primario-azul'>Direccion</h4>" +
                "</div>" +
                "<p class='card-item__parrafo color-parrafo-gris-3'>" + locations[0].nombreDeVia +
                "</p>" +
                "</div>"+
                "<div class='card-item bg-color-blanco'>" +
                " <div class='card-item__header'>" +
                "<img src='<%= request.getContextPath() %>/img/computador.svg' alt='' loading='lazy'>" +
                "<h4 class='card-item__h-principal color-titulo-primario-azul'>Horario</h4>" +
                "</div>" +
                "<p class='card-item__parrafo color-parrafo-gris-3'>" + locations[0].centroDeAtencion +
                "</p>" +
                "</div>"+
                "");
        }

        var map = new google.maps.Map(document.getElementById('map'), {
            zoom: 5,
            center: {lat: Number(latitude), lng: Number(longitude)}
        });


        if(map == 'undefined') {
            alert('map is undefined');
        }
        var labels = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';

        var infowindow = new google.maps.InfoWindow();
        var marker, i;
        var markers = locations.map(function(location, i) {

            map.zoom=10;
            marker = new google.maps.Marker({
                position: location,
                label: labels[i % labels.length],
                title: location.centroDeAtencion
            });

            google.maps.event.addListener(marker, 'click', (function(marker, i) {
                return function() {
                    infowindow.setContent('<div><strong>'+location.centroDeAtencion+'</div></strong><div>'+location.nombreDeVia+','+'</div>' );
                    $('#resultadoInformacion').empty();
                    $('#resultadoInformacion').append("<div class='card-item bg-color-blanco'>" +
                        " <div class='card-item__header'>" +
                        "<img src='<%= request.getContextPath() %>/img/computador.svg' alt='' loading='lazy'>" +
                        "<h4 class='card-item__h-principal color-titulo-primario-azul'>Direccion</h4>" +
                        "</div>" +
                        "<p class='card-item__parrafo color-parrafo-gris-3'>" + location.nombreDeVia +
                        "</p>" +
                        "</div>"+
                        "<div class='card-item bg-color-blanco'>" +
                        " <div class='card-item__header'>" +
                        "<img src='<%= request.getContextPath() %>/img/computador.svg' alt='' loading='lazy'>" +
                        "<h4 class='card-item__h-principal color-titulo-primario-azul'>Horario</h4>" +
                        "</div>" +
                        "<p class='card-item__parrafo color-parrafo-gris-3'>" + location.centroDeAtencion +
                        "</p>" +
                        "</div>"+
                        "");


                    infowindow.open(map, marker);
                }
            })(marker, i));

            return marker;
        });
        var markerCluster = new MarkerClusterer(map, markers,
            {imagePath: 'https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/m'});
    }

    function getSede() {

        if($.trim($("#distrito-input").val()).length !== 0){

            var selectdistrito = $("#distrito-input").val();
            $.ajax({
                url :"<%= getSedesURL %>",
                data:{
                    <portlet:namespace />cmd: "distrito",
                    <portlet:namespace />nombredistrito: selectdistrito
                },
                success: function(data) {
                    console.log(data.searchResults);
                    filterMapByDistrito(data.searchResults[0].codigo);
                }
            });

        } else {
            var codigoUbigeo = '';

            $('.distrito').each(function(indice, elemento) {
                if($(elemento).hasClass('selected')){
                    codigoUbigeo = $(elemento).val();
                }
            });

            if($('.distrito').hasClass('selected')){
                filterMapByDistrito(codigoUbigeo);
            }
        }
    }

    function  filterMapByDistrito(codigoUbigeo){
        var codigotramite = $('.distrito').val();

        $.ajax({
            url :"<%= getSedesURL %>",
            data:{
                <portlet:namespace />cmd: "sede",
                <portlet:namespace />codigoUbigeo: codigoUbigeo,
                <portlet:namespace />codigotramite: codigotramite
            },
            success: function(data) {
                console.log(data.searchResults);

                if(data.searchResults.length>0) {
                    var locations = [];

                    $.each( data.searchResults, function( key, value ) {
                        locations.push({
                            lat:Number(value.latitud),
                            lng:Number(value.longitud),
                            centroDeAtencion:value.centroDeAtencion,
                            nombreDeVia: value.nombreDeVia,
                            tipoDeVia: value.tipoDeVia,
                            horarioDeAtencion: value.horarioDeAtencion
                        });
                    });

                    initMap(locations[0].lat,locations[0].lng,locations);
                }

            }
        });
    }

</script>