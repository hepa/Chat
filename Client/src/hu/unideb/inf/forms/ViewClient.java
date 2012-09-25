package hu.unideb.inf.forms;

import hu.unideb.inf.classes.Client;

public class ViewClient extends javax.swing.JFrame {

    public ViewClient() {
        initComponents();
        messageView = new MessageView(this, true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        messageTextField = new javax.swing.JTextField();
        sendButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        imageLabel = new javax.swing.JLabel();
        stateTextField = new javax.swing.JTextField();
        roomNameBox = new javax.swing.JComboBox();
        changeRoomButton = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        connection = new javax.swing.JMenuItem();
        disconnect = new javax.swing.JMenuItem();
        quit = new javax.swing.JMenuItem();
        settingsMenu = new javax.swing.JMenu();
        option = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chat");
        setBounds(new java.awt.Rectangle(200, 120, 0, 0));
        setPreferredSize(new java.awt.Dimension(700, 500));
        setResizable(false);

        jTextArea1.setColumns(20);
        jTextArea1.setEditable(false);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setFocusable(false);
        jScrollPane1.setViewportView(jTextArea1);

        messageTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                messageTextFieldActionPerformed(evt);
            }
        });

        sendButton.setText("Send");
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jList1.setPreferredSize(new java.awt.Dimension(128, 80));
        jScrollPane2.setViewportView(jList1);

        imageLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hu/unideb/inf/images/Earth.png"))); // NOI18N
        imageLabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        stateTextField.setBackground(new java.awt.Color(255, 102, 102));
        stateTextField.setEditable(false);
        stateTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        stateTextField.setText("Offline");

        roomNameBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Rooms"}));

        changeRoomButton.setText("Change Room");
        changeRoomButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeRoomButtonActionPerformed(evt);
            }
        });

        fileMenu.setText("File");

        connection.setText("Connection...");
        connection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectionActionPerformed(evt);
            }
        });
        fileMenu.add(connection);

        disconnect.setText("Disconnect");
        disconnect.setEnabled(false);
        disconnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                disconnectActionPerformed(evt);
            }
        });
        fileMenu.add(disconnect);

        quit.setText("Quit");
        quit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitActionPerformed(evt);
            }
        });
        fileMenu.add(quit);

        jMenuBar1.add(fileMenu);

        settingsMenu.setText("Settings");

        option.setText("Option...");
        option.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optionActionPerformed(evt);
            }
        });
        settingsMenu.add(option);

        jMenuBar1.add(settingsMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE)
                            .addComponent(messageTextField))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(changeRoomButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                                .addComponent(roomNameBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(sendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(stateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(imageLabel)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(imageLabel)
                        .addGap(8, 8, 8)
                        .addComponent(roomNameBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(changeRoomButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(messageTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sendButton)
                    .addComponent(stateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendButtonActionPerformed
        String buffer;
        buffer = messageTextField.getText();
        try {
            c.send(buffer);
        } catch (NullPointerException ex) {
            System.out.println(ex.getMessage());
        }
        if (!isConnected) {
            messageView.setMessageView("No connection!",
                    "../images/Warning-icon.png", "Warning");
        }
        messageTextField.setText("");
    }//GEN-LAST:event_sendButtonActionPerformed

    private void messageTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_messageTextFieldActionPerformed
        String buffer;
        buffer = messageTextField.getText();
        try {
            c.send(buffer);
        } catch (NullPointerException ex) {
            System.out.println("No connection!");
        }

        messageTextField.setText("");
    }//GEN-LAST:event_messageTextFieldActionPerformed

    private void optionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optionActionPerformed
        SettingsDialog sd = new SettingsDialog(this, true);
        sd.setVisible(true);
    }//GEN-LAST:event_optionActionPerformed

    private void connectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectionActionPerformed
        ConnectDialog ncd = new ConnectDialog(this, true);
        ncd.setVisible(true);
        if (!ncd.isCanceled()) {
            ip = ncd.getIP();
            port = ncd.getPort();
            name = ncd.getName();
            c = new Client(ip, port, name, this, messageView);
            if (isConnected) {
                c.send(name);
                c.receive();
                this.setTitle("Chat - " + name);
                stateTextField.setText("Online");
                stateTextField.setBackground(new java.awt.Color(102, 255, 102));
                messageView.setMessageView("Connected to the server",
                        "../images/Information-icon.png", "Connected");
                connection.setEnabled(false);
                disconnect.setEnabled(true);
            }
        }
    }//GEN-LAST:event_connectionActionPerformed

    private void disconnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_disconnectActionPerformed
        c.close();
        stateTextField.setText("Offline");
        stateTextField.setBackground(new java.awt.Color(255, 102, 102));
        connection.setEnabled(true);
        disconnect.setEnabled(false);
    }//GEN-LAST:event_disconnectActionPerformed

    private void quitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitActionPerformed
        if (isConnected) {
            c.close();
        }
        System.exit(0);
    }//GEN-LAST:event_quitActionPerformed

    private void changeRoomButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeRoomButtonActionPerformed
        String roomName = (String) roomNameBox.getSelectedItem();
        if (!roomName.equals(currentRoom)) {
            c.send("Room*=" + roomName);
            currentRoom = roomName;
        }
    }//GEN-LAST:event_changeRoomButtonActionPerformed

    public void setIsConnected(boolean isConnected) {
        this.isConnected = isConnected;
    }

    public void lookAndFeel() {

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
            java.util.logging.Logger.getLogger(ViewClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new ViewClient().setVisible(true);
            }
        });
    }
    String currentRoom = "Default Room";
    Client c;
    MessageView messageView;
    private static String name;
    private static String ip;
    private static int port;
    private boolean isConnected = false;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton changeRoomButton;
    public javax.swing.JMenuItem connection;
    public javax.swing.JMenuItem disconnect;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JLabel imageLabel;
    public javax.swing.JList jList1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField messageTextField;
    private javax.swing.JMenuItem option;
    private javax.swing.JMenuItem quit;
    public javax.swing.JComboBox roomNameBox;
    private javax.swing.JButton sendButton;
    private javax.swing.JMenu settingsMenu;
    public javax.swing.JTextField stateTextField;
    // End of variables declaration//GEN-END:variables
}
