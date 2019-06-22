package ar.gob.hcdn.ticket.service;

import ar.gob.hcdn.ticket.dao.EscapifyDAO;
import ar.gob.hcdn.ticket.domain.Equipo;
import ar.gob.hcdn.ticket.domain.Escapista;
import ar.gob.hcdn.ticket.domain.Sala;
import ar.gob.hcdn.ticket.dto.AddEscapistaDTO;
import ar.gob.hcdn.ticket.dto.BusquedaEquipoSalaDTO;
import ar.gob.hcdn.ticket.dto.EscapistaDTO;
import ar.gob.hcdn.ticket.dto.SalaDTO;
import ar.gob.hcdn.ticket.transformer.EscapistaTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EscapifyService {
    @Autowired
    private EscapifyDAO escapifyDAO;

    @Autowired
    private EscapistaTransformer escapistaTransformer;

    public String poblar(){
        return escapifyDAO.poblar();
    }

    public List<EscapistaDTO> getEscapistas() {
        List<EscapistaDTO> dtoList = new ArrayList<>();
        for(Escapista escapista : escapifyDAO.getEscapistas()){
            dtoList.add(escapistaTransformer.transform(escapista));
        }
        return dtoList;
    }

    public EscapistaDTO getEscapista(Long userID) {
        return escapistaTransformer.transformDetallado(escapifyDAO.findEscapistaById(userID));
    }

    public BusquedaEquipoSalaDTO buscarSala(Long equipoID) {
        Equipo equipo = escapifyDAO.findEquipoById(equipoID);
        double[] preferenciasEquipo = equipo.getVectorPreferencias();
        List<Sala> salas = escapifyDAO.getSalas();
        List<Sala> salasJugadas = equipo.getSalasJugadas();


        List<Sala> salasFiltradas = salas.stream()
                .filter(sala -> !salasJugadas.contains(sala))
                .collect(Collectors.toList());

        List<SalaDTO> salasJugadasDTO = new ArrayList<>();
        List<SalaDTO> salasDTO = new ArrayList<>();

        for(Sala sala : salasFiltradas){
            salasDTO.add(escapistaTransformer.getSalaDTO(sala,preferenciasEquipo));
        }

        for(Sala sala : salasJugadas){
            salasJugadasDTO.add(escapistaTransformer.getSalaDTO(sala));
        }

        salasDTO.sort(Comparator.comparing(sala -> sala.getNombre()));
        salasDTO.sort(Comparator.comparingDouble(sala -> sala.getPuntaje()));
        salasJugadasDTO.sort(Comparator.comparing(sala -> sala.getNombre()));

        BusquedaEquipoSalaDTO res = new BusquedaEquipoSalaDTO();
        res.setSalasJugadas(salasJugadasDTO);
        res.setSalasRanking(salasDTO);
        res.setEquipoPreferencias(escapistaTransformer.transform(preferenciasEquipo));

        return res;
    }

    public EscapistaDTO crearEscapista(AddEscapistaDTO addEscapistaDTO) {
        Escapista escapista = escapifyDAO.findEscapistaByUsuario(addEscapistaDTO.getUsuario());
        return escapistaTransformer.transform(escapista);
    }


}
