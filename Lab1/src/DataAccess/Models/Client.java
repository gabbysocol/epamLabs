package DataAccess.Models;

import java.io.Serializable;
import java.io.StringReader;

public class Client  implements Serializable {
    private int clientId;
    private String name;
    private String surname;
    private double bankAccount;
    private int tariffPlanId;
    private int companyId;
    private boolean isActiveClient;

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public double getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(double bankAccount) {
        this.bankAccount = bankAccount;
    }

    public int getTariffPlanId() {
        return tariffPlanId;
    }

    public void setTariffPlanId(int tariffPlanId) {
        this.tariffPlanId = tariffPlanId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public boolean isActiveClient() {
        return isActiveClient;
    }

    public void setActiveClient(boolean activeClient) {
        isActiveClient = activeClient;
    }


    public Client(){

    }

    public Client(int id, String name, String surname, double bankAccount, int tariffId, int companyId, boolean isActive){
        this.bankAccount = bankAccount;
        this.companyId = companyId;
        this.isActiveClient = isActive;
        this.name = name;
        this.surname = surname;
        this.tariffPlanId = tariffId;
        this.clientId = id;
    }

    public String toString(){
        return String.format("%3d|%20s|%15s|%2f|%2d|%2d",clientId, name, surname, bankAccount, tariffPlanId, companyId);
    }


}
