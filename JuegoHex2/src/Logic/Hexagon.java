package Logic;

import estructura.Punto;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author cocau
 */
public class Hexagon implements ComponentInterface, Serializable{

    private int player;
    private Punto location;
    private boolean hasPredecessor;
    private boolean hexEspeciales;
    private boolean utilizado;
    ArrayList<ComponentInterface> listHexagons;

    public Hexagon(int player, int locationX, int locationY) {

        this.player = player;
        location = new Punto(locationX, locationY);
        hasPredecessor = false;
        utilizado = false;
        hexEspeciales = false;
        listHexagons = new ArrayList<>();
    }

    public ArrayList<ComponentInterface> getListHexagons() {
        return listHexagons;
    }

    public void setListHexagons(ArrayList<ComponentInterface> listHexagons) {
        this.listHexagons = listHexagons;
    }

    public boolean isHexEspeciales() {
        return hexEspeciales;
    }

    public void setHexEspeciales(boolean hexEspeciales) {
        this.hexEspeciales = hexEspeciales;
    }

    public boolean isUtilizado() {
        return utilizado;
    }

    public void setUtilizado(boolean utilizado) {
        this.utilizado = utilizado;
    }

    public int getPlayer() {
        return player;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    public Punto getLocation() {
        return location;
    }

    public void setLocation(Punto location) {
        this.location = location;
    }

    public boolean isHasPredecessor() {
        return hasPredecessor;
    }

    public void setHasPredecessor(boolean hasPredecessor) {
        this.hasPredecessor = hasPredecessor;
    }

    public void addHexagonDescendant(ComponentInterface hexagon) {

        Hexagon hexagonVerification = (Hexagon) hexagon;

        if (!hexagonVerification.isHasPredecessor()) {
            hexagonVerification.setHasPredecessor(true);
            hexagon = hexagonVerification;
            listHexagons.add(hexagon);
        }

    }

    @Override
    public String listLocationX() {

        String locations = "";
        Hexagon hexagon = null;

        locations += getLocation().getX() + ",";

        if (!listHexagons.isEmpty()) {

            for (int i = 0; i < listHexagons.size(); i++) {

                hexagon = (Hexagon) listHexagons.get(i);
                locations += hexagon.getLocation().getX() + ",";
            }

        }
        return locations;
    }

    @Override
    public String listLocationY() {

        String locations = "";
        Hexagon hexagon = null;

        locations += getLocation().getY() + ",";

        if (!listHexagons.isEmpty()) {

            for (int i = 0; i < listHexagons.size(); i++) {

                hexagon = (Hexagon) listHexagons.get(i);
                locations += hexagon.getLocation().getY() + ",";
            }

        }
        return locations;
    }

    @Override
    public String toString() {
        return "Hexagon{" + "player=" + player + ", location=" + location.toString() + ", hasPredecessor=" + hasPredecessor + ", listHexagons=" + listHexagons + '}';
    }

}
