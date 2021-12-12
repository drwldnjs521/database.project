package de.hhu.cs.dbs.propra.domain.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Premiumnutzer {
    private String benutzername;
    private LocalDate ablaufDatum;
    private boolean abgelaufen;

    public Premiumnutzer(String benutzername, LocalDate ablaufDatum) {
        this.ablaufDatum = ablaufDatum;
        this.abgelaufen = LocalDate.now().isAfter(ablaufDatum);
    }
}
