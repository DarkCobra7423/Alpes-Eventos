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

/**
 *
 * @author TAMY-IA
 */
public class Proveedores extends javax.swing.JInternalFrame {

    /**
     * Creates new form Proveedores
     */
    public Proveedores() {
        initComponents();
        this.setLocation(440, 50);
        Folio();
        PanelPagoDesactivado();
        jlFolio.setVisible(false);
    }
    
    void NuevoProveedor(){
        String id="0", nombre, categoria, nota, cantidad, folio="", telefono, correo, sitio, direccion;
        String sql="INSERT INTO proveedores (`idProveedores`, `folio`, `nombre`, `categoria`, `nota`, `estimada`, `telefono`, `correo`, `sitioweb`, `direccion`)VALUES(?,?,?,?,?,?,?,?,?,?)";
        
        folio=jlFolio.getText();
        nombre=txtNewNombre.getText();
        categoria=String.valueOf(jcbCategoria.getSelectedItem());
        nota=txtNewNota.getText();
        cantidad=txtEstimada.getText();
        telefono=txtTelefono.getText();
        correo=txtCorreo.getText();
        sitio=txtSitio.getText();
        direccion=txtDireccion.getText();
                
        try{
            PreparedStatement pst=cn.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, folio);
            pst.setString(3, nombre);
            pst.setString(4, categoria);
            pst.setString(5, nota);
            pst.setString(6, cantidad);
            pst.setString(7, telefono);
            pst.setString(8, correo);
            pst.setString(9, sitio);
            pst.setString(10, direccion);
            
            int n=pst.executeUpdate();
            
            if(n>0){
                System.out.println("Proveedor Guardado");
                LimpiarPanelProveedor();
                Folio();
            }
        
        }catch(Exception e){
            System.out.println("Error al registrar el proveedor\n"+e);
        }
        
    }
    
   /* void ActualizarPresupuesto(){
        String categoria=String.valueOf(jcbCategoria.getSelectedItem());
        String sql="UPDATE presupuesto SET `nombre`='"+txtNewNombre.getText()+"', `categoria`='"+categoria+"', `nota`='"+txtNewNota.getText()+"', `estimada`='"+txtEstimada.getText()+"', `folio`='"+jlFolio.getText()+"' WHERE `folio`='"+jlFolio.getText()+"'";
        
        try{
            PreparedStatement pst=cn.prepareStatement(sql);
            pst.executeUpdate();
            LimpiarPanelProveedor();
            
            ListaPresupuesto lp=new ListaPresupuesto();
            Principal.jdpEscritorio.add(lp);
            lp.setVisible(true);
            
            this.dispose();
            
        }catch(Exception e){
            System.out.println("error al actualizar clase presupuesto\n"+e);
        }
    }   */
    
    void LimpiarPanelProveedor(){
        txtNewNombre.setText("");
        //jcbCategoria
        txtNewNota.setText("");
        txtEstimada.setText("");
        txtTelefono.setText("");
        txtCorreo.setText("");
        txtSitio.setText("");
        txtDireccion.setText("");
    }
    
    void Pago(){
        String id="0", nombre, cantidad, estado="", expiracion="", folio="";
        String sql="INSERT INTO pagos (`idPagos`, `nombre`, `cantidad`, `estado`, `expiracion`, `folio`)VALUES(?,?,?,?,?,?)";
        folio=jlFolio.getText();
        nombre=txtPagoNombre.getText();
        cantidad=txtPagoCantidad.getText();
        if(rbPendiente.isSelected()==true){
            estado="Pendiente";
        }else if(rbPagado.isSelected()==true){
            estado="Pagado";
        }
        try{
            Date fecha1=jcFecha.getDate();
            DateFormat f=new SimpleDateFormat("dd-MM-YYYY");
            expiracion=f.format(fecha1);
        }catch(Exception e){
            
        }   
        
        try{
            PreparedStatement pst=cn.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, nombre);
            pst.setString(3, cantidad);
            pst.setString(4, estado);
            pst.setString(5, expiracion);
            pst.setString(6, folio);
            
            pst.executeUpdate();
            System.out.println("SubTarea Guardada");
            LimpiarPanelPago();
            //Cargar(folio);
            PanelPagoDesactivado();
            
        }catch(Exception e){
            System.out.println("error metodo pago\n"+e);
        }
    }
    
    void LimpiarPanelPago(){
        txtPagoNombre.setText("");
        txtPagoCantidad.setText("");
        rbPendiente.setSelected(false);
        rbPagado.setSelected(false);
        jcFecha.setDate(null);
        
        //btnPagoAceptar.setEnabled(false);
    }   //
    
    void PanelPagoDesactivado(){
        txtPagoNombre.setEnabled(false);
        txtPagoCantidad.setEnabled(false);
        rbPendiente.setEnabled(false);
        rbPagado.setEnabled(false);
        jcFecha.setEnabled(false);
        
        btnPagoAceptar.setEnabled(false);
        btnPagoCancelar.setEnabled(false);
    }   //
    
    void PanelPagoActivado(){
        txtPagoNombre.setEnabled(true);
        txtPagoCantidad.setEnabled(true);
        rbPendiente.setEnabled(true);
        rbPagado.setEnabled(true);
        jcFecha.setEnabled(true);
        
        btnPagoAceptar.setEnabled(true);
        btnPagoCancelar.setEnabled(true);
    }   //
    
    void Folio(){
        int j;
        int cont=1;
        String num="";
        String c="";
         String SQL="SELECT MAX(folio) FROM proveedores";
       
        try {
            Statement st = cn.createStatement();
            ResultSet rs=st.executeQuery(SQL);
            if(rs.next()){              
                 c=rs.getString(1);
            }
            
            if(c==null){
                jlFolio.setText("00000001");
            }
            else{
                 j=Integer.parseInt(c);
                 GenerarNumero gen= new GenerarNumero();
                 gen.generar(j);
                 jlFolio.setText(gen.serie());
                
            }
        } catch (SQLException ex) {
            System.out.println("Error en el folio presupuesto \n"+ex);
           Logger.getLogger(Presupuesto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   //completo

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
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtNewNombre = new javax.swing.JTextField();
        jcbCategoria = new javax.swing.JComboBox<>();
        txtNewNota = new javax.swing.JTextField();
        txtEstimada = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtSitio = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        txtPagoNombre = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtPagoCantidad = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jcFecha = new com.toedter.calendar.JDateChooser();
        btnPagoAceptar = new javax.swing.JButton();
        rbPendiente = new javax.swing.JRadioButton();
        rbPagado = new javax.swing.JRadioButton();
        jlFolio = new javax.swing.JLabel();
        btnPagoCancelar = new javax.swing.JButton();

        setClosable(true);
        setTitle("Proveedores");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalles Del Proveedor"));

        jLabel1.setText("Nombre:");

        jLabel2.setText("Categoria:");

        jLabel3.setText("Nota:");

        jLabel4.setText("Cantidad Estimada:");

        jLabel5.setText("Balance:");

        jLabel6.setText("0");

        jLabel7.setText("Pendiente:");

        jLabel8.setText("0");

        jLabel9.setText("Pagado:");

        jLabel10.setText("0");

        jcbCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Accesorios", "Alojamiento", "Vestimenta Y Accesorios", "Ceremonia", "Flor y Decoración", "Otro" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNewNombre)
                            .addComponent(jcbCategoria, 0, 334, Short.MAX_VALUE)
                            .addComponent(txtNewNota)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtEstimada))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNewNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jcbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNewNota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtEstimada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(jLabel8))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(jLabel6))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(jLabel10)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalles De Contacto"));

        jLabel11.setText("Telefono:");

        jLabel12.setText("Correo:");

        jLabel13.setText("Sitio Web:");

        jLabel14.setText("Direccion:");

        jButton2.setText("Guardar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setText("Pagos");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTelefono)
                            .addComponent(txtCorreo)
                            .addComponent(txtSitio)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel14))
                                .addGap(0, 125, Short.MAX_VALUE))
                            .addComponent(txtDireccion))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)
                        .addGap(26, 26, 26))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSitio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Nuevo Pago"));

        jLabel15.setText("Nombre:");

        jLabel16.setText("Cantidad:");

        jLabel17.setText("Estado De Pago:");

        jLabel18.setText("Fecha De Expiracion:");

        btnPagoAceptar.setText("Aceptar");
        btnPagoAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPagoAceptarActionPerformed(evt);
            }
        });

        rbPendiente.setText("Pendiente");
        rbPendiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbPendienteActionPerformed(evt);
            }
        });

        rbPagado.setText("Pagado");
        rbPagado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbPagadoActionPerformed(evt);
            }
        });

        jlFolio.setText("Folio");

        btnPagoCancelar.setText("Cancelar");
        btnPagoCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPagoCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPagoNombre)
                    .addComponent(txtPagoCantidad)
                    .addComponent(jcFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(rbPendiente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rbPagado, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(18, 18, 18)
                                .addComponent(jlFolio, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(btnPagoCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnPagoAceptar)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel15))
                    .addComponent(jlFolio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPagoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPagoCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbPendiente)
                    .addComponent(rbPagado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPagoAceptar)
                    .addComponent(btnPagoCancelar))
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
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rbPendienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbPendienteActionPerformed
        // TODO add your handling code here:
        rbPagado.setSelected(false);
    }//GEN-LAST:event_rbPendienteActionPerformed

    private void rbPagadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbPagadoActionPerformed
        // TODO add your handling code here:
        rbPendiente.setSelected(false);
    }//GEN-LAST:event_rbPagadoActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.NuevoProveedor();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnPagoAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPagoAceptarActionPerformed
        // TODO add your handling code here:
        this.Pago();
    }//GEN-LAST:event_btnPagoAceptarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        PanelPagoActivado();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnPagoCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPagoCancelarActionPerformed
        // TODO add your handling code here:
        this.PanelPagoDesactivado();
        this.LimpiarPanelPago();
    }//GEN-LAST:event_btnPagoCancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPagoAceptar;
    private javax.swing.JButton btnPagoCancelar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private com.toedter.calendar.JDateChooser jcFecha;
    private javax.swing.JComboBox<String> jcbCategoria;
    private javax.swing.JLabel jlFolio;
    private javax.swing.JRadioButton rbPagado;
    private javax.swing.JRadioButton rbPendiente;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtEstimada;
    private javax.swing.JTextField txtNewNombre;
    private javax.swing.JTextField txtNewNota;
    private javax.swing.JTextField txtPagoCantidad;
    private javax.swing.JTextField txtPagoNombre;
    private javax.swing.JTextField txtSitio;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
    Conectar cc=new Conectar();
    Connection cn=cc.conexion();
}
