/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formulario;

import Conectar.Conectar;
import static java.lang.Thread.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author TAMY-IA
 */
public class ListaTarea extends javax.swing.JInternalFrame implements Runnable {

    DefaultTableModel model;
    /**
     * Creates new form ListaTarea
     */
    public ListaTarea() {
        initComponents();
        this.setLocation(440, 100);
        CargarTarea("");
        CargarSubTarea("");
        //run();
    }
    
    @Override
    public void run(){
        int i=0;
        while(true){
            CargarTarea("");
            CargarSubTarea("");
            i++;
            System.out.println("hilo-->"+i);
            try{
                sleep(1000);
            }catch(Exception ex){
            
            }
        }
    }
    
   public void CargarTarea(String valor){
        try{
            String[] titulos={"Nombre","Categoria","Nota","Estado","Fecha"};
            String[] registros=new String[5];
            model=new DefaultTableModel(null, titulos);
            
            String consulta="SELECT * FROM tareas WHERE CONCAT (`idtareas`, `nombre`, `categoria`, `nota`, `estado`, `fecha`) LIKE '%"+valor+"%'";
            
            Statement st=cn.createStatement();
            ResultSet rs=st.executeQuery(consulta);
            
            while(rs.next()){
                registros[0]=rs.getString(3);
                registros[1]=rs.getString(4);
                registros[2]=rs.getString(5);
                registros[3]=rs.getString(6);
                registros[4]=rs.getString(7);
                
                model.addRow(registros);
            }
            
            tbTareas.setModel(model);
            
        }catch(Exception e){
            System.out.println("Error Metodo CargarTarea\n"+e);
        }
    }
    
   public void CargarSubTarea(String valor){
        try{
            String[] titulos={"Nombre", "Nota", "Estado"};
            String[] registros=new String[3];
            model=new DefaultTableModel(null, titulos);
            String cons="SELECT * FROM subtareas WHERE CONCAT (`idSubtareas`, `nombre`, `nota`, `estado`) LIKE '%"+valor+"%'";
            
            Statement st = cn.createStatement();
            ResultSet rs=st.executeQuery(cons);
            
            while(rs.next()){
                registros[0]=rs.getString(3);
                registros[1]=rs.getString(4);
                registros[2]=rs.getString(5);
                
                model.addRow(registros);
            }
            
            tbSubTareas.setModel(model);
            
        }catch(Exception e){
            System.out.println("Error al cargar subtareas\n"+e);
        }
    }
    
    void ModificarTarea(){
        int fila = tbTareas.getSelectedRow();
	
        try {

            String nombre="",categoria="",nota="",estado="",fecha="";

           if(fila==-1){
               JOptionPane.showMessageDialog(null, "No ha seleccionado ninguna tarea");
           }else{
               
               NuevaTarea nt=new NuevaTarea();
               Principal.jdpEscritorio.add(nt);
               nt.setVisible(true);
        
               nombre =  (String)tbTareas.getValueAt(fila, 0).toString();
               categoria =  (String)tbTareas.getValueAt(fila, 1).toString();
               nota =  (String)tbTareas.getValueAt(fila, 2).toString();
               estado =  (String)tbTareas.getValueAt(fila, 3).toString();
               fecha=(String)tbTareas.getValueAt(fila, 4).toString();
               
               NuevaTarea.txtNewNombre.setText(nombre);
               NuevaTarea.jcbNewCategoria.setSelectedItem(categoria);
               NuevaTarea.txtNewNota.setText(nota);
               if(estado.equals("Pendiente")){
                   NuevaTarea.rbNewPendiente.setSelected(true);
               }else if(estado.equals("Completo")){
                   NuevaTarea.rbNewCompleto.setSelected(true);
               }
               //NuevaTarea.jcNewFecha.setDate(date);////////////////NO SE COMO HACERLO
               nt.DesbloquearTarea();

            this.dispose();

           }
       }catch (Exception e) {
           System.out.println("Error aol enviar datos\n"+e);
       }
    }
    
