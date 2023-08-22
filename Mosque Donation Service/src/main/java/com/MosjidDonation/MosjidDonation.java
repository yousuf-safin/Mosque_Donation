/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.MosjidDonation;

import com.MosjidDonation.ui.WelcomeJFrame;
import javax.swing.UIManager;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @Yousuf
 */
public class MosjidDonation {

    public static void main(String[] args) {
        // Set the look and feel of the GUI to FlatLaf
        try {
            UIManager.setLookAndFeel(new FlatMacDarkLaf()); // Use FlatDarkLaf or other FlatLaf themes
        } catch (Exception ex) {
            // If there's an error in setting the look and feel, log the error
            Logger.getLogger(WelcomeJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Start the GUI in the Event Dispatch Thread
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // Create an instance of WelcomeJFrame and set its properties
                WelcomeJFrame homeFrame = new WelcomeJFrame();
                homeFrame.setLocationRelativeTo(null); // Center the frame on the screen
                homeFrame.setVisible(true); // Make the frame visible
            }
        });
    }
}