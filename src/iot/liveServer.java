/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iot;

import java.awt.Toolkit;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.*;
import javax.swing.JOptionPane;
import about.personal;


/**
 *
 * @author Mahadi Hassan
 */
public class liveServer extends javax.swing.JFrame {

    /**
     * Creates new form frm 
     */
    
    String url=null;    
    String server=null;
    String database=null;
    String uname=null;
    String pw=null;
    String port=null;
    
    int it;
    String nt,dt;
    
    Connection cc=null;
    Statement ss=null;
    ResultSet rr=null;
    
    public void lread() throws ClassNotFoundException{
        
        try {
            FileInputStream f=new FileInputStream("loginfrom.dat");
            ObjectInputStream o=new ObjectInputStream(f);
            server=(String) o.readObject();
            database=(String) o.readObject();
            uname=(String) o.readObject();
            pw=(String) o.readObject();
            port=(String) o.readObject();
            
            o.close();
                
            
//            url="jdbc:mysql://"+url+":"+port+"/"+root;
            url="jdbc:mysql://"+server+":"+port+"/"+database;
            
            System.out.println(url);
            System.out.println(server);
            System.out.println(database);
            System.out.println(uname);
            System.out.println(pw);
            System.out.println(port);            
       
        } catch (IOException e) {
            
            e.printStackTrace();
        } 
    }
    
