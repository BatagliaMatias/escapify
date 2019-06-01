package ar.gob.hcdn.ticket.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Escapista")
public class Escapista {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="dbId")
    private Long pk;
    private String nombre;
    private String usuario;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Preferencia preferencia = new Preferencia();
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Sala> salas = new ArrayList<>();
    @ManyToMany(cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "escapistas")
    private List<Equipo> equipos = new ArrayList<>();

    public Escapista() {
    }

    public void agregarEquipo(Equipo equipo){
        equipos.add(equipo);
    }

    public void agregarSala(Sala sala){
        salas.add(sala);
    }

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

    public Long getPk() {
        return pk;
    }

    public void setPk(Long pk) {
        this.pk = pk;
    }

    public List<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<Equipo> equipos) {
        this.equipos = equipos;
    }

    public Preferencia getPreferencia() {
        return preferencia;
    }

    public void setPreferencia(Preferencia preferencia) {
        this.preferencia = preferencia;
    }

    public List<Sala> getSalas() {
        return salas;
    }

    public void setSalas(List<Sala> salas) {
        this.salas = salas;
    }
}
