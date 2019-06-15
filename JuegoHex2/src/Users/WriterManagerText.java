/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Users;

import java.io.File;
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
public class WriterManagerText {

    private static Properties propertie;
    private static FileOutputStream output;
    private static FileInputStream input;
    

    public WriterManagerText() {
        try {
            propertie = new Properties();
            output = new FileOutputStream("Users.properties");
            input = new FileInputStream("Users.properties");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    
    public WriterManagerText getWriter(){
        return this;
    }
    
    public String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public void writerUser(String name, String password) throws FileNotFoundException, IOException {
        propertie.load(input);
        propertie.setProperty(name, getMD5(password));
        propertie.store(output, null);
    }
    
//    public void writerUser(User user) throws FileNotFoundException, IOException {
//        list.getUserList().addUser(user);
//        File file = new File("properties");
//        FileOutputStream output = new FileOutputStream(file);
//        Properties p = new Properties();
//        p.load(new FileInputStream("properties"));
//        p.setProperty("Usuarios", list.getUserList().toString());
//        p.store(output, "Nuevas propiedades");
//    }
}
