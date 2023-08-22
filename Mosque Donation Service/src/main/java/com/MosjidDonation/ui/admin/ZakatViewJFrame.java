/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.MosjidDonation.ui.admin;

import com.MosjidDonation.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @Yousuf
 */
public class ZakatViewJFrame extends javax.swing.JFrame {

    /**
     * Creates new form AdminViewZakatJFrame
     */
    public ZakatViewJFrame() {
        initComponents();
        populateMosqueComboBox();
        fetchAndPopulateData();
    }

    private void fetchAndPopulateData() {
        try {
            Connection connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT "
                    + "   Zakat.Id, "
                    + "   (SELECT Username FROM Users WHERE Id = Zakat.UserId) AS Username, "
                    + "   Zakat.Amount, "
                    + "   (SELECT Name FROM Mosque WHERE Id = Zakat.MosqueId) AS MosqueName, "
                    + "   Zakat.Date AS ZakatDate, "
                    + "   (SELECT Username FROM Admin WHERE Id = Distribution.DistributedBy) AS AdminUsername, "
                    + "   Zakat.DistributionId "
                    + "FROM Zakat "
                    + "LEFT JOIN Distribution ON Zakat.DistributionId = Distribution.Id"
            );

            DefaultTableModel tableModel = (DefaultTableModel) zakatTable.getModel();
            tableModel.setRowCount(0); // Clear existing data in the table

            while (resultSet.next()) {
                int id = resultSet.getInt("Id");
                String username = resultSet.getString("Username");
                double amount = resultSet.getDouble("Amount");
                String mosqueName = resultSet.getString("MosqueName");
                String zakatDate = resultSet.getString("ZakatDate"); // Use Zakat.Date
                String adminUsername = resultSet.getString("AdminUsername");

                // Create an array of data for each row
                Object[] rowData = {
                    id,
                    username,
                    amount,
                    mosqueName,
                    zakatDate, // Use Zakat.Date
                    adminUsername == null ? "-" : adminUsername
                };

                // Add the row to the table model
                tableModel.addRow(rowData);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void populateMosqueComboBox() {
        try {
            Connection connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT Name FROM Mosque");

            mosqueComboBox.removeAllItems(); // Clear existing items
            mosqueComboBox.addItem("Select Mosque..."); // Add mosque name to the combo box

            while (resultSet.next()) {
                String mosqueName = resultSet.getString("Name");
                mosqueComboBox.addItem(mosqueName); // Add mosque name to the combo box
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        zakatTable = new javax.swing.JTable();
        back = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        mosqueComboBox = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        totalAmount = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Mosjid Donation");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Zakat");

        zakatTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Id", "User", "Amount", "Mosque", "Date", "Distributed By"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(zakatTable);
        if (zakatTable.getColumnModel().getColumnCount() > 0) {
            zakatTable.getColumnModel().getColumn(0).setResizable(false);
            zakatTable.getColumnModel().getColumn(0).setPreferredWidth(25);
            zakatTable.getColumnModel().getColumn(1).setResizable(false);
            zakatTable.getColumnModel().getColumn(2).setResizable(false);
            zakatTable.getColumnModel().getColumn(3).setResizable(false);
            zakatTable.getColumnModel().getColumn(3).setPreferredWidth(150);
            zakatTable.getColumnModel().getColumn(4).setResizable(false);
            zakatTable.getColumnModel().getColumn(5).setResizable(false);
        }

        back.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        back.setText("Back");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel1.setText("Total Amount");

        mosqueComboBox.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        mosqueComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Mosque..." }));
        mosqueComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                mosqueComboBoxItemStateChanged(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel3.setText("Mosque");

        totalAmount.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        totalAmount.setText("0000");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 625, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(back))
                .addContainerGap(55, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(mosqueComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(totalAmount)
                .addGap(84, 84, 84))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(mosqueComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(totalAmount))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(back)
                .addGap(26, 26, 26))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        setVisible(false);
        AdminDashboardJFrame frame = new AdminDashboardJFrame();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }//GEN-LAST:event_backActionPerformed

    private void mosqueComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_mosqueComboBoxItemStateChanged
        if (mosqueComboBox.getSelectedIndex() == 0) {
            totalAmount.setText("0");
        } else try {
            Connection connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();
            String selectedMosque = (String) mosqueComboBox.getSelectedItem();
            
            String query = "SELECT SUM(Amount) AS TotalAmount FROM Zakat WHERE MosqueId = "
                            + "(SELECT Id FROM Mosque WHERE Name = ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, selectedMosque);

            ResultSet resultSet = preparedStatement.executeQuery();

            double totalAmountDouble = 0.0; // Initialize with a default value
            if (resultSet.next()) {
                totalAmountDouble = resultSet.getDouble("TotalAmount");
            }

            // Convert the double value to a string and set it as label text
            totalAmount.setText(Double.toString(totalAmountDouble));

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_mosqueComboBoxItemStateChanged

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ZakatViewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ZakatViewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ZakatViewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ZakatViewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ZakatViewJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> mosqueComboBox;
    private javax.swing.JLabel totalAmount;
    private javax.swing.JTable zakatTable;
    // End of variables declaration//GEN-END:variables
}
