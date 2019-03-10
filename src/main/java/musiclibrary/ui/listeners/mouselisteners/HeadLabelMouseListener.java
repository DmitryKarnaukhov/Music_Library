package musiclibrary.ui.listeners.mouselisteners;

import musiclibrary.ui.uipanels.AddDeletePanel;
import musiclibrary.ui.uipanels.MainTableCardPanel;
import musiclibrary.ui.uipanels.MainTablePanel;
import musiclibrary.ui.uipanels.ResultCardPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import static musiclibrary.ui.uiconstants.FontConstants.*;

public class HeadLabelMouseListener implements MouseListener {
    private MainTablePanel mainTablePanel;

    public HeadLabelMouseListener(MainTablePanel mainTablePanel) {
        this.mainTablePanel = mainTablePanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        String labelText = ((JLabel)e.getComponent()).getText();
        switch(labelText) {
            case "DELETE":
                if (mainTablePanel.getComponent(0) instanceof JScrollPane) {
                    JTable table = (JTable)(((JScrollPane) mainTablePanel.getComponent(0)).getViewport().getView());
                    int selectedRow = table.getSelectedRow();
                    if(selectedRow == -1) {
                        JOptionPane.showMessageDialog(mainTablePanel, "Choose the row!");
                    } else {
                        int id = Integer.valueOf((String) table.getValueAt(selectedRow, 0));
                        mainTablePanel.artistView.remove(id);
                        mainTablePanel.refreshArtistTable();
                    }
                }
                break;
            default:
                mainTablePanel.addTable(labelText);
                break;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        int focusedSize = e.getComponent().getToolkit().getScreenSize().height / HEAD_LABEL_OVER_FONT_SIZE_K;
        e.getComponent().setFont(new Font(Font.SANS_SERIF, Font.BOLD + Font.ITALIC, focusedSize));
        Font font = new Font(Font.SANS_SERIF, Font.BOLD + Font.ITALIC, focusedSize);
        ((JLabel)e.getComponent()).setForeground(Color.RED);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        int lostFocusSize = e.getComponent().getToolkit().getScreenSize().height / HEAD_LABEL_OUT_FONT_SIZE_K;
        e.getComponent().setFont(new Font(Font.SANS_SERIF, Font.BOLD + Font.ITALIC, lostFocusSize));
        ((JLabel)e.getComponent()).setForeground(Color.BLACK);
    }
}
