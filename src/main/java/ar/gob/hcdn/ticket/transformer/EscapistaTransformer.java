package ar.gob.hcdn.ticket.transformer;

import ar.gob.hcdn.ticket.domain.Equipo;
import ar.gob.hcdn.ticket.domain.Escapista;
import ar.gob.hcdn.ticket.domain.Preferencia;
import ar.gob.hcdn.ticket.domain.Sala;
import ar.gob.hcdn.ticket.dto.EquipoDTO;
import ar.gob.hcdn.ticket.dto.EscapistaDTO;
import ar.gob.hcdn.ticket.dto.PreferenciaDTO;
import ar.gob.hcdn.ticket.dto.SalaDTO;
import org.apache.commons.math3.ml.distance.EuclideanDistance;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class EscapistaTransformer {
    private EuclideanDistance euclideanDistance = new EuclideanDistance();
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
            equipoDTO.setCodigo(equipo.getCodigo());
            for(Escapista escapistaEquipo : equipo.getEscapistas()){
                equipoDTO.getIntegrantes().add(escapistaEquipo.getNombre());
            }
            if(equipo.getEscapistas().isEmpty()){
                equipoDTO.getIntegrantes().add(escapista.getNombre());
            }
            escapistaDTO.getEquipos().add(equipoDTO);
        }

        escapistaDTO.setSalas(new ArrayList<>());
        for(Sala sala : escapista.getSalas()){
            SalaDTO salaDTO = getSalaDTO(sala);

            escapistaDTO.getSalas().add(salaDTO);
        }

        PreferenciaDTO preferenciaDTO = new PreferenciaDTO();
        preferenciaDTO.setTerror(escapista.getPreferencia().getTerror());
        preferenciaDTO.setDificultad(escapista.getPreferencia().getDificultad());
        preferenciaDTO.setAventura(escapista.getPreferencia().getAventura());

        escapistaDTO.setPreferencia(preferenciaDTO);

        return escapistaDTO;
    }

    public SalaDTO getSalaDTO(Sala sala) {
        SalaDTO salaDTO = new SalaDTO();
        salaDTO.setId(sala.getPk());
        salaDTO.setNombre(sala.getNombre());

        PreferenciaDTO salaPreferenciaDTO = new PreferenciaDTO();
        salaPreferenciaDTO.setAventura(sala.getPreferencia().getAventura());
        salaPreferenciaDTO.setDificultad(sala.getPreferencia().getDificultad());
        salaPreferenciaDTO.setTerror(sala.getPreferencia().getTerror());

        salaDTO.setPreferencia(salaPreferenciaDTO);
        return salaDTO;
    }

    public PreferenciasEquipoDTO transform(double[] preferenciasEquipo) {
        PreferenciasEquipoDTO preferenciasEquipoDTO = new PreferenciasEquipoDTO();
        preferenciasEquipoDTO.setAventura(preferenciasEquipo[Preferencia.POS_AVENTURA]);
        preferenciasEquipoDTO.setDificultad(preferenciasEquipo[Preferencia.POS_DIFICULTAD]);
        preferenciasEquipoDTO.setTerror(preferenciasEquipo[Preferencia.POS_TERROR]);
        return preferenciasEquipoDTO;
    }

    public SalaDTO getSalaDTO(Sala sala, double[] preferenciasEquipo) {
        SalaDTO salaDTO = getSalaDTO(sala);
        double[] salaValores = sala.getPreferencia().getVectorDouble();
        salaDTO.setPuntaje(euclideanDistance.compute(salaValores,preferenciasEquipo));
        return salaDTO;
    }
}
