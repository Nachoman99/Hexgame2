/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Logic.Hexagon;
import Logic.Logic;
import Sockets.Client;
import estructura.HexagonalButton;
import estructura.ObserverWinner;
import estructura.Punto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 *
 * @author Kevin Trejos
 */
public class Tablero2 extends javax.swing.JFrame {

    private HexagonalButton[][] buttons;
    private int indicadorJugador = 2;
    private int tamaño;
    Logic logic;
    private Punto puntoActualizar;
    private Client client;
    private static Hexagon hexagonoActualizar;
    /**
     * Creates new form Tablero
     */
    public Tablero2(int tamaño, Client client) {
        initComponents();
        this.setTitle("Jugador 2");
        this.client = client;
        logic = new Logic();
        this.tamaño = tamaño;
//        ObserverWinner.getInstance().setTamanio(this.tamaño);
        setResizable(false);
        if (tamaño == 7) {
            setBounds(0, 0, 700, 500);
        } else if (tamaño == 8) {
            setBounds(0, 0, 750, 600);
        } else if (tamaño == 9) {
            setBounds(0, 0, 800, 600);
        } else if (tamaño == 10) {
            setBounds(0, 0, 850, 600);
        } else if (tamaño == 11) {
            setBounds(0, 0, 940, 650);
        } else if (tamaño == 12) {
            setBounds(0, 0, 990, 670);
        }
        buttons = new HexagonalButton[tamaño + 2][tamaño + 2];
        boardCreation(tamaño + 2);
        initializerActions(tamaño + 2);
    }

     public void updateButtons(int indicadorJugador, int x, int y) {
//         int indiceJugadorVerification = 0;
        buttons[(x+1)][(y+1)].changeColor(indicadorJugador);
//        Hexagon hexagon = new Hexagon(indicadorJugador, x, y);
//        logic.verificationPredecessor(hexagon, indicadorJugador);
        if (indicadorJugador == 1) {
//            indiceJugadorVerification = indicadorJugador;
//            logic.addHexagonTreeJ1(hexagon);
            ++indicadorJugador;

        } else if (indicadorJugador == 2) {
//            indiceJugadorVerification = indicadorJugador;
//            logic.addHexagonTreeJ2(hexagon);
            --indicadorJugador;

        }
//        ObserverWinner.getInstance().verifyWinPlayer(indiceJugadorVerification);
        repaint();
    }
    
    private void initializerActions(int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                buttons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        HexagonalButton clickedButton = (HexagonalButton) e.getSource();
                        Hexagon hexagon = new Hexagon(indicadorJugador, clickedButton.getRow(), clickedButton.getCol());
                        logic.verificationPredecessor(hexagon, indicadorJugador);

                        hexagonoActualizar = new Hexagon(indicadorJugador, clickedButton.getRow(), clickedButton.getCol());

                        try {
                            client.enviar(hexagonoActualizar);
                        } catch (IOException p) {
                            p.printStackTrace();
                        } catch (ClassNotFoundException o) {
                            o.printStackTrace();
                        }
                        
//                        if (indicadorJugador == 1) {
//                            clickedButton.changeColor(1);
////                            indiceJugadorVerification = indicadorJugador;
////                            logic.addHexagonTreeJ1(hexagon);
//                            ++indicadorJugador;
//
//                        } else 
                            if (indicadorJugador == 2) {
                            clickedButton.changeColor(2);
//                            indiceJugadorVerification = indicadorJugador;
//                            logic.addHexagonTreeJ2(hexagon);
                            //--indicadorJugador;

                        }

//                        ObserverWinner.getInstance().verifyWinPlayer(indiceJugadorVerification);
                        repaint();
                    }
                });
            }
        }
    }

     public void deshabilitar(){
        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }
    
     public void habilitar(){
        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                buttons[i][j].setEnabled(true);
            }
        }
    }
    
    private void boardCreation(int size) {
        int xStandard = 63;
        int x = 40;
        int y = 30;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                buttons[row][col] = new HexagonalButton(row - 1, col - 1);
                add(buttons[row][col]);
                buttons[row][col].setBounds(x, y, 56, 56);

                if (row == 0) {
                    buttons[0][col].colorCambiante(1);
                    buttons[0][col].setEnabled(false);
                }
                if (row == (size - 1)) {
                    buttons[(size - 1)][col].colorCambiante(1);
                    buttons[(size - 1)][col].setEnabled(false);
                }
                if (col == 0) {
                    buttons[row][0].colorCambiante(2);
                    buttons[row][0].setEnabled(false);
                }
                if (col == (size - 1)) {
                    buttons[row][(size - 1)].colorCambiante(2);
                    buttons[row][(size - 1)].setEnabled(false);
                }
                x += 45;
            }
            if (row == 0) {
                x = xStandard;
            } else {
                x = xStandard;
                x += 22 * row;
            }
            y += 40;
        }
    }

    public void getPunto(Punto punto) {
        puntoActualizar = punto;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(Tablero2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Tablero2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Tablero2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Tablero2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                Tablero2 dialog = new Tablero2(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
