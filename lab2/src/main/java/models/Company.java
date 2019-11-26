package models;

import java.io.Serializable;
import java.util.List;

public class Company implements Serializable {
    private int companyId;
    private String name;
    private Double discount;

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Company(){}

    public Company(int companyId, String name, Double discount){
        this.companyId = companyId;
        this.name = name;
        this.discount = discount;
    }

    public String toString(){
        return String.format("%6d|%12s|%f", this.companyId, this.name, this.discount);
    }
}
