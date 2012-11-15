/*
 * SpeedSettingDialog.java - Configure the speed of messages in the
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
 * This class is a swing dialog that provides interface to user for entering the speed setting associated with animation of various messages.
 * @see ServerGUI
 * @author Ahmad Abdullah
 */
public class SpeedSettingDialog extends javax.swing.JDialog {
    
    private long speedPing=500;
    private long speedPong=500;
    private long speedQuery=500;
    private long speedQueryHit=500;
    String errorText="";
    
    /**
     * Creates new form SpeedSettingDialog
     * @param parent Indicates {@code ServerGUI}
     * @param modal Indicates if {@code SpeedSettingDialog} is of modal form or not
     */
    public SpeedSettingDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setComponentsNames();
        jTextFieldPingSpeed.setText(speedPing+"");
        jTextFieldPongSpeed.setText(speedPong+"");
        jTextFieldQuerySpeed.setText(speedQuery+"");
        jTextFieldQueryHitSpeed.setText(speedQueryHit+"");
        setLocationRelativeTo(parent);
        this.setVisible(false);
    }
    
    /**
     * Creates new form SpeedSettingDialog
     */
    public SpeedSettingDialog()
    {
        initComponents();
        setComponentsNames();
        jTextFieldPingSpeed.setText(speedPing+"");
        jTextFieldPongSpeed.setText(speedPong+"");
        jTextFieldQuerySpeed.setText(speedQuery+"");
        jTextFieldQueryHitSpeed.setText(speedQueryHit+"");
    }

    /** 
     * This method is called from within the constructor to
     * initialize the form.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelSpeed = new javax.swing.JPanel();
        jLabelPingSpeed = new javax.swing.JLabel();
        jTextFieldPingSpeed = new javax.swing.JTextField();
        jLabelPongSpeed = new javax.swing.JLabel();
        jTextFieldPongSpeed = new javax.swing.JTextField();
        jLabelQuerySpeed = new javax.swing.JLabel();
        jTextFieldQuerySpeed = new javax.swing.JTextField();
        jLabelQueryHitSpeed = new javax.swing.JLabel();
        jTextFieldQueryHitSpeed = new javax.swing.JTextField();
        jButtonCancel = new javax.swing.JButton();
        jButtonOK = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Speed Setting Dialog");
        setResizable(false);

        jPanelSpeed.setBorder(javax.swing.BorderFactory.createTitledBorder("Speed Parameters"));

        jLabelPingSpeed.setText("Ping Speed");

        jLabelPongSpeed.setText("Pong Speed");

        jLabelQuerySpeed.setText("Query Speed");

        jLabelQueryHitSpeed.setText("QueryHit Speed");

        org.jdesktop.layout.GroupLayout jPanelSpeedLayout = new org.jdesktop.layout.GroupLayout(jPanelSpeed);
        jPanelSpeed.setLayout(jPanelSpeedLayout);
        jPanelSpeedLayout.setHorizontalGroup(
            jPanelSpeedLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanelSpeedLayout.createSequentialGroup()
                .addContainerGap()
                .add(jPanelSpeedLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanelSpeedLayout.createSequentialGroup()
                        .add(jLabelQueryHitSpeed)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED))
                    .add(jLabelPongSpeed)
                    .add(jLabelPingSpeed)
                    .add(jLabelQuerySpeed))
                .add(23, 23, 23)
                .add(jPanelSpeedLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jTextFieldQueryHitSpeed, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jTextFieldQuerySpeed, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jTextFieldPongSpeed, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jTextFieldPingSpeed, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelSpeedLayout.setVerticalGroup(
            jPanelSpeedLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanelSpeedLayout.createSequentialGroup()
                .add(jPanelSpeedLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jTextFieldPingSpeed, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabelPingSpeed))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanelSpeedLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jTextFieldPongSpeed, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabelPongSpeed))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanelSpeedLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jTextFieldQuerySpeed, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabelQuerySpeed))
                .add(12, 12, 12)
                .add(jPanelSpeedLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jTextFieldQueryHitSpeed, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabelQueryHitSpeed))
                .addContainerGap())
        );

        jButtonCancel.setText("Cancel");
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });

        jButtonOK.setText("OK");
        jButtonOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOKActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanelSpeed, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                        .add(org.jdesktop.layout.GroupLayout.TRAILING, jButtonOK, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(org.jdesktop.layout.GroupLayout.TRAILING, jButtonCancel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanelSpeed, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jButtonOK)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jButtonCancel)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Method invoked when OK button is pressed on dialog interface
     * @param evt Indicates associated event
     */
    private void jButtonOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOKActionPerformed
        if(!"".equals(jTextFieldPingSpeed.getText().trim()) && (isNumber(jTextFieldPingSpeed.getText())==true))
        {
            speedPing = Long.parseLong(jTextFieldPingSpeed.getText());
        }else
        {
            errorText = "NOT A NUMBER - "+jTextFieldPingSpeed.getText();
            jTextFieldPingSpeed.setText(errorText);
            this.setVisible(true);
        }
        
        if(!"".equals(jTextFieldPongSpeed.getText().trim()) && (isNumber(jTextFieldPongSpeed.getText())==true))
        {
            speedPong = Long.parseLong(jTextFieldPongSpeed.getText());
        }else
        {
            errorText = "NOT A NUMBER - "+jTextFieldPongSpeed.getText();
            jTextFieldPongSpeed.setText(errorText);
            this.setVisible(true);
        }
        
        if(!"".equals(jTextFieldQuerySpeed.getText().trim()) && (isNumber(jTextFieldQuerySpeed.getText())==true))
        {
            speedQuery = Long.parseLong(jTextFieldQuerySpeed.getText());
        }else
        {
            errorText = "NOT A NUMBER - "+jTextFieldQuerySpeed.getText();
            jTextFieldQuerySpeed.setText(errorText);
            this.setVisible(true);
        }
        
        if(!"".equals(jTextFieldQueryHitSpeed.getText().trim()) && (isNumber(jTextFieldQueryHitSpeed.getText())==true))
        {
            speedQueryHit = Long.parseLong(jTextFieldQueryHitSpeed.getText());
        }else
        {
            errorText = "NOT A NUMBER - "+jTextFieldQueryHitSpeed.getText();
            jTextFieldQueryHitSpeed.setText(errorText);
            this.setVisible(true);
        }
        
        if(!"".equals(jTextFieldPingSpeed.getText().trim()) && (isNumber(jTextFieldPingSpeed.getText())==true) && (!"".equals(jTextFieldPongSpeed.getText().trim())) && (isNumber(jTextFieldPongSpeed.getText())==true) && (!"".equals(jTextFieldQuerySpeed.getText().trim())) && (isNumber(jTextFieldQuerySpeed.getText())==true) && (!"".equals(jTextFieldQueryHitSpeed.getText().trim())) && (isNumber(jTextFieldQueryHitSpeed.getText())==true))
		{
this.setVisible(false);
		}
    }//GEN-LAST:event_jButtonOKActionPerformed

    /**
     * Method invoked when Cancel button is pressed on dialog interface
     * @param evt Indicates associated event
     */
    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        jTextFieldPingSpeed.setText(""+speedPing);
        jTextFieldPongSpeed.setText(""+speedPong);
        jTextFieldQuerySpeed.setText(""+speedQuery);
        jTextFieldQueryHitSpeed.setText(""+speedQueryHit);
        this.setVisible(false);
    }//GEN-LAST:event_jButtonCancelActionPerformed

    /**
     * Method to get current Ping speed delay
     * @return Returns current Ping speed
     */
    public long getPingSpeed()
    {
        return speedPing;
    }
    
    /**
     * Method to get current Pong speed delay
     * @return Returns current Pong speed
     */
    public long getPongSpeed()
    {
        return speedPong;
    }
    
    /**
     * Method to get current Query speed delay
     * @return Returns current Query speed
     */
    public long getQuerySpeed()
    {
        return speedQuery;
    }
    
    /**
     * Method to get current QueryHit speed delay
     * @return Returns current QueryHit speed
     */
    public long getQueryHitSpeed()
    {
        return speedQueryHit;
    }
    
    /**
     * Method to set names of all graphical components
     */
    private void setComponentsNames()
    {
        jTextFieldPingSpeed.setName("pingField");
        jTextFieldPongSpeed.setName("pongField");
        jTextFieldQueryHitSpeed.setName("queryHitField");
        jTextFieldQuerySpeed.setName("queryField");
        jButtonOK.setName("okButton");
        jButtonCancel.setName("cancelButton");
    }
    
    /**
     * Method to check of entered value in field is a number
     * @param string Value entered by user
     * @return Returns True if it is a number else it returns false.
     */
    public static boolean isNumber(String string) {
        boolean check = false;
        try
        {
        check = string.matches("^\\d+$");
        }catch (Exception e)
        {
            System.out.println("ERROR");
        }
        return check;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonOK;
    private javax.swing.JLabel jLabelPingSpeed;
    private javax.swing.JLabel jLabelPongSpeed;
    private javax.swing.JLabel jLabelQueryHitSpeed;
    private javax.swing.JLabel jLabelQuerySpeed;
    private javax.swing.JPanel jPanelSpeed;
    private javax.swing.JTextField jTextFieldPingSpeed;
    private javax.swing.JTextField jTextFieldPongSpeed;
    private javax.swing.JTextField jTextFieldQueryHitSpeed;
    private javax.swing.JTextField jTextFieldQuerySpeed;
    // End of variables declaration//GEN-END:variables
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SpeedSettingDialog().setVisible(true);
            }
        });
    }
}
