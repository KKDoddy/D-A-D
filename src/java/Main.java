
import decorators.Pepperoni;
import domain.bean.Option;
import domain.bean.OptionSet;
import domain.bean.PizzaConfig;
import domain.enums.Size;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class Main {
    
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        
        OptionSet set = new OptionSet("All Options",11);
        
        //creating Option Objects
        Option [] option = new Option[11];
        option[0] = new Option("Green Olives",1000);
        option[1] = new Option("Pepperoni",800);
        option[2] = new Option("Onion", 200);
        option[3] = new Option("Beef", 500);
        option[4] = new Option("Anchovy", 900);
        option[5] = new Option("Ham", 800);
        option[6] = new Option("Chicken", 700);
        option[7] = new Option("Mushroom", 400);
        option[8] = new Option("Black Olive", 1000);
        option[9] = new Option("Pineapple", 600);
        option[10] = new Option("Tomato", 200);
        
        //initializing serialzed objects files for Options (Toppings)
        for(Option o: option){
            File file = new File(".\\"+o.getName()+".txt");
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(o);
            out.close();
            fileOut.close();
        }

        //OUR PIZZAS
        OptionSet [] sets = new OptionSet[6];
        
        //PEPPERONI PIZZA WITH 2 TOPPINGS
        
        //TOPPINGS
        Option [] PepOptions = new Option[2];
        PepOptions[0] = option[1];
        PepOptions[1] = option[2];
        //SMALL
        sets[0] = new OptionSet("Pepperoni Pizza", 2);
        sets[0].setChoices(PepOptions);
        sets[0].setSize(Size.SMALL);
        //MEDIUM
        sets[1] = new OptionSet("Pepperoni Pizza", 2);
        sets[1].setChoices(PepOptions);
        sets[1].setSize(Size.MEDIUM);
        //LARGE
        sets[2] = new OptionSet("Pepperoni Pizza", 2);
        sets[2].setChoices(PepOptions);
        sets[2].setSize(Size.LARGE);
        
        
        
        //EPIC VEGGIE WITH 4 TOPPINGS
        
        //TOPPINGS
        Option [] veggieOptions = new Option[4];
        veggieOptions[0] = option[0];
        veggieOptions[1] = option[7];
        veggieOptions[2] = option[9];
        veggieOptions[3] = option[8];
        
        //SMALL
        sets[3] = new OptionSet("EpicVeggie", 4);
        sets[3].setChoices(veggieOptions);
        sets[3].setSize(Size.SMALL);
        //MEDIUM
        sets[4] = new OptionSet("EpicVeggie", 4);
        sets[4].setChoices(veggieOptions);
        sets[4].setSize(Size.MEDIUM);
        //LARGE
        sets[5] = new OptionSet("EppicVeggie", 4);
        sets[5].setChoices(veggieOptions);
        sets[5].setSize(Size.LARGE);
        
        
        //initializing serialzed objects files for OptionsSets (Pizza)
        for(OptionSet s: sets){
            File file = new File(".\\"+s.getName()+".txt");
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(s);
            out.close();
            fileOut.close();
        }
        
        //US (D-A-D Pizzaria)
        PizzaConfig resto = new PizzaConfig(2000, 6);
        resto.setOptions(sets);
        
        //initializing serialzed objects files for PizzaConfig (Pizzaria)
        File file = new File(".\\D-A-D Pizzaria.txt");
        FileOutputStream fileOut = new FileOutputStream(file);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(resto);
        out.close();
        fileOut.close();
        
    File pizzaFile = new File(".\\Pepperoni Pizza LARGE.txt");
    if(pizzaFile.exists()){
        FileInputStream fileIn = new FileInputStream(".\\Pepperoni Pizza LARGE.txt");
        ObjectInputStream in = new ObjectInputStream(fileIn);
        
        OptionSet pizza = (OptionSet) in.readObject();
        //printing options in pepperoni pizza before decorating the pizza object with extra pepproni
        Option[] before = pizza.getChoices();
        System.out.println("Options before decorating the pizza object with extra pepproni:");
        for(int i=0; i<before.length; i++){
            System.out.println(before[i].getName());
        }
        
        //Applying pepperoni decorator
        pizza = new Pepperoni("",3,pizza);
        
        //printing options in pepperoni pizza before decorating the pizza object with extra pepproni
        Option[] after = pizza.getChoices();
        System.out.println("Options after decorating the pizza object with extra pepproni:");
        for(int i=0; i<after.length; i++){
            System.out.println(after[i].getName());
        }
        
        in.close();
        fileIn.close();
    }
    }

}
