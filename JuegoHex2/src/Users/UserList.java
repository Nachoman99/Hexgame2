/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Users;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeMap;

/**
 *
 * @author Kevin Trejos
 */
public class UserList {
 
    private final HashSet<User> userList = new HashSet<>();
    private int count = 0;

    public UserList() {

    }

   //}

    public UserList getUserList() {
        return this;
    }
    
    public void addUser(User user){
        ++count;
        userList.add(user);
    }

    public int getCount() {
        return count;
    }
    
    @Override
    public String toString() {
        String str = "";
        Iterator<User> iterador = userList.iterator();
        while (iterador.hasNext()) {
            User next = iterador.next();
            str += next.toString();
        }
        return str;
    }
    
    public boolean containsUser(String userName){
        Iterator<User> iterador = userList.iterator();
        while (iterador.hasNext()) {
            User next = iterador.next();
            if (next.getName().equals(userName)) {
                return true;
            }
        }
        return false;
    }
}
