package gamebytes;
import Game1_Snek.GameWindow;
import Game2_WordGuessr.WordGuessr;
import Game3_MemoryGame.MemoryGame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import gamebytes.LoginFrame;
import gamebytes.Account;
import gamebytes.DataIO;

public class Launcher extends javax.swing.JFrame {
    private JPanel bgPanel;
    private Image backgroundImage;
    private int lastLoadedHour = -1;
    private boolean useDefaultBG = true;
    private boolean isFileChooserOpen = false;
    private boolean loggedIn = false;
    private JFrame loginFrame;
    private Account acc;
    private DataIO dataIO = new DataIO();

    public void updateLauncherState(boolean loggedIn, Account acc) {
        if (loggedIn) {
            this.loggedIn = true;
            loginBtn.setText("Play");

            this.acc = acc;
            accountDetailLabel.setText(acc.getUsername());
        }
    }

    public Launcher() {
        setTitle("GameBytes Launcher");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        // <editor-fold defaultstate="collapsed" desc="Initialize Background Panel"> 
        bgPanel = new JPanel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        super.paintComponent(g);

                        // Draw the background image if it is loaded
                        Image bgImage = getBackgroundImage(LocalTime.now().getHour());
                        if (bgImage != null) {
                            g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
                        }
                    }
                };     
        setContentPane(bgPanel);
        // </editor-fold>
        initComponents();
        // <editor-fold defaultstate="collapsed" desc="Popup Menu"> 
        uploadBG.addActionListener(e -> {
            showImageChooser();
            useDefaultBG = false;
            bgPanel.repaint();
        });
        
        defaultBG.addActionListener(e -> {
            useDefaultBG = true;
            bgPanel.repaint();
        });
        
        bgPanel.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseReleased(MouseEvent e) {
                // Check if the right mouse button was released
                if (e.isPopupTrigger()) {
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="editor fold"> 
        // </editor-fold>
        
        //initial 
        setTimeLabels();
        
        //recurring tasks - TimeLabels, Dynamic Background
        Timer timer = new Timer(1000, e -> {
            //update date every sec using timer
            setTimeLabels();
            bgPanel.repaint();
            //System.out.println("za warudo: " + now.getHour());
            
        });
        
        timer.start();
        
        
        ImageIcon mainLogo = new ImageIcon("src/img/GameLogo.png");
        ImageIcon appIcon = new ImageIcon("src/img/GameLogo2.png");
        setIconImage(appIcon.getImage());
        //Set the image as the icon for the JLabel
        logoLabel.setIcon(new ImageIcon(resizeImageToFit(mainLogo.getImage(), logoLabel.getBounds().width, logoLabel.getBounds().height)));
        setLocationRelativeTo(null);
    }

    

    //either default or uploaded
    public Image getBackgroundImage(int hour) {
        // If the user is uploading an image, keep returning the default background until the file chooser is done
        if (isFileChooserOpen || useDefaultBG) {
            // Calculate the image file name based on the hour
            String fileName = (hour / 2) * 2 + ""; // Maps hour 0-1 to 0, 2-3 to 2, 4-5 to 4, ...
            try {
                backgroundImage = ImageIO.read(new File("src/bg/" + fileName + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return backgroundImage;
        }
        //if useDefaultBG = false, uploaded image will not get overwritten
        return backgroundImage; // Return the current background image
    }
    
    public static Image resizeImageToFit(Image img, int targetWidth, int targetHeight) {
        // Get the original width and height of the image using the ImageObserver
        int originalWidth = img.getWidth(null); // null is passed as no observer is needed
        int originalHeight = img.getHeight(null);

        // Calculate the aspect ratio of the original image
        double aspectRatio = (double) originalWidth / (double) originalHeight;

        // Determine the new width and height that maintain the aspect ratio
        int newWidth = targetWidth;
        int newHeight = targetHeight;

        if (originalWidth > targetWidth || originalHeight > targetHeight) {
            // If the image is larger than the target container, we scale it
            if ((double) targetWidth / targetHeight > aspectRatio) {
                // Scale based on height
                newWidth = (int) (targetHeight * aspectRatio);
            } else {
                // Scale based on width
                newHeight = (int) (targetWidth / aspectRatio);
            }
        }

        // Resize the image to the new width and height
        Image resizedImage = img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        return resizedImage;
    }
    
    //backgroundImage = chosenFile
    private void showImageChooser() {
        isFileChooserOpen = true;
        
        // Create a new JFileChooser to allow user to upload an image
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select an Image");

        // Filter to allow only image files
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Image Files", "jpg", "png", "gif"));

        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            // Get the selected file
            File selectedFile = fileChooser.getSelectedFile();
            try {
                // Load the image from the selected file
                backgroundImage = ImageIO.read(selectedFile);
                bgPanel.repaint();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error loading image: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        isFileChooserOpen = false;
    }
    
    //for labels
    public void setTimeLabels(){
        LocalDateTime currentDateTime = LocalDateTime.now();
            
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss a");
        String[] formattedDateTime = currentDateTime.format(formatter).split(" ");


        dateLabel.setText(formattedDateTime[0]);

        String timeText = formattedDateTime[1] + " " + formattedDateTime[2].toLowerCase();
        timeLabel.setText(timeText.startsWith("0")? timeText.substring(1):timeText);
        //remove leading zero
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupMenu = new javax.swing.JPopupMenu();
        changeBG = new javax.swing.JMenu();
        uploadBG = new javax.swing.JMenuItem();
        defaultBG = new javax.swing.JMenuItem();
        timeLabel = new javax.swing.JLabel();
        dateLabel = new javax.swing.JLabel();
        guestBtn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        loginBtn = new javax.swing.JButton();
        logoLabel = new javax.swing.JLabel();
        accountDetailLabel = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        game = new javax.swing.JMenu();
        game1 = new javax.swing.JMenuItem();
        game2 = new javax.swing.JMenuItem();
        game3 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        exit = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        addPlayer = new javax.swing.JMenuItem();
        delPlayer = new javax.swing.JMenuItem();
        viewPlayer = new javax.swing.JMenuItem();
        highScores = new javax.swing.JMenu();
        game1_hs = new javax.swing.JMenuItem();
        game2_hs = new javax.swing.JMenuItem();
        game3_hs = new javax.swing.JMenuItem();
        about = new javax.swing.JMenu();
        aboutUs = new javax.swing.JMenuItem();

        changeBG.setText("Change Background");

        uploadBG.setText("Upload Background");
        changeBG.add(uploadBG);

        defaultBG.setText("Default Background");
        changeBG.add(defaultBG);

        popupMenu.add(changeBG);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        timeLabel.setForeground(new java.awt.Color(255, 255, 255));
        timeLabel.setText("current time");

        dateLabel.setForeground(new java.awt.Color(255, 255, 255));
        dateLabel.setText("date");

        guestBtn.setText("Guest");
        guestBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guestBtnActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel6.setText("*soundtrack player? +timer");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(41, 41, 41))
        );

        loginBtn.setText("Login/Play");
        loginBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginBtnActionPerformed(evt);
            }
        });

        logoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        accountDetailLabel.setText("Account: ");

        game.setText("Game");

        game1.setText("Snek");
        game1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gameMenuActionPerformed(evt);
            }
        });
        game.add(game1);

        game2.setText("WordGuessr");
        game2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gameMenuActionPerformed(evt);
            }
        });
        game.add(game2);

        game3.setText("Memory Game");
        game3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gameMenuActionPerformed(evt);
            }
        });
        game.add(game3);
        game.add(jSeparator1);

        exit.setText("Exit");
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gameMenuActionPerformed(evt);
            }
        });
        game.add(exit);

        jMenuBar1.add(game);

        editMenu.setText("Edit");

        addPlayer.setText("Add Player");
        editMenu.add(addPlayer);

        delPlayer.setText("Delete Player");
        editMenu.add(delPlayer);

        viewPlayer.setText("View Players");
        editMenu.add(viewPlayer);

        jMenuBar1.add(editMenu);

        highScores.setText("High Scores");

        game1_hs.setText("Snek");
        game1_hs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                game1_hsActionPerformed(evt);
            }
        });
        highScores.add(game1_hs);

        game2_hs.setText("WordGuessr");
        game2_hs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                game2_hsActionPerformed(evt);
            }
        });
        highScores.add(game2_hs);

        game3_hs.setText("Memory Game");
        highScores.add(game3_hs);

        jMenuBar1.add(highScores);

        about.setText("About");

        aboutUs.setText("About Us");
        about.add(aboutUs);

        jMenuBar1.add(about);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(timeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 816, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addGap(195, 195, 195))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(dateLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(accountDetailLabel)
                        .addGap(224, 224, 224))))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(logoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(417, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(283, 283, 283)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(guestBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(loginBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(84, 84, 84))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addComponent(logoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 208, Short.MAX_VALUE)
                        .addComponent(timeLabel)
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dateLabel)
                            .addComponent(accountDetailLabel)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(guestBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42))
                            .addComponent(loginBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(82, 82, 82)
                        .addComponent(jLabel7)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    
    
    private void gameMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gameMenuActionPerformed
        if(evt.getSource() == game1){
            this.setVisible(false);
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new GameWindow(Launcher.this, acc);
                    System.out.println("game1-snek opened.");
                }
            });
        } else if(evt.getSource() == game2){
            this.setVisible(false);
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new WordGuessr(Launcher.this, acc);
                    System.out.println("game2-wordguessr opened.");
                }
            });
        } else if(evt.getSource() == game3){
            this.setVisible(false);
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new MemoryGame(Launcher.this);
                    System.out.println("game3-memorygame opened.");
                }
            });
        } else {
            System.exit(0);
        }
    }//GEN-LAST:event_gameMenuActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        System.out.println("main exiting.");
    }//GEN-LAST:event_formWindowClosing

    private void guestBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guestBtnActionPerformed
        game.doClick();
    }//GEN-LAST:event_guestBtnActionPerformed

    private void loginBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginBtnActionPerformed
        if (loggedIn) {
            game.doClick();

        } else {
            loginFrame = new LoginFrame(this);
            loginFrame.setVisible(true);
        }
        
    }//GEN-LAST:event_loginBtnActionPerformed

    private ArrayList<Account> getAccounts() throws IOException, ClassNotFoundException {
        ArrayList<Account> accounts = Account.allAccounts;
        return accounts;

    } 
        

    private void game1_hsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_game1_hsActionPerformed
        try {
        new HighScoresFrame("Snek", getAccounts());
        } catch (IOException | ClassNotFoundException e) {
            // handle the exception here
            System.out.println(e);
        }
    }//GEN-LAST:event_game1_hsActionPerformed

    private void game2_hsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_game2_hsActionPerformed
        try {
        new HighScoresFrame("WordGuessr", getAccounts());
        } catch (IOException | ClassNotFoundException e) {
            // handle the exception here
            System.out.println(e);
        }
    }//GEN-LAST:event_game2_hsActionPerformed

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
            java.util.logging.Logger.getLogger(Launcher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Launcher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Launcher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Launcher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Launcher().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu about;
    private javax.swing.JMenuItem aboutUs;
    private javax.swing.JLabel accountDetailLabel;
    private javax.swing.JMenuItem addPlayer;
    private javax.swing.JMenu changeBG;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JMenuItem defaultBG;
    private javax.swing.JMenuItem delPlayer;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenuItem exit;
    private javax.swing.JMenu game;
    private javax.swing.JMenuItem game1;
    private javax.swing.JMenuItem game1_hs;
    private javax.swing.JMenuItem game2;
    private javax.swing.JMenuItem game2_hs;
    private javax.swing.JMenuItem game3;
    private javax.swing.JMenuItem game3_hs;
    private javax.swing.JButton guestBtn;
    private javax.swing.JMenu highScores;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JButton loginBtn;
    private javax.swing.JLabel logoLabel;
    private javax.swing.JPopupMenu popupMenu;
    private javax.swing.JLabel timeLabel;
    private javax.swing.JMenuItem uploadBG;
    private javax.swing.JMenuItem viewPlayer;
    // End of variables declaration//GEN-END:variables
}
