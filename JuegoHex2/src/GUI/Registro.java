/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Users.ManejoProperties;
import Users.ReaderManagerText;
import Users.User;
import Users.UserFactory;
import Users.UserList;
import Users.Users;
import Users.WriterManagerText;
import java.awt.HeadlessException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Kevin Trejos
 */
public class Registro extends javax.swing.JDialog {

    private static boolean tocaBoton = false;
    private static boolean waitingConnection = false;
    private static boolean iniciarEspera = false;
    UserList list = new UserList();
    ManejoProperties prop = new ManejoProperties();
    boolean ID = false;
    boolean password = false;
    private static WaitConnection wait = new WaitConnection(null, true);

    //private int sizeGame;
    /**
     * Creates new form Registro
     */
    public Registro(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(parent);

        closeX();
    }

    public static boolean isTocaBoton() {
        return tocaBoton;
    }

    public static void setTocaBoton(boolean tocaBoton) {
        Registro.tocaBoton = tocaBoton;
    }

    public static boolean getIniciarEspera() {
        return iniciarEspera;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tfID = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tfContraseña = new javax.swing.JPasswordField();
        btnAtras = new javax.swing.JButton();
        btnRegistro = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setText("Por favor ingrese los datos solicitados");

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel2.setText("Ingrese un id");

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel3.setText("Ingrese una contraseña");

        btnAtras.setText("Atrás");
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });

        btnRegistro.setText("Registrarse");
        btnRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(jLabel1)
                        .addGap(0, 56, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfID)
                            .addComponent(tfContraseña, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE))))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(btnAtras)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRegistro)
                .addGap(63, 63, 63))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tfID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(66, 66, 66)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tfContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAtras)
                    .addComponent(btnRegistro))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        VentanaPrincipal principal = new VentanaPrincipal();
        this.dispose();
        principal.setVisible(true);
    }//GEN-LAST:event_btnAtrasActionPerformed

    public static boolean isWaitingConnection() {
        return waitingConnection;
    }

    public static void setWaitingConnection(boolean waitingConnection) {
        Registro.waitingConnection = waitingConnection;
    }

    private void btnRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistroActionPerformed
        //prop.crearArchivo();
        if (prop.archivoExiste()) {
            verifyID();
            verifyPassword();
            if (ID != true && password != true) {
                try {
                    if (!prop.containsUser(tfID.getText())) {
                        Users userFactory = new UserFactory();
                        User user = userFactory.createUser(tfID.getText(), Arrays.toString(tfContraseña.getPassword()));
                        prop.writerUser(user);
                        iniciarEspera = false;
                        this.dispose();
                        tocaBoton = true;
                        if (waitingConnection) {
                            wait.setVisible(true);
//                        waitingConnection = true;
                        } else if (!waitingConnection) {
                            wait.setVisible(false);
                        }
                        //sizeTablero();
//                    new Tablero(7).setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "La id ya está en uso, por favor use otra");
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } else {
            prop.crearArchivo();
            verifyID();
            verifyPassword();
            if (ID != true && password != true) {
                try {
                    if (!prop.containsUser(tfID.getText())) {
                        Users userFactory = new UserFactory();
                        User user = userFactory.createUser(tfID.getText(), Arrays.toString(tfContraseña.getPassword()));
                        prop.writerUser(user);
                        iniciarEspera = false;
                        this.dispose();
                        tocaBoton = true;
                        if (waitingConnection) {
                            wait.setVisible(true);
//                        waitingConnection = true;
                        } else if (!waitingConnection) {
                            wait.setVisible(false);
                        }
                        //sizeTablero();
//                    new Tablero(7).setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "La id ya está en uso, por favor use otra");
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

    }//GEN-LAST:event_btnRegistroActionPerformed

//    private void sizeTablero() {
//        boolean continu = false;
//        while (continu == false) {
//            try {
//                int size = Integer.parseInt(JOptionPane.showInputDialog("Digite el tamaño del tablero"));
//                if (size < 7 || size > 12) {
//                    continu = false;
//                    JOptionPane.showMessageDialog(this, "Sólo se pueden digitar números entre 7 y 12");
//                } else {
//                    continu = true;
//                    new Tablero(size).setVisible(true);
//                }
//            } catch (HeadlessException | NumberFormatException e) {
//                JOptionPane.showMessageDialog(this, "Por favor digite sólo números");
//            }
//        }
//    }
//    public int getSizeGame() {
//        return sizeGame;
//    }
//
//    public void setSizeGame(int sizeGame) {
//        this.sizeGame = sizeGame;
//    }
    private void verifyID() {
        if (tfID.getText().length() < 4) {
            JOptionPane.showMessageDialog(this, "La ID no puede tener una extensión menor a 4 caracteres");
            ID = true;
        } else if (tfID.getText().length() > 6) {
            JOptionPane.showMessageDialog(this, "La ID no puede ser mayor a 6 caracteres");
            ID = true;
        } else if (firstCharIsNumber(tfID.getText())) {
            JOptionPane.showMessageDialog(this, "La ID no puede iniciar con un número");
            ID = true;
        } else if (!isLetter(tfID.getText())) {
            JOptionPane.showMessageDialog(this, "La ID no puede contener caracteres especiales");
            ID = true;
        } else {
            ID = false;
        }
    }

    public static void windowWait(boolean window) {
        if (window) {
            wait.setVisible(true);
            waitingConnection = true;
        } else {
            waitingConnection = false;
            wait.setVisible(false);
        }
    }

    private void verifyPassword() {
        if (tfContraseña.getPassword().length < 4) {
            JOptionPane.showMessageDialog(this, "La contraseña no puede ser menor a 4 caracteres");
            password = true;
        } else if (tfContraseña.getPassword().length > 8) {
            JOptionPane.showMessageDialog(this, "La contraseña no puede ser mayor a 8 caracteres");
            password = true;
        } else if (!passwordIsLetter(tfContraseña.getPassword())) {
            JOptionPane.showMessageDialog(this, "No se pueden digitar caracteres especiales en la contraseña");
            password = true;
        } else {
            password = false;
        }
    }

    private void closeX() {
        try {
            this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    confirm();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isNumber(String str) {
        char c;
        boolean numberB = false;
        for (int i = 0; i < str.length(); i++) {
            c = str.charAt(i);
            if (charIsNumber(c)) {
                numberB = true;
            } else {
                numberB = false;
            }
        }
        return numberB;
    }

    private boolean charIsNumber(char c) {
        int number;
        String str = Character.toString(c);
        try {
            number = Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean passwordIsNumber(char[] password) {
        int number;
        String str = "";
        try {
            for (int i = 0; i < password.length; i++) {
                str = Character.toString(password[i]);
                number = Integer.parseInt(str);
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean firstCharIsNumber(String str) {
        char c = str.charAt(0);
        if (charIsNumber(c)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean passwordIsLetter(char[] password) {
        int count = 0;
        for (int i = 0; i < password.length; i++) {
            if (Character.isLetter(password[i])) {
                ++count;
            } else {
                if (!passwordIsNumber(password)) {
                    --count;
                } else {
                    ++count;
                }
            }
        }
        if (count == password.length) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isLetter(String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (Character.isLetter(c)) {
                ++count;
            } else {
                if (isNumber(str)) {
                    ++count;
                } else {
                    --count;
                }
            }
        }
        System.out.println("Exxx" + count);
        if (count == str.length()) {
            return true;
        } else {
            return false;
        }
    }

    private void confirm() {
        VentanaPrincipal principal = new VentanaPrincipal();
        this.dispose();
        principal.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnRegistro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPasswordField tfContraseña;
    private javax.swing.JTextField tfID;
    // End of variables declaration//GEN-END:variables
}
