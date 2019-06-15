/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Users;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kevin Trejos
 */
public class Main {
    
    public static void main(String[] args) {
        
        ManejoProperties manejo = new ManejoProperties();
        System.out.println(manejo.encriptar2("Hola"));
        System.out.println("1" + manejo.encriptar2("Hola"));
//        String str = "";
//        UserList list = new UserList();
//        
//        WriterManagerText writer = new WriterManagerText();
//        ReaderManagerText reader = new ReaderManagerText();
//        ArrayList<String> array = new ArrayList<>();
////        try {
////            User user = new User("Juan", "123");
////            User user2 = new User("Pedro", "456");
//////            writer.writerUser("Juan", "123");
//////            writer.writerUser("Pedro", "345");
////            //writer.writerUser("asd", "Kevin");
////        } catch (IOException ex) {
////            ex.printStackTrace();
////        }
//        try {
//            str = reader.leerUsers();
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//        System.out.println(str);
    }
}
