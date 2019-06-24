package ar.gob.hcdn.ticket.domain;

import com.google.common.collect.Lists;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="Equipo")
public class Equipo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="dbId")
    private Long pk;
    private String nombre;
    @ManyToMany(cascade =  CascadeType.PERSIST, mappedBy = "equipos")
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

    public List<Sala> getSalasJugadas() {
        Set<Sala> setSalasEquipo = new HashSet<>();
        for(Escapista escapista: escapistas){
            setSalasEquipo.addAll(escapista.getSalas());
        }
        return Lists.newArrayList(setSalasEquipo);
    }

    public double[] getVectorPreferencias() {
        Preferencia preferencia = new Preferencia();
        for(Escapista escapista : escapistas){
            preferencia.add(escapista.getPreferencia());
        }
        double[] preferencias = preferencia.getVectorDouble();
        for (int i=0; i<preferencias.length; ++i){
            preferencias[i] = preferencias[i] / escapistas.size();
        }
        return preferencias;
    }

    public String getCodigo(){
        return String.format("%05d",pk);
    }
}
