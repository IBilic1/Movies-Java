/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra;

import hr.algebra.dal.MovieRepository;
import hr.algebra.dal.RepositoryFactory;
import hr.algebra.dal.UserInterface;
import hr.algebra.model.Movie;
import hr.algebra.parsers.rss.MovieParser;
import hr.algebra.utils.MessageUtils;
import java.awt.EventQueue;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author HT-ICT
 */
public class AdminsPanel extends javax.swing.JPanel {

    /**
     * Creates new form AdminsPanel
     */
    private DefaultListModel<Movie> moviesModel;
    private MovieRepository repository;
    private UserInterface userRepository;

    public AdminsPanel() {
        initComponents();
        init();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        lMovies = new javax.swing.JList<>();
        btnDelete = new javax.swing.JButton();
        btnUpload = new javax.swing.JButton();

        jScrollPane1.setViewportView(lMovies);

        btnDelete.setBackground(java.awt.Color.red);
        btnDelete.setText("Delete all");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnUpload.setText("Upload data");
        btnUpload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUploadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 930, Short.MAX_VALUE)
                    .addComponent(btnUpload, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(109, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 657, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(btnUpload, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(82, 82, 82))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnUploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUploadActionPerformed

        new Thread(() -> {
            try {
                List<Movie> movies = MovieParser.parse();
                repository.create(movies);
                
                EventQueue.invokeLater(() -> {
                    try {
                        loadModel();
                    } catch (Exception ex) {
                        Logger.getLogger(AdminsPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            } catch (Exception ex) {
                MessageUtils.showErrorMessage("Error", "Error occured while uploading movies.");
                Logger.getLogger(AdminsPanel.class.getName()).log(Level.SEVERE, null, ex);
            }

        }).start();


    }//GEN-LAST:event_btnUploadActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        if (MessageUtils.showConfirmDialog("Delete", "Do you want delete all data?") == JOptionPane.YES_OPTION) {

            new Thread(() -> {
                try {
                    for (Movie movie : repository.select()) {
                        if (movie.getPoster() != null) {
                            Files.deleteIfExists(Paths.get(movie.getPoster()));
                        }
                    }
                    userRepository.deleteData();

                    EventQueue.invokeLater(() -> {
                        try {
                            loadModel();
                        } catch (Exception ex) {
                            Logger.getLogger(AdminsPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });

                } catch (Exception ex) {
                    Logger.getLogger(AdminsPanel.class
                            .getName()).log(Level.SEVERE, null, ex);
                }

            }).start();
        }
    }//GEN-LAST:event_btnDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnUpload;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<Movie> lMovies;
    // End of variables declaration//GEN-END:variables

    private void init() {
        try {
            initModel();
            initRepository();
            loadModel();
        } catch (Exception ex) {
            MessageUtils.showErrorMessage("Error", "Unable to load form.");
            Logger
                    .getLogger(AdminsPanel.class
                            .getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        }
    }

    private void initModel() {
        moviesModel = new DefaultListModel<>();
    }

    private void initRepository() throws Exception {
        repository = RepositoryFactory.<MovieRepository>getRepository();
        userRepository = RepositoryFactory.<UserInterface>getRepository();
    }

    private void loadModel() throws Exception {
        List<Movie> movies = repository.select();
        moviesModel.clear();
        movies.forEach(moviesModel::addElement);
        lMovies.setModel(moviesModel);
    }

}