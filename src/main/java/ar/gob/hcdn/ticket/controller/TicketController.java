package ar.gob.hcdn.ticket.controller;

import ar.gob.hcdn.ticket.common.HcdnResponse;
import ar.gob.hcdn.ticket.dto.UserDTO;
import ar.gob.hcdn.ticket.service.EscapifyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

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




}
