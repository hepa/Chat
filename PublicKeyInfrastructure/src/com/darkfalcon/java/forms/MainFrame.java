/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.darkfalcon.java.forms;

import com.darkfalcon.java.connection.ReceiveConnectionWorker;
import com.darkfalcon.java.entity.server.KeyRegistry;
import com.darkfalcon.java.file.util.SecurityFileExporter;
import com.darkfalcon.java.services.SecurityService;
import com.darkfalcon.java.services.ServiceProvider;
import com.darkfalcon.java.utils.KeyExportUtil;
import com.darkfalcon.java.utils.SignProvider;
import java.awt.Cursor;
import java.io.IOException;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import com.darkfalcon.java.keys.AsymmetricKeyUtil;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Darkfalcon
 */
public class MainFrame extends javax.swing.JFrame {
    
    private Map<String, KeyRegistry> registries;
    private SecurityService service;

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
        addRowSelectionListener();
        initData();
        ReceiveConnectionWorker worker = new ReceiveConnectionWorker(this);
        worker.execute();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        consolePanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        consoleArea = new javax.swing.JTextArea();
        generatePanel = new javax.swing.JPanel();
        generateButton = new javax.swing.JButton();
        serverIdLabel = new javax.swing.JLabel();
        serverIdInput = new javax.swing.JTextField();
        sizeLabel = new javax.swing.JLabel();
        keySizeCombo = new javax.swing.JComboBox();
        registryPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        registryTable = new javax.swing.JTable();
        exportButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        consolePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Console"));

        consoleArea.setColumns(20);
        consoleArea.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        consoleArea.setRows(5);
        jScrollPane1.setViewportView(consoleArea);

