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
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author TAMY-IA
 */
public class RegistrarInvitados extends javax.swing.JInternalFrame {

    /**
     * Creates new form RegistrarInvitados
     */
    public RegistrarInvitados() {
        initComponents();
        this.setLocation(440, 50);
        jlId1.setVisible(false);
        jlId.setVisible(false);
        jlFolio.setVisible(false);
    }
    
    void Validar(){
        Guardar();
    }   //INCOMPLETO !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    
    void Guardar(){
        String id="0", genero="", x="", invitacion="";
        String nulo="";
        ////////    ✓   ///////////<------PALOMITA
        String consulta="INSERT INTO asistencia_invitado (`idAsistencia_Invitado`,`folio`, `nombre`, `genero`, `grupo`, `nota`, `invitacion`, `relacion`, `mesa`, `telcel`, `correo`, `direccion`, `asistencia`)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        
        if(rbMasculino.isSelected()==true){
            genero="Masculino";
        }else if(rbFemenino.isSelected()==true){
            genero="Femenino";
        }
        ////////////////////////////////////////////
        if(cbAdulto.isSelected()==true){
            x="Adulto";
        }else if(cbNiño.isSelected()==true){
            x="Niño";
        }else if(cbBebe.isSelected()==true){
            x="Bebe";
        }
        /////////////////////////////////////////////
        if(rbEnviada.isSelected()==true){
            invitacion="Enviada";
        }else if(rbNoEnviada.isSelected()==true){
            invitacion="No Enviada";
        }
        
        try{
            PreparedStatement pst=cn.prepareStatement(consulta);
            pst.setString(1, id);
            pst.setString(2, jlFolio.getText());
            pst.setString(3, txtNombre.getText());
            pst.setString(4, genero);
            pst.setString(5, x);
            pst.setString(6, txtNota.getText());
            pst.setString(7, invitacion);
            pst.setString(8, txtRelacion.getText());
            pst.setString(9, txtMesa.getText());
            pst.setString(10, txtTelefono.getText());
            pst.setString(11, txtCorreo.getText());
            pst.setString(12, txtDireccion.getText());
            pst.setString(13, nulo);
            
            pst.executeUpdate();
            Limpiar();
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Error al guardar el registro\n"+ex);
        }
    }
    
    void Limpiar(){
        txtNombre.setText("");
        rbMasculino.setSelected(false);
        rbFemenino.setSelected(false);
        cbAdulto.setSelected(false);
        cbNiño.setSelected(false);
        cbBebe.setSelected(false);
        txtNota.setText("");
        rbEnviada.setSelected(false);
        rbNoEnviada.setSelected(false);
        txtRelacion.setText("");
        txtMesa.setText("");
        
        txtTelefono.setText("");
        txtCorreo.setText("");
        txtDireccion.setText("");
    }
    
