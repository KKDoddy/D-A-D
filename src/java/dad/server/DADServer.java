
package dad.server;

import java.net.ServerSocket;
import java.net.Socket;

public class DADServer {

    static ServerSocket serverSocket;
    static Socket client;
    
    public static void main(String[] args){
        
        try {
            serverSocket = new ServerSocket(1900);
            System.out.println("Server is running on port 1900 ...");
            while (true) {                
                System.out.println("waiting for client connection");
                client = serverSocket.accept();
                ListeningThread thread = new ListeningThread(client);
                thread.start();
                System.out.println("finished connecting");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
