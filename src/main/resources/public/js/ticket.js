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
							console.log('You decided to name your dog ' + result + '.');
						}, function() {
							console.log('You didn\'t name your dog.');
						});

						console.log("crear equipo")
					};

					$scope.unirseEquipo = function(ev){
						var confirm = $mdDialog.prompt()
							.title('Ingrese el nombre de su equipo')
							.textContent('Maximo 10 caracteres sin espacios')
							.placeholder('Nombre del equipo')
							.ariaLabel('Nombre del equipo')
							.initialValue('')
							.targetEvent(ev)
							.required(true)
							.ok('Siguiente')
							.cancel('Cancelar');

						$mdDialog.show(confirm).then(function(result) {
							console.log('You decided to name your dog ' + result + '.');


							var confirm = $mdDialog.prompt()
								.title('Ingrese el c&oacute;digo secreto de su equipo')
								.textContent('Maximo 10 caracteres sin espacios')
								.placeholder('Codigo del equipo')
								.ariaLabel('Codigo del equipo')
								.initialValue('')
								.targetEvent(ev)
								.required(true)
								.ok('Unirse al equipo')
								.cancel('Cancelar');

							$mdDialog.show(confirm).then(function(result2) {
								console.log('FINAL You decided to name your dog ' + result + "-"+ result2 + '.');
							}, function() {
								console.log('FINAL You didn\'t name your dog.');
							});



						}, function() {
							console.log('You didn\'t name your dog.');
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

					$scope.salasParaEquipo =  function(idEquipo){
						console.log("/services/equipo/"+idEquipo+"/sala/buscar");
						if(idEquipo != null){
							$http(
								{
									method : 'GET',
									url : '/services/equipo/'+idEquipo+'/sala/buscar',
								})
								.then(
									function(response, status, headers,
											 config) {

										$scope.busquedaSalas = response.data.data;
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