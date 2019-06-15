package estructura;

import Logic.ComponentInterface;
import Logic.Hexagon;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author cocau
 */
public class ObserverWinner {

    private static ObserverWinner instanceobserver = new ObserverWinner();
    private int tamanio = 0;
    private int jugadorWin = 0;

    private ObserverWinner() {
    }

    public static ObserverWinner getInstance() {
        return instanceobserver;
    }

    public void setTamanio(int t) {

        this.tamanio = t - 1;
    }

    public void verifyWinPlayer(int indicadorJugador) {

        updateTree(indicadorJugador);
        ArrayList<ComponentInterface> listCabezasVerification = new ArrayList<ComponentInterface>();

        if (indicadorJugador == 1) {

            listCabezasVerification = Logic.Logic.getListHexagonsCabezasJ1();
        } else {

            listCabezasVerification = Logic.Logic.getListHexagonsCabezasJ2();
        }

        String infoPredecessor = "";
        String indiceString = "";
        Hexagon hexagonActual = null;
        String vec[] = null;
        int indice = 0;
        boolean cumple = true;

        for (int k = 0; k < listCabezasVerification.size(); k++) {

            cumple = true;
            indice = 0;
            hexagonActual = (Hexagon) listCabezasVerification.get(k);

            if (indicadorJugador == 1) {
                infoPredecessor = hexagonActual.listLocationX();
            } else {
                infoPredecessor = hexagonActual.listLocationY();
            }

            vec = infoPredecessor.split(",");
            while (cumple) {

                cumple = false;
                indiceString = indice + "";
                for (int i = 0; i < vec.length; i++) {

                    if (vec[i].equals(indiceString)) {
                        cumple = true;
                        i = vec.length;

                        if (indice == tamanio) {

                            jugadorWin = indicadorJugador;
                            cumple = false;
                        }
                    }

                }
                indice++;
            }

        }
    }

    public int verifyFinishWin() {

        return jugadorWin;
    }

    private void updateTree(int indicadorJugador) {
        ArrayList<ComponentInterface> listHexagonsVerification = new ArrayList<ComponentInterface>();

        if (indicadorJugador == 1) {

            listHexagonsVerification = Logic.Logic.getListHexagonsJ1();
        } else {

            listHexagonsVerification = Logic.Logic.getListHexagonsJ2();
        }

        if (!listHexagonsVerification.isEmpty()) {

            boolean continuarBusqueda = true;
            Hexagon hexagon = null;

            while (continuarBusqueda) {

                continuarBusqueda = false;
                for (int i = 0; i < listHexagonsVerification.size(); i++) {

                    hexagon = (Hexagon) listHexagonsVerification.get(i);
                    Logic.Logic.verificationPredecessor(hexagon, indicadorJugador);

                    if (hexagon.isHasPredecessor()) {
                        listHexagonsVerification.remove(hexagon);
                        continuarBusqueda = true;
                    }
                }
            }

        }
    }

}
