/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra;

import hr.algebra.dal.MovieRepository;
import hr.algebra.dal.PersonRepositry;
import hr.algebra.dal.RepositoryFactory;
import hr.algebra.model.Movie;
import hr.algebra.model.Person;
import hr.algebra.model.PersonTableModel;
import hr.algebra.utils.MessageUtils;
import java.awt.EventQueue;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

/**
 *
 * @author HT-ICT
 */
public class EditActorsPanel extends javax.swing.JPanel {

    /**
     * Creates new form EditActorsPanel
     */
    private List<JTextField> validationFields;
    private List<JLabel> errorLabels;

    private PersonRepositry personRepository;
    private MovieRepository repository;
    private PersonTableModel personTableModel;
    private DefaultListModel<Movie> defaultListModel = new DefaultListModel<>();

    private Person selectedPerson;

    public EditActorsPanel() {
        initComponents();
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
        tbActors = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        tfFirstName = new javax.swing.JTextField();
        lbErrorFirstName = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tfLastName = new javax.swing.JTextField();
        lbErrorLastName = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listMovies = new javax.swing.JList<>();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        lbErrorListMovie = new javax.swing.JLabel();

        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        tbActors.setModel(new javax.swing.table.DefaultTableModel(
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
        tbActors.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbActorsMouseClicked(evt);
            }
        });
        tbActors.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbActorsKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tbActors);

        jLabel1.setText("First name:");

        lbErrorFirstName.setForeground(java.awt.Color.red);

        jLabel3.setText("Last name:");
        jLabel3.setToolTipText("");

        lbErrorLastName.setForeground(java.awt.Color.red);

        jScrollPane2.setViewportView(listMovies);

        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setBackground(java.awt.Color.red);
        btnDelete.setForeground(java.awt.Color.white);
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        lbErrorListMovie.setForeground(java.awt.Color.red);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tfFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(lbErrorFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(btnAdd, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tfLastName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE))
                                .addGap(40, 40, 40)
                                .addComponent(lbErrorLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(66, 66, 66)
                                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(lbErrorListMovie, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 999, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbErrorListMovie, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tfFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbErrorFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tfLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbErrorLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(94, 94, 94)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(95, 95, 95))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        init();
    }//GEN-LAST:event_formComponentShown

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        if (listMovies.getSelectedValues() == null) {
            MessageUtils.showInformationMessage("Information", "You need to select at leat one movie to add actor.");
            return;
        }
        if (listMovies.getSelectedValues().length == 0) {
            MessageUtils.showInformationMessage("Information", "You need to select movie to delete actor.");
            return;
        }
        if (formValid()) {
            try {
                Person person = new Person(tfFirstName.getText().trim(), tfLastName.getText().trim());
                Arrays.asList(listMovies.getSelectedValues()).forEach(m -> {
                    try {
                        personRepository.create(((Movie) m).getId(), Arrays.asList(person));
                    } catch (Exception ex) {
                        Logger.getLogger(EditActorsPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                personTableModel.setPersonos(personRepository.selectActors());
                clearForm();
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        if (selectedPerson == null) {
            MessageUtils.showInformationMessage("Wrong operation", "Please choose person to update");
            return;
        }

        if (formValid()) {
            try {
                selectedPerson.setFirstName(tfFirstName.getText().trim());
                selectedPerson.setLastName(tfLastName.getText().trim());
                personRepository.update(selectedPerson.getId(), selectedPerson);
                personTableModel.setPersonos(personRepository.selectActors());
                clearForm();
            } catch (Exception ex) {
                Logger.getLogger(EditActorsPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        if (selectedPerson == null) {
            MessageUtils.showInformationMessage("Wrong operation", "Please choose person to update");
            return;
        }
        if (listMovies.getSelectedValues().length == 0) {
            MessageUtils.showInformationMessage("Information", "You need to select movie to delete actor.");
            return;
        }
        if (MessageUtils.showConfirmDialog(
                "Delete actor",
                "Do you really want to delete actor for this movie?") == JOptionPane.YES_OPTION) {
            try {
                Arrays.asList(listMovies.getSelectedValues()).forEach(movie -> {
                    try {
                        personRepository.delete(((Movie) movie).getId(), selectedPerson.getId(), 1);
                    } catch (Exception ex) {
                        Logger.getLogger(EditActorsPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                personTableModel.setPersonos(personRepository.selectActors());
                clearForm();
            } catch (Exception ex) {
                Logger.getLogger(EditActorsPanel.class.getName()).log(Level.SEVERE, null, ex);
                MessageUtils.showErrorMessage("Error", "Unable to delete actor!");
            }
        }

    }//GEN-LAST:event_btnDeleteActionPerformed

    private void tbActorsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbActorsMouseClicked
        showActor();
    }//GEN-LAST:event_tbActorsMouseClicked

    private void tbActorsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbActorsKeyReleased
        showActor();
    }//GEN-LAST:event_tbActorsKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbErrorFirstName;
    private javax.swing.JLabel lbErrorLastName;
    private javax.swing.JLabel lbErrorListMovie;
    private javax.swing.JList<Movie> listMovies;
    private javax.swing.JTable tbActors;
    private javax.swing.JTextField tfFirstName;
    private javax.swing.JTextField tfLastName;
    // End of variables declaration//GEN-END:variables

    private void init() {
        try {
            initValidation();
            initRepository();
            initTable();
            loadListModel();

        } catch (Exception ex) {
            Logger.getLogger(EditActorsPanel.class.getName()).log(Level.SEVERE, null, ex);
            MessageUtils.showErrorMessage("Unrecoverable error", "Cannot initiate the form");
            System.exit(1);
        }
    }

    private void initValidation() {
        validationFields = Arrays.asList(tfFirstName, tfLastName);
        errorLabels = Arrays.asList(lbErrorFirstName, lbErrorLastName);
    }

    private void initRepository() throws Exception {
        personRepository = RepositoryFactory.<PersonRepositry>getRepository();
        repository = RepositoryFactory.<MovieRepository>getRepository();
    }

    private void initTable() {
        tbActors.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbActors.setAutoCreateRowSorter(true);
        tbActors.setRowHeight(25);
        new Thread(() -> {
            try {
                personTableModel = new PersonTableModel(personRepository.selectActors());
                EventQueue.invokeLater(() -> {
                    tbActors.setModel(personTableModel);
                });
            } catch (Exception ex) {
                Logger.getLogger(EditActorsPanel.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
            }

        }
        ).start();
    }

    private void loadListModel() throws Exception {
        new Thread(() -> {
            try {
                defaultListModel.clear();
                repository.select().forEach(defaultListModel::addElement);
                listMovies.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
                listMovies.setSelectedIndex(0);
                EventQueue.invokeLater(() -> {
                    listMovies.setModel(defaultListModel);
                });

            } catch (Exception ex) {
                Logger.getLogger(EditActorsPanel.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        ).start();
    }

    private void showActor() {
        try {
            int selectedRow = tbActors.getSelectedRow();
            int rowIndex = tbActors.convertRowIndexToModel(selectedRow);
            int selectedActorId = (int) personTableModel.getValueAt(rowIndex, 0);
            Optional<Person> optActor = personRepository.selecActor(selectedActorId);
            if (optActor.isPresent()) {
                selectedPerson = optActor.get();
                fillForm(selectedPerson);

            }
        } catch (Exception ex) {
            Logger.getLogger(EditActorsPanel.class
                    .getName()).log(Level.SEVERE, null, ex);
            MessageUtils.showErrorMessage("Error", "Unable to show article!");
        }
    }

    private void clearForm() throws Exception {
        validationFields.forEach(e -> e.setText(""));
        errorLabels.forEach(e -> e.setText(""));
        selectedPerson = null;
        loadListModel();

    }
    public ReadWriteLock lock = new ReentrantReadWriteLock();

    private void fillForm(Person selectedPerson) throws Exception {
        tfFirstName.setText(selectedPerson.getFirstName());
        tfLastName.setText(selectedPerson.getPasswod());

        new Thread(() -> {
            defaultListModel.clear();
            try {
                lock.writeLock().lock();
                repository.select(selectedPerson, 1).forEach(defaultListModel::addElement);
                EventQueue.invokeLater(() -> {
                    listMovies.setModel(defaultListModel);
                });

            } catch (Exception ex) {
                Logger.getLogger(EditDirectorPanel.class
                        .getName()).log(Level.SEVERE, null, ex);
            } finally {
                lock.writeLock().unlock();
            }
        }).start();

    }

    private boolean formValid() {
        boolean ok = true;

        for (int i = 0; i < validationFields.size(); i++) {
            ok &= !validationFields.get(i).getText().trim().isEmpty();
            errorLabels.get(i).setText(validationFields.get(i).getText().trim().isEmpty() ? "X" : "");
        }
        return ok;
    }
}
