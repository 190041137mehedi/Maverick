package sample.mailapiplusjdbc;


import java.sql.*;

public class Myjdbc {
    public static boolean IfAPersonMatches(String UserName, String Password, Connection connection) throws SQLException{
        String query="Select * from employee.employee_info where Employee_User_Name=? and Employee_passWord=?";
        PreparedStatement pa= connection.prepareStatement(query);
        //pa.setInt(1,3);
//        StringBuilder sb=new StringBuilder(UserName);
//        sb.insert(0,'\'');
//        sb.insert(UserName.length()-1,'\'');
//        StringBuilder sb1=new StringBuilder(Password);
//        sb1.insert(0,'\'');
//        sb1.insert(Password.length()-1,'\'');

        pa.setString(1,UserName);
        pa.setString(2,Password);
        ResultSet r=pa.executeQuery();
        if(r.next()) return true;
        else {
            return  false;
        }
    }
    public static boolean InsertPassAndUser(String UserName, String Password, Connection connection) throws SQLException {
        String query="INSERT INTO employee.employee_info (Employee_User_Name,Employee_passWord) VALUES(?,?)";
        PreparedStatement pa= connection.prepareStatement(query);
        //pa.setInt(1,3);
        pa.setString(1,UserName);
        pa.setString(2,Password);
        pa.executeUpdate();
        return  true;
    }
    public  static void  StartConnection(){
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee", "root", "abc.abc.2");
            Statement statement = connection.createStatement();
            //boolean n=InsertPassAndUser("jhdss","234d", connection);
            //System.out.println(n);
            boolean v=IfAPersonMatches("jhdss","234",connection);
            if(v) System.out.println("Exists");
            else System.out.println("Can't find");
            ResultSet resultSet = statement.executeQuery("select * from employee_info");
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + " " + resultSet.getString(2)+" "+resultSet.getString(3));
            }


        }catch (Exception e){
            e.printStackTrace();
        }
    }
//    public static void main(String[] args) {
//        StartConnection();
//    }
}
