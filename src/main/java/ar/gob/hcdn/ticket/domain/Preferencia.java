package ar.gob.hcdn.ticket.domain;

import javax.persistence.*;

@Entity
@Table(name="Preferencia")
public class Preferencia {
    public static final int CANT_PREFERENCIAS = 3;
    public static final int POS_TERROR = 0;
    public static final int POS_AVENTURA = 1;
    public static final int POS_DIFICULTAD = 2;
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

    public void add(Preferencia preferencia){
        this.terror += preferencia.getTerror();
        this.aventura += preferencia.getAventura();
        this.dificultad += preferencia.getDificultad();
    }

    public double[] getVectorDouble() {
        double[] res = new double[CANT_PREFERENCIAS];
        res[POS_TERROR] = terror;
        res[POS_AVENTURA] = aventura;
        res[POS_DIFICULTAD] = dificultad;
        return res;
    }
}
