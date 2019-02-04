package musiclibrary.ui.tables.models;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class MainTableModel implements TableModel {
    private Object[][] rowData;
    private Object[] columnNames;

    public MainTableModel(Object[][] rowData, Object[] columnNames) {
        this.rowData = rowData;
        this.columnNames = columnNames;
    }

    @Override
    public int getRowCount() {
        return rowData.length;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return (String) columnNames[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return getValueAt(0, columnIndex).getClass();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return rowData[rowIndex][columnIndex];
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        rowData[rowIndex][columnIndex] = aValue;
    }

    @Override
    public void addTableModelListener(TableModelListener l) {

    }

    @Override
    public void removeTableModelListener(TableModelListener l) {

    }
}
