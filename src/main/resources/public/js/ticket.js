angular
		.module('ticket', ['ngMaterial', 'ngMessages'])
		.controller(
				'CargaTickets',
				function($scope, $http) {
					$scope.idTicket = {
						selected : "None"
					};

					$scope.idEscapista = {
						selected : "None"
					};
					$scope.escapistas = [];
					$scope.escapista = {};
					$scope.tickets = [];
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
					$scope.username = "manastasio";

					if ($scope.username != null && $scope.username != "") {
					//	obtenerUsuario();
					}


					$scope.cargarActivoEnTicket = function() {
						if ($scope.idTicket.selected != "None") {
							$scope.inputcode.show = true;
							$scope.error.show = false;
							$scope.ok.show = false;
						} else {
							$scope.inputcode.show = false;
						}
					};

					$scope.cargarActivo = function() {
						var jsonToSave = {
							ticketID : '"' + $scope.idTicket.selected + '"',
							activoNro : '"' + $scope.activoNro + '"'
						};

						$http({
							method : 'POST',
							url : 'http://srv-apache-desa.hcdn.gob.ar:9093/services/activo',
							data : jsonToSave
						})
								.then(
										function(response, status, headers,
												config) { // Para convertir el
															// json a string
															// angular.toJson(jsonToSave,
															// false);
											$scope.error.show = false;
											$scope.ok.show = true;
											$scope.ok.message = ""
													+ response.data + " ["
													+ status + "]";
											// $scope.inputcode.show=false;
										},
										function(response, status, headers,
												config) {
											$scope.error.show = true;
											$scope.ok.show = false;
											$scope.error.message = response.error.codigo
													+ "-"
													+ response.error.descCorta
													+ " [" + status + "]";
										});
					};

					$scope.checkCodigoActivo = function() {
						return $scope.activoNro.trim().length != 9;
					};

					$scope.cargarDatosEscapista = function() {
						console.log($scope.escapista.id)
					};

					$scope.cargarEscapistas = function() {
						console.log('/services/escapistas');
						$http(
							{
								method : 'GET',
								url : '/services/escapistas',
							})
							.then(
								function(response, status, headers,
										 config) {

									$scope.escapista.length = 0;
									angular.forEach(response.data.data,
										function(row) {
											$scope.escapistas
												.push(row);
										});
									console.log($scope.escapistas);

								},function(response, status, headers,
										 config) {
									$scope.error.show = true;
									$scope.error.message = "Se produjo un error obteniendo los escapistas";
							});
					};
					function obtenerTickets() {
						$http({
									method : 'GET',
									url : 'http://srv-apache-desa.hcdn.gob.ar:9093/services/'
											+ $scope.user.id + '/tickets',
						}).then(function(response, status, headers,
												config) {
											angular.forEach(response.data,
													function(row) {
														$scope.tickets
																.push(row);
													});
								},function(response, status, headers,
												config) {
											$scope.error.show = true;
											$scope.error.message = "Se produjo un error obteniendo los tickets";
						});
					}

				});