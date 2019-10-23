
package decorators;

import domain.bean.Option;
import domain.bean.OptionSet;

public class Tomato extends OptionSetDecorator{
    OptionSet pizza;

    public Tomato(String name, int numberOfChoices, OptionSet pizza) {
        super(name, numberOfChoices);
        this.pizza = pizza;
        this.setId(pizza.getId());
        this.setName(pizza.getName());
        this.setSize(pizza.getSize());
        setChoice();
    }

    @Override
    public Option[] setChoice() {
       Option [] usual = pizza.getChoices();
       Option [] ops = new Option[usual.length+1];
       for(int i=0; i<usual.length; i++){
           ops[i] = new Option(usual[i].getName(), usual[i].getPrice());
       }
       ops[usual.length] = new Option("Tomato", 200);
       this.setChoices(ops);
       return ops;
    }
}
