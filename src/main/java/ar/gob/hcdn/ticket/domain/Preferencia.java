package ar.gob.hcdn.ticket.domain;

import javax.persistence.*;

@Entity
@Table(name="Equipo")
public class Preferencia {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="dbId")
    private Long pk;
    private int terror = 0;
    private int aventura = 0;
    private int dificultad = 0;


    public int getTerror() {
        return terror;
    }

    public void setTerror(int terror) {
        this.terror = terror;
    }

    public int getAventura() {
        return aventura;
    }

    public void setAventura(int aventura) {
        this.aventura = aventura;
    }

    public int getDificultad() {
        return dificultad;
    }

    public void setDificultad(int dificultad) {
        this.dificultad = dificultad;
    }
}
