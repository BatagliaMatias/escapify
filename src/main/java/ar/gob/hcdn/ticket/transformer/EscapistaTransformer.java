package ar.gob.hcdn.ticket.transformer;

import ar.gob.hcdn.ticket.domain.Equipo;
import ar.gob.hcdn.ticket.domain.Escapista;
import ar.gob.hcdn.ticket.domain.Sala;
import ar.gob.hcdn.ticket.dto.EquipoDTO;
import ar.gob.hcdn.ticket.dto.EscapistaDTO;
import ar.gob.hcdn.ticket.dto.PreferenciaDTO;
import ar.gob.hcdn.ticket.dto.SalaDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class EscapistaTransformer {
    public EscapistaDTO transform(Escapista escapista){
        EscapistaDTO escapistaDTO = new EscapistaDTO();
        escapistaDTO.setNombre(escapista.getNombre());
        escapistaDTO.setUsuario(escapista.getUsuario());
        escapistaDTO.setId(escapista.getPk());
        return escapistaDTO;
    }

    public EscapistaDTO transformDetallado(Escapista escapista) {
        EscapistaDTO escapistaDTO = new EscapistaDTO();

        escapistaDTO.setUsuario(escapista.getUsuario());
        escapistaDTO.setNombre(escapista.getNombre());
        escapistaDTO.setId(escapista.getPk());

        escapistaDTO.setEquipos(new ArrayList<>());
        for(Equipo equipo : escapista.getEquipos()){
            EquipoDTO equipoDTO = new EquipoDTO();
            equipoDTO.setId(equipo.getPk());
            equipoDTO.setNombre(equipo.getNombre());
            equipoDTO.setIntegrantes(new ArrayList<>());
            for(Escapista escapistaEquipo : equipo.getEscapistas()){
                equipoDTO.getIntegrantes().add(escapistaEquipo.getNombre());
            }
            escapistaDTO.getEquipos().add(equipoDTO);
        }

        escapistaDTO.setSalas(new ArrayList<>());
        for(Sala sala : escapista.getSalas()){
            SalaDTO salaDTO = new SalaDTO();
            salaDTO.setId(sala.getPk());
            salaDTO.setNombre(sala.getNombre());

            PreferenciaDTO salaPreferenciaDTO = new PreferenciaDTO();
            salaPreferenciaDTO.setAventura(sala.getPreferencia().getAventura());
            salaPreferenciaDTO.setDificultad(sala.getPreferencia().getDificultad());
            salaPreferenciaDTO.setTerror(sala.getPreferencia().getTerror());

            salaDTO.setPreferencia(salaPreferenciaDTO);

            escapistaDTO.getSalas().add(salaDTO);
        }

        PreferenciaDTO preferenciaDTO = new PreferenciaDTO();
        preferenciaDTO.setTerror(escapista.getPreferencia().getTerror());
        preferenciaDTO.setDificultad(escapista.getPreferencia().getDificultad());
        preferenciaDTO.setAventura(escapista.getPreferencia().getAventura());

        escapistaDTO.setPreferencia(preferenciaDTO);

        return escapistaDTO;
    }
}
