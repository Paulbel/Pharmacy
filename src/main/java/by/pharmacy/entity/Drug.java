package by.pharmacy.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "drug")
@XmlAccessorType(XmlAccessType.FIELD)
public class Drug implements Serializable {
    private static final long serialVersionUID = 4340011690482548403L;
    private int id;
    private String name;
    private String composition;
    private int number;
    private int amount;
    private int dosage;
    private String description;
    private boolean needPrescription;
    private double price;
    private Manufacturer manufacturer;


    public Drug() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getDosage() {
        return dosage;
    }

    public void setDosage(int dosage) {
        this.dosage = dosage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isNeedPrescription() {
        return needPrescription;
    }

    public void setNeedPrescription(boolean needPrescription) {
        this.needPrescription = needPrescription;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Manufacturer getManufacturer() {
        return this.manufacturer;
    }


    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getComposition() {
        return composition;
    }

    public void setComposition(String composition) {
        this.composition = composition;
    }

    @Override
    public String toString() {
        return "Drug{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", composition='" + composition + '\'' +
                ", number=" + number +
                ", amount=" + amount +
                ", dosage=" + dosage +
                ", description='" + description + '\'' +
                ", needPrescription=" + needPrescription +
                ", price=" + price +
                ", manufacturer=" + manufacturer +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Drug drug = (Drug) o;

        if (id != drug.id) return false;
        if (number != drug.number) return false;
        if (amount != drug.amount) return false;
        if (dosage != drug.dosage) return false;
        if (needPrescription != drug.needPrescription) return false;
        if (Double.compare(drug.price, price) != 0) return false;
        if (!name.equals(drug.name)) return false;
        if (composition != null ? !composition.equals(drug.composition) : drug.composition != null) return false;
        if (description != null ? !description.equals(drug.description) : drug.description != null) return false;
        return manufacturer != null ? manufacturer.equals(drug.manufacturer) : drug.manufacturer == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + (composition != null ? composition.hashCode() : 0);
        result = 31 * result + number;
        result = 31 * result + amount;
        result = 31 * result + dosage;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (needPrescription ? 1 : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (manufacturer != null ? manufacturer.hashCode() : 0);
        return result;
    }

}
