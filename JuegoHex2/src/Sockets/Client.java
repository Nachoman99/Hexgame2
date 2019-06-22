package Sockets;

import GUI.Ingresar;
import GUI.Registro;
import GUI.Tablero2;
import GUI.VentanaPrincipal;
import GUI.WaitConnection;
import Logic.Hexagon;
import estructura.HexagonCommunication;
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
    private int jugadorWinner = 0;

    public boolean isWaiting() {
        return waiting;
    }

    public void setWaiting(boolean waiting) {
        this.waiting = waiting;
    }

    public synchronized void runClient() {
        this.mainWindow = new VentanaPrincipal();
        mainWindow.setVisible(true);
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
            wait.setVisible(false);
            tablero = new Tablero2(7, this);
            tablero.setVisible(true);
            while (continuar) {

                recibir();
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

    public void enviar(HexagonCommunication hexa) throws IOException, ClassNotFoundException {
        output.writeObject(hexa);
        tablero.deshabilitar();
    }

    private void recibir() throws IOException, ClassNotFoundException {
        try {
            int jugadorWin = input.readInt();
            if (jugadorWin != 0) {

                if (jugadorWin == 1) {
                    jugadorWinner = 1;
                } else {
                    jugadorWinner = 2;
                }
                continuar = false;
            }
            HexagonCommunication hexa = (HexagonCommunication) input.readObject();
            if (hexa != null) {
                tablero.updateButtons(hexa.getPlayer(), hexa.getLocation().getX(), hexa.getLocation().getY());
                tablero.habilitar();
            }
        } catch (SocketException e) {
            continuar = false;
            JOptionPane.showMessageDialog(null, "El jugador 1 a abandonado el juego");
        }

    }

    private void connectToServer() throws IOException {
        wait.setVisible(true);
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
                conectado = true;
            }
        }
    }

    private void getStreams() throws IOException {
        try {
            output = new ObjectOutputStream(client.getOutputStream());
            output.flush();
            input = new ObjectInputStream(client.getInputStream());
        } catch (SocketException e) {
            wait.dispose();
            JOptionPane.showMessageDialog(null, "Se perdió la conexión con el servidor");
            System.exit(0);
        }
    }

    private void closeConnection() {

        if (jugadorWinner == 1) {
            JOptionPane.showMessageDialog(null, "USTED HA PERDIDO");
        }

        if (jugadorWinner == 2) {
            JOptionPane.showMessageDialog(null, "USTED HA GANADO");
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
