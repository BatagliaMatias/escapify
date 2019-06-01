package ar.gob.hcdn.ticket.service;

import ar.gob.hcdn.ticket.dao.EscapifyDAO;
import ar.gob.hcdn.ticket.domain.Escapista;
import ar.gob.hcdn.ticket.dto.EscapistaDTO;
import ar.gob.hcdn.ticket.transformer.EscapistaTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EscapifyService {
    @Autowired
    private EscapifyDAO escapifyDAO;

    @Autowired
    private EscapistaTransformer escapistaTransformer;

    public String poblar(){
        return escapifyDAO.poblar();
    }

    public List<EscapistaDTO> getEscapistas() {
        List<EscapistaDTO> dtoList = new ArrayList<>();
        for(Escapista escapista : escapifyDAO.getEscapistas()){
            dtoList.add(escapistaTransformer.transform(escapista));
        }
        return dtoList;
    }

    public EscapistaDTO getEscapista(Long userID) {
        return escapistaTransformer.transformDetallado(escapifyDAO.findEscapistaById(userID));
    }
}
