package migration;

import exceptions.DatabaseException;
import models.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class ClientMigration {

    private static final Logger logger = LogManager.getLogger(ClientMigration.class);

    private static final String TABLE = "Client";
    private static final String SELECT_QUERY = "SELECT * FROM " + TABLE + " WHERE id = ";
    private static final String INSERT_QUERY = "INSERT INTO " + TABLE
            + " (Id, Name, Surname, BankAccount, TariffPlanId,CompanyId, IsActiveClient) VALUES ";
    // (moreid, ...)


    private Connection dbConnection;

    public ClientMigration(Connection dbConnection){
        this.dbConnection = dbConnection;
    }

    public int migrate(ArrayList<Client> clients) {
        AtomicInteger counter = new AtomicInteger();
        for (Client client : clients) {
            try {
                saveClient(client);
                counter.getAndIncrement();
            } catch (Exception e) {
                counter.getAndDecrement();
                logger.debug(e);
            }
        }
        System.out.println(counter + " clients migrated");
        return counter.get();
    }

    private void saveClient(Client client) throws DatabaseException {
        ResultSet resultSet = null;
        try {
            Statement stmt = dbConnection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

            String selectQuery = SELECT_QUERY + client.getClientId() + ';';
            resultSet = stmt.executeQuery(selectQuery);

            if (resultSet.next()) {
                System.out.println("Record Client with id " + client.getClientId() + " already exists id database");
            }

            Statement stmtInsert = dbConnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            // (Id, Name, Surname, BankAccount, TariffPlanId,CompanyId, IsActiveClient) VALUES
            String insertQuery = INSERT_QUERY + '(' +
                    client.getClientId() + ",'" +
                    client.getName()+ "','" +
                    client.getSurname() +"'," +
                    client.getBankAccount() + "," +
                    client.getTariffPlanId() + "," +
                    client.getCompanyId() + ",'" +
                    client.isActiveClient() + "');";

            resultSet = stmt.executeQuery(insertQuery);

        } catch (SQLException e) {
            logger.error(e);
            //throw new DatabaseException(e.getMessage());
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
            } catch (SQLException e) {
                logger.error(e);
            }

        }
    }

}
