package domain.bean;

import domain.enums.Size;
import java.io.Serializable;



public class OptionSet implements Serializable{
 
    private String name;
    private Option choices[];
    private Size size;
    private double price;

    public OptionSet(String name, int numberOfChoices) {
        this.name = name;
        this.choices = new Option[numberOfChoices];
        
        for(Option o: choices){
            o = new Option("", 0);
        }
        this.price = 0;
        this.size = Size.SMALL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Option[] getChoices() {
        return choices;
    }

    public void setChoices(Option[] choices) {
        for(int i=0; i < choices.length; i++){
            if(choices[i] != null){
                this.choices[i] = choices[i];
            }
        }
        getPrice();
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
        this.name = this.name + " " + this.size;
    }

    public double getPrice() {
        
        this.price = 0;
        for (Option choice : choices) {
            if(choice != null){
                price += choice.getPrice();
            }
        }
        
        switch(this.size) {
            case MEDIUM:
                price += 2000;
                break;
            case LARGE:
                price += 3500;
                break;
        }
        
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    
    //inner Option class as required
    public class Option implements Serializable{
        
        private String name;
        private double price;

        public Option(String name, double price) {
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }
        
    }
}


