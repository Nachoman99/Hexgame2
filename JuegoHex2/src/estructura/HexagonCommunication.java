package estructura;

import java.io.Serializable;

/**
 *
 * @author cocau
 */
public class HexagonCommunication implements Serializable{

    private int player;
    private Punto location;

    public HexagonCommunication(int player, int locationX, int locationY) {

        this.player = player;
        location = new Punto(locationX, locationY);
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

    @Override
    public String toString() {
        return "HexagonCommunication{" + "player=" + player + ", location=" + location + '}';
    }

}
