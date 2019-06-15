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
    private VentanaPrincipal principal;
    private WaitConnection wait;
    private boolean ingresar = false;
    private boolean registro = false;

    public void runServer() {
        try {
//            Ingresar.setWaitingConnection(true);
//            Registro.setWaitingConnection(true);
//            this.principal = new VentanaPrincipal();
//            principal.setVisible(true);
            server = new ServerSocket(PORT);
//            while (true) {
//                if (!ingresar && !registro) {
//                    ingresar = Ingresar.getIniciarEspera();
//                    registro = Registro.getIniciarEspera();
//                } else {
//                    
                    
//                }
//            }
            waitForConnection();
            new LogicThread(connection).start();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            closeServer();
        }
    }

    private void waitForConnection() throws IOException {
        System.out.println("Waiting for connection...\n");
        Ingresar.setWaitingConnection(false);
        Registro.setWaitingConnection(false);
        connection = server.accept();
        System.out.println("Connection received from: " + connection.getInetAddress().getHostName());

    }

    private void closeServer() {
        System.out.println("\nTerminating server");
        try {
            server.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Server().runServer();
    }
}
