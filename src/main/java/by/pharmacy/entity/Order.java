package by.pharmacy.entity;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {
    private static final long serialVersionUID = 8272997380570653403L;
    private long id;
    private User client;
    private Drug drug;
    private int number;
    private Date date;

    public Order() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != order.id) return false;
        if (number != order.number) return false;
        if (client != null ? !client.equals(order.client) : order.client != null) return false;
        if (drug != null ? !drug.equals(order.drug) : order.drug != null) return false;
        return date != null ? date.equals(order.date) : order.date == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (client != null ? client.hashCode() : 0);
        result = 31 * result + (drug != null ? drug.hashCode() : 0);
        result = 31 * result + number;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", client=" + client +
                ", drug=" + drug +
                ", number=" + number +
                ", date=" + date +
                '}';
    }
}