    void Actualizar(){
        
        String genero="", x="", invitacion="";
        if(rbMasculino.isSelected()==true){
            genero="Masculino";
        }else if(rbFemenino.isSelected()==true){
            genero="Femenino";
        }
        ////////////////////////////////////////////
        if(cbAdulto.isSelected()==true){
            x="Adulto";
        }else if(cbNiño.isSelected()==true){
            x="Niño";
        }else if(cbBebe.isSelected()==true){
            x="Bebe";
        }
        /////////////////////////////////////////////
        if(rbEnviada.isSelected()==true){
            invitacion="Invitacion Enviada";
        }else if(rbNoEnviada.isSelected()==true){
            invitacion="Invitacion No Enviada";
        }
        
        String sql="UPDATE asistencia_invitado SET `nombre`='"+txtNombre.getText()+"', `genero`='"+genero+"', `grupo`='"+x+"', `nota`='"+txtNota.getText()+"', `invitacion`='"+invitacion+"', `relacion`='"+txtRelacion.getText()+"', `mesa`='"+txtMesa.getText()+"', `telcel`='"+txtTelefono.getText()+"', `correo`='"+txtCorreo.getText()+"', `direccion`='"+txtDireccion.getText()+"' WHERE `idAsistencia_Invitado`='"+jlId.getText()+"'";
        try{
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Invitado Actualizado");
            //cargar("");
            //bloquear();
            //limpiar();
            //btnNuevo.setEnabled(true);
            this.dispose();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void CargarDatos(String valor){
        try{
            String genero="", grupo="", nota="", invitacion="", telcel="", correo="", direccion="";
            //model=new DefaultTableModel(null, titulos);
            
            String consulta="SELECT `genero`, `grupo`, `nota`, `invitacion`, `telcel`, `correo`, `direccion` FROM asistencia_invitado WHERE `idAsistencia_Invitado`='"+valor+"'";
            
            Statement st = cn.createStatement(); 
            ResultSet rs=st.executeQuery(consulta);
            //System.out.println("Aqui terminal la ejecucion");
            while(rs.next()){
                genero = rs.getString(1);
                grupo=rs.getString(2);
                nota= rs.getString(3);
                invitacion=rs.getString(4);
                telcel=rs.getString(5);
                correo = rs.getString(6);
                direccion=rs.getString(7);
            }
            //System.out.println("Aqui terminal la ejecucion");
            if(genero.equals("Masculino")){
                rbMasculino.setSelected(true);
            }else if(genero.equals("Femenino")){
                rbFemenino.setSelected(true);
            }
            
            if(grupo.equals("Adulto")){
                cbAdulto.setSelected(true);
            }else if(grupo.equals("Niño")){
                cbNiño.setSelected(true);
            }else if(grupo.equals("Bebe")){
                cbBebe.setSelected(true);
            }
            
            if(invitacion.equals("Enviada")){
                rbEnviada.setSelected(true);
            }else if(invitacion.equals("No Enviada")){
                rbNoEnviada.setSelected(true);
            }
            
            txtNota.setText(nota);
            txtTelefono.setText(telcel);
            txtCorreo.setText(correo);
            txtDireccion.setText(direccion);
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Error al cargar la lista\n"+ex);
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtRelacion = new javax.swing.JTextField();
        txtMesa = new javax.swing.JTextField();
        jlTitulo = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        rbMasculino = new javax.swing.JRadioButton();
        rbFemenino = new javax.swing.JRadioButton();
        cbAdulto = new javax.swing.JCheckBox();
        cbNiño = new javax.swing.JCheckBox();
        cbBebe = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        txtNota = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        rbEnviada = new javax.swing.JRadioButton();
        rbNoEnviada = new javax.swing.JRadioButton();
        jlId1 = new javax.swing.JLabel();
        jlId = new javax.swing.JLabel();
        jlFolio = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnCancelar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();

        setClosable(true);
        setTitle("Nuevo Invitado");

        jLabel1.setText("Nombre Completo:");

        jLabel2.setText("Relacion:");

        jLabel3.setText("Mesa:");

        jlTitulo.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jlTitulo.setText("Registrar Invitado");

        jLabel5.setText("Genero:");

        rbMasculino.setText("Masculino");
        rbMasculino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbMasculinoActionPerformed(evt);
            }
        });

        rbFemenino.setText("Femenino");
        rbFemenino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbFemeninoActionPerformed(evt);
            }
        });

        cbAdulto.setText("Adulto");
        cbAdulto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbAdultoActionPerformed(evt);
            }
        });

        cbNiño.setText("Niño");
        cbNiño.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbNiñoActionPerformed(evt);
            }
        });

        cbBebe.setText("Bebe");
        cbBebe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbBebeActionPerformed(evt);
            }
        });

        jLabel6.setText("Nota:");

        jLabel7.setText("Estado De Invitacion:");

        rbEnviada.setText("Invitacion Enviada");
        rbEnviada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbEnviadaActionPerformed(evt);
            }
        });

        rbNoEnviada.setText("Invitacion No Enviada");
        rbNoEnviada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbNoEnviadaActionPerformed(evt);
            }
        });

        jlId1.setText("ID:");

        jlId.setText("vacio");

        jlFolio.setText("Folio");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jlTitulo)
                .addGap(133, 133, 133))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(rbEnviada)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                        .addComponent(rbNoEnviada))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jlFolio, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNota)
                            .addComponent(txtNombre)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(cbAdulto)
                                        .addGap(77, 77, 77)
                                        .addComponent(cbNiño)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cbBebe))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(rbMasculino)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(rbFemenino)
                                        .addGap(52, 52, 52))))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(54, 54, 54)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtRelacion)
                            .addComponent(txtMesa)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jlId1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlId, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jlTitulo)
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlId1)
                    .addComponent(jlId))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(rbMasculino)
                    .addComponent(rbFemenino))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbAdulto)
                    .addComponent(cbNiño)
                    .addComponent(cbBebe)
                    .addComponent(jlFolio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtNota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(rbEnviada)
                    .addComponent(rbNoEnviada))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtRelacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtMesa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalles de contacto"));

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        jLabel8.setText("Telefono:");

        jLabel9.setText("Correo:");

        jLabel10.setText("Direccion:");

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(btnCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnGuardar))
                    .addComponent(txtTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
                    .addComponent(txtCorreo)
                    .addComponent(txtDireccion))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnGuardar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        String folio=jlId.getText();
        String folio1="";
        String sql="SELECT * FROM asistencia_invitado WHERE `idAsistencia_Invitado`='"+folio+"'";
        
        try{
            Statement st=cn.createStatement();
            ResultSet rs=st.executeQuery(sql);
            System.out.println("este es --->> <<<<");
            while(rs.next()){
                folio1=rs.getString(1);
                System.out.println("este es --->>"+folio1+"<<<<");
            }
        
        }catch(Exception e){
            
        }
        
        if(folio1.equals(folio)){
            //System.out.println("Funciona la comparacion");
            Actualizar();
        }else if(folio1!=folio){
            Validar();
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void rbMasculinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbMasculinoActionPerformed
        // TODO add your handling code here:
        rbFemenino.setSelected(false);
    }//GEN-LAST:event_rbMasculinoActionPerformed

    private void rbFemeninoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbFemeninoActionPerformed
        // TODO add your handling code here:
        rbMasculino.setSelected(false);
    }//GEN-LAST:event_rbFemeninoActionPerformed

    private void cbAdultoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbAdultoActionPerformed
        // TODO add your handling code here:
        cbNiño.setSelected(false);
        cbBebe.setSelected(false);
    }//GEN-LAST:event_cbAdultoActionPerformed

    private void cbNiñoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbNiñoActionPerformed
        // TODO add your handling code here:
        cbAdulto.setSelected(false);
        cbBebe.setSelected(false);
    }//GEN-LAST:event_cbNiñoActionPerformed

    private void cbBebeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbBebeActionPerformed
        // TODO add your handling code here:
        cbAdulto.setSelected(false);
        cbNiño.setSelected(false);
    }//GEN-LAST:event_cbBebeActionPerformed

    private void rbEnviadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbEnviadaActionPerformed
        // TODO add your handling code here:
        rbNoEnviada.setSelected(false);
    }//GEN-LAST:event_rbEnviadaActionPerformed

    private void rbNoEnviadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbNoEnviadaActionPerformed
        // TODO add your handling code here:
        rbEnviada.setSelected(false);
    }//GEN-LAST:event_rbNoEnviadaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    public static javax.swing.JCheckBox cbAdulto;
    public static javax.swing.JCheckBox cbBebe;
    public static javax.swing.JCheckBox cbNiño;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel jlFolio;
    public static javax.swing.JLabel jlId;
    public static javax.swing.JLabel jlId1;
    public static javax.swing.JLabel jlTitulo;
    public static javax.swing.JRadioButton rbEnviada;
    public static javax.swing.JRadioButton rbFemenino;
    public static javax.swing.JRadioButton rbMasculino;
    public static javax.swing.JRadioButton rbNoEnviada;
    public static javax.swing.JTextField txtCorreo;
    public static javax.swing.JTextField txtDireccion;
    public static javax.swing.JTextField txtMesa;
    public static javax.swing.JTextField txtNombre;
    public static javax.swing.JTextField txtNota;
    public static javax.swing.JTextField txtRelacion;
    public static javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
    Conectar cc=new Conectar();
    Connection cn=cc.conexion();
}
