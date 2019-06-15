/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Users;

/**
 *
 * @author Kevin Trejos
 */
public class UserFactory implements Users{

    @Override
    public User createUser(String ID, String password) {
        return new User(ID, password);
    }
    
    
}
