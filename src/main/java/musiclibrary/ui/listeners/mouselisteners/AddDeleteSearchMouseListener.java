package musiclibrary.ui.listeners.mouselisteners;

import musiclibrary.ui.uiconstants.ActionNames;
import musiclibrary.ui.uiconstants.EntityNames;
import musiclibrary.ui.uipanels.MainTableCardPanel;
import musiclibrary.ui.uipanels.MainTablePanel;
import musiclibrary.ui.uipanels.ResultCardPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static musiclibrary.ui.uiconstants.ActionNames.*;
import static musiclibrary.ui.uiconstants.EntityNames.*;
import static musiclibrary.ui.uiconstants.FontConstants.HEAD_LABEL_OUT_FONT_SIZE_K;
import static musiclibrary.ui.uiconstants.FontConstants.HEAD_LABEL_OVER_FONT_SIZE_K;

public class AddDeleteSearchMouseListener implements MouseListener {
    private MainTablePanel mainTablePanel;
    private ResultCardPanel resultCardPanel;

    public AddDeleteSearchMouseListener(MainTablePanel mainTablePanel, ResultCardPanel resultCardPanel) {
        this.mainTablePanel = mainTablePanel;
        this.resultCardPanel = resultCardPanel;
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
        ((CardLayout) mainTablePanel.getLayout()).show(mainTablePanel, labelText);
        String activePanelName = null;
        for (int pointer = 0; pointer < mainTablePanel.getComponentCount(); pointer++) {
            if (mainTablePanel.getComponent(pointer).isVisible())
                activePanelName = mainTablePanel.getComponent(pointer).getName();
        }
        switch(labelText) {
            case ACTION_NAME_ADD:
                switch (activePanelName) {
                    case ENTITY_NAME_USER:
                        ((CardLayout) resultCardPanel.getLayout()).show(resultCardPanel, ACTION_NAME_ADD_USER);
                        break;
                    case ENTITY_NAME_ARTIST:
                        ((CardLayout) resultCardPanel.getLayout()).show(resultCardPanel, ACTION_NAME_ADD_ARTIST);
                        break;
                    case ENTITY_NAME_TRACK:
                        ((CardLayout) resultCardPanel.getLayout()).show(resultCardPanel, ACTION_NAME_ADD_TRACK);
                        break;
                    case ENTITY_NAME_TRACKLIST:
                        ((CardLayout) resultCardPanel.getLayout()).show(resultCardPanel, ACTION_NAME_ADD_TRACKLIST);
                        break;
                }
                break;
        }
        ((CardLayout) resultCardPanel.getLayout()).show(resultCardPanel, labelText);
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