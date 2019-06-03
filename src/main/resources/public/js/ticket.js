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
				function($scope, $http,$mdSidenav) {

					$scope.idEscapista = {
						selected : "None"
					};
					$scope.escapistas = [];
					$scope.escapista = {};
					$scope.escapistaDetalle = {};
					$scope.isEscapistaDetalle = {
						show : false
					};

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

				});