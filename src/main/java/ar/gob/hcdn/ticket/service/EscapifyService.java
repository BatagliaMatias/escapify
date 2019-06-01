package ar.gob.hcdn.ticket.service;

import ar.gob.hcdn.ticket.dao.EscapifyDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EscapifyService {
    @Autowired
    private EscapifyDAO escapifyDAO;

    public String poblar(){
        return escapifyDAO.poblar();
    }
}
