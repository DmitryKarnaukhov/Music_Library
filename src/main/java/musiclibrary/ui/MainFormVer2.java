package musiclibrary.ui;

import javax.swing.*;
import javax.swing.text.StyleConstants;
import java.awt.*;

public class MainFormVer2 extends JFrame {
    GroupLayout layout;
    private JLabel userHeadLabel,
                   trackListHeadLabel,
                   trackHeadLabel,
                   artistHeadLabel;

    public MainFormVer2() {
        initWindowProperties();
        init();
    }

    private void init() {
        layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        userHeadLabel = new JLabel("USER");
        userHeadLabel.setSize(100, 50);
        userHeadLabel.setFont(new Font(this.getFont().getFontName(), StyleConstants.ALIGN_CENTER, 30));
        trackListHeadLabel = new JLabel("TRACKLIST");
        trackHeadLabel = new JLabel("TRACK");
        artistHeadLabel = new JLabel("ARTIST");
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(userHeadLabel, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addComponent(trackListHeadLabel, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addComponent(trackHeadLabel, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addComponent(artistHeadLabel, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)

//                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
//                        .addGroup(layout.createSequentialGroup()
//                                .addComponent(userHeadLabel)
////                                .addContainerGap()
////                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
////                                        .addComponent(userHeadLabel)
//////                                        .addComponent(trackListHeadLabel)
//////                                        .addComponent(trackHeadLabel)
//////                                        .addComponent(artistHeadLabel)
////                                )
//                        )
        );
    }

    private void initWindowProperties() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        this.setSize(new Dimension(screenWidth / 2, screenHeight / 2));
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setTitle("Music Library");
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
