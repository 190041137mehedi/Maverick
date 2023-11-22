package sample.serverClient;

import sample.controller.questionController;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
interface Forum{

    public void req_for_ques(String str,int n) throws IOException;
    public String get_the_ques();
}

interface Login{
//    private BufferedWriter bufferedWriter_for_login;
//    private BufferedReader bufferedReader_for_login;
//    Login(Socket sampleSocket) throws IOException {
//        this.bufferedReader_for_login=new BufferedReader(new InputStreamReader(sampleSocket.getInputStream()));
//        this.bufferedWriter_for_login=new BufferedWriter(new OutputStreamWriter(sampleSocket.getOutputStream()));
//
//    }
public void VerifyUser(String Message) throws  IOException;
public String ListenforAccknowledgement() throws  IOException;
}
public class Client implements Login,Forum {
    private Socket socket;
    private BufferedWriter bufferedWriter;
    private BufferedReader bufferedReader;
    private String userName;
    private String password;
    private Thread t,tack;
    public  ArrayList<String>q ;
    public ArrayList<questionController>QC;

    public Client(Socket socket){
        try{
            this.socket=socket;
            //Client is sending//read things
            this.bufferedReader=new BufferedReader((new InputStreamReader((socket.getInputStream()))));
            //server is sending or used to send
            this.bufferedWriter=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.t=new Thread(new Runnable() {
                @Override
                public void run() {
                    String  msgFromGroupCHat;
                    while(socket.isConnected()){
                        try{
                            msgFromGroupCHat=bufferedReader.readLine();
                            System.out.println(msgFromGroupCHat);
                        }catch(IOException e){
                            closeEverything(socket,bufferedReader,bufferedWriter);
                        }
                    }
                }
            });

        }catch (IOException e){
            closeEverything(socket,bufferedReader,bufferedWriter);
        }
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName(){ return this.userName; }
    public String getPassword(){ return this.password; }
    public void sendMessage(){
        try{
            //to clienthandler newline
            bufferedWriter.write(userName);
            bufferedWriter.newLine();
            bufferedWriter.flush();
            //what the client want to send
           // Scanner scanner=new Scanner((System.in));
            //while (socket.isConnected()) {
                //String messageTosend =scanner.nextLine();
                //this is the message to send part
                bufferedWriter.write("Login"+","+userName + "," + password);
                //bufferedWriter.newLine();
                //bufferedWriter.flush();

            //}
        }catch(IOException e){
            closeEverything(socket,bufferedReader,bufferedWriter);
        }
    }
    @Override
    public void VerifyUser(String Message) throws IOException {
        bufferedWriter.write(Message);
        bufferedWriter.newLine();
        bufferedWriter.flush();
    }
    @Override
    public String ListenforAccknowledgement() throws IOException {

        String messageFromServer= null;
        try {

            messageFromServer = bufferedReader.readLine();
            System.out.println(messageFromServer);

        } catch (IOException e) {
            e.printStackTrace();
        }
        //if(messageFromServer=="ACK") tack.stop();
        //tack.setDaemon(true);
        return  messageFromServer;

    }
    //this is a blocking operation so we don not want to wait for here
    //this the message from which a client is waiting to receive some thing
    public void listenForMessage(){
//       new Thread(new Runnable() {
//            @Override
//            public void run() {
//                String  msgFromGroupCHat;
//                while(socket.isConnected()){
//                    try{
//                        msgFromGroupCHat=bufferedReader.readLine();
//                        System.out.println(msgFromGroupCHat);
//                    }catch(IOException e){
//                        closeEverything(socket,bufferedReader,bufferedWriter);
//                    }
//                }
//            }
//        }).start();
        t.start();
    }

    private void closeEverything(Socket socket,BufferedReader bufferedReader, BufferedWriter bufferedWriter) {

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

    @Override
    public void req_for_ques(String str,int n) throws IOException {

        bufferedWriter.write(str);
        bufferedWriter.newLine();
        bufferedWriter.flush();

    }

    @Override
    public String get_the_ques() {
        String messageFromServer= null;
        try {

            messageFromServer = bufferedReader.readLine();
            System.out.println(messageFromServer);

        } catch (IOException e) {
            e.printStackTrace();
        }
        //if(messageFromServer=="ACK") tack.stop();
        //tack.setDaemon(true);
        return  messageFromServer;
    }

    /*public static void main(String[] args) throws IOException {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Please enter user name");
        String username=scanner.nextLine();
        Socket socket=new Socket("localhost" ,1234);
        Client client=new Client(socket,username);
        client.listenForMessage();
        client.sendMessage();
    }*/
}
