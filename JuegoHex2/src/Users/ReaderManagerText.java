/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Users;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Kevin Trejos
 */
public class ReaderManagerText {
    
    private final static UserList list = new UserList();
    private static Properties propertie;
    private static FileInputStream input;
    
    public ReaderManagerText() {
        try {
            input = new FileInputStream("Users.properties");
            propertie = new Properties();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    
    public ReaderManagerText getReader(){
        return this;
    }
    
    public boolean containsUser(String userName) throws FileNotFoundException, IOException{
        propertie.load(input);
        if (propertie.getProperty(userName) != null) {
            return true;
        } else {
            return false;
        }
    }
    
//    public ArrayList leerContraseñas() throws FileNotFoundException, IOException{
//        ArrayList<String> listaNombres = new ArrayList<>();
//        Properties p = new Properties();
//        p.load(new FileInputStream("properties"));
//        Enumeration propiedades = p.keys();
//        while (propiedades.hasMoreElements()) {            
//            String propiedad = (String) propiedades.nextElement();
//            listaNombres.add(propiedad + "=" + p.get(propiedad));
//        }
//        return listaNombres;
//    }
    
    public UserList leer() throws FileNotFoundException, IOException{
        BufferedReader bf = new BufferedReader(new FileReader("properties"));
        String str = "";
        String[] parts;
        String nombre;
        String contraseña;
        while ((str = bf.readLine()) != null) {            
            parts = str.split(",");
            nombre = parts[1];
            contraseña = parts[2];
            User user = new User(nombre, contraseña);
            list.getUserList().addUser(user);
        }
        return list;
    }
    
    public String leerUsers() throws FileNotFoundException, IOException{
        String user = "";
        ArrayList<String> nameList = new ArrayList<>();
        propertie.load(input);
        Enumeration propiedades = propertie.elements();
        while (propiedades.hasMoreElements()) {            
            user = (String) propiedades.nextElement();
            nameList.add(user);
        }
        return nameList.toString();
    }
    
    public String leerPorPassword(String password) throws FileNotFoundException, IOException{
        Properties p = new Properties();
        p.load(new FileInputStream("properties"));
        return p.getProperty("Contraseña");
    }
    
    public String leerPorNombre(String name) throws FileNotFoundException, IOException{
        Properties p = new Properties();
        p.load(new FileInputStream("properties"));
        return p.getProperty("Nombre");
    }
//        FileInputStream input = null;
//        try {
//            User user = new User();
//            File file = new File("properties");
//            input = new FileInputStream(file);
//            Properties propertie = new Properties();
//            propertie.load(input);
//            String name = propertie.getProperty("Nombre");
//            return name;
//        } catch (FileNotFoundException ex) {
//            ex.printStackTrace();
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        } finally {
//            try {
//                input.close();
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//        }
//        return "";
//    }
}