        javax.swing.GroupLayout consolePanelLayout = new javax.swing.GroupLayout(consolePanel);
        consolePanel.setLayout(consolePanelLayout);
        consolePanelLayout.setHorizontalGroup(
            consolePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(consolePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        consolePanelLayout.setVerticalGroup(
            consolePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(consolePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                .addContainerGap())
        );

        generatePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Generate"));

        generateButton.setText("Generate");
        generateButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                generateButtonMouseClicked(evt);
            }
        });

        serverIdLabel.setText("Server ID:");

        sizeLabel.setText("Size:");

        keySizeCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "512", "768", "1024", "2048" }));

        javax.swing.GroupLayout generatePanelLayout = new javax.swing.GroupLayout(generatePanel);
        generatePanel.setLayout(generatePanelLayout);
        generatePanelLayout.setHorizontalGroup(
            generatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generatePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(generatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, generatePanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(generateButton))
                    .addComponent(serverIdInput)
                    .addGroup(generatePanelLayout.createSequentialGroup()
                        .addGroup(generatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(serverIdLabel)
                            .addComponent(sizeLabel)
                            .addComponent(keySizeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 98, Short.MAX_VALUE)))
                .addContainerGap())
        );
        generatePanelLayout.setVerticalGroup(
            generatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, generatePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(serverIdLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(serverIdInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sizeLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(keySizeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 138, Short.MAX_VALUE)
                .addComponent(generateButton)
                .addContainerGap())
        );

        registryPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Registries"));

        registryTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "Server ID", "Key length (bit)", "Recorded", "Modified"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(registryTable);

        exportButton.setText("Export");
        exportButton.setEnabled(false);
        exportButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exportButtonMouseClicked(evt);
            }
        });

        deleteButton.setText("Delete");
        deleteButton.setEnabled(false);
        deleteButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deleteButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout registryPanelLayout = new javax.swing.GroupLayout(registryPanel);
        registryPanel.setLayout(registryPanelLayout);
        registryPanelLayout.setHorizontalGroup(
            registryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(registryPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(registryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, registryPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(deleteButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(exportButton)))
                .addContainerGap())
        );
        registryPanelLayout.setVerticalGroup(
            registryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(registryPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(registryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(exportButton)
                    .addComponent(deleteButton))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(consolePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(generatePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(registryPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(generatePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(registryPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(consolePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void initData() {
        registries = new HashMap<>();
        service = ServiceProvider.getSecurityService();
        List<KeyRegistry> result = service.getKeyRegistries();
        addConsoleMessage(result.size() + " key registies found.\n");
        for (KeyRegistry registry: result) {
            registries.put(registry.getServerId(), registry);
            addRegistryToRegistryTable(registry);
        }
    }
    
    private void addRowSelectionListener() {
        registryTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (registryTable.getSelectedRow() > -1) {
                    deleteButton.setEnabled(true);
                    exportButton.setEnabled(true);
                } else {
                    deleteButton.setEnabled(false);
                    exportButton.setEnabled(false);
                }
            }
        });
    }
    
    private void addRegistryToRegistryTable(KeyRegistry keyRegistry) {
        DefaultTableModel tableModel = getDefaultTableModel(registryTable);
        RSAPublicKey publicKey = (RSAPublicKey) keyRegistry.getKeyPair().getPublic();
        Object[] registryRow = {keyRegistry.getServerId(), publicKey.getModulus().bitLength(), keyRegistry.getRecDate(), keyRegistry.getModDate()};
        tableModel.addRow(registryRow);
        tableModel.fireTableDataChanged();
    }
    
    private void addConsoleMessage(String message) {
        consoleArea.append(message);
    }
    
    private DefaultTableModel getDefaultTableModel(javax.swing.JTable table) {
        return (DefaultTableModel) table.getModel();
    }
    
    private KeyRegistry getSelectedRegistry() {
        final int SERVER_ID_COLUMN = 0;
        String serverId = (String) registryTable.getModel().getValueAt(registryTable.getSelectedRow(), SERVER_ID_COLUMN);
        return registries.get(serverId);
    }
    
    private void setCursorState(int state) {
        this.setCursor(Cursor.getPredefinedCursor(state));
    }
    
    private void exportButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exportButtonMouseClicked
        try {
            KeyExportUtil.exportRSAKeys(getSelectedRegistry());
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_exportButtonMouseClicked

    private void deleteButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteButtonMouseClicked
        setCursorState(Cursor.WAIT_CURSOR);
        KeyRegistry registry = getSelectedRegistry();
        if (service.deleteKeyRegistry(registry)) {
            getDefaultTableModel(registryTable).removeRow(registryTable.getSelectedRow());
            registries.remove(registry.getServerId());
            addConsoleMessage("Keyregistry for <" + registry.getServerId() + "> is removed!\n");
        }
        setCursorState(Cursor.DEFAULT_CURSOR);
    }//GEN-LAST:event_deleteButtonMouseClicked

    private void generateButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_generateButtonMouseClicked
        
        setCursorState(Cursor.WAIT_CURSOR);
        
        String serverId = serverIdInput.getText();
        
        if (serverId != null && !serverId.isEmpty()) {
            KeyRegistry registry = new KeyRegistry();
            registry.setServerId(serverIdInput.getText());
            int keySize = Integer.parseInt(String.valueOf(keySizeCombo.getSelectedItem()));
            registry.setKeyPair(AsymmetricKeyUtil.generateKeyPair(keySize, AsymmetricKeyUtil.ALGORITHM_RSA));
            if (service.createKeyRegistry(registry)) {
                KeyRegistry registryFromDb = service.getKeyRegistryByServerId(serverId);
                registries.put(serverId, registryFromDb);
                addRegistryToRegistryTable(registryFromDb);
                serverIdInput.setText(null);
                addConsoleMessage(keySize + " bit long RSA key pair generated for <" + serverId + ">. \n");
               
            }
        }
        
        setCursorState(Cursor.DEFAULT_CURSOR);
    }//GEN-LAST:event_generateButtonMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
    
     public void appendToConsoleArea(String message) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        consoleArea.append(dateFormat.format(cal.getTime()) + " - " + message + "\n");
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea consoleArea;
    private javax.swing.JPanel consolePanel;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton exportButton;
    private javax.swing.JButton generateButton;
    private javax.swing.JPanel generatePanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox keySizeCombo;
    private javax.swing.JPanel registryPanel;
    private javax.swing.JTable registryTable;
    private javax.swing.JTextField serverIdInput;
    private javax.swing.JLabel serverIdLabel;
    private javax.swing.JLabel sizeLabel;
    // End of variables declaration//GEN-END:variables
}
