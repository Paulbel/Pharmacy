package by.pharmacy.entity;


import java.io.Serializable;

public class Drug implements Serializable{
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
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setId(1);//TODO: manufacturer logic in DrugDAO.getDrugs()
        return manufacturer;
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
}
