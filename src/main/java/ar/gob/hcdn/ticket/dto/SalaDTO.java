package ar.gob.hcdn.ticket.dto;

public class SalaDTO {
    private String nombre;
    private Long id;
    private PreferenciaDTO preferencia;
    private double puntaje = 0;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PreferenciaDTO getPreferencia() {
        return preferencia;
    }

    public void setPreferencia(PreferenciaDTO preferencia) {
        this.preferencia = preferencia;
    }

    public double getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(double puntaje) {
        this.puntaje = puntaje;
    }
}
