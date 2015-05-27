package hu.unideb.inf.forms;

import com.darkfalcon.java.config.ApplicationConfig;
import hu.unideb.inf.classes.Room;
import hu.unideb.inf.classes.Server;
import java.util.Iterator;
import javax.swing.JList;
import javax.swing.ListModel;

public class ServerView extends javax.swing.JFrame {

    private Server server;

    public ServerView() {
        lookAndFeel();
        initComponents();
        server = new Server(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PopupMenu = new javax.swing.JPopupMenu();
        deleteMenuItem = new javax.swing.JMenuItem();
        moveUpMenuItem = new javax.swing.JMenuItem();
        moveDownMenuItem = new javax.swing.JMenuItem();
        startButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        roomList = new javax.swing.JList();
        addButton = new javax.swing.JButton();
        roomLabel = new javax.swing.JLabel();
        deleteButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        infoArea = new javax.swing.JTextArea();
        logLabel = new javax.swing.JLabel();

        deleteMenuItem.setText("Delete");
        deleteMenuItem.setEnabled(false);
        deleteMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteMenuItemActionPerformed(evt);
            }
        });
        PopupMenu.add(deleteMenuItem);

        moveUpMenuItem.setText("Move up");
        moveUpMenuItem.setEnabled(false);
        moveUpMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveUpMenuItemActionPerformed(evt);
            }
        });
        PopupMenu.add(moveUpMenuItem);

        moveDownMenuItem.setText("Move down");
        moveDownMenuItem.setEnabled(false);
        moveDownMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveDownMenuItemActionPerformed(evt);
            }
        });
        PopupMenu.add(moveDownMenuItem);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(550, 350));

        startButton.setText("Start");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });

        roomList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        roomList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                roomListMouseReleased(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                roomListMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(roomList);

        addButton.setText("Add");
        addButton.setEnabled(false);
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        roomLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        roomLabel.setText("Rooms:");

        deleteButton.setText("Delete");
        deleteButton.setEnabled(false);
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        infoArea.setEditable(false);
        infoArea.setColumns(20);
        infoArea.setRows(5);
        infoArea.setFocusable(false);
        jScrollPane1.setViewportView(infoArea);

        logLabel.setText("Server info:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(logLabel)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(roomLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                                .addComponent(startButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(roomLabel)
                    .addComponent(logLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 185, Short.MAX_VALUE)
                        .addComponent(startButton))
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        String confPort = ApplicationConfig.getConfig().getProperty("", "4301");
        int port = Integer.parseInt(confPort);

        server.listenSocket(port);
        addButton.setEnabled(true);
        deleteButton.setEnabled(true);
        deleteMenuItem.setEnabled(true);
        moveUpMenuItem.setEnabled(true);
        moveDownMenuItem.setEnabled(true);

    }//GEN-LAST:event_startButtonActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        CreateRoom createRoom = new CreateRoom(this, true);
        createRoom.setVisible(true);
        if (!createRoom.isCanceled) {
            server.createRoom(createRoom.getRoomName());
        }

    }//GEN-LAST:event_addButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        String room = (String) roomList.getSelectedValue();
        Room r1 = null;
        for (Iterator<Room> it = server.getRooms().iterator(); it.hasNext();) {
            Room r = it.next();
            if (r.getRoomName().equals(room)) {
                if (r.getRoomID() == 0) {
                    infoArea.append("Default Room cannot be deleted!\n");
                    System.out.println("Default Room cannot be deleted!");
                    continue;
                }
                r1 = r;
            }
        }
        if (r1 != null) {
            server.deleteRoom(r1);
        }
    }//GEN-LAST:event_deleteButtonActionPerformed

    /**
     * Occours when clicked on the roomList button.
     *
     * @param evt
     */
    private void roomListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_roomListMouseClicked
        JList list = (JList) evt.getSource();
        if (evt.getClickCount() == 2) {
            int index = list.locationToIndex(evt.getPoint());
            if (index >= 0) {
                ListModel lm = list.getModel();
                Object item = lm.getElementAt(index);
                list.ensureIndexIsVisible(index);
                for (Iterator<Room> it = server.getRooms().iterator(); it.hasNext();) {
                    Room r = it.next();
                    if (r.getRoomName().equals(item)) {
                        r.roomLoggerVisible(true);
                    }
                }
            }
        }
    }//GEN-LAST:event_roomListMouseClicked

    private void roomListMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_roomListMouseReleased
        if (evt.isPopupTrigger()) {
            PopupMenu.show(evt.getComponent(), evt.getX(), evt.getY());
            int index = roomList.locationToIndex(evt.getPoint());
            roomList.setSelectedIndex(index);

        }

    }//GEN-LAST:event_roomListMouseReleased

    private void deleteMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteMenuItemActionPerformed
        int index = roomList.getSelectedIndex();
        if (index >= 0) {
            ListModel lm = roomList.getModel();
            Object item = lm.getElementAt(index);
            roomList.ensureIndexIsVisible(index);
            Room r1 = null;
            for (Iterator<Room> it = server.getRooms().iterator(); it.hasNext();) {
                Room r = it.next();
                if (r.getRoomName().equals(item)) {
                    if (r.getRoomID() == 0) {
                        infoArea.append("Default Room cannot be deleted!\n");
                        System.out.println("Default Room cannot be deleted!");
                        continue;
                    }
                    r1 = r;
                }
            }
            server.deleteRoom(r1);
        }
    }//GEN-LAST:event_deleteMenuItemActionPerformed

    private void moveUpMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveUpMenuItemActionPerformed
        System.out.println("MoveUP: " + roomList.getSelectedIndex());
    }//GEN-LAST:event_moveUpMenuItemActionPerformed

    private void moveDownMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveDownMenuItemActionPerformed
        System.out.println("MoveDown: " + roomList.getSelectedIndex());
    }//GEN-LAST:event_moveDownMenuItemActionPerformed

    private static void lookAndFeel() {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServerView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPopupMenu PopupMenu;
    private javax.swing.JButton addButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JMenuItem deleteMenuItem;
    public javax.swing.JTextArea infoArea;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel logLabel;
    private javax.swing.JMenuItem moveDownMenuItem;
    private javax.swing.JMenuItem moveUpMenuItem;
    private javax.swing.JLabel roomLabel;
    public javax.swing.JList roomList;
    private javax.swing.JButton startButton;
    // End of variables declaration//GEN-END:variables
}
