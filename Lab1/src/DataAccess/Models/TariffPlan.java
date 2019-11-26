package DataAccess.Models;

import jdk.internal.org.objectweb.asm.util.TraceAnnotationVisitor;

import java.io.Serializable;

public class TariffPlan implements Serializable {
    private int id;

    private String name;
    private Double subscriptionFee;
    private Debit debit;

    private Double smsPrice;
    private Double callPrice;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSubscriptionFee() {
        return subscriptionFee;
    }

    public void setSubscriptionFee(Double subscriptionFee) {
        this.subscriptionFee = subscriptionFee;
    }

    public Debit getDebit() {
        return debit;
    }

    public void setDebit(Debit debit) {
        this.debit = debit;
    }

    public Double getSmsPrice() {
        return smsPrice;
    }

    public void setSmsPrice(Double smsPrice) {
        this.smsPrice = smsPrice;
    }

    public Double getCallPrice() {
        return callPrice;
    }

    public void setCallPrice(Double callPrice) {
        this.callPrice = callPrice;
    }

    public TariffPlan(){

    }

    public TariffPlan(int id, String name, Double subscriptionFee, Debit debit, Double smsPrice, Double callPrice){
        this.callPrice = callPrice;
        this.debit = debit;
        this.name = name;
        this.smsPrice = smsPrice;
        this.subscriptionFee = subscriptionFee;
        this.id = id;
    }

    public String toString(){
        return String.format("%3d|%15s|%s|%s|%f|%f", this.id, this.name, this.subscriptionFee, this.debit, this.smsPrice, this.callPrice);
    }

}
