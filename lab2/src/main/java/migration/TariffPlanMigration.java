package migration;
import com.sun.tools.javac.Main;
import exceptions.DatabaseException;
import models.TariffPlan;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class TariffPlanMigration {

    private static final Logger logger = LogManager.getLogger(TariffPlanMigration.class);
    private static final String TABLE = "TariffPlan";
    private static final String SELECT_QUERY = "SELECT * FROM " + TABLE + " WHERE id = ";
    private static final String INSERT_QUERY = "INSERT INTO " + TABLE
            + " (Id, Name, SubscriptionFee, Debit, SmsPrice, CallPrice) VALUES ";
    // (moreid, ...)

    private Connection dbConnection;

    public TariffPlanMigration(Connection dbConnection){
        this.dbConnection = dbConnection;
    }

    public int migrate(ArrayList<TariffPlan> tariffPlans) {
        AtomicInteger counter = new AtomicInteger();
        for (TariffPlan tariffPlan : tariffPlans) {
            try {
                saveTariffPlan(tariffPlan);
                counter.getAndIncrement();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                //logger.debug(e);
            }
        }

        System.out.println(counter + " tariff plans migrated");
        return counter.get();
    }

    private void saveTariffPlan(TariffPlan tariffPlan) throws DatabaseException {
        ResultSet resultSet = null;
        try {
            Statement stmt = dbConnection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

            String selectQuery = SELECT_QUERY + tariffPlan.getId() + ';';
            resultSet = stmt.executeQuery(selectQuery);

            if (resultSet.next()) {
                System.out.println("Record Tariff Plan with id " + tariffPlan.getId() + " already exists id database");
            }

            Statement stmtInsert = dbConnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            // (Id, Name, SubscriptionFee, Debit, SmsPrice, CallPrice) VALUES
            String insertQuery = INSERT_QUERY + '(' +
                    tariffPlan.getId() + ",'" +
                    tariffPlan.getName()+ "'," +
                    tariffPlan.getSubscriptionFee() +",'" +
                    tariffPlan.getDebit() + "'," +
                    tariffPlan.getSmsPrice() + ',' +
                    tariffPlan.getCallPrice() + ");";

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
