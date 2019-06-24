package ar.gob.hcdn.ticket.controller;

import ar.gob.hcdn.ticket.common.HcdnResponse;
import ar.gob.hcdn.ticket.dto.*;
import ar.gob.hcdn.ticket.service.EscapifyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("services")
@Api(tags = "services")
public class TicketController {

	@Autowired
	private EscapifyService escapifyService;

	private static Logger logger = Logger.getLogger(TicketController.class.getSimpleName());

	@GetMapping("users/{userID}")
	@ApiOperation(value = "Devuelve los datos del usuario ", notes = "Conecta al CRM para pedir los datos del usuario")
	public ResponseEntity<HcdnResponse<UserDTO>> findUser(@PathVariable("userID") String userID,
			@RequestHeader(required = false, name = "Mail-Auth") String auth) {

			HcdnResponse<UserDTO> response = new HcdnResponse<UserDTO>();
			UserDTO userDTO = new UserDTO();
			userDTO.setNombre("DUMMY");
			userDTO.setNombreDominio("DUMMY");
			userDTO.setUserID("1");
			response.setData(userDTO);
			return new ResponseEntity<HcdnResponse<UserDTO>>(response, HttpStatus.OK);

	}

	@GetMapping("poblar")
	public ResponseEntity<HcdnResponse<String>> poblar(){
		HcdnResponse<String> response = new HcdnResponse<>();
		response.setData(escapifyService.poblar());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("escapistas")
	public ResponseEntity<HcdnResponse<List<EscapistaDTO>>> getEscapistas(){
		HcdnResponse<List<EscapistaDTO>> response = new HcdnResponse<>();
		response.setData(escapifyService.getEscapistas());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/escapista")
	public ResponseEntity<HcdnResponse<EscapistaDTO>> crearEscapista(@RequestBody AddEscapistaDTO addEscapistaDTO){
		HcdnResponse<EscapistaDTO> response = new HcdnResponse<>();
		response.setData(escapifyService.crearEscapista(addEscapistaDTO));
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

    @PostMapping("/escapista/{usuario}/equipo")
    public ResponseEntity<HcdnResponse<EscapistaDTO>> crearEquipo(@PathVariable("usuario") String usuario, @RequestBody AddEquipoDTO addEquipoDTO){
        HcdnResponse<EscapistaDTO> response = new HcdnResponse<>();
        response.setData(escapifyService.crearEquipo(usuario,addEquipoDTO));
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

	@PostMapping("/escapista/{usuario}/preferencias")
	public ResponseEntity<HcdnResponse<EscapistaDTO>> crearEscapista(@PathVariable("usuario") String usuario,@RequestBody UpdatePreferenciasDTO updatePreferenciasDTO){
		HcdnResponse<EscapistaDTO> response = new HcdnResponse<>();
		response.setData(escapifyService.updatePreferencias(usuario,updatePreferenciasDTO));
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@GetMapping("escapista/{userID}")
	public ResponseEntity<HcdnResponse<EscapistaDTO>> getEscapista(@PathVariable("userID") Long userID){
		HcdnResponse<EscapistaDTO> response = new HcdnResponse<>();
		response.setData(escapifyService.getEscapista(userID));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("equipo/{equipoID}/sala/buscar")
	public ResponseEntity<HcdnResponse<BusquedaEquipoSalaDTO>> buscarSala(@PathVariable("equipoID") Long equipoID){
		HcdnResponse<BusquedaEquipoSalaDTO> response = new HcdnResponse<>();
		response.setData(escapifyService.buscarSala(equipoID));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}



}
