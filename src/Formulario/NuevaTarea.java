/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formulario;

import Conectar.Conectar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author TAMY-IA
 */
public class NuevaTarea extends javax.swing.JInternalFrame {

    /**
     * Creates new form NuevaTarea
     */
    public NuevaTarea() {
        initComponents();
        this.setLocation(440, 100);
        BloquearTarea();
        BloquearSubtarea();
        Folio();
        jlFolio.setVisible(false);
        
    } //COMPLETO
        
    void NuevaTarea(){
        String id, nombre, categoria, nota, estado="", fecha="";
        String sql="INSERT INTO tareas (`idtareas`,`folio`, `nombre`, `categoria`, `nota`, `estado`, `fecha`)VALUES(?,?,?,?,?,?,?)";
        
        id="0";
        nombre=txtNewNombre.getText();
        categoria = String.valueOf(jcbNewCategoria.getSelectedItem());
        nota=txtNewNota.getText();
        //radio botones pendientes que se desactive si otro esta activo
        if(rbNewPendiente.isSelected()==true){
            estado="Pendiente";
        }else if(rbNewCompleto.isSelected()==true){
            estado="Completo";
        }
        //////////////////////////////para obt5ener la fecha 
        try{
            Date fecha1=jcNewFecha.getDate();
            DateFormat f=new SimpleDateFormat("dd-MM-YYYY");
            fecha=f.format(fecha1);
        }catch(Exception e){
            
        }        
        /////////////////////////////
        try{
            PreparedStatement pst=cn.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, jlFolio.getText());
            pst.setString(3, nombre);
            pst.setString(4, categoria);
            pst.setString(5, nota);
            pst.setString(6, estado);
            pst.setString(7, fecha);
            
            pst.executeUpdate();
            System.out.println("Guardado");
            Limpiar();
            this.dispose();
        }catch(Exception e){
            System.out.println("Error metodo nueva tarea\n"+e);
        }
    }   //COMPLETO
    
    void NuevaSubtarea(){
        String id="0",nombre, nota, estado="";
        String sql="INSERT INTO subtareas (`idSubtareas`,`folio`, `nombre`, `nota`, `estado`)VALUES(?,?,?,?,?)";
        
        nombre=txtSubNombre.getText();
        nota=txtSubNota.getText();
        if(rbSubPendiente.isSelected()==true){
            estado="Pendiente";
        }else if(rbSubCompleto.isSelected()==true){
            estado="Completo";
        }
        
        try{
            PreparedStatement pst=cn.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, jlFolio.getText());
            pst.setString(3, nombre);
            pst.setString(4, nota);
            pst.setString(5, estado);
            
            pst.executeUpdate();
            System.out.println("SubTarea Guardada");
            Limpiar2();
            this.dispose();
        }catch(Exception e){
            System.out.println("error metodo subtarea\n"+e);
        }
    }   //COMPLETO
    
    void Limpiar(){
        txtNewNombre.setText("");
        //categoria
        txtNewNota.setText("");
        rbNewPendiente.setSelected(false);
        rbNewCompleto.setSelected(false);
        jcNewFecha.setCalendar(null);
    }   //COMPLETO
    
    void Limpiar2(){
        txtSubNombre.setText("");
        txtSubNota.setText("");
        rbSubPendiente.setSelected(false);
        rbSubCompleto.setSelected(false);
    }   //COMPLETO
    
    void BloquearTarea(){
        txtNewNombre.setEnabled(false);
        jcbNewCategoria.setEnabled(false);
        txtNewNota.setEnabled(false);
        rbNewPendiente.setEnabled(false);
        rbNewCompleto.setEnabled(false);
        jcNewFecha.setEnabled(false);
        
        btnNewCancelar.setEnabled(false);
        btnNewGuardar.setEnabled(false);
    }
    
    void DesbloquearTarea(){
        txtNewNombre.setEnabled(true);
        jcbNewCategoria.setEnabled(true);
        txtNewNota.setEnabled(true);
        rbNewPendiente.setEnabled(true);
        rbNewCompleto.setEnabled(true);
        jcNewFecha.setEnabled(true);
        
        btnNewCancelar.setEnabled(true);
        btnNewGuardar.setEnabled(true);
    }
    
    public void BloquearSubtarea(){
        txtSubNombre.setEnabled(false);
        txtSubNota.setEnabled(false);
        rbSubPendiente.setEnabled(false);
        rbSubCompleto.setEnabled(false);
        
        btnSubCancelar.setEnabled(false);
        btnSubGuardar.setEnabled(false);
    }
    
    public void DesbloquearSubtarea(){
        txtSubNombre.setEnabled(true);
        txtSubNota.setEnabled(true);
        rbSubPendiente.setEnabled(true);
        rbSubCompleto.setEnabled(true);
        
        btnSubCancelar.setEnabled(true);
        btnSubGuardar.setEnabled(true);
    }
    
    void ModificarSubtarea(){
        //String categoria=String.valueOf(jcbCategoria.getSelectedItem());
        String estado="";
        if(rbSubPendiente.isSelected()==true){
            estado="Pendiente";
        }else if(rbSubCompleto.isSelected()==true){
            estado="Completo";
        }
        
        String sql="UPDATE subtareas SET `nombre`='"+txtSubNombre.getText()+"', `nota`='"+txtSubNota.getText()+"', `estado`='"+estado+"' WHERE `nombre`='"+txtSubNombre.getText()+"'";
        
        try{
            PreparedStatement pst=cn.prepareStatement(sql);
            pst.executeUpdate();
            //LimpiarPanelPresupuesto();
            JOptionPane.showMessageDialog(null, "Actualizado");
            
            ListaTarea lts=new ListaTarea();
            Principal.jdpEscritorio.add(lts);
            lts.setVisible(true);
                        
            this.dispose();
        }catch(Exception e){
            System.out.println("error al actualizar clase nuevatarea\n"+e);
        }
    }
    
    void ModificarTarea(){
        String estado="", categoria=String.valueOf(jcbNewCategoria.getSelectedItem()), fecha="";
        
        if(rbNewPendiente.isSelected()==true){
            estado="Pendiente";
        }else if(rbNewCompleto.isSelected()==true){
            estado="Completo";
        }
        
        try{
            Date fecha1=jcNewFecha.getDate();
            DateFormat f=new SimpleDateFormat("dd-MM-YYYY");
            fecha=f.format(fecha1);
        }catch(Exception e){
            
        }  
        
        String sql="UPDATE tareas SET `nombre`='"+txtNewNombre.getText()+"', `categoria`='"+categoria+"', `nota`='"+txtNewNota.getText()+"', `estado`='"+estado+"', `fecha`='"+fecha+"' WHERE `nombre`='"+txtNewNombre.getText()+"'";
        
        try{
            PreparedStatement pst=cn.prepareStatement(sql);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Actualizado");
            //LimpiarPanelPresupuesto();
            this.dispose();
            
            ListaTarea ltt=new ListaTarea();
            Principal.jdpEscritorio.add(ltt);
            ltt.setVisible(true);
            
        }catch(Exception e){
            System.out.println("error al actualizar clase nuevatarea\n"+e);
        }
    }
    
    void Folio(){
        int j;
        int cont=1;
        String num="";
        String c="";
         String SQL="SELECT MAX(folio) FROM eventos";
       
        try {
            Statement st = cn.createStatement();
            ResultSet rs=st.executeQuery(SQL);
            if(rs.next()){              
                 c=rs.getString(1);
            }
            
            if(c==null){
                jlFolio.setText("00000001");
                //jmFolio.setText("Evento: "+"00000001");
            }
            else{
                 j=Integer.parseInt(c);
                 GenerarNumero gen= new GenerarNumero();
                 gen.generar(j);
                 jlFolio.setText(gen.serie());
                 //jmFolio.setText("Evento: "+gen.serie());
            }
            //cn.close();
        } catch (SQLException ex) {
            System.out.println("Error en el folio principal \n"+ex);
           Logger.getLogger(Presupuesto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtNewNombre = new javax.swing.JTextField();
        jcbNewCategoria = new javax.swing.JComboBox<>();
        txtNewNota = new javax.swing.JTextField();
        rbNewPendiente = new javax.swing.JRadioButton();
        rbNewCompleto = new javax.swing.JRadioButton();
        jcNewFecha = new com.toedter.calendar.JDateChooser();
        btnNewCancelar = new javax.swing.JButton();
        btnNewGuardar = new javax.swing.JButton();
        jlFolio = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtSubNombre = new javax.swing.JTextField();
        txtSubNota = new javax.swing.JTextField();
        rbSubPendiente = new javax.swing.JRadioButton();
        rbSubCompleto = new javax.swing.JRadioButton();
        btnSubCancelar = new javax.swing.JButton();
        btnSubGuardar = new javax.swing.JButton();

        setClosable(true);
        setTitle("Nueva Tarea");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Nueva Tarea"));

        jLabel2.setText("Nombre:");

        jLabel3.setText("Categoria:");

        jLabel4.setText("Nota:");

        jLabel5.setText("Estado:");

        jLabel6.setText("Fecha:");

        jcbNewCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Accesorios", "Alojamiento", "Vestimenta Y Accesorios", "Ceremonia", "Flor y Decoración", "Otro" }));

        rbNewPendiente.setText("Pendiente");
        rbNewPendiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbNewPendienteActionPerformed(evt);
            }
        });

        rbNewCompleto.setText("Completo");
        rbNewCompleto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbNewCompletoActionPerformed(evt);
            }
        });

        btnNewCancelar.setText("Cancelar");
        btnNewCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewCancelarActionPerformed(evt);
            }
        });

        btnNewGuardar.setText("Guardar");
        btnNewGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewGuardarActionPerformed(evt);
            }
        });

        jlFolio.setText("jLabel1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(27, 27, 27)
                        .addComponent(txtNewNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jcbNewCategoria, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(rbNewPendiente)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jlFolio, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(rbNewCompleto)
                                .addGap(41, 41, 41))
                            .addComponent(txtNewNota)
                            .addComponent(jcNewFecha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(btnNewCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnNewGuardar)
                        .addGap(48, 48, 48)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNewNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jcbNewCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(txtNewNota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rbNewPendiente)
                        .addComponent(jlFolio))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(rbNewCompleto)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(jcNewFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNewCancelar)
                    .addComponent(btnNewGuardar))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Nueva SubTarea"));

        jLabel8.setText("Nombre:");

        jLabel9.setText("Nota:");

        jLabel10.setText("Estado:");

        rbSubPendiente.setText("Pendiente");
        rbSubPendiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbSubPendienteActionPerformed(evt);
            }
        });

        rbSubCompleto.setText("Completo");
        rbSubCompleto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbSubCompletoActionPerformed(evt);
            }
        });

        btnSubCancelar.setText("Cancelar");
        btnSubCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubCancelarActionPerformed(evt);
            }
        });

        btnSubGuardar.setText("Guardar");
        btnSubGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubGuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSubNombre, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSubNota, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(rbSubPendiente))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(btnSubCancelar)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSubGuardar)
                            .addComponent(rbSubCompleto))
                        .addGap(37, 37, 37)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtSubNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(txtSubNota, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rbSubPendiente)
                        .addComponent(rbSubCompleto)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSubCancelar)
                    .addComponent(btnSubGuardar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rbNewPendienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbNewPendienteActionPerformed
        // TODO add your handling code here:
        rbNewCompleto.setSelected(false);
    }//GEN-LAST:event_rbNewPendienteActionPerformed

    private void rbNewCompletoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbNewCompletoActionPerformed
        // TODO add your handling code here:
        rbNewPendiente.setSelected(false);
    }//GEN-LAST:event_rbNewCompletoActionPerformed

    private void btnNewGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewGuardarActionPerformed
        // TODO add your handling code here:
        String folio=txtNewNombre.getText();
        String folio1="";
        String sql="SELECT * FROM `tareas` WHERE `nombre`='"+folio+"'";
        
        try{
            Statement st=cn.createStatement();
            ResultSet rs=st.executeQuery(sql);
            
            while(rs.next()){
                folio1=rs.getString(3);
            }
        
        }catch(Exception e){
            System.out.println("Funciona la comparacion -->"+e);
        }
        
        if(folio1.equals(folio)){
            System.out.println("Funciona la comparacion");
            ModificarTarea();
        }else{
            NuevaTarea();
        }
    }//GEN-LAST:event_btnNewGuardarActionPerformed

    private void btnNewCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewCancelarActionPerformed
        // TODO add your handling code here:
        ListaTarea lt=new ListaTarea();
        Principal.jdpEscritorio.add(lt);
        lt.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnNewCancelarActionPerformed

    private void btnSubGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubGuardarActionPerformed
        // TODO add your handling code here:
              
        String folio=txtSubNombre.getText();
        String folio1="";
        String sql="SELECT * FROM `subtareas` WHERE `nombre`='"+folio+"'";
        
        try{
            Statement st=cn.createStatement();
            ResultSet rs=st.executeQuery(sql);
            
            while(rs.next()){
                folio1=rs.getString(2);
            }
        
        }catch(Exception e){
            System.out.println("Funciona la comparacion -->"+e);
        }
        
        if(folio1.equals(folio)){
            System.out.println("Funciona la comparacion");
            ModificarSubtarea();
        }else{
            NuevaSubtarea();
        }
    }//GEN-LAST:event_btnSubGuardarActionPerformed

    private void rbSubCompletoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbSubCompletoActionPerformed
        // TODO add your handling code here:
        rbSubPendiente.setSelected(false);
    }//GEN-LAST:event_rbSubCompletoActionPerformed

    private void rbSubPendienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbSubPendienteActionPerformed
        // TODO add your handling code here:
        rbSubCompleto.setSelected(false);
    }//GEN-LAST:event_rbSubPendienteActionPerformed

    private void btnSubCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubCancelarActionPerformed
        // TODO add your handling code here:
        ListaTarea lt=new ListaTarea();
        Principal.jdpEscritorio.add(lt);
        lt.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnSubCancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNewCancelar;
    private javax.swing.JButton btnNewGuardar;
    private javax.swing.JButton btnSubCancelar;
    private javax.swing.JButton btnSubGuardar;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    public static com.toedter.calendar.JDateChooser jcNewFecha;
    public static javax.swing.JComboBox<String> jcbNewCategoria;
    private javax.swing.JLabel jlFolio;
    public static javax.swing.JRadioButton rbNewCompleto;
    public static javax.swing.JRadioButton rbNewPendiente;
    public static javax.swing.JRadioButton rbSubCompleto;
    public static javax.swing.JRadioButton rbSubPendiente;
    public static javax.swing.JTextField txtNewNombre;
    public static javax.swing.JTextField txtNewNota;
    public static javax.swing.JTextField txtSubNombre;
    public static javax.swing.JTextField txtSubNota;
    // End of variables declaration//GEN-END:variables
    
    Conectar cc= new Conectar();
    Connection cn=cc.conexion();
}
