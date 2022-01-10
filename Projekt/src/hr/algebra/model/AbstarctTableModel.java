/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author HT-ICT
 */
public class AbstarctTableModel extends AbstractTableModel {

    private static final String[] COLUMN_NAMES = {"ID", "Title", "Published date", "Description", "Original name", "Duration", "Genre", "Poster", "Start", "Directors", "Actors"};

    private List<Movie> movies;
    JTable table;

    public AbstarctTableModel(List<Movie> movies, JTable table) {
        this.table = table;
        this.movies = movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        fireTableDataChanged();
    }

    @Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Integer.class;
            case 2:
                return LocalDateTime.class;
            case 8:
                return LocalDate.class;
        }
        return super.getColumnClass(columnIndex);
    }

    @Override
    public int getRowCount() {
        return movies.size();
    }

    @Override
    public int getColumnCount() {
        return Movie.class.getDeclaredFields().length - 2;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
         TableColumn testColumn = table.getColumnModel().getColumn(10);
        JComboBox<Person> comboBox = new JComboBox<>();
        table.setRowSelectionInterval(rowIndex, rowIndex);
        movies.get(rowIndex).getActors().forEach(comboBox::addItem);

        testColumn.setCellEditor(new DefaultCellEditor(comboBox));
        return columnIndex == 10;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return movies.get(rowIndex).getId();
            case 1:
                return movies.get(rowIndex).getTitle();
            case 2:
                return movies.get(rowIndex).getPublishedDate();
            case 3:
                return movies.get(rowIndex).getDescription();
            case 4:
                return movies.get(rowIndex).getOriginalName();
            case 5:
                return movies.get(rowIndex).getDuration();
            case 6:
                return movies.get(rowIndex).getGenre();
            case 7:
                return movies.get(rowIndex).getPoster();
            case 8:
                return movies.get(rowIndex).getStart();
            case 9:
                return movies.get(rowIndex).getDirector();
            case 10:
                return movies.get(rowIndex).getActors().size()>0? movies.get(rowIndex).getActors().get(0):"";
        }
        throw new RuntimeException("No such column.");
    }

   

}
