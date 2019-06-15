package Logic;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author cocau
 */
public class Logic {

    private static ArrayList<ComponentInterface> listHexagonsJ1;
    private static ArrayList<ComponentInterface> listHexagonsJ2;
    private static ArrayList<ComponentInterface> listHexagonsCabezasJ1;
    private static ArrayList<ComponentInterface> listHexagonsCabezasJ2;

    public Logic() {
        listHexagonsJ1 = new ArrayList<>();
        listHexagonsJ2 = new ArrayList<>();
        listHexagonsCabezasJ1 = new ArrayList<>();
        listHexagonsCabezasJ2 = new ArrayList<>();
    }

    public static ArrayList<ComponentInterface> getListHexagonsJ1() {
        return listHexagonsJ1;
    }

    public static ArrayList<ComponentInterface> getListHexagonsJ2() {
        return listHexagonsJ2;
    }

    public static ArrayList<ComponentInterface> getListHexagonsCabezasJ1() {
        return listHexagonsCabezasJ1;
    }

    public static ArrayList<ComponentInterface> getListHexagonsCabezasJ2() {
        return listHexagonsCabezasJ2;
    }

    public void addHexagonTreeJ1(ComponentInterface hexagon) {

        Hexagon hex = (Hexagon) hexagon;

        if (!hex.isHasPredecessor() && hex.getLocation().getX() != 0) {
            listHexagonsJ1.add(hexagon);
        }

    }

    public static void verificationPredecessor(ComponentInterface hegagonV, int indicadorJugador) {

        ArrayList<ComponentInterface> listCabezasVerification = new ArrayList<ComponentInterface>();
        Hexagon hexagonVerification = (Hexagon) hegagonV;
        Hexagon hexagonCabeza = null;
        boolean isCabeza = false;
        boolean isLegitimateSon = false;

        if (indicadorJugador == 1) {

            if (hexagonVerification.getLocation().getX() == 0) {
                listHexagonsCabezasJ1.add(hexagonVerification);
                isCabeza = true;
            }

            if (!listHexagonsCabezasJ1.isEmpty() && isCabeza == false) {

                for (int i = 0; i < listHexagonsCabezasJ1.size(); i++) {

                    hexagonCabeza = (Hexagon) listHexagonsCabezasJ1.get(i);

                    if ((hexagonCabeza.getLocation().getX() + 1) == (hexagonVerification.getLocation().getX())) {

                        if ((hexagonCabeza.getLocation().getY() - 1) == (hexagonVerification.getLocation().getY())) {

                            hexagonCabeza.addHexagonDescendant(hegagonV);
                            isLegitimateSon = true;
                        } else if ((hexagonCabeza.getLocation().getY()) == (hexagonVerification.getLocation().getY())) {

                            hexagonCabeza.addHexagonDescendant(hegagonV);
                            isLegitimateSon = true;
                        }

                    }

                }
            }

            listCabezasVerification = listHexagonsCabezasJ1;
        } else {

            if (hexagonVerification.getLocation().getY() == 0) {
                listHexagonsCabezasJ2.add(hexagonVerification);
                isCabeza = true;
            }

            if (!listHexagonsCabezasJ2.isEmpty() && isCabeza == false) {

                for (int i = 0; i < listHexagonsCabezasJ2.size(); i++) {

                    hexagonCabeza = (Hexagon) listHexagonsCabezasJ2.get(i);

                    if ((hexagonCabeza.getLocation().getX() + 1) == (hexagonVerification.getLocation().getX())) {

                        if ((hexagonCabeza.getLocation().getY()) == (hexagonVerification.getLocation().getY())) {

                            hexagonCabeza.addHexagonDescendant(hegagonV);
                            isLegitimateSon = true;
                        }

                    } else if (hexagonCabeza.getLocation().getX() == hexagonVerification.getLocation().getX()) {

                        if ((hexagonCabeza.getLocation().getY() + 1) == (hexagonVerification.getLocation().getY())) {

                            hexagonCabeza.addHexagonDescendant(hegagonV);
                            isLegitimateSon = true;
                        }

                    } else if ((hexagonCabeza.getLocation().getX() - 1) == (hexagonVerification.getLocation().getX())) {

                        if ((hexagonCabeza.getLocation().getY() + 1) == (hexagonVerification.getLocation().getY())) {

                            hexagonCabeza.addHexagonDescendant(hegagonV);
                            isLegitimateSon = true;
                        }

                    }

                }

            }

            listCabezasVerification = listHexagonsCabezasJ2;
        }

        if (!isLegitimateSon && isCabeza == false) {

            Hexagon hexagonSon = null;

            for (int j = 0; j < listCabezasVerification.size(); j++) {

                hexagonCabeza = (Hexagon) listCabezasVerification.get(j);

                for (int k = 0; k < hexagonCabeza.getListHexagons().size(); k++) {

                    hexagonSon = (Hexagon) hexagonCabeza.getListHexagons().get(k);

                    if ((hexagonSon.getLocation().getX() + 1) == (hexagonVerification.getLocation().getX())) {

                        if ((hexagonSon.getLocation().getY() - 1) == (hexagonVerification.getLocation().getY())) {

                            hexagonCabeza.addHexagonDescendant(hegagonV);
                        } else if ((hexagonSon.getLocation().getY()) == (hexagonVerification.getLocation().getY())) {

                            hexagonCabeza.addHexagonDescendant(hegagonV);
                        }

                    } else if (hexagonSon.getLocation().getX() == hexagonVerification.getLocation().getX()) {

                        if ((hexagonSon.getLocation().getY() - 1) == (hexagonVerification.getLocation().getY())) {

                            hexagonCabeza.addHexagonDescendant(hegagonV);

                        } else if ((hexagonSon.getLocation().getY() + 1) == (hexagonVerification.getLocation().getY())) {

                            hexagonCabeza.addHexagonDescendant(hegagonV);
                        }

                    } else if ((hexagonSon.getLocation().getX() - 1) == (hexagonVerification.getLocation().getX())) {

                        if ((hexagonSon.getLocation().getY() + 1) == (hexagonVerification.getLocation().getY())) {

                            hexagonCabeza.addHexagonDescendant(hegagonV);
                        } else if ((hexagonSon.getLocation().getY()) == (hexagonVerification.getLocation().getY())) {

                            hexagonCabeza.addHexagonDescendant(hegagonV);
                        }

                    }
                }

            }

        }

    }

    public void addHexagonTreeJ2(ComponentInterface hexagon) {

        Hexagon hex = (Hexagon) hexagon;

        if (!hex.isHasPredecessor() && hex.getLocation().getY() != 0) {
            listHexagonsJ2.add(hexagon);
        }

    }

}
