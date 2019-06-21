/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sockets;

import GUI.Ingresar;
import GUI.Registro;
import GUI.Tablero2;
import GUI.VentanaPrincipal;
import GUI.WaitConnection;
import Logic.Hexagon;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author Kevin Trejos
 */
public class Client {

    private ObjectInputStream input;
    private ObjectOutputStream output;
    private Socket client;
    private final String HOST = "127.0.0.1";
    private final int PORT = 12345;
    private VentanaPrincipal mainWindow;
    private Tablero2 tablero = new Tablero2(7, this);
    private boolean continuar = true;
    private WaitConnection wait = new WaitConnection(this);
    private boolean waiting = false;

    public boolean isWaiting() {
        return waiting;
    }

    public void setWaiting(boolean waiting) {
        this.waiting = waiting;
    }

    public synchronized void runClient() {
        this.mainWindow = new VentanaPrincipal();
        mainWindow.setVisible(true);
//        while (!waiting) {
//            mostrarWaiting();
//        }
        while (!waiting) {
            waiting = Ingresar.isTocaBoton();
            if (waiting == false) {
                waiting = Registro.isTocaBoton();
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            connectToServer();
            getStreams();
//            tablero.deshabilitar();
            while (continuar) {
                recibir();
//                System.out.println("Recibe cliente");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    private void mostrarWaiting() {
        if (Ingresar.isTocaBoton() || Registro.isTocaBoton()) {
            wait.mostrarVentana();
            waiting = true;
        } else {
            wait.dispose();
            waiting = false;
        }
    }

    public void enviar(Hexagon hexa) throws IOException, ClassNotFoundException {
        output.writeObject(hexa);
        //output.writeBoolean(continuar);no se cooo mandarlo
        tablero.deshabilitar();
    }

    private void recibir() throws IOException, ClassNotFoundException {

        int jugadorWin = input.readInt();

        if (jugadorWin != 0) {

            if (jugadorWin == 1) {
                JOptionPane.showMessageDialog(null, "Gano Jugador 1");
            } else {
                JOptionPane.showMessageDialog(null, "Gano Jugador 2");
            }

        }

        Hexagon hexa = (Hexagon) input.readObject();
        if (hexa != null) {
            //JOptionPane.showMessageDialog(null, hexa);
            tablero.updateButtons(hexa.getPlayer(), hexa.getLocation().getX(), hexa.getLocation().getY());

            //JOptionPane.showMessageDialog(null, jugadorWin);
            tablero.habilitar();
        }
    }

    private void connectToServer() throws IOException {
        System.out.println("Attempting connection\n");
        client = new Socket(HOST, PORT);
        System.out.println("Connected to: " + client.getInetAddress().getHostName());
        tablero = new Tablero2(7, this);
        tablero.setVisible(true);
    }

    private void getStreams() throws IOException {
        output = new ObjectOutputStream(client.getOutputStream());
        output.flush();
        input = new ObjectInputStream(client.getInputStream());
    }

    private void closeConnection() {
        System.out.println("\nClosing connection");
        try {
            output.close();
            input.close();
            client.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Client().runClient();
    }
}
