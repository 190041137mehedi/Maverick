package sample.controller;

import java.io.File;

public class settingsController {
    public void changeTheme() {
        File file = new File("../css/style.css");
        File rename = new File("../css/temp.css");
        boolean flag1 = file.renameTo(rename);

        /*
        file = new File("../css/style2.css");
        rename = new File("../css/style.css");
        boolean flag2 = file.renameTo(rename);

        file = new File("../css/temp.css");
        rename = new File("../css/style2.css");
        boolean flag3 = file.renameTo(rename);

        if (flag1==true && flag2==true && flag3==true)
            System.out.println("File Successfully Rename");
        else
            System.out.println("Operation Failed");
        System.out.println("flag1:" + flag1 + " flag2:" + flag2 + " flag3:" + flag3);

         */
    }
}
