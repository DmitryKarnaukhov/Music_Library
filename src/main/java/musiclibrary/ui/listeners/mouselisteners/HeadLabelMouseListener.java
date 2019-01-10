package musiclibrary.ui.listeners.mouselisteners;

import musiclibrary.ui.uipanels.TableCardPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import static musiclibrary.ui.uiconstants.FontConstants.*;

public class HeadLabelMouseListener implements MouseListener {
    private TableCardPanel tableCardPanel;

    public HeadLabelMouseListener(TableCardPanel tableCardPanel) {
        this.tableCardPanel = tableCardPanel;
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
        ((CardLayout)tableCardPanel.getLayout()).show(tableCardPanel, labelText);
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
