package models;

import java.io.Serializable;
import java.util.List;

public class PizzaConfig implements Serializable {
    private int id;
    private String name;
    private List<OptionSet> choice;
    private double basePrice;
    private double delivery;
    private Size size;

    public PizzaConfig() {}

    public PizzaConfig(String name, double basePrice, Size size) {
        this.name = name;
        this.basePrice = basePrice;
        this.size = size;
    }

    public PizzaConfig(String name, List<OptionSet> choice, double basePrice, double delivery, Size size) {
        this.name = name;
        this.choice = choice;
        this.basePrice = basePrice;
        this.delivery = delivery;
        this.size = size;
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

    public List<OptionSet> getChoice() {
        return choice;
    }

    public void setChoice(List<OptionSet> choice) {
        this.choice = choice;
    }

    public double getBasePrice() { return basePrice; }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public double getDelivery() {
        return delivery;
    }

    public void setDelivery(double delivery) {
        this.delivery = delivery;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }
}
