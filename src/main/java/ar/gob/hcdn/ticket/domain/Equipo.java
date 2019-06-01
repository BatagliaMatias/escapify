package ar.gob.hcdn.ticket.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Equipo")
public class Equipo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="dbId")
    private Long pk;
    private String nombre;
    @ManyToMany(cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "equipo_escapistas",
            joinColumns = { @JoinColumn(name = "equipo_id") },
            inverseJoinColumns = { @JoinColumn(name = "escapista_id") })
    private List<Escapista> escapistas = new ArrayList<>();

    public List<Escapista> getEscapistas() {
        return escapistas;
    }

    public void setEscapistas(List<Escapista> escapistas) {
        this.escapistas = escapistas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getPk() {
        return pk;
    }

    public void setPk(Long pk) {
        this.pk = pk;
    }
}
