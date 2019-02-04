package musiclibrary.ui.listeners.mouselisteners;

import musiclibrary.ui.uipanels.MainTableCardPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static musiclibrary.ui.uiconstants.FontConstants.HEAD_LABEL_OUT_FONT_SIZE_K;
import static musiclibrary.ui.uiconstants.FontConstants.HEAD_LABEL_OVER_FONT_SIZE_K;

public class AddDeleteSearchMouseListener implements MouseListener {
    private MainTableCardPanel mainTableCardPanel;

    public AddDeleteSearchMouseListener(MainTableCardPanel mainTableCardPanel) {
        this.mainTableCardPanel = mainTableCardPanel;
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
        ((CardLayout) mainTableCardPanel.getLayout()).show(mainTableCardPanel, labelText);
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