    public void com(){
        try {
            int l=0;;
            rr=ss.executeQuery("SELECT COUNT(id) from info");
            if(rr.next()){
                l=rr.getInt("COUNT(id)");
            }
            String [] s = new String [l];
            rr=ss.executeQuery("SELECT name FROM info");
            for(int i=0; rr.next();i++){
                s[i]=(i+1)+". "+rr.getString("name");
            }
            n.setModel(new javax.swing.DefaultComboBoxModel<>(s));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void read(){
        try {
            String sss=n.getSelectedItem().toString().substring(3, n.getSelectedItem().toString().length());
            int count=0, c=0;
            boolean bo=true;
            
            rr=ss.executeQuery("SELECT * FROM info WHERE name="+ "\""+sss+"\"");
            
            for (int i = n.getSelectedIndex()-1; 0<=i; i--) {
                if(sss.toLowerCase().matches(n.getItemAt(i).substring(3,n.getItemAt(i).length()).toLowerCase())){
                    count++;
                }
            }
            
            System.out.println(count+"==");
            
            while(rr.next()){
                if(count==c){
                    System.out.println(rr.getString("name")+"\t"+rr.getString("id")+"\t"+rr.getString("dep")+"\t");
                    s.setText(rr.getString("id"));
                    d.setText(rr.getString("dep"));
                    bo=false;
                    break;
                }
                c++;
            }
            if(bo){
                rr.previous();
                System.out.println(rr.getString("name")+"\t"+rr.getString("id")+"\t"+rr.getString("dep")+"\t");
                s.setText(rr.getString("id"));
                d.setText(rr.getString("dep"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public void delete(){
        try {
            String sss=n.getSelectedItem().toString().substring(3, n.getSelectedItem().toString().length());
            
            int count=0, c=0;
            boolean bo=true;
            
            rr=ss.executeQuery("SELECT * FROM info WHERE name="+ "\""+sss+"\"");
            
            
            for (int i = n.getSelectedIndex()-1; 0<=i; i--) {
                if(sss.toLowerCase().matches(n.getItemAt(i).substring(3,n.getItemAt(i).length()).toLowerCase())){
                    count++;
                }
            }
            String ntt="";
            int itt=0;
            String dtt="";
            while(rr.next()){
                if(count==c){
                    
                    ntt=rr.getString("name");
                    itt=rr.getInt("id");
                    dtt=rr.getString("dep");
                    
                    ss.executeUpdate("DELETE FROM info WHERE name=\""+ntt+"\" AND id="+itt+" AND dep=\""+dtt+"\"");
                    System.out.println("a");
                    
                    bo=false;
                    break;
                }
                c++;
            }
            if(bo){
                rr.previous(); 
                
                ntt=rr.getString("name");
                itt=rr.getInt("id");
                dtt=rr.getString("dep");
                
                ss.executeUpdate("DELETE FROM info WHERE name=\""+ntt+"\" AND id="+itt+" AND dep=\""+dtt+"\"");
                
                System.out.println("b");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void check(){
        try {
            cc=DriverManager.getConnection(url,uname,pw);
            ss=cc.createStatement();
            JOptionPane.showMessageDialog(jPanel1,"DataBase Conneced !","Connectivity",JOptionPane.INFORMATION_MESSAGE);  
            
            try {
                ss.executeQuery("Select * from test1");
            } catch (Exception e) {
                ss.executeUpdate("Create Table info (user varchar(20),transmit varchar(20),received varchar(20))");
                ss.executeUpdate("Insert Into info values \"mithun1st\",\"mh_mithun\",\"s1#s2#s3#\"");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(jPanel1,"DataBase Connection Failed !","Connectivity",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    public liveServer() {
        
        initComponents();      
        this.setLocationRelativeTo(this);
        
        //setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("cloud.png")));
        
        try {
            lread();
            check();
            com();
        } catch (Exception e) {
            jButton2.setEnabled(false);
            jButton4.setEnabled(false);
            jToggleButton1.setEnabled(false);
            jToggleButton2.setEnabled(false);
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
        n = new javax.swing.JComboBox<>();
        s = new javax.swing.JTextField();
        d = new javax.swing.JTextField();
        jToggleButton1 = new javax.swing.JToggleButton();
        jButton2 = new javax.swing.JButton();
        jToggleButton2 = new javax.swing.JToggleButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Database Live Server- Develop By Mithun");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(51, 0, 51));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Name");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("ID No.");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Dep.");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        n.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        n.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nMouseClicked(evt);
            }
        });
        n.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nActionPerformed(evt);
            }
        });

        s.setEditable(false);
        s.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        s.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        d.setEditable(false);
        d.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        d.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jToggleButton1.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jToggleButton1.setText("Create");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jButton2.setText("Read");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jToggleButton2.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jToggleButton2.setText("Update");
        jToggleButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton2ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jButton4.setText("Delete");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(n, 0, 200, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(s, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(d, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(268, 268, 268)
                                .addComponent(jToggleButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 30, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(n, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(s, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(d, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jToggleButton1)
                    .addComponent(jButton2)
                    .addComponent(jToggleButton2)
                    .addComponent(jButton4))
                .addGap(39, 39, 39))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nMouseClicked
        // TODO add your handling code here:
        //System.out.println("ii");
    }//GEN-LAST:event_nMouseClicked

    private void nActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nActionPerformed
        // TODO add your handling code here:
        if(!jToggleButton1.isSelected() && !jToggleButton2.isSelected()){
            s.setText("- -");
            d.setText("- -");
            System.out.println("Index Selected!");
        }
        System.out.println(n.getSelectedIndex());
        
    }//GEN-LAST:event_nActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if(n.getItemCount()>0){
            //
            read();
        }
        else{
            JOptionPane.showMessageDialog(jPanel1, "List is Empty.", "Message",JOptionPane.INFORMATION_MESSAGE);
        }
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        
        
        if(jToggleButton1.isSelected()){
            
            jButton2.setEnabled(false);
            jToggleButton2.setEnabled(false);
            jButton4.setEnabled(false);
            
            jToggleButton1.setText("Add");
            n.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{""}));
            
            s.setText("");
            d.setText("");
            
            n.setEditable(true);
            s.setEditable(true);
            d.setEditable(true);
        }
        else{
            
            if(s.getText().isEmpty() || d.getText().isEmpty()){
                JOptionPane.showMessageDialog(jPanel1, "Field is Empty!","Alert",JOptionPane.WARNING_MESSAGE);
                jToggleButton1.setSelected(true);
            }
            
            else{
                try {
                    System.out.println((int)Integer.parseInt(s.getText()));
                    
                    jToggleButton1.setText("Create");

                    jButton2.setEnabled(true);
                    jToggleButton2.setEnabled(true);
                    jButton4.setEnabled(true);

                    //---
                    ss.executeUpdate("Insert Into info values (\""+n.getSelectedItem().toString()+"\","+Integer.parseInt(s.getText().trim())+",\""+d.getText().trim()+"\")");
                    com();
                    
                    
                    n.setEditable(false);
                    s.setEditable(false);
                    d.setEditable(false);



                    s.setText("");
                    d.setText("");


                } catch (Exception e) {
                    JOptionPane.showMessageDialog(jPanel1, "ID Must be Contain a Number!","Error",JOptionPane.ERROR_MESSAGE);
                    jToggleButton1.setSelected(true);
                }
                        
            }
        }
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jToggleButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton2ActionPerformed
        // TODO add your handling code here:
        if(n.getItemCount()>0){
            
            
            if(jToggleButton2.isSelected()){
                
                jToggleButton2.setText("Set");
                
                
                jButton2.doClick();
                
                n.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{n.getSelectedItem().toString().substring(3,n.getSelectedItem().toString().length())}));
                
                nt=n.getSelectedItem().toString();
                it=Integer.parseInt(s.getText().trim());
                dt=d.getText().trim();
               
           
                jButton2.setEnabled(false);
                jToggleButton1.setEnabled(false);
                jButton4.setEnabled(false);
                
                n.setEditable(true);
                s.setEditable(true);
                d.setEditable(true);
                
            }
            else{
                
                if(s.getText().isEmpty() || d.getText().isEmpty()){
                    JOptionPane.showMessageDialog(jPanel1, "Field is Empty!","Alert",JOptionPane.WARNING_MESSAGE);
                    jToggleButton2.setSelected(true);
                }
                
                else{
                    try {
                        System.out.println((int)Integer.parseInt(s.getText()));
                        
                        jToggleButton2.setText("Update");
                        
                        ss.executeUpdate("UPDATE info set name=\""+n.getSelectedItem().toString()+"\", id="+Integer.parseInt(s.getText().trim())+", dep=\""+d.getText().trim()+"\" WHERE name=\""+nt+"\" AND id="+it+" AND dep=\""+dt+"\"");
                        
                        com();
                        
                        jButton2.setEnabled(true);
                        jToggleButton1.setEnabled(true);
                        jButton4.setEnabled(true);
                        
                        n.setEditable(false);
                        s.setEditable(false);
                        d.setEditable(false);
                        
                        s.setText("");
                        d.setText("");
                        
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(jPanel1, "ID Must be Contain a Number!","Error",JOptionPane.ERROR_MESSAGE);
                        jToggleButton2.setSelected(true);
                    }
                }
                
                
            }                   
        }
        else{
            JOptionPane.showMessageDialog(jPanel1, "List is Empty.", "Message",JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jToggleButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        if(n.getItemCount()>0){
            s.setText("");
            d.setText("");     
            
            delete();
            com();
            
            
        }
        else{
            JOptionPane.showMessageDialog(jPanel1, "List is Empty.", "Message",JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        new personal().setVisible(true);
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        new personal().setVisible(true);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:
        new personal().setVisible(true);
    }//GEN-LAST:event_jLabel3MouseClicked

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
            java.util.logging.Logger.getLogger(liveServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(liveServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(liveServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(liveServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new liveServer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField d;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JComboBox<String> n;
    private javax.swing.JTextField s;
    // End of variables declaration//GEN-END:variables
}
