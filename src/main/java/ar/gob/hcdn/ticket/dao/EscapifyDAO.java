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
        salaTerror.setNombre("Terror media");
        salaTerror.getPreferencia().setAventura(1);
        salaTerror.getPreferencia().setTerror(10);
        salaTerror.getPreferencia().setDificultad(5);
        persist(salaTerror);

        Sala salaAventura = new Sala();
        salaAventura.setNombre("Aventura media");
        salaAventura.getPreferencia().setAventura(10);
        salaAventura.getPreferencia().setTerror(1);
        salaAventura.getPreferencia().setDificultad(5);
        persist(salaAventura);

        Sala salaTerrorDificil = new Sala();
        salaTerrorDificil.setNombre("Terror dificil");
        salaTerrorDificil.getPreferencia().setAventura(1);
        salaTerrorDificil.getPreferencia().setTerror(10);
        salaTerrorDificil.getPreferencia().setDificultad(10);
        persist(salaTerrorDificil);

        Equipo equipoA = new Equipo();
        equipoA.setNombre("Equipo A");

        Equipo equipoB = new Equipo();
        equipoB.setNombre("Equipo B");

        Escapista escapista1 = new Escapista();
        escapista1.setNombre("pepe dificil terror");
        escapista1.setUsuario("pepe");
        escapista1.getPreferencia().setDificultad(10);
        escapista1.getPreferencia().setTerror(10);
        escapista1.getPreferencia().setAventura(1);
        escapista1.agregarEquipo(equipoA);
        escapista1.agregarEquipo(equipoB);
        escapista1.agregarSala(salaTerror);
        persist(escapista1);

        Escapista escapista2 = new Escapista();
        escapista2.setNombre("fede terror");
        escapista2.setUsuario("fede");
        escapista2.getPreferencia().setDificultad(5);
        escapista2.getPreferencia().setTerror(10);
        escapista2.getPreferencia().setAventura(1);
        escapista2.agregarEquipo(equipoA);
        persist(escapista2);

        Escapista escapista3 = new Escapista();
        escapista3.setNombre("angie aventura");
        escapista3.setUsuario("angie");
        escapista3.getPreferencia().setDificultad(5);
        escapista3.getPreferencia().setTerror(1);
        escapista3.getPreferencia().setAventura(10);
        escapista3.agregarEquipo(equipoB);
        escapista3.agregarSala(salaTerrorDificil);
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


}
