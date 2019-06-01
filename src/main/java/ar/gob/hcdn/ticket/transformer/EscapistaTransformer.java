package ar.gob.hcdn.ticket.transformer;

import ar.gob.hcdn.ticket.domain.Escapista;
import ar.gob.hcdn.ticket.dto.EscapistaDTO;
import org.springframework.stereotype.Service;

@Service
public class EscapistaTransformer {
    public EscapistaDTO transform(Escapista escapista){
        EscapistaDTO escapistaDTO = new EscapistaDTO();
        escapistaDTO.setNombre(escapista.getNombre());
        escapistaDTO.setUsuario(escapista.getUsuario());
        return escapistaDTO;
    }
}
