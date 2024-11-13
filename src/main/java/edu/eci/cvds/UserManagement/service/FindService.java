package edu.eci.cvds.UserManagement.service;

import edu.eci.cvds.UserManagement.model.Responsible;
import edu.eci.cvds.UserManagement.repositories.ResponsibleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class FindService {
    private final ResponsibleRepository responsibleRepository;

    @Autowired
    public FindService(ResponsibleRepository responsibleRepository) {
        this.responsibleRepository = responsibleRepository;
    }

    public Responsible findResponsibleByDocument(Long responsibleDocNumber) {
        try {
            return responsibleRepository.findResponsibleByDocument(responsibleDocNumber);
        } catch (SQLException e) {
            System.err.println("Error al buscar responsable: " + e.getMessage());
            return null;
        }
    }
}
