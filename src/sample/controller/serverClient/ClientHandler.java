package sample.serverClient;

import sample.mailapiplusjdbc.Myjdbc;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ClientHandler  implements Runnable {
    //To have a list to loop through the list if a client send messsage to another
    //broadcast to multiple clients
    public static ArrayList<ClientHandler> clientHandlers=new ArrayList<>();
    //This will get from the server class to establilsh a connecction to server and client

    public Socket socket;
    //to broadcast the message from server to client;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private  String clientUsername;
    //the socket here connect to server
    public  ClientHandler(Socket socket){
        try {
            this.socket=socket;
            //Client is sending//read things
            this.bufferedReader=new BufferedReader((new InputStreamReader((socket.getInputStream()))));
            //server is sending or used to send
            this.bufferedWriter=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            //get the userName from the client upon log in

//            this.clientUsername=this.bufferedReader.readLine();
//            System.out.println("message from client"+this.clientUsername);
//            List<String> temp=getTokens(this.clientUsername);
//            int t=0,c=0;
//            for(String s:temp){
//                if(s.equals("Login")){
//                    t=1;
//                    c++;
//                    continue;
//                }
//                if(t==1){
//                    if(c==1){
//                        this.clientUsername=s;
//                        clientHandlers.add(this);
//                    }
//
//                    c++;
//                    System.out.println(s);
//                }
//                if(c==2){
//                    //Myjdbc.StartConnection();
//
//                    Asynchronusmessag("ACKk");
//                    c=0;
//                }
//
//            }
//            System.out.println(this.clientUsername);
            //add clients info into here
            //so check the databases

            //broadcastMessage("SERVER "+clientUsername +" has entered the chat");
            clientHandlers.add(this);

        }catch (IOException e){
            closeEverything(socket,bufferedWriter,bufferedReader);
        }

    }
    public List<String> getTokens(String str) {
        List<String> tokens = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(str, ",");
        while (tokenizer.hasMoreElements()) {
            tokens.add(tokenizer.nextToken());
        }
        return tokens;
    }
    public void handleDifferentUSERREQ(String messageFromClient) throws IOException {
        List<String> temp=getTokens(messageFromClient);
                int t=0,c=0;
                for(String s:temp){
                    if(s.equals("Login")){
                        t=1;
                        c++;
                        continue;
                    }
                    else if (s.equals("Register")){
                        t=2;
                        c++;
                        continue;
                    }
                    else if(s.equals("Ques")){
                        t=3;
                        c++;
                        continue;
                    }
                    if(t==1){
                        if(c==1) this.clientUsername=s;
                        else if(c==2){


                            c=0;
                            Asynchronusmessag("ACK");
                        }
                        c++;
                        System.out.println(s);
                    }
                    else if(t==2){
                        if(c==1) this.clientUsername=s;
                        else if(c==2) System.out.println(s);
                        else if(c==3) System.out.println(s);
                        else if(c==4) {
                            c=0;
                           // Asynchronusmessag("ACK");
                            System.out.println(s);
                        }
                        c++;
                    }
                    else if(t==3){

                            System.out.println("working ques");
                            send_the_ques();

                        c=0;

                    }


                }
        if(t==3){

            System.out.println("working ques");
            send_the_ques();

            c=0;

        }
                System.out.println(this.clientUsername);
    }
        public void send_the_ques(){

            String  messageToSend="Ques";
            for(ClientHandler clientHandler: clientHandlers ){
                try {
                    if(clientHandler.clientUsername.equals(clientUsername)){
                        for(int i=0;i<5;i++){
                            System.out.println("server side ques");
                            clientHandler.bufferedWriter.write(messageToSend);
                            clientHandler.bufferedWriter.newLine();

                        }


                    }
                    clientHandler.bufferedWriter.flush();
                }catch (IOException e){
                    closeEverything(socket,bufferedWriter,bufferedReader);
                }
            }
        }

    //this is important because this thread is waiting for messages because
    //if we don't block the listing to messages then the whole program is halting here
    //this is  a parallel process to want receive the message before it could send
    @Override
    public void run() {
        String messageFromClient;
        while(socket.isConnected()){
            try {
                messageFromClient=bufferedReader.readLine();
                System.out.println("message from client"+messageFromClient);
//                List<String> temp=getTokens(messageFromClient);
//                int t=0,c=0;
//                for(String s:temp){
//                    if(s.equals("Login")){
//                        t=1;
//                        c++;
//                        continue;
//                    }
//                    if(t==1){
//                        if(c==1) this.clientUsername=s;
//                        else if(c==2){
//                            c=0;
//                            Asynchronusmessag("ACK");
//                        }
//                        c++;
//                        System.out.println(s);
//                    }
//
//
//                }
//                System.out.println(this.clientUsername);
                handleDifferentUSERREQ(messageFromClient);
                //broadcastMessage(messageFromClient);
            } catch (IOException e) {
                closeEverything(socket,bufferedWriter,bufferedReader);
                //must use break to separate the clients
                break;
            }
        }


    }
    private void Asynchronusmessag(String messagetoSend) throws IOException {
        for(ClientHandler clientHandler : clientHandlers){
            if(clientHandler.clientUsername.equals(clientUsername)){
                System.out.println("find thes client");
                clientHandler.bufferedWriter.write(messagetoSend);
                clientHandler.bufferedWriter.newLine();
                clientHandler.bufferedWriter.flush();
            }
            else {
                System.out.println("Can't find");
            }
        }
    }
    private boolean checkIfUserisPresent(){
        for(ClientHandler clientHandler : clientHandlers) {
            if (clientHandler.clientUsername.equals(clientUsername)) {
                return true;
            }
        }
        return  false;
    }
    private void broadcastMessage(String messageToSend) {
        //this the client handlers to loop through
        //what can we save in the db to check the client handler
        for(ClientHandler clientHandler: clientHandlers ){
            try {
                if(!clientHandler.clientUsername.equals(clientUsername)){
                    clientHandler.bufferedWriter.write(messageToSend);
                    clientHandler.bufferedWriter.newLine();
                    clientHandler.bufferedWriter.flush();
                }
            }catch (IOException e){
                closeEverything(socket,bufferedWriter,bufferedReader);
            }
        }
    }

    private void closeEverything(Socket socket, BufferedWriter bufferedWriter, BufferedReader bufferedReader) {
        removeClientHandler();
        try{
            if(bufferedReader!=null){
                bufferedReader.close();
            }
            if(bufferedWriter!=null)
            {
                bufferedWriter.close();
            }
            if(socket!=null){
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeClientHandler(){
        clientHandlers.remove(this);
        broadcastMessage("SERVER " +clientUsername +" has left the chat");
    }

}
