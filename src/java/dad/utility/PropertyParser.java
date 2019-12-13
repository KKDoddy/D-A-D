package dad.utility;

import dad.model.Option;
import dad.model.OptionSet;
import dad.model.PizzaConfig;
import dad.model.Size;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PropertyParser {
    public void getPizzaConfig(Properties props) throws IOException, NullPointerException {
        String pizzeria = props.getProperty("Pizzeria");
        String size = props.getProperty("Size");
        Double basePrice = Double.valueOf(props.getProperty("BasePrice"));
//        Double delivery = Double.valueOf(props.getProperty("Delivery"));

        if(pizzeria != null && basePrice != null && size != null) {
            PizzaConfig pizzaConfig = new PizzaConfig(pizzeria, basePrice, Size.valueOf(size));

            List<OptionSet> optionSets = getOptionSets(props);

            pizzaConfig.setChoice(optionSets);

            optionSets.forEach(optionSet -> {
                System.out.println(optionSet.getName());
                optionSet.getChoices().forEach(option -> {
                    System.out.println(option.getName());
                });
                System.out.println("\n");
            });
        }
    }

    public static List<OptionSet> getOptionSets(Properties props){
        int i = 1;
        List<OptionSet> optionSets = new ArrayList<>();
        while (true) {
            List<Option> options = new ArrayList<>();

            OptionSet optionSet = getOptionSetOnIndex(i, props);
            if (optionSet == null) break;

            optionSet.setChoices(getOptionsOnIndex(i, props));
            optionSets.add(optionSet);

            i++;
        }
        return optionSets;
    }

    public static OptionSet getOptionSetOnIndex(int i, Properties props){
        String optionSetName = props.getProperty("Option" + i);

        if (optionSetName == null) return null;

        return new OptionSet(optionSetName);
    }

    public static List<Option> getOptionsOnIndex(int i, Properties props){
        List<Option> options = new ArrayList<>();
        char c;
        for (c = 'a'; c <= 'z'; ++c) {
            String option = props.getProperty("OptionValue" + i + c);
            if (option == null) break;
            options.add(new Option(option));
        }
        return options;
    }


    public Option getOption(Properties props){
        try{
            String optionSetName = props.getProperty("Name");
            Double optionSetPrice = Double.valueOf(props.getProperty("Price"));

            try{
                String id = props.getProperty("Id");
                return new Option(Integer.parseInt(id),optionSetName, optionSetPrice);
            } catch (Exception ex){
                return new Option(optionSetName, optionSetPrice);
            }
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
