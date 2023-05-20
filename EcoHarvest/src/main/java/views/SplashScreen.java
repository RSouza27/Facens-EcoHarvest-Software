/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import communication.Communication;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;

/**
 *
 * @author Unknown Account
 */
public class SplashScreen extends javax.swing.JFrame {
    private static final Logger logger = Logger.getLogger(SplashScreen.class.getName());
    Communication dbAccess = new Communication();
    private int progressValue = 0;
    private final int progressTimer = 25;
    
    private void progressBarUpdate(int amount) {
        if (amount <= 0) {
            progressBar.setValue(0);
        } else {
            progressValue += amount;
            if (progressValue > 100) progressValue = 100;
            progressBar.setValue(progressValue);
        }
    }
    
    private void progressSlow(int amount, Thread thPtr) {
        for (int x=0; x<=amount; x++) {
            progressBarUpdate(1);
            try {
                thPtr.sleep(progressTimer);
            } catch (InterruptedException ex) {}
        }
    }
    
    private void setLogoImage(BufferedImage image) {
        ImageIcon icon = new ImageIcon(image);
        logoEcoHarvest.setIcon(icon);
    }
    
    /**
     * Creates new form SplashScreen
     */
    public SplashScreen() {
        initComponents();
        
        setLocationRelativeTo(null);
        setTitle("EcoHarvest - Loading");
        progressBarUpdate(0);
        
        try {
            URL imageUrl = new URL("https://github.com/WhitePoodleMoth/EcoHarvest/blob/main/Assets/logo.jpeg?raw=true");
            BufferedImage image = ImageIO.read(imageUrl);
            SwingUtilities.invokeLater(() -> setLogoImage(image));
        } catch (IOException ex) {}
        
        new Thread(){
            @Override
            public void run(){
                try {
                    logger.info("Inicializando Sistema");
                    HomeScreen home = new HomeScreen();
                    progressSlow(25, this);
                    //progressBarUpdate(100);
                            
                    // Verificando conectividade.
                    if (dbAccess.checkDatabaseConnection()){
                        logger.info("Comunicacao com banco de dados realizada com sucesso.");
                        progressSlow(25, this);
                    } else {
                        logger.warning("Erro ao se conectar com bando de dados");
                        System.exit(0);
                    }

                    // Verificando Estrutura.
                    if (dbAccess.checkDatabaseStructure()){
                        logger.info("Estrutura do banco de dados correta.");
                        progressSlow(25, this);
                    } else {
                        logger.info("Estrutura do banco de dados incorreta");
                        System.exit(0);
                    }

                    logger.info("Sistema e banco de dados carregado com sucesso.");
                    progressSlow(25, this);
                    
                    home.setVisible(true);
                } catch (Exception ex) {
                    logger.warning("Erro ao carregar sistema.");
                    System.exit(0);
                } finally {
                    dispose();
                }
            }
        }.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        progressBar = new javax.swing.JProgressBar();
        logoEcoHarvest = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 51, 0));

        progressBar.setBackground(new java.awt.Color(255, 255, 255));
        progressBar.setForeground(new java.awt.Color(153, 51, 0));
        progressBar.setBorder(javax.swing.BorderFactory.createLineBorder(null));

        logoEcoHarvest.setLabelFor(logoEcoHarvest);
        logoEcoHarvest.setText("EcoHarvest");
        logoEcoHarvest.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(logoEcoHarvest, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(logoEcoHarvest, javax.swing.GroupLayout.PREFERRED_SIZE, 476, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
            java.util.logging.Logger.getLogger(SplashScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SplashScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SplashScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SplashScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new SplashScreen().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel logoEcoHarvest;
    private javax.swing.JProgressBar progressBar;
    // End of variables declaration//GEN-END:variables
}
