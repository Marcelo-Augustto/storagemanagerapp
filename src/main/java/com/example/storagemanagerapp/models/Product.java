package com.example.storagemanagerapp.models;

import jakarta.persistence.*;

import javax.validation.constraints.NotEmpty;

@Entity
public class Product {

    @Id
    @NotEmpty
    private String serial;

    @NotEmpty
    private String name;

    @NotEmpty
    private float price;

    @NotEmpty
    private int quantity;

    @NotEmpty
    private String description;

    @ManyToOne
    private Section section;

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }
}
