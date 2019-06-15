package Sockets;

import GUI.Ingresar;
import GUI.Registro;
import GUI.Tablero;
import GUI.VentanaPrincipal;
import Logic.Hexagon;
import java.awt.Point;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kevin Trejos
 */
public class LogicThread extends Thread {

    private Socket connection;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private Tablero tablero;
    private boolean continuar = true;

    public LogicThread(Socket connection) {
        this.connection = connection;
//        this.tablero = new Tablero(7,this);
//        this.tablero.setVisible(true);
    }

    @Override
    public void run() {
        try {
            Ingresar.setWaitingConnection(false);
            Registro.setWaitingConnection(false);
            getStreams();
            mostrarTablero();
            while (continuar) {
                recibir();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    private void getStreams() throws IOException {
        output = new ObjectOutputStream(connection.getOutputStream());
        output.flush();
        input = new ObjectInputStream(connection.getInputStream());
        Ingresar.setWaitingConnection(false);
        Registro.setWaitingConnection(false);
    }

    public synchronized void enviar(Hexagon hexa, int jugadorWin) throws IOException, ClassNotFoundException {
        output.writeInt(jugadorWin);
        output.writeObject(hexa);
        //output.writeBoolean(continuar);no se como mandarlo
        tablero.deshabilitar();
    }
//
//    public synchronized void enviarJugadorWin(int jugadorWin) throws IOException, ClassNotFoundException {
//        output.writeInt(jugadorWin);
//        //output.writeBoolean(continuar);no se como mandarlo
//        //tablero.deshabilitar();
//    }

    private void recibir() throws IOException, ClassNotFoundException {
        Hexagon hexa = (Hexagon) input.readObject();
        tablero.updateButtons(hexa.getPlayer(), hexa.getLocation().getX(), hexa.getLocation().getY());
        tablero.habilitar();
    }

    private void mostrarTablero() {
        if (!Ingresar.isWaitingConnection() || !Registro.isWaitingConnection()) {
            tablero = new Tablero(7, this);
            tablero.setVisible(true);
        }
    }

    private void closeConnection() {
        System.out.println("\nClosing connection");
        try {
            output.close();
            input.close();
            connection.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
