/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formulario;

import Conectar.Conectar;
import static java.lang.Thread.sleep;
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
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author TAMY-IA
 */
public class Presupuesto extends javax.swing.JInternalFrame {

    DefaultTableModel model;
    /**
     * Creates new form Presupuesto
     */
    public Presupuesto() {
        initComponents();
        Folio();
        PanelPagoDesactivado();
        
        //Cargar(fol);
        //System.out.println("este es el folio-->"+fol);
    }
   
    public void Cargar(String valor){
        try{
            String[] titulos={"Folio","Nombre","Expiracion","Suma","Estado"};
            String[] registros=new String[5];
            model=new DefaultTableModel(null, titulos);
            //String cons="SELECT * FROM pagos WHERE CONCAT (`idPagos`, `nombre`, `cantidad`, `estado`, `expiracion`, `folio`) LIKE '%"+valor+"%'";
            String cons="SELECT * FROM pagos WHERE CONCAT (`folio`) LIKE '%"+valor+"%'";
            
            Statement st=cn.createStatement();
            ResultSet rs=st.executeQuery(cons);
            
            while(rs.next()){
                registros[0]=rs.getString(6);
                registros[1]=rs.getString(2);
                registros[2]=rs.getString(5);
                registros[3]=rs.getString(3);
                registros[4]=rs.getString(4);
                
                model.addRow(registros);
                
            }
            
            tbPagos.setModel(model);
            Calcular();
        }catch(Exception e){
            System.out.println("Error al cargar la lista\n"+e);
        }
    }
    
