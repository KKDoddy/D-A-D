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

    }

    public static Properties getOption() throws IOException {
        String rootPath = "\\Users\\Kalimba k Doddz\\Documents";
        String filename = rootPath + "\\option.properties";

        Properties props = new Properties();
        FileInputStream in = new FileInputStream(filename);

        props.load(in);

        return props;
    }

}
