/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sockets;

import GUI.Ingresar;
import GUI.Registro;
import GUI.Tablero;
import GUI.Tablero2;
import GUI.VentanaPrincipal;
import GUI.WaitConnection;
import Logic.Hexagon;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
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
    int jugadorWinner = 0;

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
            tablero = new Tablero2(7, this);
            tablero.setVisible(true);
            while (continuar) {
                while (!Tablero.isSalir()) {                    
                    recibir();
                }
                
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
        try {
            int jugadorWin = input.readInt();
            if (jugadorWin != 0) {

                if (jugadorWin == 1) {
                    jugadorWinner = 1;
                    System.out.println("Hola");
                } else {
                    System.out.println("Hola2");
                    jugadorWinner = 2;
                }
                continuar = false;
            }
            Hexagon hexa = (Hexagon) input.readObject();
            if (hexa != null) {
                tablero.updateButtons(hexa.getPlayer(), hexa.getLocation().getX(), hexa.getLocation().getY());
                tablero.habilitar();
            }
        } catch (SocketException e) {
            continuar = false;
            JOptionPane.showMessageDialog(null, "El jugador 1 a abandonado el juego");
           // System.exit(0);
        }

    }

    private void connectToServer() throws IOException {
        boolean conectado = false;
        System.out.println("Attempting connection\n");
        while (!conectado) {
            try {
                client = new Socket(HOST, PORT);
            } catch (ConnectException e) {
                System.out.println("esperando server");
            }

            if (client != null) {
                System.out.println("Connected to: " + client.getInetAddress().getHostName());
//                tablero = new Tablero2(7, this);
//                tablero.setVisible(true);
                conectado = true;
            }
        }

    }


    private void getStreams() throws IOException {
        output = new ObjectOutputStream(client.getOutputStream());
        output.flush();
        input = new ObjectInputStream(client.getInputStream());
    }

    private void closeConnection() {

        if (jugadorWinner == 1) {
            JOptionPane.showMessageDialog(null, "C: Gano Jugador 1");
        }

        if (jugadorWinner == 2) {
            JOptionPane.showMessageDialog(null, "C: Gano Jugador 2");
        }

        tablero.dispose();

        System.out.println("\nClosing connection Client");
        try {
            output.close();
            input.close();
            client.close();
            System.exit(0);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Client().runClient();
    }
}
