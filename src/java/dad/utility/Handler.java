package dad.utility;

import java.io.*;
import java.net.Socket;

public class Handler {
    public void sendResponse(Socket socket, String msg){
        OutputStream outputStream;
        DataOutputStream dataOutputStream;
        try {
            outputStream = socket.getOutputStream();
            dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.writeUTF(msg);
            dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendObject(Socket socket, Object object) throws IOException {
        OutputStream outputStream = socket.getOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(object);
    }

    public Object getObject(Socket socket) throws IOException, ClassNotFoundException {
        InputStream inputStream = socket.getInputStream();
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        Object obj= objectInputStream.readObject();
        return obj;
    }
}
