/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;
import javax.swing.JLabel;

/**
 *
 * @author HT-ICT
 */
public class MenuPanel extends javax.swing.JPanel {

    /**
     * Creates new form MenuPanel
     */
    private static final String PNL_EDIT = "Edit Movie";
    private static final String PNL_EDIT_ACTORS = "Edit Actors";
    private static final String PNL_EDIT_DIRECTOR = "Edit Director";
    private static final String PNL_XML_DOWNLOAD = "XML downlaod";
    private static final String PNL_DO_JOB = "Join actor and movie";

    private static final Color UNSELECTED = Color.WHITE;
    private static final Color SELECTED = Color.LIGHT_GRAY;

    private static final EditActorsPanel EditActorsPanel = new EditActorsPanel();
    private static final EditDirectorPanel EditDirectorPanel = new EditDirectorPanel();
    private static final EditMoviePanel EditMoviePanel = new EditMoviePanel();
    private static final JoinPersonMoviePanel JoinPersonMoviePanel = new JoinPersonMoviePanel();

    private List<JLabel> allLabels;

    public MenuPanel() {
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

        jSplitPane1 = new javax.swing.JSplitPane();
        pMenu = new javax.swing.JPanel();
        lbMyApplication = new javax.swing.JLabel();
        lbMovies = new javax.swing.JLabel();
        lbActors = new javax.swing.JLabel();
        lbDirectors = new javax.swing.JLabel();
        lbDoJob = new javax.swing.JLabel();
        lbXmlDownload = new javax.swing.JLabel();
        pCards = new javax.swing.JTabbedPane();

        setLayout(new java.awt.CardLayout());

        pMenu.setBackground(new java.awt.Color(102, 102, 255));

        lbMyApplication.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lbMyApplication.setForeground(new java.awt.Color(255, 204, 0));
        lbMyApplication.setText("JAVA");

        lbMovies.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lbMovies.setForeground(new java.awt.Color(255, 255, 255));
        lbMovies.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbMovies.setText("Movies");
        lbMovies.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbMoviesMouseClicked(evt);
            }
        });

        lbActors.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lbActors.setForeground(new java.awt.Color(255, 255, 255));
        lbActors.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbActors.setText("Actors");
        lbActors.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbActorsMouseClicked(evt);
            }
        });

        lbDirectors.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lbDirectors.setForeground(new java.awt.Color(255, 255, 255));
        lbDirectors.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbDirectors.setText("Directors");
        lbDirectors.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbDirectorsMouseClicked(evt);
            }
        });

        lbDoJob.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lbDoJob.setForeground(new java.awt.Color(255, 255, 255));
        lbDoJob.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbDoJob.setText("Do Job");
        lbDoJob.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbDoJobMouseClicked(evt);
            }
        });

        lbXmlDownload.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lbXmlDownload.setForeground(new java.awt.Color(255, 255, 255));
        lbXmlDownload.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbXmlDownload.setText("XML download");
        lbXmlDownload.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbXmlDownloadMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pMenuLayout = new javax.swing.GroupLayout(pMenu);
        pMenu.setLayout(pMenuLayout);
        pMenuLayout.setHorizontalGroup(
            pMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pMenuLayout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addGroup(pMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pMenuLayout.createSequentialGroup()
                        .addComponent(lbMyApplication)
                        .addGap(49, 49, 49))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pMenuLayout.createSequentialGroup()
                        .addComponent(lbMovies)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pMenuLayout.createSequentialGroup()
                        .addComponent(lbActors)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pMenuLayout.createSequentialGroup()
                        .addComponent(lbXmlDownload)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pMenuLayout.createSequentialGroup()
                        .addComponent(lbDirectors)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pMenuLayout.createSequentialGroup()
                        .addComponent(lbDoJob)
                        .addContainerGap())))
        );
        pMenuLayout.setVerticalGroup(
            pMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pMenuLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(lbMyApplication)
                .addGap(111, 111, 111)
                .addComponent(lbMovies)
                .addGap(44, 44, 44)
                .addComponent(lbActors)
                .addGap(44, 44, 44)
                .addComponent(lbDoJob)
                .addGap(52, 52, 52)
                .addComponent(lbDirectors)
                .addGap(37, 37, 37)
                .addComponent(lbXmlDownload)
                .addContainerGap(503, Short.MAX_VALUE))
        );

        jSplitPane1.setLeftComponent(pMenu);
        jSplitPane1.setRightComponent(pCards);

        add(jSplitPane1, "card2");
    }// </editor-fold>//GEN-END:initComponents

    private void lbMoviesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbMoviesMouseClicked
        unselectAll();
        lbMovies.setForeground(SELECTED);
        pCards.removeAll();
        pCards.add(PNL_EDIT, EditMoviePanel);

    }//GEN-LAST:event_lbMoviesMouseClicked

    private void lbActorsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbActorsMouseClicked
        unselectAll();
        lbActors.setForeground(SELECTED);
        pCards.removeAll();
        pCards.add(PNL_EDIT_ACTORS, EditActorsPanel);

    }//GEN-LAST:event_lbActorsMouseClicked

    private void lbDoJobMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbDoJobMouseClicked
        unselectAll();
        lbDoJob.setForeground(SELECTED);
        pCards.removeAll();
        pCards.add(PNL_DO_JOB, JoinPersonMoviePanel);

    }//GEN-LAST:event_lbDoJobMouseClicked

    private void lbXmlDownloadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbXmlDownloadMouseClicked
        unselectAll();
        lbXmlDownload.setForeground(SELECTED);
        pCards.removeAll();
        pCards.add(PNL_XML_DOWNLOAD, new XMLDownloadPanel());
    }//GEN-LAST:event_lbXmlDownloadMouseClicked

    private void lbDirectorsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbDirectorsMouseClicked
        unselectAll();
        lbDirectors.setForeground(SELECTED);
        pCards.removeAll();
        pCards.add(PNL_EDIT_DIRECTOR, EditDirectorPanel);
    }//GEN-LAST:event_lbDirectorsMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JLabel lbActors;
    private javax.swing.JLabel lbDirectors;
    private javax.swing.JLabel lbDoJob;
    private javax.swing.JLabel lbMovies;
    private javax.swing.JLabel lbMyApplication;
    private javax.swing.JLabel lbXmlDownload;
    private javax.swing.JTabbedPane pCards;
    private javax.swing.JPanel pMenu;
    // End of variables declaration//GEN-END:variables

    private void init() {
        allLabels = Arrays.asList(lbMovies, lbActors, lbDirectors, lbDoJob, lbXmlDownload);
        lbMovies.setForeground(SELECTED);
    }

    private void unselectAll() {
        allLabels.forEach(l -> l.setForeground(UNSELECTED));
    }

}