package com.TunkDesign.McFix;

import com.TunkDesign.McFix.packets.Download;
import com.TunkDesign.McFix.packets.Fix;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.UIManager;

/**
 *
 * @author Karl Håkansson
 * @version 0.1
 * @website http://tunkdesign.com
 * 
 */

public class Main extends javax.swing.JFrame {
    
    /**
     * Declarations
     */
    //Classes
    public Download Download = new Download(this);
    public Fix Fix = new Fix(this);
    
    //Strings
    public String appData = System.getenv("APPDATA") + "/.minecraft/saves"; //Windows AppData
    public String appSupport = System.getProperty("user.home") + "/Library/Application Support/minecraft/saves"; //OSX Application support
    public String linuxHome = System.getProperty("user.home") + "/.minecraft/saves"; //Linux home
    public String appPath = System.getProperty("user.dir");
    
    //Ints
    public int DownloadSize;
    
    //Files
    File winSaves = new File(appData); //Windows saves folder
    File macSaves = new File(appSupport); //Mac saves folder
    File linuxSaves = new File(linuxHome); //Linux saves folder
    
    //Lists
    String[] winList = winSaves.list();
    String[] macList = macSaves.list();
    String[] linuxList = linuxSaves.list();
    
    //Models
    DefaultListModel model = new DefaultListModel();
    
    
    
    /**
     * Init form
     */
    public Main() {
        /**
         * Init components
         */
        initComponents();
        /**
         * Open application in the middle of the screen
         */
        setLocationRelativeTo(null);
        
        System.out.println(appPath);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        simWorld = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        savesList = new javax.swing.JList(winList);
        fixBTN = new javax.swing.JButton();
        jProgressbar = new javax.swing.JProgressBar();
        aboutSimWorld = new javax.swing.JButton();
        copyRight = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MCFix");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        simWorld.setText("Simulate World");
        simWorld.setToolTipText("Simulate the world after fixing to ensure that the world works.");

        savesList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(savesList);

        fixBTN.setText("Fix");
        fixBTN.setEnabled(false);
        fixBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fixBTNActionPerformed(evt);
            }
        });

        aboutSimWorld.setText("?");
        aboutSimWorld.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutSimWorldActionPerformed(evt);
            }
        });

        copyRight.setText("Copyright © 2013 TunkDesign.com");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(copyRight)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jProgressbar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(simWorld)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(aboutSimWorld, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fixBTN)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(simWorld)
                    .addComponent(fixBTN)
                    .addComponent(aboutSimWorld))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressbar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(copyRight)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fixBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fixBTNActionPerformed
        
        
        
   /*     ProcessBuilder   ps=new ProcessBuilder("java", "-jar", "sim.jar", "nogui");

    //From the DOC:  Initially, this property is false, meaning that the 
    //standard output and error output of a subprocess are sent to two 
    //separate streams
    ps.redirectErrorStream(true);

    Process pr = null;  
        try {
            pr = ps.start();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    BufferedReader in = new BufferedReader(new 

              InputStreamReader(pr.getInputStream()));
    String line;
        try {
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            pr.waitFor();
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            in.close();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        
        /**
        try {
            Runtime.getRuntime().exec("java -jar sim.jar nogui");
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }//GEN-LAST:event_fixBTNActionPerformed

    private void aboutSimWorldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutSimWorldActionPerformed
        message("What is \"Simulate World\" you might ask? "
                + "\n\n"
                + "Simulate World tries to simulate a launch of the world by \nloading up the world with the Minecraft server software."
                + "\n\n"
                + "This is a good way to get your character coordinates back \nas well, and usually works without any problems."
                + "\n\n"
                + "If you choose to not use it, your character will end up at \n0, 0 with a chance of suffocation.");
    }//GEN-LAST:event_aboutSimWorldActionPerformed

     private void savesListActionPerformed(java.awt.event.ActionEvent evt) {
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code">
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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /**
         * Set application default style
         */
        try{
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            //UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        }
        catch(Exception e){
            System.out.println("UIManager Exception : "+e);
        }
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
    
    /**
     * Fast void to access JOptionPane's showMessageDialog
     * @param MSG Message you'd like to show
     */
    public static void message(String MSG){
        JOptionPane.showMessageDialog(null, MSG);
    }
    
    /**
     * Copy local file inside JAR archive
     * @param resource Get the following resource
     * @param destination Copy it to the following location
     */
    public static void copy(String resource, String destination) {
        InputStream resStreamIn = Main.class.getClassLoader().getResourceAsStream("res/" + resource);
        File resDestFile = new File(destination);
        try {
            OutputStream resStreamOut = new FileOutputStream(resDestFile);
            int readBytes;
            byte[] buffer = new byte[4096];
            while ((readBytes = resStreamIn.read(buffer)) > 0) {
                resStreamOut.write(buffer, 0, readBytes);
            }
 
        } catch (Exception ex) {
        }
 
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aboutSimWorld;
    private javax.swing.JLabel copyRight;
    private javax.swing.JButton fixBTN;
    public static javax.swing.JProgressBar jProgressbar;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JList savesList;
    private javax.swing.JCheckBox simWorld;
    // End of variables declaration//GEN-END:variables
}
