package sample.serverClient;

import sample.mailapiplusjdbc.Myjdbc;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server  {
    private ServerSocket serverSocket;
    static Myjdbc DBC;


    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void startServer(){
        try{
            while (!serverSocket.isClosed()){
                //program basically halt here
                Socket socket=serverSocket.accept();
                System.out.println("A client is connected");
                ClientHandler clientHandler =new ClientHandler(socket);
                //the server basically halts here to serve the client
                //it is a theread creating indinite state
                Thread thread=new Thread(clientHandler);
                thread.start();

            }
        }catch (IOException e){
            e.printStackTrace();

        }
    }
    //to avoid multiple try catch e
    //check to see null or not
    public void closeServerSocket(){
        try{
            if(serverSocket!=null){
                serverSocket.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public static void main(String[] args)throws  IOException {
        //Server is listening to the client through the port 1234
        ServerSocket serverSocket=new ServerSocket(1235);
        Server server=new Server(serverSocket);
        //this the server starts here
        server.startServer();
    }
}
