package musiclibrary.ui.uipanels;

import musiclibrary.entities.Track;
import musiclibrary.ui.tables.collnames.ReflectionCollNames;

import javax.swing.*;
import java.awt.*;

import static javax.swing.GroupLayout.Alignment.LEADING;
import static musiclibrary.ui.uiconstants.EntityNames.ENTITY_NAME_TRACK;
import static musiclibrary.ui.uiconstants.EntityNames.ENTITY_NAME_USER;

public class ResultPanel extends JPanel {
    GroupLayout layout = new GroupLayout(this);
    JLabel idLabel = new JLabel("Id: "),
           resultIdLabel = new JLabel(),
           nameLabel = new JLabel("Name: "),
           resultNameLabel = new JLabel();
    String[] collNames = ReflectionCollNames.getEntityFieldNames(Track.class);
    JTable resultTable = new JTable(new Object[][] { { "1", "2", "1", "2", "1" }, { "1", "2", "1", "2", "1" } }, collNames);

    public ResultPanel() {
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup(LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(idLabel)
                            .addComponent(resultIdLabel)
                        )
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(nameLabel)
                            .addComponent(resultNameLabel)
                        )
                        .addComponent(resultTable)
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                    .addComponent(idLabel)
                    .addComponent(resultIdLabel)
                )
                .addGroup(layout.createParallelGroup()
                     .addComponent(nameLabel)
                     .addComponent(resultNameLabel)
                )
                .addComponent(resultTable)
        );
    }
}

/*
package musiclibrary.ui.uipanels;

        import musiclibrary.entities.Track;
        import musiclibrary.entities.User;
        import musiclibrary.ui.tables.MainDataTable;
        import musiclibrary.ui.tables.collnames.ReflectionCollNames;

        import javax.swing.*;
        import javax.swing.event.TableModelListener;
        import javax.swing.table.TableModel;
        import java.awt.*;
        import static musiclibrary.ui.uiconstants.EntityNames.*;

public class MainTableCardPanel extends JPanel {
    private CardLayout layout;
    private MainDataTable userTable,
            trackTable,
            trackListTable,
            artistTable;
    private JScrollPane userScroll,
            trackScroll,
            trackListScroll,
            artistScroll;
    public MainTableCardPanel() {
        layout = new CardLayout(50, 0);
        setLayout(layout);

        String[] userCollNames = new String[0];
        userCollNames = ReflectionCollNames.getEntityFieldNames(User.class);
        String[][] userTableData = new String[][] {
                { "1", "Henry 1st", "1trackList" },
                { "2", "Monroe", "2trackList" }
        };
        String[] trackCollNames = ReflectionCollNames.getEntityFieldNames(Track.class);
        String[][] trackTableData = new String[][] {
                { "1", "myName", "artist1", "3.0", "Rock" },
                { "1", "myName", "artist1", "3.0", "Rock" }
        };
        userTable = new MainDataTable(userTableData, userCollNames);
        userScroll = new JScrollPane(userTable);

        CellEditor cellEditor = new DefaultCellEditor(new JTextField());
        //userTable.getColumnModel().getColumn(0).setMaxWidth(200);
        //userTable.getColumnModel().getColumn(0).setPreferredWidth(100);
        trackTable = new MainDataTable(trackTableData, trackCollNames);
        trackScroll = new JScrollPane(trackTable);
        add(ENTITY_NAME_USER, userScroll);
        add(ENTITY_NAME_TRACK, trackScroll);
    }
}
*/