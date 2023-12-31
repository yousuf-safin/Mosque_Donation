/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.MosjidDonation.ui.admin;

import com.MosjidDonation.DatabaseConnection;
import static com.MosjidDonation.ui.LoginJFrame.loggedInAdmin;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @Yousuf
 */
public class DonationDistributeJFrame extends javax.swing.JFrame {

    /**
     * Creates new form AdminDonationDistributeJFrame
     */
    public DonationDistributeJFrame() {
        initComponents();
        fetchAndPopulateDonationData();
    }

    private void fetchAndPopulateDonationData() {
        try {
            Connection connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT "
                    + "   Donation.Id, "
                    + "   (SELECT Username FROM Users WHERE Id = Donation.UserId) AS Username, "
                    + "   Donation.Category, "
                    + "   Donation.Amount, "
                    + "   (SELECT Name FROM Mosque WHERE Id = Donation.MosqueId) AS MosqueName, "
                    + "   Donation.Date AS DonationDate "
                    + "FROM Donation "
                    + "WHERE Donation.DistributionId IS NULL"
            );

            DefaultTableModel tableModel = (DefaultTableModel) donationListTable.getModel();
            tableModel.setRowCount(0); // Clear existing data in the table

            while (resultSet.next()) {
                int id = resultSet.getInt("Id");
                String username = resultSet.getString("Username");
                String category = resultSet.getString("Category");
                double amount = resultSet.getDouble("Amount");
                String mosqueName = resultSet.getString("MosqueName");
                String donationDate = resultSet.getString("DonationDate");
                String adminUsername = "-";

                // Create an array of data for each row
                Object[] rowData = {
                    id,
                    username,
                    category,
                    amount,
                    mosqueName,
                    donationDate,
                    adminUsername
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

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        donationListTable = new javax.swing.JTable();
        back = new javax.swing.JButton();
        distributeSelected = new javax.swing.JButton();
        distributeAll = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Mosjid Donation");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Donation List");

        donationListTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "User", "Category", "Amount", "Mosque", "Date", "Distributed By"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(donationListTable);
        if (donationListTable.getColumnModel().getColumnCount() > 0) {
            donationListTable.getColumnModel().getColumn(0).setResizable(false);
            donationListTable.getColumnModel().getColumn(0).setPreferredWidth(50);
            donationListTable.getColumnModel().getColumn(1).setResizable(false);
            donationListTable.getColumnModel().getColumn(2).setResizable(false);
            donationListTable.getColumnModel().getColumn(3).setResizable(false);
            donationListTable.getColumnModel().getColumn(4).setResizable(false);
            donationListTable.getColumnModel().getColumn(4).setPreferredWidth(150);
            donationListTable.getColumnModel().getColumn(5).setResizable(false);
            donationListTable.getColumnModel().getColumn(6).setResizable(false);
        }

        back.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        back.setText("Back");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        distributeSelected.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        distributeSelected.setText("Distribute Selected");
        distributeSelected.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                distributeSelectedActionPerformed(evt);
            }
        });

        distributeAll.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        distributeAll.setText("Distribute All");
        distributeAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                distributeAllActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 625, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(back)
                        .addGap(29, 29, 29)
                        .addComponent(distributeSelected)
                        .addGap(28, 28, 28)
                        .addComponent(distributeAll)
                        .addGap(114, 114, 114)))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(back)
                    .addComponent(distributeSelected)
                    .addComponent(distributeAll))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        setVisible(false);
        AdminDashboardJFrame frame = new AdminDashboardJFrame();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }//GEN-LAST:event_backActionPerformed

    private void distributeSelectedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_distributeSelectedActionPerformed
        int selectedRow = donationListTable.getSelectedRow();

        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Please select a row to distribute.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int selectedRowId = (int) donationListTable.getValueAt(selectedRow, 0);

        try {
            Connection connection = DatabaseConnection.getConnection();

            // Prepare statement to insert into Distribution table
            PreparedStatement insertStatement = connection.prepareStatement(
                    "INSERT INTO Distribution (Date, DistributedBy) VALUES (?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );

            // Set current date and loggedInAdmin as values for the insert statement
            java.util.Date currentDate = new java.util.Date();
            insertStatement.setDate(1, new Date(currentDate.getTime()));
            insertStatement.setInt(2, loggedInAdmin);

            // Execute the insert statement
            int rowsInserted = insertStatement.executeUpdate();

            if (rowsInserted > 0) {
                // Retrieve the generated ID of the newly inserted distribution
                ResultSet generatedKeys = insertStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int distributionId = generatedKeys.getInt(1);

                    // Update Zakat table with the distributionId for the selected row
                    PreparedStatement updateStatement = connection.prepareStatement(
                            "UPDATE Donation SET distributionId = ? WHERE Id = ?"
                    );
                    updateStatement.setInt(1, distributionId);
                    updateStatement.setInt(2, selectedRowId);
                    updateStatement.executeUpdate();
                    updateStatement.close();

                    JOptionPane.showMessageDialog(null, "Donation distributed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            }

            // Close the statements
            insertStatement.close();

            // Refresh the table after distributing donations
            fetchAndPopulateDonationData();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_distributeSelectedActionPerformed

    private void distributeAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_distributeAllActionPerformed
        try {
            Connection connection = DatabaseConnection.getConnection();

            // Prepare statement to insert into Distribution table
            PreparedStatement insertStatement = connection.prepareStatement(
                    "INSERT INTO Distribution (Date, DistributedBy) VALUES (?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );

            // Set current date and loggedInAdmin as values for the insert statement
            java.util.Date currentDate = new java.util.Date();
            insertStatement.setDate(1, new Date(currentDate.getTime()));
            insertStatement.setInt(2, loggedInAdmin);

            // Execute the insert statement
            int rowsInserted = insertStatement.executeUpdate();

            if (rowsInserted > 0) {
                // Retrieve the generated ID of the newly inserted distribution
                ResultSet generatedKeys = insertStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int distributionId = generatedKeys.getInt(1);

                    // Update Zakat table with the distributionId for the selected row
                    PreparedStatement updateStatement = connection.prepareStatement(
                            "UPDATE Donation SET distributionId = ? WHERE DistributionId IS NULL"
                    );
                    updateStatement.setInt(1, distributionId);
                    updateStatement.executeUpdate();
                    updateStatement.close();

                    JOptionPane.showMessageDialog(null, "All donations distributed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            }

            // Close the statements
            insertStatement.close();

            // Refresh the table after distributing zakat
            fetchAndPopulateDonationData();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_distributeAllActionPerformed

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
            java.util.logging.Logger.getLogger(DonationDistributeJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DonationDistributeJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DonationDistributeJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DonationDistributeJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DonationDistributeJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back;
    private javax.swing.JButton distributeAll;
    private javax.swing.JButton distributeSelected;
    private javax.swing.JTable donationListTable;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
