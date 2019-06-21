package Sockets;

import GUI.Ingresar;
import GUI.Registro;
import GUI.Tablero;
import GUI.Tablero2;
import GUI.VentanaPrincipal;
import GUI.WaitConnection;
import Logic.Hexagon;
import estructura.ObserverWinner;
import java.awt.Point;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Kevin Trejos
 */
public class LogicThread extends Thread {

    private Socket connection;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private Tablero tablero = new Tablero(7, this);
    private boolean continuar;
//    private WaitConnection wait = new WaitConnection(this);
//    private static boolean waiting = false;

    public LogicThread(Socket connection) {
        this.connection = connection;
        this.tablero = new Tablero(7, this);
        this.tablero.setVisible(true);
        continuar = true;
    }

    public LogicThread() {
    }

//    public boolean isWaiting() {
//        return waiting;
//    }
//
//    public void setWaiting(boolean waiting) {
//        this.waiting = waiting;
//    }
    @Override
    public void run() {

        try {
//            tablero.setVisible(true);
            getStreams();
            //mostrarTablero();
            while (continuar) {
                Thread.sleep(1000);
                //while (!Tablero2.isSalir()) {
                recibir();
                // }
                Thread.sleep(1000);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (InterruptedException ex) {
            Logger.getLogger(LogicThread.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }

    }

    public Tablero getTablero() {
        return tablero;
    }

//    private void mostrarVentana() {
//        if (Ingresar.isTocaBoton() || Registro.isTocaBoton()) {
//            wait.mostrarVentana();
//            waiting = true;
//        } else {
//            wait.dispose();
//            waiting = false;
//        }
//    }
    private void getStreams() throws IOException {
        output = new ObjectOutputStream(connection.getOutputStream());
        output.flush();
        input = new ObjectInputStream(connection.getInputStream());
    }

    public synchronized void enviar(Hexagon hexa, int jugadorWin) throws IOException, ClassNotFoundException {
        try {
            output.writeInt(jugadorWin);
            output.writeObject(hexa);
            //output.writeBoolean(continuar);no se como mandarlo
            tablero.deshabilitar();

            if (jugadorWin != 0) {
                continuar = false;
                try {
                    sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        } catch (SocketException e) {

        }
    }

    private void recibir() throws IOException, ClassNotFoundException {
        try {
            Hexagon hexa = (Hexagon) input.readObject();
            tablero.updateButtons(hexa.getPlayer(), hexa.getLocation().getX(), hexa.getLocation().getY());
            tablero.habilitar();
        } catch (SocketException e) {
            continuar = false;
            JOptionPane.showMessageDialog(null, "El jugador 2 ha abandonado el juego");
        }

    }

    private void mostrarTablero() {
        if (!Ingresar.isWaitingConnection() || !Registro.isWaitingConnection()) {
            tablero = new Tablero(7, this);
            tablero.setVisible(true);
        }
    }

    private void closeConnection() {

        if (ObserverWinner.getInstance().verifyFinishWin() != 0) {

            if (ObserverWinner.getInstance().verifyFinishWin() == 1) {

                JOptionPane.showMessageDialog(null, "S: USTED HA GANADO");
            } else {

                JOptionPane.showMessageDialog(null, "S: USTED HA PERDIDO");
            }
        }
        tablero.dispose();

        System.out.println("\nClosing connection hilo");
        try {
            output.close();
            input.close();
            connection.close();
            System.exit(0);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
