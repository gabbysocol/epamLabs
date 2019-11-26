import exceptions.InvalidFileException;
import exceptions.NotFoundException;
import migration.*;
import models.*;
import org.apache.logging.log4j.LogManager;
import org.wiztools.xsdgen.ParseException;
import services.FileValidationService;
import services.LoadingDataService;

import java.io.IOException;
import java.sql.*;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Main {

    public static String ConnectionString = "jdbc:sqlserver://LAPTOP-0681GAS6\\SQLEXPRESS:1433;databaseName=Mobile_operator;user=Katya_dub;password=12345678qwerty";

    private static final String XML_DATA_FILE = "src/main/resources/in.xml";

    public static TariffPlanMigration tariffPlanMigration;
    public static SMSMigration smsMigration;
    public static CompanyMigration companyMigration;
    public static CallMigration callMigration;
    public static ClientMigration clientMigration;
    public static FileValidationService fileValidation = new FileValidationService();
    public static LoadingDataService loadingDataService = new LoadingDataService();

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws  ClassNotFoundException {
        //DataBaseObject db = DataBaseObject.GetInstance();
        /*XmlMapper xmlMapper = new XmlMapper();
        String xml = xmlMapper.writeValueAsString(db);
        System.out.println(xml);
        try(FileWriter writer = new FileWriter("in.xml", false))
        {
            writer.write(xml);
            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        XsdGen gen = new XsdGen();
        gen.parse(new File("in.xml"));
        File out = new File("out.xsd");
        gen.write(new FileOutputStream(out));
         */
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        //Logger logger = Logger.getLogger("com.microsoft.sqlserver.jdbc.Statement");

        try (Connection connection = DriverManager.getConnection(ConnectionString); ) {
            DataBaseObject db = loadingDataService.loadDateFromXml(fileValidation.checkFile(XML_DATA_FILE));

            tariffPlanMigration = new TariffPlanMigration(connection);
            companyMigration = new CompanyMigration(connection);
            clientMigration = new ClientMigration(connection);
            smsMigration = new SMSMigration(connection);
            callMigration = new CallMigration(connection);

            tariffPlanMigration.migrate(db.getTariffPlans());
            companyMigration.migrate(db.getCompanies());
            clientMigration.migrate(db.getClients());
            smsMigration.migrate(db.getSmsList());
            callMigration.migrate(db.getCalls());

        }
        catch (InvalidFileException e){
            logger.fatal(e.getMessage());
        }
        catch (NotFoundException e){
            logger.fatal(e.getMessage());
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
