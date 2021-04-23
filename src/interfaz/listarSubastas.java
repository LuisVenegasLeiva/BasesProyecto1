/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleResultSet;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author luisv
 */
public class listarSubastas extends javax.swing.JFrame {

    /**
     * Creates new form listarSubastas
     */
    public listarSubastas() {
        initComponents();
        
            try{    
            String query = "begin ? := listarCategorias(); end;";

            CallableStatement stmt = conn.prepareCall(query);

            // register the type of the out param - an Oracle specific type
            stmt.registerOutParameter(1, OracleTypes.CURSOR);

            // execute and retrieve the result set
            stmt.execute();
            ResultSet rs = (ResultSet)stmt.getObject(1);

            try{
                while (rs.next()) {
                    this.comboCat.addItem(rs.getString(1));
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,"El dato ingresado no es correcto");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

        Connection conn= login.conn;
        CallableStatement callStmt = null;
        
        OraclePreparedStatement pst =null;
        OracleResultSet rs=null;

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        comboCat = new javax.swing.JComboBox<>();
        comboSub = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id Subasta", "Descripcion", "Precio Inicial", "Ultimo Precio", "Fecha limite"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabla);

        jLabel1.setText("Categoría");

        jLabel2.setText("Sub-Categoría");

        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        comboCat.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboCatItemStateChanged(evt);
            }
        });
        comboCat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboCatActionPerformed(evt);
            }
        });
        comboCat.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                comboCatPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
                            .addComponent(comboCat, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboSub, 0, 346, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(comboCat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboSub, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try{
            DefaultTableModel dm = (DefaultTableModel) tabla.getModel();
            int rowCount = dm.getRowCount();
            for (int i = rowCount - 1; i >= 0; i--) {
            dm.removeRow(i);
            }
            
            String query = "begin ? := listarsubastas(?,?); end;";

            CallableStatement stmt = conn.prepareCall(query);

            // register the type of the out param - an Oracle specific type
            stmt.registerOutParameter(1, OracleTypes.CURSOR);

            // set the in param
            String cat = this.comboCat.getSelectedItem().toString();
            stmt.setString(2, cat);
            
            String sub = this.comboSub.getSelectedItem().toString();
            stmt.setString(3, sub);
            System.out.println(cat+" "+sub);
            // execute and retrieve the result set
            stmt.execute();
            ResultSet rs = (ResultSet)stmt.getObject(1);

            // print the results
            DefaultTableModel mod = (DefaultTableModel) tabla.getModel();
            try{
                while (rs.next()) {
                    Object[] row = { rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getFloat(4), rs.getString(5) };
                    mod.addRow(row);
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,"Datos incorrectos");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void comboCatItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboCatItemStateChanged
        try{
            int itemCount = this.comboSub.getItemCount();

            for(int i=0;i<itemCount;i++){
                this.comboSub.removeItemAt(0);
            }
            String query1 = "begin ? := listarSubCategorias(?); end;";

            CallableStatement stmt1 = conn.prepareCall(query1);

            // register the type of the out param - an Oracle specific type
            stmt1.registerOutParameter(1, OracleTypes.CURSOR);

            // set the in param
            String cat = this.comboCat.getSelectedItem().toString();
            System.out.println(this.comboCat.getSelectedItem().toString());
            stmt1.setString(2, cat);

            // execute and retrieve the result set
            stmt1.execute();
            ResultSet rs1 = (ResultSet)stmt1.getObject(1);

            // print the results
            try{
                while (rs1.next()) {
                    this.comboSub.addItem(rs1.getString(1));
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,"El dato ingresado no es correcto");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_comboCatItemStateChanged

    private void comboCatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboCatActionPerformed

    }//GEN-LAST:event_comboCatActionPerformed

    private void comboCatPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_comboCatPropertyChange

    }//GEN-LAST:event_comboCatPropertyChange

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
            java.util.logging.Logger.getLogger(listarSubastas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(listarSubastas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(listarSubastas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(listarSubastas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new listarSubastas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboCat;
    private javax.swing.JComboBox<String> comboSub;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
