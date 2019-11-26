package models;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.sql.Array;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class DataBaseObject {
    @JacksonXmlElementWrapper(localName = "Clients")
    @JacksonXmlProperty(localName = "Client")
    private ArrayList<Client> clients;

    @JacksonXmlElementWrapper(localName = "Calls")
    @JacksonXmlProperty(localName = "Call")
    private ArrayList<Call> calls;

    @JacksonXmlElementWrapper(localName = "TariffPlans")
    @JacksonXmlProperty(localName = "TariffPlan")
    private ArrayList<TariffPlan> tariffPlans;

    @JacksonXmlElementWrapper(localName = "SMSes")
    @JacksonXmlProperty(localName = "SMS")
    private ArrayList<SMS> smsList;

    @JacksonXmlElementWrapper(localName = "Companies")
    @JacksonXmlProperty(localName = "Company")
    private ArrayList<Company> companies;

    public ArrayList<Client> getClients() {
        return clients;
    }

    public void setClients(ArrayList<Client> clients) {
        this.clients = clients;
    }

    public ArrayList<Call> getCalls() {
        return calls;
    }

    public void setCalls(ArrayList<Call> calls) {
        this.calls = calls;
    }

    public ArrayList<TariffPlan> getTariffPlans() {
        return tariffPlans;
    }

    public void setTariffPlans(ArrayList<TariffPlan> tariffPlans) {
        this.tariffPlans = tariffPlans;
    }

    public ArrayList<SMS> getSmsList() {
        return smsList;
    }

    public void setSmsList(ArrayList<SMS> smsList) {
        this.smsList = smsList;
    }

    public ArrayList<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(ArrayList<Company> companies) {
        this.companies = companies;
    }

    public static DataBaseObject GetInstance(){
        DataBaseObject db = new DataBaseObject();

        ArrayList<Call> calls = new ArrayList<Call>() {
            {
                add(new Call(1, 2, new Time(123), 1));
                add(new Call(1, 2, new Time(123), 1));
                add(new Call(1, 2, new Time(123), 1));
            }
        };
        db.setCalls(calls);

        //public Client(int id, String name, String surname, double bankAccount, int tariffId, int companyId, boolean isActive)
        ArrayList<Client> clients = new ArrayList<Client>() {
            {
                add(new Client(1, "Katya", "Dub", 12, 1, 1,true ) );
                add(new Client(2, "Katya1", "Dub1", 32, 1, 2,true ));
                add(new Client(3, "Katya2", "Dub2", 123, 2, 1,true ));
            }
        };
        db.setClients(clients);

        //    public SMS(int id, int source, int destination, int textSize){
        ArrayList<SMS> smsArrayList = new ArrayList<SMS>() {
            {
                add(new SMS(1,1,2, 123));
                add(new SMS(2,2,1, 143));
                add(new SMS(3,1,2, 153));
            }
        };
        db.setSmsList(smsArrayList);

        //  public Company(int companyId, String name, Double discount){
        ArrayList<Company> companies = new ArrayList<Company>() {
            {
                add(new Company(1, "MIT", 0.4));
                add(new Company(2, "MIT1", 0.2));
                add(new Company(3, "MIT2", 0.1));
            }
        };
        db.setCompanies(companies);

        //public TariffPlan(int id, String name, Double subscriptionFee, Debit debit, Double smsPrice, Double callPrice){
        ArrayList<TariffPlan> tariffPlans = new ArrayList<TariffPlan>() {
            {
                add(new TariffPlan(1, "Lemon", 3.2, Debit.everyDay, 2.1, 3.2));
                add(new TariffPlan(2, "Lemon1", 3.4, Debit.everyDay, 2.1, 3.2));
                add(new TariffPlan(3, "Lemon3", 3.1, Debit.everyWeek, 2.9, 3.2));
            }
        };
        db.setTariffPlans(tariffPlans);
        return db;

    }
}
