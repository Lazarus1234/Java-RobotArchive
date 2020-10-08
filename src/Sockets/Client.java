package Sockets;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;





public class Client implements MessageSender {

    Socket connection;
    PrintWriter outputStream;
    BufferedReader inputStream;

    MessageListener messageListener;
    Thread messageListenerThread;

    public Client(String address, MessageListener messageListener){
        this.messageListener = messageListener;
        MessageSender sender = this;
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

        System.out.println("Creating client with remote host" + host + "and port" + port);

        try{
            connection =new Socket(host, port);
            outputStream = new PrintWriter(connection.getOutputStream(), true);
            inputStream = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            messageListenerThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (connection.isConnected()){
                        try {
                            String message = inputStream.readLine();
                            messageListener.message(message, sender);
                        } catch (Exception e){
                            System.err.println("Failed to receive message:" + e);
                            break;
                        }
                    }
                }
            });

            messageListenerThread.start();


        } catch (Exception e) {
            System.err.println("Failed to connect:" + e);
        }
    }
    private boolean isConnected(){
        return connection !=null && outputStream !=null && inputStream != null;
    }
    public void disconnect() {
        if(isConnected()){
            try {
                inputStream = null;
                outputStream = null;
                connection.close();
            }catch (Exception e) {
                System.err.println("Failed to close connection: " + e);
            }
        }
    }

    @Override
    public void sendMessage(String msg) {
        if (isConnected()) {
            outputStream.println(msg);
        }
    }
}
