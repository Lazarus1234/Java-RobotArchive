package Sockets;
import java.io.BufferedReader;
import java.io.BufferedInputStream;
import java.io.PrintWriter;
import java.net.Socket;





public class Client implements MessageSender {

    Socket connection;
    PrintWriter outputStream;
    BufferedReader inputStream;

    MessageListener messageListener;
    Thread messageListenerThread;

    public Client(String address){
        String host = "Localhost";
        short port = 20000;
// int port 20000
        if (address.contains(":")) {
            String[] hostAndPort = address.split(":");
            host=hostAndPort[0];
            port = (short) Integer.parseInt(hostAndPort[1]);
        } else {
            host=address;
        }

        try{
            connection =new Socket(host, port);

        } catch (Exception e) {
            System.err.println("Failed to connect:" + e);
        }
    }

    @Override
    public void sendMessage(String msg) {

    }
}
