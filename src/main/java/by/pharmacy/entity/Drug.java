package by.pharmacy.entity;

import by.pharmacy.entity.Manufacturer;

public class Drug {
    private int id;
    private String name;
    private String composition;
    private int number;
    private int amount;
    private int dosage;
    private String description;
    private boolean needPresciption;
    private double price;
    private Manufacturer manufacturer;

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

    public boolean isNeedPresciption() {
        return needPresciption;
    }

    public void setNeedPresciption(boolean needPresciption) {
        this.needPresciption = needPresciption;
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
        manufacturer.setId(1);
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
                ", needPresciption=" + needPresciption +
                ", price=" + price +
                ", manufacturer=" + manufacturer +
                '}';
    }
}
