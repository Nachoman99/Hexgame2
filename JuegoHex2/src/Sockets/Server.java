/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sockets;

import GUI.Ingresar;
import GUI.Registro;
import GUI.VentanaPrincipal;
import GUI.WaitConnection;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author Kevin Trejos
 */
public class Server {

    private ServerSocket server;
    private Socket connection;
    private final int PORT = 12345;
    private WaitConnection wait = new WaitConnection(this);
    private static boolean waiting = false;

    public synchronized void runServer() {
        try {
            server = new ServerSocket(PORT);
            new VentanaPrincipal().setVisible(true);
            
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
            waitForConnection();
            new LogicThread(connection).start();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            closeServer();
        }
    }

    private void waitForConnection() throws IOException {
        wait.setVisible(true);
        System.out.println("Waiting for connection...\n");
        connection = server.accept();
        System.out.println("Connection received from: " + connection.getInetAddress().getHostName());
        wait.setVisible(false);

    }

    private void closeServer() {

        System.out.println("\nTerminating server");
        try {
            server.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public boolean isWaiting() {
        return waiting;
    }

    public void setWaiting(boolean waiting) {
        this.waiting = waiting;
    }

    public static void main(String[] args) {
        new Server().runServer();
    }
}
