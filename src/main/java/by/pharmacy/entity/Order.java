package by.pharmacy.entity;

import java.util.Date;

public class Order {
    private User client;
    private Drug drug;
    private int number;
    private Date date;

    public Order() {

    }



    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public Drug getDrug() {
        return drug;
    }

    public void setDrug(Drug drug) {
        this.drug = drug;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
