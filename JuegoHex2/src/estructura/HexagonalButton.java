/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructura;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Polygon;
import javax.swing.JButton;

/**
 *
 * @author Nacho
 */
public class HexagonalButton extends JButton {

    private static final int SIDES = 6;
    public static final int LENGTH = 60;
    public static final int WIDTH = 60;
    private int row = 0;
    private int col = 0;
    private int x1[] = new int[SIDES];
    private int y1[] = new int[SIDES];
    private Color color = Color.WHITE;

    public HexagonalButton(int row, int col) {
        this.row = row;
        this.col = col;

        this.setContentAreaFilled(false);
        this.setFocusPainted(false);
        this.setBorderPainted(false);
        this.setPreferredSize(new Dimension(WIDTH, LENGTH));
    }

    @Override
    public boolean contains(int x11, int y11) {
        Polygon poly = new Polygon();
        if (poly == null || !poly.getBounds().equals(getBounds())) {
            for (int i = 0; i < SIDES; i++) {
                x1[i] = WIDTH / 2 + (int) ((WIDTH - 2) / 2 * Math.cos(i * 2 * Math.PI / 6) * 0.9);
                y1[i] = LENGTH / 2 + (int) ((LENGTH - 2) / 2 * Math.sin(i * 2 * Math.PI / 6) * 0.9);
            }
            poly = new Polygon(x1, y1, SIDES);
        }
        return poly.contains(x11, y11);
    }

    public void changeColor(int cambio) {
//        Graphics g= getGraphics();
//        Polygon poly = new Polygon();
//        for (int i = 0; i < SIDES; i++) {
//            int x = WIDTH / 2 + (int) ((WIDTH - 2) / 2 * Math.cos(i * 2 * Math.PI / 6) * 0.9);
//            int y = LENGTH / 2 + (int) ((LENGTH - 2) / 2 * Math.sin(i * 2 * Math.PI / 6) * 0.9);
//            poly.addPoint(y, x);
//
//        }
//     
//        
//        g.drawPolygon(poly);
//        g.setColor(Color.red);
//        g.fillPolygon(poly);

//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//        }
//        g.drawPolygon(poly);
//       this.setEnabled(false);
        colorCambiante(cambio);
        this.setEnabled(false);
    }

    public Color colorCambiante(int cambio) {

        if (cambio == 1) {
            return color = Color.RED;
        } else if(cambio == 2) {
           return color = Color.BLUE;
        }    
        return color = Color.WHITE;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.BLACK);
        this.setForeground(Color.black);
        Polygon poly = new Polygon();
        for (int i = 0; i < 6; i++) {
            int x = WIDTH / 2 + (int) ((WIDTH - 2) / 2 * Math.cos(i * 2 * Math.PI / 6) * 0.9);
            int y = LENGTH / 2 + (int) ((LENGTH - 2) / 2 * Math.sin(i * 2 * Math.PI / 6) * 0.9);
            x1[i] = WIDTH / 2 + (int) ((WIDTH - 2) / 2 * Math.cos(i * 2 * Math.PI / 6) * 0.9);
            y1[i] = LENGTH / 2 + (int) ((LENGTH - 2) / 2 * Math.sin(i * 2 * Math.PI / 6) * 0.9);
            poly.addPoint(y, x);
        }
        g.setColor(color);
        g.fillPolygon(poly);
        g.setColor(Color.BLACK);
        g.drawPolygon(poly);

    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

}
