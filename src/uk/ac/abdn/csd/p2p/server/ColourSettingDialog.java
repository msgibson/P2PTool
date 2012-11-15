/*
 * ColourSettingDialog.java - Control the colour of Servents in the
 * Server GUI
 * 
 * Copyright (C) 2012 Michael Gibson
 * 
 * This file is part of P2PTool.
 *
 * P2PTool is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package uk.ac.abdn.csd.p2p.server;
/**
 * This class is a swing dialog that provides interface to user for setting colour 
 * to various animation activities, which would be displayed on the overlay graph.
 * @see ServerGUI
 * @see Vertex
 * @author Ahmad Abdullah
 */
public class ColourSettingDialog extends javax.swing.JDialog {

    private String DefaultColor;
    private String PingColor;
    private String PongColor;
    private String QueryColor;
    private String QueryHitColor;
    private String RelayorColor;
    private String SearchColor;
    
    int indexDef;
    int indexPin;
    int indexPon;
    int indexQur;
    int indexQuh;
    int indexRel;
    int indexSer;
    
    
    /**
     * Creates new form ColourSettingDialog
     * @param parent Indicates {@code ServerGUI}
     * @param modal Indicates if {@code ColourSettingDialog} is of modal form or not
     */
    public ColourSettingDialog( java.awt.Frame parent, boolean modal) {
    	super(parent, modal);
    	
        indexDef=5;
        indexPin=1;
        indexPon=9;
        indexQur=12;
        indexQuh=2;
        indexRel=11;
        indexSer=10;
        
    	initComponents();
        setComponentsNames();
    	setLocationRelativeTo(parent);
        this.setVisible(false);
    }
    
    /**
     * Creates new form ColourSettingDialog
     */
    public ColourSettingDialog()
    {
        indexDef=5;
        indexPin=1;
        indexPon=9;
        indexQur=12;
        indexQuh=2;
        indexRel=11;
        indexSer=10;
        
        initComponents();
        setComponentsNames();
    }

    /** 
     * This method is called from within the constructor to
     * initialize the form.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jComboBox2 = new javax.swing.JComboBox();
        jComboBox3 = new javax.swing.JComboBox();
        jComboBox4 = new javax.swing.JComboBox();
        jComboBox5 = new javax.swing.JComboBox();
        jComboBox6 = new javax.swing.JComboBox();
        jComboBox7 = new javax.swing.JComboBox();

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Colour Settings Dialog");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Colour Setting"));
        jPanel1.setName("jPanel1"); // NOI18N

        jLabel1.setText("Node Colour (Default)");
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setText("Node Colour (Sending Ping)");
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel3.setText("Node Colour (Sending Pong)");
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel4.setText("Node Colour (Sending Query)");
        jLabel4.setName("jLabel4"); // NOI18N

        jLabel5.setText("Node Colour (Sending QueryHit)");
        jLabel5.setName("jLabel5"); // NOI18N
        
        jLabel7.setText("Node Colour (Relayor)");
        jLabel7.setName("jLabel7"); // NOI18N
        
        jLabel6.setText("Node Colour (Search for a node)");
        jLabel6.setName("jLabel6"); // NOI18N
        
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	jButton1ActionPerformed(evt);
            }
        });
        
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	jButton2ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Red", "Yellow", "Green", "Blue", "Black", "White", "Grey", "Dark_Grey", "Light_Grey", "Pink", "Cyan", "Orange", "Megenta" }));
        jComboBox1.setSelectedIndex(indexDef);
        jComboBox1.setName("jComboBox1"); // NOI18N

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Red", "Yellow", "Green", "Blue", "Black", "White", "Grey", "Dark_Grey", "Light_Grey", "Pink", "Cyan", "Orange", "Megenta" }));
        jComboBox2.setSelectedIndex(indexPin);
        jComboBox2.setName("jComboBox2"); // NOI18N

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Red", "Yellow", "Green", "Blue", "Black", "White", "Grey", "Dark_Grey", "Light_Grey", "Pink", "Cyan", "Orange", "Megenta" }));
        jComboBox3.setSelectedIndex(indexPon);
        jComboBox3.setName("jComboBox3"); // NOI18N

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Red", "Yellow", "Green", "Blue", "Black", "White", "Grey", "Dark_Grey", "Light_Grey", "Pink", "Cyan", "Orange", "Megenta" }));
        jComboBox4.setSelectedIndex(indexQur);
        jComboBox4.setName("jComboBox4"); // NOI18N

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Red", "Yellow", "Green", "Blue", "Black", "White", "Grey", "Dark_Grey", "Light_Grey", "Pink", "Cyan", "Orange", "Megenta" }));
        jComboBox5.setSelectedIndex(indexQuh);
        jComboBox5.setName("jComboBox5"); // NOI18N
        
        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Red", "Yellow", "Green", "Blue", "Black", "White", "Grey", "Dark_Grey", "Light_Grey", "Pink", "Cyan", "Orange", "Megenta" }));
        jComboBox7.setSelectedIndex(indexRel);
        jComboBox7.setName("jComboBox7"); // NOI18N
        
        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Red", "Yellow", "Green", "Blue", "Black", "White", "Grey", "Dark_Grey", "Light_Grey", "Pink", "Cyan", "Orange", "Megenta" }));
        jComboBox6.setSelectedIndex(indexSer);
        jComboBox6.setName("jComboBox6"); // NOI18N
        
        jButton1.setText("OK");
        jButton1.setName("jButton1"); // NOI18N

        jButton2.setText("Cancel");
        jButton2.setName("jButton2"); // NOI18N

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING,false)
                    .add(jLabel1,org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jLabel4,org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jLabel5,org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jLabel2,org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jLabel3,org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jLabel7,org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jLabel6,org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .add(56, 56, 56)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .add(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .add(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .add(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .add(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .add(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .add(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap())
                .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(269, Short.MAX_VALUE)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jButton2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jButton1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 11, Short.MAX_VALUE)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel2))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 11, Short.MAX_VALUE)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel3)
                    .add(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 11, Short.MAX_VALUE)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel4)
                    .add(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 11, Short.MAX_VALUE)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel5)
                    .add(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 11, Short.MAX_VALUE)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel7)
                    .add(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 11, Short.MAX_VALUE)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel6)
                    .add(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(jButton1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jButton2)
                    .addContainerGap())
        );

        pack();
    }// </editor-fold>

    /**
     * Method invoked when OK button is pressed on dialog interface
     * @param evt Indicates associated event
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
    	DefaultColor = (String)jComboBox1.getSelectedItem();
        PingColor = (String)jComboBox2.getSelectedItem();
        PongColor = (String)jComboBox3.getSelectedItem();
        QueryColor = (String)jComboBox4.getSelectedItem();
        QueryHitColor = (String)jComboBox5.getSelectedItem();
        RelayorColor = (String)jComboBox7.getSelectedItem();
        SearchColor =(String)jComboBox6.getSelectedItem();
        
        indexDef = jComboBox1.getSelectedIndex();
        indexPin = jComboBox2.getSelectedIndex();
        indexPon = jComboBox3.getSelectedIndex();
        indexQur = jComboBox4.getSelectedIndex();
        indexQuh = jComboBox5.getSelectedIndex();
        indexRel = jComboBox7.getSelectedIndex();
        indexSer = jComboBox6.getSelectedIndex();
       
        this.setVisible(false);
    }
   
    /**
     * Method invoked when Cancel button is pressed on dialog interface
     * @param evt Indicates associated event
     */
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        jComboBox1.setSelectedIndex(indexDef);
        jComboBox2.setSelectedIndex(indexPin);
        jComboBox3.setSelectedIndex(indexPon);
        jComboBox4.setSelectedIndex(indexQur);
        jComboBox5.setSelectedIndex(indexQuh);
        jComboBox7.setSelectedIndex(indexRel);
        jComboBox6.setSelectedIndex(indexSer);
        
