/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.model;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author HT-ICT
 */
public class PersonTableModel extends AbstractTableModel{

    private static final String[] COLUMN_NAMES = {"ID","NAME","LAST NAME"};
    private List<Person> personos;

    public PersonTableModel(List<Person> personos) {
        this.personos = personos;
    }

    public void setPersonos(List<Person> personos) {
        this.personos = personos;
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
        }
        return super.getColumnClass(columnIndex);
    }
    
    @Override
    public int getRowCount() {
        return personos.size();
    }

    @Override
    public int getColumnCount() {
        return Person.class.getDeclaredFields().length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return personos.get(rowIndex).getId();
            case 1:
                return personos.get(rowIndex).getFirstName();
            case 2:
                return personos.get(rowIndex).getPasswod();
        }
         throw new RuntimeException("No such column.");
    }
    
}
