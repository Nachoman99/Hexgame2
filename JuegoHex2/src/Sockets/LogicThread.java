package Sockets;

import GUI.Ingresar;
import GUI.Registro;
import GUI.Tablero;
import GUI.Tablero2;
import GUI.VentanaPrincipal;
import GUI.WaitConnection;
import Logic.Hexagon;
import estructura.HexagonCommunication;
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

    @Override
    public void run() {

        try {

            getStreams();
            while (continuar) {
                Thread.sleep(1000);
                recibir();
                Thread.sleep(1000);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection();
        }

    }

    private void getStreams() throws IOException {
        output = new ObjectOutputStream(connection.getOutputStream());
        output.flush();
        input = new ObjectInputStream(connection.getInputStream());
    }

    public synchronized void enviar(HexagonCommunication hexa, int jugadorWin) throws IOException, ClassNotFoundException {
        try {
            output.writeInt(jugadorWin);
            output.writeObject(hexa);
            tablero.deshabilitar();

            if (jugadorWin != 0) {

                continuar = false;
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    private void recibir() throws IOException, ClassNotFoundException {
        try {
            HexagonCommunication hexa = (HexagonCommunication) input.readObject();
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

        if (ObserverWinner.getInstance().verifyFinishWin() == 1) {

            JOptionPane.showMessageDialog(null, "USTED HA GANADO");
        } else {

            JOptionPane.showMessageDialog(null, "USTED HA PERDIDO");
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
