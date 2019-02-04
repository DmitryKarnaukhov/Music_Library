package musiclibrary.ui.tables;

import musiclibrary.ui.listeners.mouselisteners.MainTableListener;
import musiclibrary.ui.tables.models.MainTableModel;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class MainDataTable extends JTable {
    public MainDataTable(Object[][] rowData, Object[] columnNames) {
        super(new MainTableModel(rowData, columnNames));
        setFillsViewportHeight(true);
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        addMouseListener(new MainTableListener(this));
    }
}
