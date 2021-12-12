package de.hhu.cs.dbs.propra.infrastructure.repositories;

import de.hhu.cs.dbs.propra.domain.model.PremiumNutzerRepo;
import de.hhu.cs.dbs.propra.domain.model.Premiumnutzer;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PremiumNutzerNutzerRepoImpl implements PremiumNutzerRepo {

    @Inject
    private DataSource dataSource;

    @Override
    public List<Premiumnutzer> fetchAll() {
        List<Premiumnutzer> premiumnutzerList = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT p.ROWID, p.Benutzername, p.Premiumfrist, n.ROWID, n.Email, n.Passwort FROM Premiumnutzer p JOIN Nutzer n ON p.Benutzername = n.Benutzername;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.closeOnCompletion();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                premiumnutzerList.add(mappingPremiumNutzer(resultSet));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return premiumnutzerList;
    }



    public Premiumnutzer mappingPremiumNutzer(ResultSet rs){
        Premiumnutzer premiumnutzer = null;
        try {
            premiumnutzer = new Premiumnutzer(rs.getString(1),rs.getObject(2, LocalDate.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return premiumnutzer;


    }

}
