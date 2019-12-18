
package dad.server;

import dad.dbconnection.OptionDao;
import models.Option;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Properties;
import dad.utility.PropertyParser;
import dad.utility.Handler;


public class ListeningThread extends Thread {

    static Socket client;
    static InputStream inputStream;
    static PropertyParser propertyParser;
    private static OptionDao optionDao;
    private static Handler responseHandler;
    
    public ListeningThread(Socket newClient) {
        client = newClient;
    }

    public void run(){
        
        DataInputStream dataInputStream = null;
        try {
            inputStream = client.getInputStream();
            dataInputStream = new DataInputStream(client.getInputStream());
            String request = dataInputStream.readUTF();
            
            userRequest(request);
        } catch (IOException ex) {
            System.out.println(ex);
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        } finally {
            try {
                dataInputStream.close();
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
    }
    
    public static void userRequest(String request) throws IOException, ClassNotFoundException {
        String[] reqs = request.split("<>");

        switch (reqs[0]){
            case "option":
                optionRequestMethod(reqs);
                return;
            case "save<>pizza_config" :
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                Properties props = (Properties) objectInputStream.readObject();
                propertyParser.getPizzaConfig(props);
                //TODO: SAVE TO DB
                return;
        }
    }

    public static void optionRequestMethod(String[] request) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream;
        Properties props = new Properties();
        Option option = new Option();
        switch (request[1]){
            case "save":
                objectInputStream = new ObjectInputStream(inputStream);
                props = (Properties) objectInputStream.readObject();
                String optionSetName = props.getProperty("Name");
                Double optionSetPrice = Double.valueOf(props.getProperty("Price"));
                String id = props.getProperty("Id");
//                option = propertyParser.getOption(props);
                option.setId(Integer.parseInt(id));
                option.setName(optionSetName);
                option.setPrice(optionSetPrice);
                boolean check = optionDao.insertOption(option);
                responseHandler.sendResponse(client, (check ? "success" : "failed"));
                return;
            case "get":
                try {
                    String name = request[2];
                    Option obtainedOption = optionDao.getOptionByName(name);
                    responseHandler.sendObject(client, obtainedOption);
                } catch (NullPointerException ex){
                    responseHandler.sendResponse(client, "Error: " + ex.getLocalizedMessage());
                    ex.printStackTrace();
                } catch (SQLException ex){
                    responseHandler.sendResponse(client, "Error: " + ex.getLocalizedMessage());
                    ex.printStackTrace();
                }
            case "update":
                try {
                    inputStream = client.getInputStream();
                    ObjectInputStream objectInputStream2 = new ObjectInputStream(inputStream);
                    props = (Properties) objectInputStream2.readObject();
                    option = propertyParser.getOption(props);
                    optionDao.updateOption(option);
                } catch (Exception ex) {
                    responseHandler.sendResponse(client, "Error: " + ex.getLocalizedMessage());
                    ex.printStackTrace();
                }
            default:
                return;
        }
    }
}
