package GUI;

import Sockets.Client;
import Sockets.Server;

/**
 *
 * @author Kevin Trejos
 */
public class WaitConnection extends javax.swing.JDialog {

    private Client client;
    private Server server;

    public WaitConnection(Client client) {
        initComponents();
        this.client = client;
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        new WaitConnectionThread(this).start();
    }

    public WaitConnection(Server server) {
        initComponents();
        this.server = server;
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        new WaitConnectionThread(this).start();
    }
    
    
    /**
     * Creates new form WaitConnection
     */
    public WaitConnection(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(parent);
        WaitConnectionThread thread = new WaitConnectionThread(this);
        thread.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel2.setText("jLabel2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addContainerGap(72, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(WaitConnection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(WaitConnection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(WaitConnection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(WaitConnection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                WaitConnection dialog = new WaitConnection(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
//    }
    public void mostrarVentana(){
        this.setVisible(true);
    }
    
    public void quiitarVentana(){
        this.dispose();
    }
    
    public void verificar(){
        if(server == null){
            this.dispose();
            if(this.client.isWaiting()){
//                new Tablero2(7, this.client).setVisible(true);
                this.client.setWaiting(false);
            }
        }else{
            this.dispose();
            if(this.server.isWaiting()){
//                new Tablero(7, this.thread).setVisible(true);
                this.server.setWaiting(false);
            }
            
            
        }
        
//        if (this.client.isWaiting() || this.thread.isWaiting()) {
//            this.dispose();
//            new Tablero(7, this.thread).setVisible(true);
//            new Tablero2(7, this.client).setVisible(true);
//            if(client!=null){
//                this.client.setWaiting(false);
//            }else if(thread!=null){
//                this.thread.setWaiting(false);
//            }else if(thread!=null ||client!=null){
//                this.thread.setWaiting(false);
//                this.client.setWaiting(false);
//            }
//            
//            
//        }
    }
    
    private class WaitConnectionThread extends Thread{

        WaitConnection wait;
        
        public WaitConnectionThread(WaitConnection wait) {
            this.wait = wait;
        }
        
        @Override
        public void run() {
            while (true) {                
                try {
                    jLabel2.setText("Esperando jugador");
                    this.sleep(1000);
                    jLabel2.setText("Esperando jugador.");
                    this.sleep(1000);
                    jLabel2.setText("Esperando jugador..");
                    this.sleep(1000);
                    jLabel2.setText("Esperando jugador...");
                    this.sleep(1000);
//                    if (!Ingresar.isWaitingConnection() || !Registro.isWaitingConnection()) {
//                        wait.dispose();
//                    }
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
