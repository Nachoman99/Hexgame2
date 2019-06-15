package GUI;

import javax.swing.JButton;

/**
 *
 * @author cocau
 */
public class FrameJuego extends javax.swing.JFrame {

    /**
     * Creates new form FrameJuego
     */
    private JButton[][] matrizButtons;

    public FrameJuego() {
        initComponents();
        matrizButtons = new JButton[7][7];
        crearMatrizButtons();
        inicializadorGUIElemento();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(1200, 900));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1300, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrameJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameJuego().setVisible(true);
            }
        });
    }

        public void inicializadorGUIElemento() {

        setTitle("Juego-Hex");
        setLayout(null);
        setSize(550, 550);
        setVisible(true);
        setLocationRelativeTo(null);
        setAlwaysOnTop(false);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }// Fin metodo inicializador
    
    public void crearMatrizButtons() {

        for (int i = 0; i < 7; i++) {

            for (int j = 0; j < 7; j++) {

                JButton buttonNuevo = new JButton();
                buttonNuevo.setBounds((70 * j) + 2, 70 * i, 70, 70);
                matrizButtons[i][j] = buttonNuevo;
                add(buttonNuevo);
            }

        }

    }

public JButton[][] getMatrizButt(){

return matrizButtons;
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
