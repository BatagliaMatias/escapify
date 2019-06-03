package ar.gob.hcdn.ticket.dao;

import ar.gob.hcdn.ticket.domain.Equipo;
import ar.gob.hcdn.ticket.domain.Escapista;
import ar.gob.hcdn.ticket.domain.Sala;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


@Transactional
@Repository
public class EscapifyDAO {
    @Autowired
    private EntityManager persistenceService;

    public String poblar(){
        Sala salaTerror = new Sala();
        salaTerror.setNombre("Juego del miedo");
        salaTerror.getPreferencia().setAventura(1);
        salaTerror.getPreferencia().setTerror(10);
        salaTerror.getPreferencia().setDificultad(5);
        persist(salaTerror);

        Sala salaAventura = new Sala();
        salaAventura.setNombre("Maxima velocidad");
        salaAventura.getPreferencia().setAventura(10);
        salaAventura.getPreferencia().setTerror(1);
        salaAventura.getPreferencia().setDificultad(5);
        persist(salaAventura);

        Sala salaTerrorDificil = new Sala();
        salaTerrorDificil.setNombre("El rito");
        salaTerrorDificil.getPreferencia().setAventura(1);
        salaTerrorDificil.getPreferencia().setTerror(10);
        salaTerrorDificil.getPreferencia().setDificultad(10);
        persist(salaTerrorDificil);

        Sala casaDePapel = new Sala();
        casaDePapel.setNombre("La casa de papel");
        casaDePapel.getPreferencia().setAventura(8);
        casaDePapel.getPreferencia().setTerror(2);
        casaDePapel.getPreferencia().setDificultad(8);
        persist(casaDePapel);

        Sala breakingBad = new Sala();
        breakingBad.setNombre("Breaking Bad");
        breakingBad.getPreferencia().setAventura(9);
        breakingBad.getPreferencia().setTerror(3);
        breakingBad.getPreferencia().setDificultad(7);
        persist(breakingBad);

        Sala alien  = new Sala();
        alien.setNombre("Alien");
        alien.getPreferencia().setAventura(5);
        alien.getPreferencia().setTerror(7);
        alien.getPreferencia().setDificultad(10);
        persist(alien);

        Sala pobreAngelito  = new Sala();
        pobreAngelito.setNombre("Mi pobre angelito");
        pobreAngelito.getPreferencia().setAventura(7);
        pobreAngelito.getPreferencia().setTerror(3);
        pobreAngelito.getPreferencia().setDificultad(3);
        persist(pobreAngelito);

        Sala hannibal  = new Sala();
        hannibal.setNombre("Hannibal");
        hannibal.getPreferencia().setAventura(5);
        hannibal.getPreferencia().setTerror(8);
        hannibal.getPreferencia().setDificultad(8);
        persist(hannibal);

        Sala catalepsia  = new Sala();
        catalepsia.setNombre("Catalepsia");
        catalepsia.getPreferencia().setAventura(2);
        catalepsia.getPreferencia().setTerror(10);
        catalepsia.getPreferencia().setDificultad(9);
        persist(catalepsia);

        Sala prisonBreak  = new Sala();
        prisonBreak.setNombre("Prison Break");
        prisonBreak.getPreferencia().setAventura(6);
        prisonBreak.getPreferencia().setTerror(3);
        prisonBreak.getPreferencia().setDificultad(6);
        persist(prisonBreak);

        Equipo equipoA = new Equipo();
        equipoA.setNombre("Equipo A");

        Equipo equipoB = new Equipo();
        equipoB.setNombre("Equipo B");

        Escapista escapista1 = new Escapista();
        escapista1.setNombre("Seba");
        escapista1.setUsuario("seba");
        escapista1.getPreferencia().setDificultad(10);
        escapista1.getPreferencia().setTerror(10);
        escapista1.getPreferencia().setAventura(1);
        escapista1.agregarEquipo(equipoA);
        escapista1.agregarEquipo(equipoB);
        escapista1.agregarSala(salaTerror);
        escapista1.agregarSala(hannibal);


        Escapista escapista2 = new Escapista();
        escapista2.setNombre("Fede");
        escapista2.setUsuario("fede");
        escapista2.getPreferencia().setDificultad(5);
        escapista2.getPreferencia().setTerror(10);
        escapista2.getPreferencia().setAventura(1);
        escapista2.agregarEquipo(equipoA);
        escapista2.agregarSala(salaTerror);
        escapista2.agregarSala(prisonBreak);


        Escapista escapista3 = new Escapista();
        escapista3.setNombre("Angie");
        escapista3.setUsuario("angie");
        escapista3.getPreferencia().setDificultad(5);
        escapista3.getPreferencia().setTerror(1);
        escapista3.getPreferencia().setAventura(10);
        escapista3.agregarEquipo(equipoB);
        escapista3.agregarSala(salaTerrorDificil);
        escapista3.agregarSala(casaDePapel);

        persist(escapista1);
        persist(escapista2);
        persist(escapista3);

        return "OK";
    }

    public Sala findSalaById(long id) {
        return persistenceService.find(Sala.class, id);
    }

    public Sala persist(Sala sala) {
        persistenceService.persist(sala);
        return sala;
    }

    public Equipo findEquipoById(long id) {
        return persistenceService.find(Equipo.class, id);
    }

    public Equipo persist(Equipo equipo) {
        persistenceService.persist(equipo);
        return equipo;
    }

    public Escapista findEscapistaById(long id) {
        return persistenceService.find(Escapista.class, id);
    }

    public Escapista persist(Escapista escapista) {
        persistenceService.persist(escapista);
        return escapista;
    }

    public List<Escapista> getEscapistas() {
        return persistenceService.createQuery("from Escapista e", Escapista.class).getResultList();
    }


    public List<Sala> getSalas() {
        return persistenceService.createQuery("from Sala s", Sala.class).getResultList();
    }
}
