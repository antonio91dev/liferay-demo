<%@ include file="/init.jsp" %>

<section class="container bg-color-blanco">
	<div class="r-c-mapaoficina">
		<div class="r-c-mapaoficina-header">
			<h2 class="r-c-mapaoficina__h-principal color-titulo-primario-azul">
				Contacto
			</h2>
			<h3 class="r-c-mapaoficina__h-secundario color-titulo-gris-3">
				Lorem ipsum dolor sit amet consectetur adipisicing elit. Ipsa
				voluptas vitae nam recusandae quod non magnam, velit aliquid,
				veniam quas deleniti, est tempore assumenda vero accusantium
				provident repudiandae quisquam incidunt.
			</h3>
		</div>
		<div class="r-c-mapaoficina-search">
			<div class="r-c-mapaoficina-search__input">
				<input
						type="text"
						id="distrito"
						name="distrito"
						placeholder="Ingresa el Distrito en el que te encuentras"
						aria-label="Distrito"
				/>
				<img
						src="./../img/iconos/azules/computador.svg"
						alt="Ícono de computadora"
						loading="lazy"
				/>
			</div>

			<button
					class="r-c-mapaoficina-search__button btn r-o-button r-o-button__primary"
			>
				Buscar
			</button>
		</div>

		<div class="r-c-mapaoficina-filter">
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
								src="./../img/iconos/morados/selectarrow.svg"
								alt="Ícono de computadora"
								loading="lazy"
						/>
					</div>
				</button>
				<div class="dropdown-menu" aria-labelledby="departamento">
					<button class="dropdown-item" href="#">
						Consultar el estado del trámite de DNI o DNIe
					</button>
					<button class="dropdown-item" href="#">Option 2A</button>
					<button class="dropdown-item" href="#">Option 3A</button>
				</div>
			</div>

			<div class="dropdown r-o-dropdown">
				<button
						class="btn btn-secondary dropdown-toggle"
						type="button"
						id="provincia"
						data-toggle="dropdown"
						aria-haspopup="true"
						aria-expanded="false"
				>
					<div class="text-container bg-color-blanco">
						<p class="selected-option">Provincia</p>
					</div>

					<div class="dropdown-icon bg-color-blanco">
						<img
								src="./../img/iconos/morados/selectarrow.svg"
								alt="Ícono de computadora"
								loading="lazy"
						/>
					</div>
				</button>
				<div class="dropdown-menu" aria-labelledby="provincia">
					<button class="dropdown-item" href="#">
						Consultar el estado del trámite de DNI o DNIe
					</button>
					<button class="dropdown-item" href="#">Option 2B</button>
					<button class="dropdown-item" href="#">Option 3B</button>
				</div>
			</div>

			<div class="dropdown r-o-dropdown">
				<button
						class="btn btn-secondary dropdown-toggle"
						type="button"
						id="distrito"
						data-toggle="dropdown"
						aria-haspopup="true"
						aria-expanded="false"
				>
					<div class="text-container bg-color-blanco">
						<p class="selected-option">Distrito</p>
					</div>

					<div class="dropdown-icon bg-color-blanco">
						<img
								src="./../img/iconos/morados/selectarrow.svg"
								alt="Ícono de computadora"
								loading="lazy"
						/>
					</div>
				</button>
				<div class="dropdown-menu" aria-labelledby="distrito">
					<button class="dropdown-item" href="#">
						Consultar el estado del trámite de DNI o DNIe
					</button>
					<button class="dropdown-item" href="#">Option 2B</button>
					<button class="dropdown-item" href="#">Option 3B</button>
				</div>
			</div>

			<div class="dropdown r-o-dropdown">
				<button
						class="btn btn-secondary dropdown-toggle"
						type="button"
						id="tramite"
						data-toggle="dropdown"
						aria-haspopup="true"
						aria-expanded="false"
				>
					<div class="text-container bg-color-blanco">
						<p class="selected-option">Tramite</p>
					</div>

					<div class="dropdown-icon bg-color-blanco">
						<img
								src="./../img/iconos/morados/selectarrow.svg"
								alt="Ícono de computadora"
								loading="lazy"
						/>
					</div>
				</button>
				<div class="dropdown-menu" aria-labelledby="tramite">
					<button class="dropdown-item" href="#">
						Consultar el estado del trámite de DNI o DNIe
					</button>
					<button class="dropdown-item" href="#">Option 2B</button>
					<button class="dropdown-item" href="#">Option 3B</button>
				</div>
			</div>
		</div>
		<div class="r-c-mapaoficina-cuerpo">
			<div class="r-c-mapaoficina-mapa">
				<iframe
						frameborder="0"
						style="border: 0"
						src="https://www.google.com/maps/embed/v1/place?q=YOUR_LOCATION"
						allowfullscreen
				>
				</iframe>
			</div>
			<div class="r-c-mapaoficina-cards r-j-mapaoficina-cards">
				<div class="card-item bg-color-blanco">
					<div class="card-item__header">
						<img
								src="./../img/iconos/azules/computador.svg"
								alt=""
								loading="lazy"
						/>
						<h4 class="card-item__h-principal color-titulo-primario-azul">
							Direccion
						</h4>
					</div>
					<p class="card-item__parrafo color-parrafo-gris-3">
						Encuentra rápidamente respuesta a muchas preguntas que nos
						realizan los ciudadanos.
					</p>
				</div>
				<div class="card-item bg-color-blanco">
					<div class="card-item__header">
						<img
								src="./../img/iconos/azules/computador.svg"
								alt=""
								loading="lazy"
						/>
						<h4 class="card-item__h-principal color-titulo-primario-azul">
							Direccion
						</h4>
					</div>
					<p class="card-item__parrafo color-parrafo-gris-3">
						Encuentra rápidamente respuesta a muchas preguntas que nos
						realizan los ciudadanos.
					</p>
				</div>
				<div class="card-item bg-color-blanco">
					<div class="card-item__header">
						<img
								src="./../img/iconos/azules/computador.svg"
								alt=""
								loading="lazy"
						/>
						<h4 class="card-item__h-principal color-titulo-primario-azul">
							Direccion
						</h4>
					</div>
					<p class="card-item__parrafo color-parrafo-gris-3">
						Encuentra rápidamente respuesta a muchas preguntas que nos
						realizan los ciudadanos.
					</p>
				</div>
			</div>
		</div>
	</div>
</section>


<script>
	function <portlet:namespace/>getCargaInitial(){


	}

	function <portlet:namespace/>getCargaBUscar(){


	}

	function <portlet:namespace/>getDepartamento(){


	}

</script>