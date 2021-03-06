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
public class iniciarSubasta extends javax.swing.JFrame {

    /**
     * Creates new form iniciarSubasta
     */
    public iniciarSubasta() {
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        textDesc = new javax.swing.JTextField();
        textPrecio = new javax.swing.JTextField();
        textDuracion = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        comboCat = new javax.swing.JComboBox<>();
        comboSub = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Categor??a");

        jLabel2.setText("Sub Categor??a");

        jLabel3.setText("Precio");

        jLabel4.setText("Descripci??n");

        jLabel5.setText("Duraci??n(minutos)");

        jButton1.setText("Iniciar Subasta");
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
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5))
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                        .addComponent(textDuracion)
                        .addComponent(textPrecio)
                        .addComponent(textDesc))
                    .addComponent(comboCat, 0, 346, Short.MAX_VALUE)
                    .addComponent(comboSub, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(179, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(comboCat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(comboSub, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(textDesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(textPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(textDuracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(jButton1)
                .addContainerGap(52, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try{
            CallableStatement cs = conn.prepareCall("{call iniciarSubasta (?,?,?,?,?)}");
            cs.setString(1,this.comboCat.getSelectedItem().toString());
            cs.setString(2,this.comboSub.getSelectedItem().toString());
            cs.setString(3,this.textDesc.getText());
            cs.setInt(4,Integer.parseInt(this.textPrecio.getText()));
            cs.setInt(5,Integer.parseInt(this.textDuracion.getText()));
            boolean ej =cs.execute();
            JOptionPane.showMessageDialog(null,"Agregada Correctamente");

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void comboCatPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_comboCatPropertyChange

    }//GEN-LAST:event_comboCatPropertyChange

    private void comboCatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboCatActionPerformed
       
    }//GEN-LAST:event_comboCatActionPerformed

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
            java.util.logging.Logger.getLogger(iniciarSubasta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(iniciarSubasta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(iniciarSubasta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(iniciarSubasta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new iniciarSubasta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboCat;
    private javax.swing.JComboBox<String> comboSub;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField textDesc;
    private javax.swing.JTextField textDuracion;
    private javax.swing.JTextField textPrecio;
    // End of variables declaration//GEN-END:variables
}
