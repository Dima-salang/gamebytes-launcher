/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamebytes;
import javax.swing.*;
import java.awt.*;
import java.util.List;
/**
 *
 * @author Luis
 */
public class HighScoresFrame extends JFrame {
    public HighScoresFrame(String gameName, List<Account> accounts) {
        super("High Scores - " + gameName);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        // Create main panel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        // Add game name header
        JLabel gameLabel = new JLabel(gameName);
        gameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(gameLabel);
        panel.add(Box.createVerticalStrut(10));
        
        // Get and display high scores
        List<Account.ScoreEntry> highScores = Account.getHighScores(gameName, accounts);
        // Create a panel for scores with alternating background colors
        JPanel scoresPanel = new JPanel();
        scoresPanel.setLayout(new BoxLayout(scoresPanel, BoxLayout.Y_AXIS));
        scoresPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        for (int i = 0; i < highScores.size(); i++) {
            Account.ScoreEntry entry = highScores.get(i);
            JPanel scoreRow = new JPanel(new FlowLayout(FlowLayout.CENTER));
            
            // Add rank number
            JLabel rankLabel = new JLabel((i + 1) + ".");
            rankLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            scoreRow.add(rankLabel);
            
            // Add username and score
            JLabel scoreLabel = new JLabel(entry.username + ": " + entry.score);
            System.out.println("Username: " + entry.username + ", Score: " + entry.score);
            scoreLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            scoreRow.add(scoreLabel);
            
            // Alternate background colors
            if (i % 2 == 0) {
                scoreRow.setBackground(new Color(240, 240, 240));
            }
            scoreRow.setOpaque(true);
            
            scoresPanel.add(scoreRow);
        }
        
        panel.add(scoresPanel);
        
        // Add close button at bottom
        JButton closeButton = new JButton("Close");
        closeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        closeButton.addActionListener(e -> dispose());
        
        panel.add(Box.createVerticalStrut(10));
        panel.add(closeButton);
        
        add(panel);
        pack();
        setSize(300, Math.min(400, getHeight())); // Set width and limit height
        setLocationRelativeTo(null); // Center on screen
        setVisible(true);
    }
}