    void NuevoPresupuesto(){
        String id="0", nombre, categoria, nota, cantidad, folio="";
        String sql="INSERT INTO presupuesto (`idPresupuesto`, `nombre`, `categoria`, `nota`, `estimada`, `folio`)VALUES(?,?,?,?,?,?)";
        
        nombre=txtNombre.getText();
        categoria=String.valueOf(jcbCategoria.getSelectedItem());
        nota=txtNota.getText();
        cantidad=txtCantidad.getText();
        folio=jlFolio.getText();
        
        try{
            PreparedStatement pst=cn.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, nombre);
            pst.setString(3, categoria);
            pst.setString(4, nota);
            pst.setString(5, cantidad);
            pst.setString(6, folio);
            
            int n=pst.executeUpdate();
            
            if(n>0){
                System.out.println("Presupuesto Guardado");
                LimpiarPanelPresupuesto();
                Folio();
            }
        
        }catch(Exception e){
            System.out.println("Error al registrar el presupuesto\n"+e);
        }
        
    }
    
    void ActualizarPresupuesto(){
        String categoria=String.valueOf(jcbCategoria.getSelectedItem());
        String sql="UPDATE presupuesto SET `nombre`='"+txtNombre.getText()+"', `categoria`='"+categoria+"', `nota`='"+txtNota.getText()+"', `estimada`='"+txtCantidad.getText()+"', `folio`='"+jlFolio.getText()+"' WHERE `folio`='"+jlFolio.getText()+"'";
        
        try{
            PreparedStatement pst=cn.prepareStatement(sql);
            pst.executeUpdate();
            LimpiarPanelPresupuesto();
            
            ListaPresupuesto lp=new ListaPresupuesto();
            Principal.jdpEscritorio.add(lp);
            lp.setVisible(true);
            
            this.dispose();
            
        }catch(Exception e){
            System.out.println("error al actualizar clase presupuesto\n"+e);
        }
    }
    
    void LimpiarPanelPresupuesto(){
        txtNombre.setText("");
        //jcbCategoria
        txtNota.setText("");
        txtCantidad.setText("");
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
            Cargar(folio);
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
    }   //COIMPLETO
    
    void PanelPagoDesactivado(){
        txtPagoNombre.setEnabled(false);
        txtPagoCantidad.setEnabled(false);
        rbPendiente.setEnabled(false);
        rbPagado.setEnabled(false);
        jcFecha.setEnabled(false);
        
        btnPagoAceptar.setEnabled(false);
        btnPagoCancelar.setEnabled(false);
    }   //COMPLETO
    
    void PanelPagoActivado(){
        txtPagoNombre.setEnabled(true);
        txtPagoCantidad.setEnabled(true);
        rbPendiente.setEnabled(true);
        rbPagado.setEnabled(true);
        jcFecha.setEnabled(true);
        
        btnPagoAceptar.setEnabled(true);
        btnPagoCancelar.setEnabled(true);
    }   //COMPLETO
    
    void Folio(){
        int j;
        int cont=1;
        String num="";
        String c="";
         String SQL="SELECT MAX(folio) FROM presupuesto";
       
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
    }
    
    void Calcular(){
        double estimacion=0, cant=0, pagados=0, pendientes=0;
        String cantidad="", estado="";
        estimacion=Double.valueOf(jlEquilibrar.getText());
        
        for(int i=0;i<tbPagos.getRowCount();i++){
            cantidad=tbPagos.getValueAt(i, 3).toString();
            estado=tbPagos.getValueAt(i, 4).toString();
            
            cant=Double.valueOf(cantidad);
            
            if(estado.equals("Pagado")){
                pagados=pagados+cant;
                jlPagado.setText(""+Math.rint(pagados));
                jlPendiente.setText(""+Math.rint(pendientes-pagados));
            }
            if(estado.equals("Pendiente")){
                pendientes=pendientes+cant;
                jlPendiente.setText(""+Math.rint(pendientes));
                jlEquilibrar.setText(""+Math.rint(estimacion-pendientes));
            }
            
        }//fin del for
        
    }   //PENDIENTE NO CALCULA BIEN!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
   
     /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jcbCategoria = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        txtNota = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        btnAceptar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jlPagado = new javax.swing.JLabel();
        jlPendiente = new javax.swing.JLabel();
        jlEquilibrar = new javax.swing.JLabel();
        jlFolio = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btnAbonar = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();
        jpPanelpago = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtPagoNombre = new javax.swing.JTextField();
        txtPagoCantidad = new javax.swing.JTextField();
        rbPendiente = new javax.swing.JRadioButton();
        rbPagado = new javax.swing.JRadioButton();
        jcFecha = new com.toedter.calendar.JDateChooser();
        btnPagoAceptar = new javax.swing.JButton();
        btnPagoCancelar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbPagos = new javax.swing.JTable();
        btnPagado = new javax.swing.JButton();
        btnPendiente = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setClosable(true);
        setTitle("Presupuestos");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Presupuesto"));

        jLabel2.setText("Nombre:");

        jLabel3.setText("Categoria:");

        jcbCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Accesorios", "Alojamiento", "Vestimenta Y Accesorios", "Ceremonia", "Flor y Decoración", "Otro" }));

        jLabel4.setText("Nota:");

        jLabel5.setText("<html>Cantidad<br>Estimada:<html>");

        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCantidadKeyReleased(evt);
            }
        });

        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        jLabel6.setText("Equilibrar:");

        jLabel7.setText("Pendiente:");

        jLabel10.setText("Pagado:");

        jlPagado.setText("0");

        jlPendiente.setText("0");

        jlEquilibrar.setText("0");

        jLabel1.setText("Folio:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlEquilibrar, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jlPendiente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlPagado, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlFolio, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jlEquilibrar)
                    .addComponent(jLabel1)
                    .addComponent(jlFolio, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jlPendiente)
                    .addComponent(jLabel10)
                    .addComponent(jlPagado)))
        );

        btnAbonar.setText("Abonar");
        btnAbonar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbonarActionPerformed(evt);
            }
        });

        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCantidad)
                            .addComponent(txtNombre, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jcbCategoria, javax.swing.GroupLayout.Alignment.TRAILING, 0, 230, Short.MAX_VALUE)
                            .addComponent(txtNota))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnRegresar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAbonar)
                                .addGap(34, 34, 34)
                                .addComponent(btnAceptar))
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(21, 21, 21))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jcbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtNota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar)
                    .addComponent(btnAbonar)
                    .addComponent(btnRegresar))
                .addContainerGap())
        );

        jpPanelpago.setBorder(javax.swing.BorderFactory.createTitledBorder("Nuevo Pago"));

        jLabel13.setText("Nombre:");

        jLabel14.setText("Cantidad:");

        jLabel15.setText("<html>Estado<br>del pago:<html>");

        jLabel16.setText("<html>Fecha De<br>Expiracion:<html>");

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

        btnPagoAceptar.setText("Aceptar");
        btnPagoAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPagoAceptarActionPerformed(evt);
            }
        });

        btnPagoCancelar.setText("Cancelar");
        btnPagoCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPagoCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpPanelpagoLayout = new javax.swing.GroupLayout(jpPanelpago);
        jpPanelpago.setLayout(jpPanelpagoLayout);
        jpPanelpagoLayout.setHorizontalGroup(
            jpPanelpagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpPanelpagoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpPanelpagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpPanelpagoLayout.createSequentialGroup()
                        .addGroup(jpPanelpagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14)
                            .addComponent(jLabel16)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpPanelpagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpPanelpagoLayout.createSequentialGroup()
                                .addComponent(rbPendiente)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1, Short.MAX_VALUE)
                                .addComponent(rbPagado)
                                .addGap(30, 30, 30))
                            .addGroup(jpPanelpagoLayout.createSequentialGroup()
                                .addGroup(jpPanelpagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnPagoAceptar)
                                    .addGroup(jpPanelpagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jcFecha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                        .addComponent(txtPagoCantidad, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtPagoNombre, javax.swing.GroupLayout.Alignment.LEADING)))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jpPanelpagoLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(btnPagoCancelar)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(24, 24, 24))
        );
        jpPanelpagoLayout.setVerticalGroup(
            jpPanelpagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPanelpagoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpPanelpagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpPanelpagoLayout.createSequentialGroup()
                        .addGroup(jpPanelpagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txtPagoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpPanelpagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txtPagoCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpPanelpagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rbPendiente)
                        .addComponent(rbPagado)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpPanelpagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpPanelpagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnPagoCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPagoAceptar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 171, Short.MAX_VALUE)
        );

        tbPagos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nombre", "Expira", "Suma", "Estado"
            }
        ));
        jScrollPane2.setViewportView(tbPagos);

        btnPagado.setText("Pagado");
        btnPagado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPagadoActionPerformed(evt);
            }
        });

        btnPendiente.setText("Pendiente");
        btnPendiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPendienteActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jpPanelpago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnEliminar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnPendiente)
                                .addGap(18, 18, 18)
                                .addComponent(btnPagado))))
                    .addComponent(jScrollPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jpPanelpago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnPagado)
                            .addComponent(btnPendiente)
                            .addComponent(btnEliminar))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(6, 6, 6))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyReleased
        // TODO add your handling code here:
        jlEquilibrar.setText(txtCantidad.getText());
    }//GEN-LAST:event_txtCantidadKeyReleased

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:
        String folio=jlFolio.getText();
        String folio1="";
        String sql="SELECT * FROM Presupuesto WHERE folio='"+folio+"'";
        
        try{
            Statement st=cn.createStatement();
            ResultSet rs=st.executeQuery(sql);
            
            while(rs.next()){
                folio1=rs.getString(6);
            }
        
        }catch(Exception e){
            
        }
        
        if(folio1.equals(folio)){
            //System.out.println("Funciona la comparacion");
            ActualizarPresupuesto();
        }else{
            NuevoPresupuesto();
        }
        
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnAbonarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbonarActionPerformed
        // TODO add your handling code here:
        PanelPagoActivado();
    }//GEN-LAST:event_btnAbonarActionPerformed

    private void btnPagoCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPagoCancelarActionPerformed
        // TODO add your handling code here:
        PanelPagoDesactivado();
        LimpiarPanelPago();
    }//GEN-LAST:event_btnPagoCancelarActionPerformed

    private void btnPagoAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPagoAceptarActionPerformed
        // TODO add your handling code here:
        Pago();
    }//GEN-LAST:event_btnPagoAceptarActionPerformed

    private void rbPendienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbPendienteActionPerformed
        // TODO add your handling code here:
        rbPagado.setSelected(false);
    }//GEN-LAST:event_rbPendienteActionPerformed

    private void rbPagadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbPagadoActionPerformed
        // TODO add your handling code here:
        rbPendiente.setSelected(false);
    }//GEN-LAST:event_rbPagadoActionPerformed

    private void btnPendienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPendienteActionPerformed
        // TODO add your handling code here:
       int filase1=tbPagos.getSelectedRow();
        
        try{
            if(filase1==-1){
                JOptionPane.showMessageDialog(null, "Por favor seleccione un adeudo");
            }else{
                String folio=(String)tbPagos.getValueAt(filase1, 1);
                String con="UPDATE Pagos SET `estado` = 'Pendiente' WHERE nombre ='"+folio+"'";
        
                try{
                    PreparedStatement pst=cn.prepareStatement(con);
                    pst.executeUpdate();
                    Cargar(jlFolio.getText());
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(null, "Error al realizar el pago\n"+ex);
                }
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Error al pagar\n"+ex);
        }
    }//GEN-LAST:event_btnPendienteActionPerformed

    private void btnPagadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPagadoActionPerformed
        // TODO add your handling code here:
        int filase1=tbPagos.getSelectedRow();
        
        try{
            if(filase1==-1){
                JOptionPane.showMessageDialog(null, "Por favor seleccione un adeudo");
            }else{
                String folio=(String)tbPagos.getValueAt(filase1, 1);
                String con="UPDATE Pagos SET `estado` = 'Pagado' WHERE nombre ='"+folio+"'";
        
                try{
                    PreparedStatement pst=cn.prepareStatement(con);
                    pst.executeUpdate();
                    Cargar(jlFolio.getText());
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(null, "Error al realizar el pago\n"+ex);
                }
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Error al pagar\n"+ex);
        }
    }//GEN-LAST:event_btnPagadoActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        int filase1= tbPagos.getSelectedRow();
        int confirmar=JOptionPane.showConfirmDialog(null, "¿Eliminar Pago?", "Confirmar Eliminacion", JOptionPane.YES_NO_OPTION);
        
        if(confirmar==JOptionPane.YES_OPTION){
            try{
            if(filase1==-1){
                JOptionPane.showMessageDialog(null, "Seleccione el pago a eliminar");
            }else{
                String cod=(String)tbPagos.getValueAt(filase1, 0);
                String eliminarSQL="DELETE FROM Pagos WHERE nombre='"+cod+"'";
                try{
                    PreparedStatement pst = cn.prepareStatement(eliminarSQL);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Pago Eliminado");
                    Cargar(jlFolio.getText());
                    
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, e);
                }
            }
            
        }catch(Exception e){
            
        }
        }else{
            //JOptionPane.showMessageDialog(null, "cancelado");
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        // TODO add your handling code here:
        ListaPresupuesto lp=new ListaPresupuesto();
        Principal.jdpEscritorio.add(lp);
        lp.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnRegresarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbonar;
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnPagado;
    private javax.swing.JButton btnPagoAceptar;
    private javax.swing.JButton btnPagoCancelar;
    private javax.swing.JButton btnPendiente;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private com.toedter.calendar.JDateChooser jcFecha;
    public static javax.swing.JComboBox<String> jcbCategoria;
    public static javax.swing.JLabel jlEquilibrar;
    public static javax.swing.JLabel jlFolio;
    private javax.swing.JLabel jlPagado;
    private javax.swing.JLabel jlPendiente;
    private javax.swing.JPanel jpPanelpago;
    private javax.swing.JRadioButton rbPagado;
    private javax.swing.JRadioButton rbPendiente;
    private javax.swing.JTable tbPagos;
    public static javax.swing.JTextField txtCantidad;
    public static javax.swing.JTextField txtNombre;
    public static javax.swing.JTextField txtNota;
    private javax.swing.JTextField txtPagoCantidad;
    private javax.swing.JTextField txtPagoNombre;
    // End of variables declaration//GEN-END:variables
    
    Conectar cc=new Conectar();
    Connection cn=cc.conexion();
}
