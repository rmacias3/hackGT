/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hackgt;

import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Scanner;
import processing.core.PApplet;

/**
 *
 * @author NiNJa
 */
public class HackGTUI extends javax.swing.JFrame {

    /**
     * Creates new form HackGTUI
     */
    public HackGTUI() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList3 = new javax.swing.JList();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "2010", "2011", "2012", "2013", "2014" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        jList2.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "30318", "30309", "30313" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jList2);

        jList3.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(jList3);

        jButton1.setText("Submit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(158, 158, 158)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(48, 48, 48))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String years[] = {"2010","2011","2012","2013","2014"};
        String months[] = {"1","2","3","4","5","6","7","8","9","10","11","12"};
        String zipcode[] = {"30313","30309","30318"};
        
//        HackGT hgt = new HackGT();
//        hgt.run();
//        CountryBubbleMapApp app = CountryBubbleMapApp.INSTANCE;
        
        int ind1[] = jList1.getSelectedIndices();
        int ind2[] = jList2.getSelectedIndices();
        int ind3[] = jList3.getSelectedIndices();
        String selyears[] = new String[ind1.length];
        String selmonths[] = new String[ind3.length];
        String selzip[] = new String[ind2.length];
        String stryears = "";
        String strmonths = "";
        String strzip = "";
        
        for(int i=0;i<ind1.length;i++){
            selyears[i] = years[ind1[i]];
            stryears = stryears + "," + selyears[i];
        }
        for(int i=0;i<ind3.length;i++){
            System.out.println(Arrays.toString(ind3));
            selmonths[i] = months[ind3[i]];
            strmonths = strmonths + "," + selmonths[i];
        }
        for(int i=0;i<ind2.length;i++){
            selzip[i] = zipcode[ind2[i]];
            strzip = strzip + "," + selzip[i];
        }
        double soln[] = new double[8];
        try{
        Scanner sc = new Scanner(new File("C:\\Users\\NiNJa\\Downloads\\data_created.csv"));
        while(sc.hasNext()){
            String line = sc.nextLine();
            
            String tokens[] = line.split(",");
        
            if(stryears.contains("," + tokens[7])){
                if(strmonths.contains("," + tokens[6])){
                    if(strzip.contains(tokens[4])){
                        soln[Integer.parseInt(tokens[5])-1] += Integer.parseInt(tokens[8]);
                    }
                }
            }
        }
        int total = 0;
        for(int i = 0;i<soln.length;i++){
        	total += soln[i];
        }
        FileWriter fw = new FileWriter(new File("C:\\Users\\NiNJa\\Downloads\\data.csv"));
        for(int i = 0;i<soln.length;i++){
        	soln[i] = (soln[i]*2)/(total *0.1);
        	fw.write("" + soln[i] + "\n");
        }
        fw.flush();
        fw.close();
        
            System.out.println(stryears);
//        Thread.sleep(1000);
//        app.loadData(soln);
        System.out.println("" + Arrays.toString(ind1));
        System.out.println("" + Arrays.toString(selyears));
        System.out.println("" + Arrays.toString(soln));
        } catch(Exception e){
            e.printStackTrace();
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(HackGTUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HackGTUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HackGTUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HackGTUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HackGTUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JList jList1;
    private javax.swing.JList jList2;
    private javax.swing.JList jList3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables
}