    void ModificarSubtarea(){
        try {
            int filaMod=tbSubTareas.getSelectedRow();
            if(filaMod==-1){
                JOptionPane.showMessageDialog(null, "Seleccione alguna fila");
            }else{
                
                String nombre="",nota="",estado="";
                
                NuevaTarea nst=new NuevaTarea();
                Principal.jdpEscritorio.add(nst);
                nst.setVisible(true);

                nombre =  (String)tbSubTareas.getValueAt(filaMod, 0).toString();
                nota =  (String)tbSubTareas.getValueAt(filaMod, 1).toString();
                estado =  (String)tbSubTareas.getValueAt(filaMod, 2).toString();

                NuevaTarea.txtSubNombre.setText(nombre);
                NuevaTarea.txtSubNota.setText(nota);
                if(estado.equals("Pendiente")){
                    NuevaTarea.rbSubPendiente.setSelected(true);
                }else if(estado.equals("Completo")){
                    NuevaTarea.rbSubCompleto.setSelected(true);
                }
                nst.DesbloquearSubtarea();

             this.dispose();
            }
        }catch (Exception e) {
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tbTareas = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbSubTareas = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        btnNueva = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnCompleta = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnSubtarea = new javax.swing.JButton();
        btnMidificarSubtarea = new javax.swing.JButton();
        btnEliminarSubtarea = new javax.swing.JButton();

        setClosable(true);
        setTitle("Lista De Tareas Y Subtareas");

        tbTareas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Nombre", "Categoria", "Nota", "", "Fecha"
            }
        ));
        jScrollPane1.setViewportView(tbTareas);

        tbSubTareas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nombre", "Nota", "Estado"
            }
        ));
        jScrollPane2.setViewportView(tbSubTareas);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        btnNueva.setText("Nueva Tarea");
        btnNueva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevaActionPerformed(evt);
            }
        });

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnCompleta.setBackground(new java.awt.Color(0, 204, 0));
        btnCompleta.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnCompleta.setText("Completa");
        btnCompleta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompletaActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnSubtarea.setText("Nueva SubTarea");
        btnSubtarea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubtareaActionPerformed(evt);
            }
        });

        btnMidificarSubtarea.setText("Modificar SubTarea");
        btnMidificarSubtarea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMidificarSubtareaActionPerformed(evt);
            }
        });

        btnEliminarSubtarea.setText("Eliminar SubTarea");
        btnEliminarSubtarea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarSubtareaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNueva, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCompleta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSubtarea, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                    .addComponent(btnMidificarSubtarea, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminarSubtarea, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNueva)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnModificar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminar)
                .addGap(91, 91, 91)
                .addComponent(btnSubtarea)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMidificarSubtarea)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminarSubtarea)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCompleta, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevaActionPerformed
        // TODO add your handling code here:
        NuevaTarea nt = new NuevaTarea();
        Principal.jdpEscritorio.add(nt);
        nt.toFront();
        nt.setVisible(true);
        nt.DesbloquearTarea();
       this.dispose();
        
    }//GEN-LAST:event_btnNuevaActionPerformed

    private void btnSubtareaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubtareaActionPerformed
        // TODO add your handling code here:
        NuevaTarea nsb = new NuevaTarea();
        Principal.jdpEscritorio.add(nsb);
        nsb.toFront();
        nsb.setVisible(true);
        nsb.DesbloquearSubtarea();
        this.dispose();
    }//GEN-LAST:event_btnSubtareaActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
        ModificarTarea();
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        int filase1= tbTareas.getSelectedRow();
        int confirmar=JOptionPane.showConfirmDialog(null, "¿Eliminar Tarea?", "Confirmar Eliminacion", JOptionPane.YES_NO_OPTION);
        
        if(confirmar==JOptionPane.YES_OPTION){
            try{
            if(filase1==-1){
                JOptionPane.showMessageDialog(null, "Seleccione la tarea a eliminar");
            }else{
                String cod=(String)tbTareas.getValueAt(filase1, 0);
                String eliminarSQL="DELETE FROM tareas WHERE `nombre`='"+cod+"'";
                try{
                    PreparedStatement pst = cn.prepareStatement(eliminarSQL);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Tarea Eliminada");
                    CargarTarea("");
                    
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, e);
                }
            }
            
        }catch(Exception e){
            
        }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnMidificarSubtareaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMidificarSubtareaActionPerformed
        // TODO add your handling code here:
        ModificarSubtarea();
    }//GEN-LAST:event_btnMidificarSubtareaActionPerformed

    private void btnEliminarSubtareaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarSubtareaActionPerformed
        // TODO add your handling code here:
        int filasel= tbSubTareas.getSelectedRow();
        try {
            if(filasel==-1)
            {
                JOptionPane.showMessageDialog(null, "Seleccione algun dato");
            }
            else
            {
                String  cod=(String)tbSubTareas.getValueAt(filasel, 0);
                String eliminarSQL="DELETE FROM subtareas WHERE nombre = '"+cod+"'";
                try {
                    PreparedStatement pst  = cn.prepareStatement(eliminarSQL);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Borrado");
                    CargarSubTarea("");
                }
                catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        }
        catch (Exception e)
        {
        }
    }//GEN-LAST:event_btnEliminarSubtareaActionPerformed

    private void btnCompletaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompletaActionPerformed
        // TODO add your handling code here:
        //UPDATE tareas SET `estado`='Completo' WHERE `nombre`=''
        int fila = tbTareas.getSelectedRow();
	
        try {

            String nombre="";

           if(fila==-1){
               JOptionPane.showMessageDialog(null, "No ha seleccionado ninguna tarea");
           }else{
               nombre =  (String)tbTareas.getValueAt(fila, 0).toString();
               String sql="UPDATE tareas SET `estado`='Completo' WHERE `nombre`='"+nombre+"'";
       
               PreparedStatement pst=cn.prepareStatement(sql);
               pst.executeUpdate();
               //JOptionPane.showMessageDialog(null, "Actualizado");
               CargarTarea("");         
           }
       }catch (Exception e) {
           System.out.println("Error aol enviar datos\n"+e);
       }
    }//GEN-LAST:event_btnCompletaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCompleta;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnEliminarSubtarea;
    private javax.swing.JButton btnMidificarSubtarea;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNueva;
    private javax.swing.JButton btnSubtarea;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tbSubTareas;
    private javax.swing.JTable tbTareas;
    // End of variables declaration//GEN-END:variables
    
    Conectar cc=new Conectar();
    Connection cn=cc.conexion();
}
