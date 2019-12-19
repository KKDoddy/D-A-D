package dad.client;

import models.*;

import java.io.IOException;
import java.net.Socket;
import java.io.*;
import java.util.Properties;

public class Client {

    public static void main(String[] args) throws IOException {

        // need host and port, we want to connect to the ServerSocket at port 7777
        Socket socket = new Socket("localhost", 1900);
        System.out.println("Connected!");

//save Option
        OutputStream outputStream = socket.getOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        dataOutputStream.writeUTF("option<>save");
        dataOutputStream.flush();

        Properties properties = getOption();

        outputStream = socket.getOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(properties);
        objectOutputStream.flush();
        InputStream inputStream = socket.getInputStream();
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        String response = dataInputStream.readUTF();
        System.out.println(response);
        
        outputStream = socket.getOutputStream();
        dataOutputStream = new DataOutputStream(outputStream);
        dataOutputStream.writeUTF("option<>get<>Tomato");
        dataOutputStream.flush();

        try{
            inputStream = socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            Option option = (Option) objectInputStream.readObject();
            option.setPrice(12300);

            writeOptionToProps(option);

            outputStream = socket.getOutputStream();
            dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.writeUTF("option<>update");
            dataOutputStream.flush();

            Properties optionProps = getOption();

            outputStream = socket.getOutputStream();
            objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(optionProps);
            objectOutputStream.flush();

        } catch (NullPointerException e){
            System.out.println(e.getLocalizedMessage());
        } catch (IOException e){
            System.out.println(e.getLocalizedMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getLocalizedMessage());
        }

    }

    public static Properties getOption() throws IOException {
        String rootPath = "\\Users\\Kalimba k Doddz\\Documents";
        String filename = rootPath + "\\option.properties";

        Properties props = new Properties();
        FileInputStream in = new FileInputStream(filename);

        props.load(in);

        return props;
    }
    
    public static boolean writeOptionToProps(Option option){
        Properties prop = new Properties();
        String rootPath = "\\Users\\Kalimba k Doddz\\Documents";
        String filename = rootPath + "\\option.properties";

        try {
            InputStream in = new FileInputStream(filename);
            prop.load(in);
        } catch (IOException ex) {
            System.out.println(ex);
        }
        //Setting the value to  our properties file.
        prop.setProperty("Id", Integer.toString(option.getId()));
        prop.setProperty("Option", option.getName());
        prop.setProperty("Price", Double.toString(option.getPrice()));

        try {
            prop.store(new FileOutputStream(filename), null);

        } catch (IOException ex) {
            System.out.println(ex);
        }
        return false;
    }

}
