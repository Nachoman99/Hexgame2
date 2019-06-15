package juego.hex;

import GUI.FrameJuego;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author cocau
 */
public class Controller implements ActionListener {

    FrameJuego frameJuego;
    int indicadorJugador = 1;

    public Controller() {

        frameJuego = new FrameJuego();
        initializerActions();
    }

    public void initializerActions() {

        for (int i = 0; i < 7; i++) {

            for (int j = 0; j < 7; j++) {

                frameJuego.getMatrizButt()[i][j].addActionListener(this);
            }

        }
    }

    public void actionPerformed(ActionEvent e) {

        for (int i = 0; i < 7; i++) {

            for (int j = 0; j < 7; j++) {

                if (e.getSource() == frameJuego.getMatrizButt()[i][j]) {

                    if (indicadorJugador == 1) {

                        frameJuego.getMatrizButt()[i][j].setBackground(Color.RED);
                        indicadorJugador++;
                    } else if (indicadorJugador == 2) {
                        frameJuego.getMatrizButt()[i][j].setBackground(Color.BLUE);
                        indicadorJugador--;
                    }

                }

            }

        }

    }

}
