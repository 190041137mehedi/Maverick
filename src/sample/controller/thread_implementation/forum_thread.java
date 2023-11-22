package sample.thread_implementation;

import javafx.fxml.FXMLLoader;
import sample.Main;
import sample.controller.questionController;
;

import java.io.IOException;
import java.util.ArrayList;

public class forum_thread implements Runnable{
    ArrayList<String> thread_q=new ArrayList<String>();

    @Override
    public void run() {
        try {
            System.out.println("QUES");
            Main.client.req_for_ques("Ques",5);
            Main.client.q=new ArrayList<String>();
            Main.client.QC=new ArrayList<questionController>();

            for(int i=0;i<5;i++){
                String s=Main.client.get_the_ques();
                s+='1'+i;
                System.out.println(s);
                thread_q.add(s);
                Main.client.q.add(s);


//                questionController q=fxmlLoad  r;
//                q.setTitle(s);
//
//                Main.client.QC.add(q);
            }
//            for(String s1:Main.client.q){
//                System.out.println(s1);
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
