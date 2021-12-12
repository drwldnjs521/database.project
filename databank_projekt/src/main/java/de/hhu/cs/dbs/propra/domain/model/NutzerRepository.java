package de.hhu.cs.dbs.propra.domain.model;

import de.hhu.cs.dbs.propra.application.exceptions.ResourceNotFoundException;
import de.hhu.cs.dbs.propra.domain.model.Nutzer;

import java.util.List;
import java.util.Optional;

public interface NutzerRepository {

    List<Nutzer> fetchAll();

    Optional<Nutzer> findByName(String name);

    Optional<Nutzer> findByEmail(String email);

    Optional<Nutzer> findByNameAndEmail(String name, String email);

    void save(Nutzer nutzer);

    long countByNameAndPassword(String name, String password);
}
