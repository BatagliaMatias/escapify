angular
		.module('ticket', ['ngMaterial', 'ngMessages'])
		.config(function($mdThemingProvider) {
			$mdThemingProvider.theme('default')
				.primaryPalette('orange')
				.accentPalette('deep-orange');

			$mdThemingProvider.enableBrowserColor({
				theme: 'default',
				palette: 'orange',      // <-- Note this, you have to use a predefined palette name
				hue: '800'
			});

		})
		.controller(
				'CargaTickets',
				function($scope, $http,$mdSidenav,$mdDialog) {

					$scope.idEscapista = {
						selected : "None"
					};
					$scope.escapistas = [];
					$scope.escapista = {};
					$scope.escapistaDetalle = {
						preferencia : {
							terror: 1,
							dificultad: 1,
							aventura: 1
						}
					};
					$scope.isEscapistaDetalle = {
						show : false
					};
					$scope.edicionParametrosEscapista = false;
					$scope.busquedaSalas={};

					$scope.tabSelectedIndex = 0;
					$scope.mostrarSalas = false;
					$scope.error = {
						show : false,
						message : ""
					};
					$scope.ok = {
						show : false,
						message : ""
					};
					$scope.inputcode = {
						show : false
					};
					$scope.activoNro = "";
					$scope.user = {
						id : "",
						nombre : ""
					};

					$scope.crearEquipo = function(ev){
						var confirm = $mdDialog.prompt()
							.title('Ingrese el nombre de su equipo.')
							.textContent('Maximo 10 caracteres sin espacios')
							.placeholder('Nombre del equipo')
							.ariaLabel('Nombre del equipo')
							.initialValue('')
							.targetEvent(ev)
							.required(true)
							.ok('Crear equipo')
							.cancel('Cancelar');

						$mdDialog.show(confirm).then(function(result) {
							console.log("/services/escapista/" + $scope.escapistaDetalle.usuario + "/equipo" );
							var crearEquipoData = {
								nombre : result
							};
							$http(
								{
									method : 'POST',
									url : "/services/escapista/"+ $scope.escapistaDetalle.usuario + "/equipo",
									data: crearEquipoData,
								})
								.then(
									function(response, status, headers,
											 config) {

										$scope.escapistaDetalle = response.data.data;

									});

						}, function() {

						});


					};

					$scope.unirseEquipo = function(ev){
							var confirm = $mdDialog.prompt()
								.title('Ingrese el codigo de su equipo')
								.placeholder('Codigo del equipo')
								.ariaLabel('Codigo del equipo')
								.initialValue('')
								.targetEvent(ev)
								.required(true)
								.ok('Unirse al equipo')
								.cancel('Cancelar');

							$mdDialog.show(confirm).then(function(result) {
							    var unirEquipoData = {
							        codigo : result
                                };
							    console.log("/services/escapista/"+ $scope.escapistaDetalle.usuario + "/equipo/unirse");
                                $http(
                                    {
                                        method : 'POST',
                                        url : "/services/escapista/"+ $scope.escapistaDetalle.usuario + "/equipo/unirse",
                                        data: unirEquipoData,
                                    })
                                    .then(
                                        function(response, status, headers,
                                                 config) {

                                            $scope.escapistaDetalle = response.data.data;

                                        },function(response, status, headers,
                                                   config) {
                                           alert("Codigo incorrecto");
                                        });
							}, function() {

							});




						console.log("unirse equipo")
					};

					$scope.iniciarEdicionPreferencias = function(){
						$scope.edicionParametrosEscapista = true;
					};

					$scope.guardarEdicionPreferencias = function(){
						console.log("/services/escapista/"+ $scope.escapistaDetalle.usuario + "/preferencias");

						var preferenciasEscapistaEdicion = {
							"dificultad" : $scope.escapistaDetalle.preferencia.dificultad,
							"terror" : $scope.escapistaDetalle.preferencia.terror,
							"aventura" : $scope.escapistaDetalle.preferencia.aventura
						};

						$http(
							{
								method : 'POST',
								url : "/services/escapista/"+ $scope.escapistaDetalle.usuario + "/preferencias",
								data: preferenciasEscapistaEdicion,
							})
							.then(
								function(response, status, headers,
										 config) {
									$scope.edicionParametrosEscapista = false;
									$scope.escapistaDetalle = response.data.data;

								});




					};

					$scope.salaJugada = function(item){
						console.log("/services/escapista/"+ $scope.escapistaDetalle.usuario + "/sala/" + item.id);
						$http(
							{
								method : 'POST',
								url : "/services/escapista/"+ $scope.escapistaDetalle.usuario + "/sala/" + item.id

							})
							.then(
								function(response, status, headers,
										 config) {

									$scope.escapistaDetalle = response.data.data;
									$scope.salasParaEquipo($scope.equipoActual);

								});
                    };

					$scope.equipoActual = 0;

					$scope.salasParaEquipo =  function(idEquipo){
						console.log("/services/equipo/"+idEquipo+"/sala/buscar");
						if(idEquipo != null){
							$scope.equipoActual = idEquipo;
							$http(
								{
									method : 'GET',
									url : '/services/equipo/'+idEquipo+'/sala/buscar',
								})
								.then(
									function(response, status, headers,
											 config) {

										$scope.busquedaSalas = response.data.data;
										$scope.busquedaSalas.equipoPreferencias.dificultad = parseFloat($scope.busquedaSalas.equipoPreferencias.dificultad.toFixed(2));
										$scope.busquedaSalas.equipoPreferencias.aventura = parseFloat($scope.busquedaSalas.equipoPreferencias.aventura.toFixed(2));
										$scope.busquedaSalas.equipoPreferencias.terror = parseFloat($scope.busquedaSalas.equipoPreferencias.terror.toFixed(2));
										$scope.mostrarSalas = true;
										$scope.tabSelectedIndex = 2;

									},function(response, status, headers,
											   config) {
										$scope.error.show = true;
										$scope.error.message = "Se produjo un error obteniendo datos del escapista";
									});
						}

					};

					$scope.verEquipos = function(){
						$scope.tabSelectedIndex = 1;
					};

					$scope.cargarDatosEscapista = function() {
						console.log("/services/escapista/" + $scope.escapista.id);
						if($scope.escapista.id != null){
							$http(
								{
									method : 'GET',
									url : '/services/escapista/' + $scope.escapista.id,
								})
								.then(
									function(response, status, headers,
											 config) {

										$scope.escapistaDetalle = response.data.data;
										$scope.isEscapistaDetalle.show = true;
										console.log($scope.escapistaDetalle)

									},function(response, status, headers,
											   config) {
										$scope.error.show = true;
										$scope.error.message = "Se produjo un error obteniendo datos del escapista";
									});
						}
					};

					$scope.escapistasCargados = false;
					$scope.cargarEscapistas = function() {
						if(!$scope.escapistasCargados){
							console.log('/services/escapistas');
							$http(
								{
									method : 'GET',
									url : '/services/escapistas',
								})
								.then(
									function(response, status, headers,
											 config) {

										angular.forEach(response.data.data,
											function(row) {
												$scope.escapistas
													.push(row);
											});
										console.log($scope.escapistas);
										$scope.escapistasCargados = true;

									},function(response, status, headers,
											   config) {
										$scope.error.show = true;
										$scope.error.message = "Se produjo un error obteniendo los escapistas";
									});
						}

					};

					$scope.historial = false;
					$scope.verHistorial = function(valor){
						$scope.historial = valor;
					};

					$scope.toggleRight = buildToggler('right');

					function buildToggler(componentId) {
						return function() {
							$mdSidenav(componentId).toggle();
						};
					}

					function onSignIn(googleUser) {
						var profile = googleUser.getBasicProfile();
						console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
						console.log('Name: ' + profile.getName());
						console.log('Image URL: ' + profile.getImageUrl());
						console.log('Email: ' + profile.getEmail()); // This is null if the 'email' scope is not present.


						console.log("/services/escapista/");
						var dataEscapistaGoogle = {
							"nombre" : profile.getName(),
							"usuario" : profile.getEmail()
						};
						console.log(dataEscapistaGoogle);

						$http(
							{
								method : 'POST',
								url : '/services/escapista/',
								data: dataEscapistaGoogle,
							})
							.then(
								function(response, status, headers,
										 config) {

									$scope.escapistaDetalle = response.data.data;
									$scope.isEscapistaDetalle.show = true;
									console.log($scope.escapistaDetalle)

								},function(response, status, headers,
										   config) {
									$scope.error.show = true;
									$scope.error.message = "Se produjo un error obteniendo datos del escapista";
								});



						$scope.isEscapistaDetalle.show = true;
						$scope.$apply();
					}

					window.onSignIn = onSignIn;

					function signOut() {
						var auth2 = gapi.auth2.getAuthInstance();
						auth2.signOut().then(function () {
							console.log('User signed out.');
						});
						$scope.isEscapistaDetalle.show = false;
						$scope.$apply();
					}

					window.signOut = signOut;

				});