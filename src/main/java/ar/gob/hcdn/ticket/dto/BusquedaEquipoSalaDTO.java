package ar.gob.hcdn.ticket.dto;

import java.util.List;

public class BusquedaEquipoSalaDTO {
    private List<SalaDTO> salasJugadas;
    private List<SalaDTO> salasRanking;

    public List<SalaDTO> getSalasJugadas() {
        return salasJugadas;
    }

    public void setSalasJugadas(List<SalaDTO> salasJugadas) {
        this.salasJugadas = salasJugadas;
    }

    public List<SalaDTO> getSalasRanking() {
        return salasRanking;
    }

    public void setSalasRanking(List<SalaDTO> salasRanking) {
        this.salasRanking = salasRanking;
    }
}
