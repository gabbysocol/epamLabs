package migration;


import exceptions.DatabaseException;
import models.Call;
import models.Company;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class CallMigration {

    private static final Logger logger = LogManager.getLogger(CallMigration.class);

    private static final String TABLE = "Calling";
    private static final String SELECT_QUERY = "SELECT * FROM " + TABLE + " WHERE Id = ";
    private static final String INSERT_QUERY = "INSERT INTO " + TABLE
            + " (Id, DateCalled, Duration, SourceClientId, DestinationClientId) VALUES ";

    private Connection dbConnection;

    public CallMigration( Connection dbConnection){
        this.dbConnection = dbConnection;
    }

    public int migrate(ArrayList<Call> calls) {
        AtomicInteger counter = new AtomicInteger();
        for (Call call : calls) {
            try {
                saveCompany(call);
                counter.getAndIncrement();
            } catch (Exception e) {
                logger.debug(e);
            }
        }

        System.out.println(counter + " callings migrated");
        return counter.get();
    }

    private void saveCompany(Call call) throws DatabaseException {
        ResultSet resultSet = null;
        try {
            Statement stmt = dbConnection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

            String selectQuery = SELECT_QUERY + call.getServiceId() + ';';
            resultSet = stmt.executeQuery(selectQuery);

            if (resultSet.next()) {
                System.out.println("Record call with id " + call.getServiceId() + " already exists id database");
            }
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Statement stmtInsert = dbConnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            //  " (Id, DateCalled, Duration, SourceClientId, DestinationClientId) VALUES ";
            String insertQuery = INSERT_QUERY + '(' +
                    call.getServiceId() + ",'" +
                    format.format(call.getDateTime()) + "','" +
                    format.format(call.getDuration()) + "'," +
                    call.getSourceClientId() + "," +
                    call.getDestinationClientId() + ");";

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
