package models;

public enum Size {
    S("Small",0),
    M("Medium",1500),
    L("Large", 3500);

    private final double price;
    private final String name;

    Size(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}
