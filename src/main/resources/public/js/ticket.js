angular
		.module('ticket', [])
		.controller(
				'CargaTickets',
				function($scope, $http) {
					$scope.idTicket = {
						selected : "None"
					};
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
						obtenerUsuario();
					}

					cargarEscapistas();

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
								.success(
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
										})
								.error(
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

					function cargarEscapistas() {
						console.log('/services/escapistas');
						$http(
							{
								method : 'GET',
								url : '/services/escapistas',
							})
							.success(
								function(response, status, headers,
										 config) {
									$scope.escapistas = response.data;
								})
							.error(
								function(response, status, headers,
										 config) {
									$scope.error.show = true;
									$scope.error.message = "Se produjo un error obteniendo los escapistas";
								});
					}

					function obtenerUsuario() {
						console.log('http://srv-apache-desa.hcdn.gob.ar:9093/services/users/'
								+ $scope.username);
						$http(
								{
									method : 'GET',
									url : '/services/users/1'
											+ $scope.username,
								})
								.success(
										function(response, status, headers,
												config) {
											$scope.user.id = response.data.userID;
											$scope.user.nombre = response.data.nombre;
											obtenerTickets();
										})
								.error(
										function(response, status, headers,
												config) {
											$scope.error.show = true;
											$scope.error.message = "Se produjo un error obteniendo los datos de usuario";
										});

					}

					function obtenerTickets() {
						$http(
								{
									method : 'GET',
									url : 'http://srv-apache-desa.hcdn.gob.ar:9093/services/'
											+ $scope.user.id + '/tickets',
								})
								.success(
										function(response, status, headers,
												config) {
											angular.forEach(response.data,
													function(row) {
														$scope.tickets
																.push(row);
													});
										})
								.error(
										function(response, status, headers,
												config) {
											$scope.error.show = true;
											$scope.error.message = "Se produjo un error obteniendo los tickets";
										});
					}

				});