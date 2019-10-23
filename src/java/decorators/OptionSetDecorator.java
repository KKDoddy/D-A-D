
package decorators;

import domain.bean.Option;
import domain.bean.OptionSet;

public abstract class OptionSetDecorator extends OptionSet{

    public OptionSetDecorator(String name, int numberOfChoices) {
        super(name, numberOfChoices);
    }
    
    public abstract Option [] setChoice();
    
}
