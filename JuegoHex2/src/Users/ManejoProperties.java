/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Users;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kevin Trejos
 */
public class ManejoProperties {

    private static Properties propertie = new Properties();
    private static FileOutputStream output;
    private static FileInputStream input;

//    public String encriptar(String input) {
//        try {
//            MessageDigest md = MessageDigest.getInstance("MD5");
//            byte[] messageDigest = md.digest(input.getBytes());
//            BigInteger number = new BigInteger(1, messageDigest);
//            String hashtext = number.toString(16);
//
//            while (hashtext.length() < 32) {
//                hashtext = "0" + hashtext;
//            }
//            return hashtext;
//        } catch (NoSuchAlgorithmException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public boolean archivoExiste(){
        try {
            input = new FileInputStream("Users.properties");
            return true;
        } catch (FileNotFoundException ex) {
            return false;
        }
    }
    
    public void crearArchivo() {
        try {
            output = new FileOutputStream("Users.properties");
            input = new FileInputStream("Users.properties");
        } catch (FileNotFoundException ex) {
            System.out.println("EL archivo no se pudo crear");
        }
    }
    
    public void writerUser(User user) throws FileNotFoundException, IOException {
       // propertie = new Properties();
        output = new FileOutputStream("Users.properties");
        input = new FileInputStream("Users.properties");
        propertie.load(input);
        propertie.setProperty(user.getName(), encriptar2(user.getPassword()));
        propertie.store(output, null);
    }

    public boolean containsUser(String userName) throws FileNotFoundException, IOException {
        //propertie = new Properties();
        try {
            input = new FileInputStream("Users.properties");
        } catch (FileNotFoundException e) {
            output = new FileOutputStream("Users.properties");
            input = new FileInputStream("Users.properties");
        }
        propertie.load(input);
        if (propertie.getProperty(userName) != null) {
            return true;
        } else {
            return false;
        }
    } 
    
    public String encriptar2(String encript) {
        try { 
            MessageDigest md = MessageDigest.getInstance("MD5"); 
            byte[] messageDigest = md.digest(encript.getBytes()); 
            BigInteger no = new BigInteger(1, messageDigest); 
            String hashtext = no.toString(16); 
            while (hashtext.length() < 32) { 
                hashtext = "0" + hashtext; 
            } 
            return hashtext; 
        }  catch (NoSuchAlgorithmException e) { 
            throw new RuntimeException(e); 
        }
        
    }

    public boolean verifyPassword(String userName, String password) {
        try {
            try {
                input = new FileInputStream("Users.properties");
            } catch (FileNotFoundException e) {
                output = new FileOutputStream("Users.properties");
                input = new FileInputStream("Users.properties");
            }
            propertie.load(input);
            if (propertie.getProperty(userName) != null) {
                if (propertie.getProperty(userName).equals(password)) {
                    return true;
                }
            }
            return false;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
