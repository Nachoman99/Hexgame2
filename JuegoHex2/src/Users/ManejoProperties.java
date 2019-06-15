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
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Kevin Trejos
 */
public class ManejoProperties {

    private static final String clave = "Juego Hex";
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

    public void writerUser(User user) throws FileNotFoundException, IOException {
        output = new FileOutputStream("Users.properties");
        input = new FileInputStream("Users.properties");
        propertie.load(input);
        propertie.setProperty(user.getName(), encriptar2(user.getPassword()));
        propertie.store(output, null);
    }

    public boolean containsUser(String userName) throws FileNotFoundException, IOException {
        input = new FileInputStream("Users.properties");
        propertie.load(input);
        if (propertie.getProperty(userName) != null) {
            return true;
        } else {
            return false;
        }
    } 
    
    public String encriptar2(String encript){
        try { 
            MessageDigest md = MessageDigest.getInstance("MD5"); 
            byte[] messageDigest = md.digest(encript.getBytes()); 
            BigInteger no = new BigInteger(1, messageDigest); 
            String hashtext = no.toString(16); 
            while (hashtext.length() < 32) { 
                hashtext = "0" + hashtext; 
            } 
            return hashtext; 
        }  
        catch (NoSuchAlgorithmException e) { 
            throw new RuntimeException(e); 
        } 
    }

    public boolean verifyPassword(String userName, String password) {
        try {
            input = new FileInputStream("Users.properties");
            propertie.load(input);
            if (propertie.getProperty(userName) != null) {
                if (propertie.getProperty(userName).equals(password)) {
                    return true;
                }
            }
            return false;
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
