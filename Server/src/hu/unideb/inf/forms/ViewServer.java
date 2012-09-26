package hu.unideb.inf.forms;

import hu.unideb.inf.classes.Room;
import hu.unideb.inf.classes.Server;
import java.util.Iterator;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;

public class ViewServer extends javax.swing.JFrame {

    private Server server;

    public ViewServer() {
        initComponents();
        server = new Server(this);
        portTextField.requestFocusInWindow();
        roomList.setComponentPopupMenu(PopupMenu);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PopupMenu = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        portTextField = new javax.swing.JTextField();
        startButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        roomList = new javax.swing.JList();
        addButton = new javax.swing.JButton();
        roomLabel = new javax.swing.JLabel();
        deleteButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        infoArea = new javax.swing.JTextArea();
        logLabel = new javax.swing.JLabel();

        jMenuItem1.setText("Delete");
        jMenuItem1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem1MouseClicked(evt);
            }
        });
        PopupMenu.add(jMenuItem1);

        jMenuItem2.setText("Move up");
        PopupMenu.add(jMenuItem2);

        jMenuItem3.setText("Move down");
        PopupMenu.add(jMenuItem3);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(550, 350));

        portTextField.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

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

        infoArea.setColumns(20);
        infoArea.setEditable(false);
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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(portTextField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(startButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(roomLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(addButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(deleteButton))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(portTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(startButton)))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        String inPort;
        inPort = portTextField.getText();
        int port = 0;
        try {
            port = Integer.parseInt(inPort);
        } catch (NumberFormatException ex) {
            System.out.println(ex.getMessage());
        }
        if (port > 0 && port < 65336) {
            server.listenSocket(port);
            addButton.setEnabled(true);
            deleteButton.setEnabled(true);
        }
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
//        if (SwingUtilities.isRightMouseButton(evt)) {
//            int index = list.locationToIndex(evt.getPoint());
//            System.out.println(index);
//            list.setSelectedIndex(index);
//            roomList.setSelectedIndex(index);
//            roomList.getComponentPopupMenu().show(evt.getComponent(), evt.getX(), evt.getY());
//            
//        }
    }//GEN-LAST:event_roomListMouseClicked

    private void jMenuItem1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MouseClicked
        if (SwingUtilities.isLeftMouseButton(evt)) {
            
        }
    }//GEN-LAST:event_jMenuItem1MouseClicked

    public static void main(String args[]) {

        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ViewServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new ViewServer().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPopupMenu PopupMenu;
    private javax.swing.JButton addButton;
    private javax.swing.JButton deleteButton;
    public javax.swing.JTextArea infoArea;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel logLabel;
    private javax.swing.JTextField portTextField;
    private javax.swing.JLabel roomLabel;
    public javax.swing.JList roomList;
    private javax.swing.JButton startButton;
    // End of variables declaration//GEN-END:variables
}
