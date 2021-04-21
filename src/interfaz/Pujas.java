/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import Modelo.Puja;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import subastas.PostgresConnection;
//import oracle.jdbc.OraclePreparedStatement;
//import oracle.jdbc.OracleResultSet;


public class Pujas extends javax.swing.JFrame {


    public Pujas() {
        initComponents();
    }
        Connection conn= login.conn;
        CallableStatement callStmt = null;
        
     //   OraclePreparedStatement pst =null;
       // OracleResultSet rs=null;

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        idField = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaPujas = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("NumSubasta");

        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        TablaPujas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null}
            },
            new String [] {
                "Usuario", "Fecha", "Monto"
            }
        ));
        jScrollPane2.setViewportView(TablaPujas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(idField, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(idField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    /*    try{
            callStmt=conn.prepareCall("{call ?:= listarhistorialpujas (?)}");
            callStmt.registerOutParameter(1, Types.NUMERIC);
            callStmt.setInt(2,Integer.parseInt(this.jTextField1.getText()));
            callStmt.executeUpdate();
            
            //pst = (OraclePreparedStatement) conn.prepareStatement("select listarhistorialpujas (?) from dual;");
            //pst.setInt(1,Integer.parseInt(this.jTextField1.getText()));
            //rs = (OracleResultSet) pst.executeQuery();
            if(rs.next()){
                System.out.println(callStmt.getInt(1));
                //this.jTextArea1.setText(rs.toString());
            }else{
                 JOptionPane.showMessageDialog(null,"El dato ingresado no es correcto");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }*/
        PostgresConnection db = PostgresConnection.getInstance();
        ArrayList<Puja> pujas = db.getPujas(Integer.parseInt(idField.getText()));
        for (int i = 0; i< pujas.size(); i++){
            Puja p = pujas.get(i);
            TablaPujas.setValueAt(p.getComprador(), i, 0);
            TablaPujas.setValueAt( p.getFecha().toString(), i, 1);
            TablaPujas.setValueAt(p.getMonto(), i, 2);
            DefaultTableModel modelo = (DefaultTableModel) TablaPujas.getModel();
            modelo.addRow(new Object[]{"","",""});
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaPujas;
    private javax.swing.JTextField idField;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
