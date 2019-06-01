package ar.gob.hcdn.ticket.domain;

import javax.persistence.*;

@Entity
@Table(name="Sala")
public class Sala {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="dbId")
    private Long pk;
    private String nombre;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Preferencia preferencia = new Preferencia();

    public Long getPk() {
        return pk;
    }

    public void setPk(Long pk) {
        this.pk = pk;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Preferencia getPreferencia() {
        return preferencia;
    }

    public void setPreferencia(Preferencia preferencia) {
        this.preferencia = preferencia;
    }
}
