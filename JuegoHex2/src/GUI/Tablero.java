/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Logic.Hexagon;
import Logic.Logic;
import Sockets.LogicThread;
import Sockets.Server;
import estructura.HexagonalButton;
import estructura.ObserverWinner;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Nacho
 */
public class Tablero extends javax.swing.JFrame {

    private HexagonalButton[][] buttons;
    private int indicadorJugador = 1;
    private int tamaño;
    Logic logic;
    private static Hexagon hexagonoActualizar;
    private LogicThread connector;

    /**
     * Creates new form Tablero
     */
    public Tablero(int tamaño, LogicThread conector) {
        initComponents();
        this.setTitle("Jugador 1");
        this.connector = conector;
        logic = new Logic();
        this.tamaño = tamaño;
        ObserverWinner.getInstance().setTamanio(this.tamaño);
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
        int indiceJugadorVerification = 0;
        buttons[(x + 1)][(y + 1)].changeColor(indicadorJugador);
        Hexagon hexagon = new Hexagon(indicadorJugador, x, y);
        logic.verificationPredecessor(hexagon, indicadorJugador);
        if (indicadorJugador == 1) {
            indiceJugadorVerification = indicadorJugador;
            logic.addHexagonTreeJ1(hexagon);
            ++indicadorJugador;

        } else if (indicadorJugador == 2) {
            indiceJugadorVerification = indicadorJugador;
            logic.addHexagonTreeJ2(hexagon);
            --indicadorJugador;

        }
        ObserverWinner.getInstance().verifyWinPlayer(indiceJugadorVerification);

        if (ObserverWinner.getInstance().verifyFinishWin() != 0) {

            if (ObserverWinner.getInstance().verifyFinishWin() == 1) {

                JOptionPane.showMessageDialog(null, "Gano el jugador 1");
            } else {

                JOptionPane.showMessageDialog(null, "Gano el jugador 2");
            }
        }

        repaint();
    }

    private void initializerActions(int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                buttons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int indiceJugadorVerification = 0;
                        HexagonalButton clickedButton = (HexagonalButton) e.getSource();
                        Hexagon hexagon = new Hexagon(indicadorJugador, clickedButton.getRow(), clickedButton.getCol());
                        logic.verificationPredecessor(hexagon, indicadorJugador);

                        if (indicadorJugador == 1) {
                            clickedButton.changeColor(1);
                            indiceJugadorVerification = indicadorJugador;
                            logic.addHexagonTreeJ1(hexagon);
                            //++indicadorJugador;

                        }
//                        else if (indicadorJugador == 2) {
//                            clickedButton.changeColor(2);
//                            indiceJugadorVerification = indicadorJugador;
//                            logic.addHexagonTreeJ2(hexagon);
//                            --indicadorJugador;
//
//                        }

                        ObserverWinner.getInstance().verifyWinPlayer(indiceJugadorVerification);
                        hexagonoActualizar = new Hexagon(indicadorJugador, clickedButton.getRow(), clickedButton.getCol());

                        try {
                            connector.enviar(hexagonoActualizar, ObserverWinner.getInstance().verifyFinishWin());
                            //connector.enviarJugadorWin(ObserverWinner.getInstance().verifyFinishWin());
                        } catch (IOException p) {
                            p.printStackTrace();
                        } catch (ClassNotFoundException o) {
                            o.printStackTrace();
                        }

                        if (ObserverWinner.getInstance().verifyFinishWin() != 0) {

                            if (ObserverWinner.getInstance().verifyFinishWin() == 1) {

                                JOptionPane.showMessageDialog(null, "Gano el jugador 1");
                            } else {

                                JOptionPane.showMessageDialog(null, "Gano el jugador 2");
                            }
                        }
                        repaint();
                    }
                });
            }
        }
    }

    public void deshabilitar() {
        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }

    public void habilitar() {
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 744, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

//    public static void main(String[] args) {
//        Tablero tablero = new Tablero(7);
//        tablero.setVisible(true);
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
