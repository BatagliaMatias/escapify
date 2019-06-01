package ar.gob.hcdn.ticket.dto;

import java.util.List;

public class EscapistaDTO {
    private String nombre;
    private String usuario;
    private List<EquipoDTO> equipos;
    private List<SalaDTO> salas;
    private PreferenciaDTO preferencia;
    private Long id;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<EquipoDTO> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<EquipoDTO> equipos) {
        this.equipos = equipos;
    }

    public List<SalaDTO> getSalas() {
        return salas;
    }

    public void setSalas(List<SalaDTO> salas) {
        this.salas = salas;
    }

    public PreferenciaDTO getPreferencia() {
        return preferencia;
    }

    public void setPreferencia(PreferenciaDTO preferencia) {
        this.preferencia = preferencia;
    }
}
