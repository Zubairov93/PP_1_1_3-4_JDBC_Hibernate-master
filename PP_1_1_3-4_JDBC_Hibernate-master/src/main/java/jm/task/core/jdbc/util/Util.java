package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private static final String USERNAME = "root";
    private static final String PASSWORD = "12345678";
    private static final String URL = "jdbc:mysql://localhost:3306/sys";
    public static  Connection connection;



   public static Connection getConnection(){
       Connection useConnection = connection;
       try {
           if(useConnection == null) {
               synchronized (Connection.class){
                   useConnection = connection;
                   if(useConnection == null){
                       connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                       useConnection = connection;
                       System.out.println("Соединение произошло");
                   }
               }
           }

       } catch (SQLException e){
           e.printStackTrace();
           System.out.println("Ошибка соединения");
       }
       return useConnection;

   }


}
