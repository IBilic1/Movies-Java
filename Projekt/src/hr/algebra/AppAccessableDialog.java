/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra;

import hr.algebra.model.AppAccessable;
import hr.algebra.model.User;
import hr.algebra.utils.HashUtil;
import hr.algebra.utils.MessageUtils;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author HT-ICT
 */
public class AppAccessableDialog extends javax.swing.JDialog {

    /**
     * Creates new form AppAccessableDialog
     */
    private List<JTextField> validationTextFields;
    private List<JLabel> errorLables;

    private final AppAccessable appAccessable;

    public AppAccessableDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        appAccessable = (AppAccessable) parent;
        initComponents();
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(1);
            }

        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        tfUserName = new javax.swing.JTextField();
        lbErrorUserName = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lbErrorPassword = new javax.swing.JLabel();
        btnLogIn = new javax.swing.JButton();
        pfPassword = new javax.swing.JPasswordField();
        btnignIn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("ACCESS");
        setBackground(new java.awt.Color(153, 153, 255));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jLabel1.setText("User name:");

        lbErrorUserName.setForeground(java.awt.Color.red);

        jLabel3.setText("Password:");

        lbErrorPassword.setForeground(java.awt.Color.red);

        btnLogIn.setBackground(new java.awt.Color(102, 102, 255));
        btnLogIn.setText("LOG IN");
        btnLogIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogInActionPerformed(evt);
            }
        });

        btnignIn.setBackground(new java.awt.Color(204, 153, 255));
        btnignIn.setText("SIGN IN");
        btnignIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnignInActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tfUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbErrorUserName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbErrorPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnignIn, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLogIn, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbErrorUserName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfUserName, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
                .addGap(31, 31, 31)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbErrorPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(btnignIn, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLogIn, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        initValidation();
    }//GEN-LAST:event_formComponentShown

    private void btnignInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnignInActionPerformed
        if (formValid()) {
            try {
                User user = new User(tfUserName.getText().trim(), HashUtil.getHashFromString(pfPassword.getText().trim()));
                System.out.println(user.getUserName());
                if (appAccessable.addUser(user)) {
                    dispose();
                } else {
                    MessageUtils.showErrorMessage("Error", "Duplicate user");
                }
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(AppAccessableDialog.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_btnignInActionPerformed

    private void btnLogInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogInActionPerformed
        if (formValid()) {
            try {
                User user = new User(tfUserName.getText().trim(), HashUtil.getHashFromString(pfPassword.getText().trim()));
                if (appAccessable.selectUser(user)) {
                    dispose();
                } else {
                    MessageUtils.showErrorMessage("Error", "No such user.");
                }
            } catch (Exception ex) {
                Logger.getLogger(AppAccessableDialog.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_btnLogInActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogIn;
    private javax.swing.JButton btnignIn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lbErrorPassword;
    private javax.swing.JLabel lbErrorUserName;
    private javax.swing.JPasswordField pfPassword;
    private javax.swing.JTextField tfUserName;
    // End of variables declaration//GEN-END:variables

    private void initValidation() {
        validationTextFields = Arrays.asList(tfUserName, pfPassword);
        errorLables = Arrays.asList(lbErrorUserName, lbErrorPassword);
    }

    private boolean formValid() {
        boolean ok = true;
        for (int i = 0; i < validationTextFields.size(); i++) {
            ok &= !validationTextFields.get(i).getText().trim().isEmpty();
            errorLables.get(i).setText(validationTextFields.get(i).getText().trim().isEmpty() ? "X" : "");
        }
        return ok;
    }
}