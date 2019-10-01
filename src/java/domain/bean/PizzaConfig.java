package domain.bean;

import java.io.Serializable;




public class PizzaConfig implements Serializable{
    
    private double basePrice;
    private OptionSet options[];

    public PizzaConfig(double basePrice,int maxOptionSets) {
        this.basePrice = basePrice;
        this.options = new OptionSet[maxOptionSets];
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public OptionSet[] getOptions() {
        return options;
    }

    public void setOptions(OptionSet[] options) {
        for(int i=0; i<options.length; i++){
            if(options[i]!=null){
                this.options[i] = options[i];
            }
        }
        setPrices();
    }
    
    public void setPrices(){
        for(OptionSet set: options){
            if(set != null){
                set.setPrice(set.getPrice() + this.basePrice);
            }
        }
    }
    
}