        this.setVisible(false);
    }
    
     /**
     * Method to get default colour of {@code Vertex}
     * @return Returns colour associated with {@code Vertex}
     */
     public String getDefaultColor()
     {
    	 return DefaultColor;
     }
     
     /**
      * Method to get colour associated with Ping messaging activity
      * @return Returns colour associated with Ping messaging activity
      */
     public String getPingColor()
     {
    	 return PingColor;
     }
     
     /**
      * Method to get colour associated with Pong messaging activity
      * @return Returns colour associated with Pong messaging activity
      */
     public String getPongColor()
     {
    	 return PongColor;
     }
     
     /**
      * Method to get colour associated with Query messaging activity
      * @return Returns colour associated with Query messaging activity
      */
     public String getQueryColor()
     {
    	 return QueryColor;
     }
     
     /**
      * Method to get colour associated with QueryHit messaging activity
      * @return Returns colour associated with QueryHit messaging activity
      */
     public String getQueryHitColor()
     {
    	 return QueryHitColor;
     }
     
     /**
      * Method to get colour associated with Relayor node
      * @return Returns colour associated with Relayor node
      */
     public String getRelayorColor()
     {
    	 return RelayorColor;
     }
     
     /**
      * Method to get colour associated with searched node
      * @return Returns colour associated with searched node
      */
     public String getSearchColor() 
     {
         return SearchColor;
     }
    
     /**
      * Method to set names for all graphical components
      */
     private void setComponentsNames()
     {
        jComboBox1.setName("comboBox1");
        jComboBox2.setName("comboBox2");
        jComboBox3.setName("comboBox3");
        jComboBox4.setName("comboBox4");
        jComboBox5.setName("comboBox5");
        jComboBox6.setName("comboBox6");
        jComboBox7.setName("comboBox7");
        jButton1.setName("okButton");
        jButton2.setName("cancelButton");
     }
	
    // Variables declaration - do not modify
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JComboBox jComboBox4;
    private javax.swing.JComboBox jComboBox5;
    private javax.swing.JComboBox jComboBox6;
    private javax.swing.JComboBox jComboBox7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    // End of variables declaration
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ColourSettingDialog().setVisible(true);
            }
        });
    }
}

