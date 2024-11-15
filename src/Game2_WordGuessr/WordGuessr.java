package Game2_WordGuessr;


import gamebytes.Launcher;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import gamebytes.Account;
import gamebytes.DataIO;

public class WordGuessr extends javax.swing.JFrame {
    ImageIcon appImg = new ImageIcon("src/Game2_WordGuessr/logo.png");
    int difficulty;
    int score = 0;
    int chances;
    boolean dialogPresence = false;
    String keyword;
    List<List<String>> keyFiles = new ArrayList<List<String>>(3);
    private Account acc;

    public WordGuessr(Launcher launcher, Account acc) {
        this.acc = acc;
        setTitle("WordGuessr");
        setIconImage(appImg.getImage());
        setResizable(false);
        initComponents();
        setLocationRelativeTo(null);
        setVisible(true);
        
        //go back to main menu when closing
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                launcher.setVisible(true);
                System.out.println("game2 closed.");

                try {
                    DataIO dataIO = new DataIO();
                    dataIO.SaveData(Account.allAccounts); // Assuming Account.accounts is your list of current accounts
                    System.out.println("Account data saved successfully.");
                } catch (IOException ex) {
                    System.err.println("Error saving account data: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });
        
        //load keywords from text files
        try{
            keyFiles.add(Files.readAllLines(Paths.get("src/Game2_WordGuessr/1-easy.txt")));
            keyFiles.add(Files.readAllLines(Paths.get("src/Game2_WordGuessr/2-normal.txt")));
            keyFiles.add(Files.readAllLines(Paths.get("src/Game2_WordGuessr/3-hard.txt")));
        } catch(Exception e){e.printStackTrace();}

        startPanel.setVisible(true);
        gamePanel.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        diffButtons = new javax.swing.ButtonGroup();
        startPanel = new javax.swing.JPanel();
        chooseLabel = new javax.swing.JLabel();
        normalDiff = new javax.swing.JRadioButton();
        easyDiff = new javax.swing.JRadioButton();
        hardDiff = new javax.swing.JRadioButton();
        playButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        gameLabel = new javax.swing.JLabel();
        gameLabel1 = new javax.swing.JLabel();
        gameLabel2 = new javax.swing.JLabel();
        gamePanel = new javax.swing.JPanel();
        centerPanel = new javax.swing.JPanel();
        gridPanel = new javax.swing.JPanel();
        r1 = new javax.swing.JPanel();
        r1c1 = new javax.swing.JLabel();
        r1c2 = new javax.swing.JLabel();
        r1c3 = new javax.swing.JLabel();
        r1c4 = new javax.swing.JLabel();
        r1c5 = new javax.swing.JLabel();
        r2 = new javax.swing.JPanel();
        r2c1 = new javax.swing.JLabel();
        r2c2 = new javax.swing.JLabel();
        r2c3 = new javax.swing.JLabel();
        r2c4 = new javax.swing.JLabel();
        r2c5 = new javax.swing.JLabel();
        r3 = new javax.swing.JPanel();
        r3c1 = new javax.swing.JLabel();
        r3c2 = new javax.swing.JLabel();
        r3c3 = new javax.swing.JLabel();
        r3c4 = new javax.swing.JLabel();
        r3c5 = new javax.swing.JLabel();
        r4 = new javax.swing.JPanel();
        r4c1 = new javax.swing.JLabel();
        r4c2 = new javax.swing.JLabel();
        r4c3 = new javax.swing.JLabel();
        r4c4 = new javax.swing.JLabel();
        r4c5 = new javax.swing.JLabel();
        r5 = new javax.swing.JPanel();
        r5c1 = new javax.swing.JLabel();
        r5c2 = new javax.swing.JLabel();
        r5c3 = new javax.swing.JLabel();
        r5c4 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        topPanel = new javax.swing.JPanel();
        backButton = new javax.swing.JButton();
        diffLabel = new javax.swing.JLabel();
        chanceLabel = new javax.swing.JLabel();
        scoreLabel = new javax.swing.JLabel();
        bottomPanel = new javax.swing.JPanel();
        submitButton = new javax.swing.JButton();
        guessField = new javax.swing.JTextField();
        guessLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMinimumSize(new java.awt.Dimension(440, 462));
        setResizable(false);
        getContentPane().setLayout(new java.awt.CardLayout());

        startPanel.setBackground(new java.awt.Color(255, 255, 153));
        startPanel.setEnabled(false);
        startPanel.setMaximumSize(new java.awt.Dimension(447, 462));
        startPanel.setMinimumSize(new java.awt.Dimension(447, 462));

        chooseLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        chooseLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chooseLabel.setText("Choose Difficulty");

        normalDiff.setBackground(new java.awt.Color(255, 255, 153));
        diffButtons.add(normalDiff);
        normalDiff.setText("Normal");
        normalDiff.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                diffButtonsItemStateChanged(evt);
            }
        });

        easyDiff.setBackground(new java.awt.Color(255, 255, 153));
        diffButtons.add(easyDiff);
        easyDiff.setText("Easy");
        easyDiff.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                diffButtonsItemStateChanged(evt);
            }
        });

        hardDiff.setBackground(new java.awt.Color(255, 255, 153));
        diffButtons.add(hardDiff);
        hardDiff.setText("Hard");
        hardDiff.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                diffButtonsItemStateChanged(evt);
            }
        });

        playButton.setBackground(new java.awt.Color(255, 255, 153));
        playButton.setText("Play");
        playButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playButtonActionPerformed(evt);
            }
        });

        gameLabel.setBackground(new java.awt.Color(51, 51, 51));
        gameLabel.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        gameLabel.setForeground(new java.awt.Color(30, 215, 96));
        gameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gameLabel.setText("WORD");
        gameLabel.setOpaque(true);

        gameLabel1.setBackground(new java.awt.Color(153, 153, 153));
        gameLabel1.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        gameLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gameLabel1.setText("GUESS");
        gameLabel1.setOpaque(true);

        gameLabel2.setBackground(new java.awt.Color(255, 153, 0));
        gameLabel2.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        gameLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gameLabel2.setText("R");
        gameLabel2.setOpaque(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(gameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                .addGap(2, 2, 2)
                .addComponent(gameLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                .addGap(2, 2, 2)
                .addComponent(gameLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(gameLabel)
                    .addComponent(gameLabel1)
                    .addComponent(gameLabel2)))
        );

        javax.swing.GroupLayout startPanelLayout = new javax.swing.GroupLayout(startPanel);
        startPanel.setLayout(startPanelLayout);
        startPanelLayout.setHorizontalGroup(
            startPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, startPanelLayout.createSequentialGroup()
                .addContainerGap(177, Short.MAX_VALUE)
                .addGroup(startPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(startPanelLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(startPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(normalDiff)
                            .addComponent(easyDiff)
                            .addComponent(hardDiff)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, startPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(chooseLabel)
                        .addComponent(playButton, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(176, 176, 176))
            .addGroup(startPanelLayout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        startPanelLayout.setVerticalGroup(
            startPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(startPanelLayout.createSequentialGroup()
                .addGap(140, 140, 140)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(chooseLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(easyDiff)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(normalDiff)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hardDiff, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(playButton)
                .addContainerGap(141, Short.MAX_VALUE))
        );

        getContentPane().add(startPanel, "card2");

        gamePanel.setLayout(new java.awt.BorderLayout());

        centerPanel.setBackground(new java.awt.Color(255, 255, 204));

        gridPanel.setBackground(new java.awt.Color(153, 153, 153));
        gridPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        gridPanel.setPreferredSize(new java.awt.Dimension(248, 248));
        gridPanel.setLayout(new java.awt.GridLayout(5, 5, 0, 3));

        r1.setBackground(new java.awt.Color(153, 153, 153));
        r1.setLayout(new java.awt.GridLayout(1, 0, 3, 0));

        r1c1.setBackground(new java.awt.Color(204, 204, 204));
        r1c1.setFont(new java.awt.Font("Segoe UI Black", 1, 28)); // NOI18N
        r1c1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        r1c1.setText("A");
        r1c1.setOpaque(true);
        r1.add(r1c1);

        r1c2.setBackground(new java.awt.Color(204, 204, 204));
        r1c2.setFont(new java.awt.Font("Segoe UI Black", 1, 28)); // NOI18N
        r1c2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        r1c2.setText("A");
        r1c2.setOpaque(true);
        r1.add(r1c2);

        r1c3.setBackground(new java.awt.Color(204, 204, 204));
        r1c3.setFont(new java.awt.Font("Segoe UI Black", 1, 28)); // NOI18N
        r1c3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        r1c3.setText("A");
        r1c3.setOpaque(true);
        r1.add(r1c3);

        r1c4.setBackground(new java.awt.Color(204, 204, 204));
        r1c4.setFont(new java.awt.Font("Segoe UI Black", 1, 28)); // NOI18N
        r1c4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        r1c4.setText("A");
        r1c4.setOpaque(true);
        r1.add(r1c4);

        r1c5.setBackground(new java.awt.Color(204, 204, 204));
        r1c5.setFont(new java.awt.Font("Segoe UI Black", 1, 28)); // NOI18N
        r1c5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        r1c5.setText("A");
        r1c5.setOpaque(true);
        r1.add(r1c5);

        gridPanel.add(r1);

        r2.setBackground(new java.awt.Color(153, 153, 153));
        r2.setLayout(new java.awt.GridLayout(1, 0, 3, 0));

        r2c1.setBackground(new java.awt.Color(204, 204, 204));
        r2c1.setFont(new java.awt.Font("Segoe UI Black", 1, 28)); // NOI18N
        r2c1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        r2c1.setText("A");
        r2c1.setOpaque(true);
        r2.add(r2c1);

        r2c2.setBackground(new java.awt.Color(204, 204, 204));
        r2c2.setFont(new java.awt.Font("Segoe UI Black", 1, 28)); // NOI18N
        r2c2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        r2c2.setText("A");
        r2c2.setOpaque(true);
        r2.add(r2c2);

        r2c3.setBackground(new java.awt.Color(204, 204, 204));
        r2c3.setFont(new java.awt.Font("Segoe UI Black", 1, 28)); // NOI18N
        r2c3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        r2c3.setText("A");
        r2c3.setOpaque(true);
        r2.add(r2c3);

        r2c4.setBackground(new java.awt.Color(204, 204, 204));
        r2c4.setFont(new java.awt.Font("Segoe UI Black", 1, 28)); // NOI18N
        r2c4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        r2c4.setText("A");
        r2c4.setOpaque(true);
        r2.add(r2c4);

        r2c5.setBackground(new java.awt.Color(204, 204, 204));
        r2c5.setFont(new java.awt.Font("Segoe UI Black", 1, 28)); // NOI18N
        r2c5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        r2c5.setText("A");
        r2c5.setOpaque(true);
        r2.add(r2c5);

        gridPanel.add(r2);

        r3.setBackground(new java.awt.Color(153, 153, 153));
        r3.setLayout(new java.awt.GridLayout(1, 0, 3, 0));

        r3c1.setBackground(new java.awt.Color(204, 204, 204));
        r3c1.setFont(new java.awt.Font("Segoe UI Black", 1, 28)); // NOI18N
        r3c1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        r3c1.setText("A");
        r3c1.setOpaque(true);
        r3.add(r3c1);

        r3c2.setBackground(new java.awt.Color(204, 204, 204));
        r3c2.setFont(new java.awt.Font("Segoe UI Black", 1, 28)); // NOI18N
        r3c2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        r3c2.setText("A");
        r3c2.setOpaque(true);
        r3.add(r3c2);

        r3c3.setBackground(new java.awt.Color(204, 204, 204));
        r3c3.setFont(new java.awt.Font("Segoe UI Black", 1, 28)); // NOI18N
        r3c3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        r3c3.setText("A");
        r3c3.setOpaque(true);
        r3.add(r3c3);

        r3c4.setBackground(new java.awt.Color(204, 204, 204));
        r3c4.setFont(new java.awt.Font("Segoe UI Black", 1, 28)); // NOI18N
        r3c4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        r3c4.setText("A");
        r3c4.setOpaque(true);
        r3.add(r3c4);

        r3c5.setBackground(new java.awt.Color(204, 204, 204));
        r3c5.setFont(new java.awt.Font("Segoe UI Black", 1, 28)); // NOI18N
        r3c5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        r3c5.setText("A");
        r3c5.setOpaque(true);
        r3.add(r3c5);

        gridPanel.add(r3);

        r4.setBackground(new java.awt.Color(153, 153, 153));
        r4.setLayout(new java.awt.GridLayout(1, 0, 3, 0));

        r4c1.setBackground(new java.awt.Color(204, 204, 204));
        r4c1.setFont(new java.awt.Font("Segoe UI Black", 1, 28)); // NOI18N
        r4c1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        r4c1.setText("A");
        r4c1.setOpaque(true);
        r4.add(r4c1);

        r4c2.setBackground(new java.awt.Color(204, 204, 204));
        r4c2.setFont(new java.awt.Font("Segoe UI Black", 1, 28)); // NOI18N
        r4c2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        r4c2.setText("A");
        r4c2.setOpaque(true);
        r4.add(r4c2);

        r4c3.setBackground(new java.awt.Color(204, 204, 204));
        r4c3.setFont(new java.awt.Font("Segoe UI Black", 1, 28)); // NOI18N
        r4c3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        r4c3.setText("A");
        r4c3.setOpaque(true);
        r4.add(r4c3);

        r4c4.setBackground(new java.awt.Color(204, 204, 204));
        r4c4.setFont(new java.awt.Font("Segoe UI Black", 1, 28)); // NOI18N
        r4c4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        r4c4.setText("A");
        r4c4.setOpaque(true);
        r4.add(r4c4);

        r4c5.setBackground(new java.awt.Color(204, 204, 204));
        r4c5.setFont(new java.awt.Font("Segoe UI Black", 1, 28)); // NOI18N
        r4c5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        r4c5.setText("A");
        r4c5.setOpaque(true);
        r4.add(r4c5);

        gridPanel.add(r4);

        r5.setBackground(new java.awt.Color(153, 153, 153));
        r5.setLayout(new java.awt.GridLayout(1, 0, 3, 0));

        r5c1.setBackground(new java.awt.Color(204, 204, 204));
        r5c1.setFont(new java.awt.Font("Segoe UI Black", 1, 28)); // NOI18N
        r5c1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        r5c1.setText("A");
        r5c1.setOpaque(true);
        r5.add(r5c1);

        r5c2.setBackground(new java.awt.Color(204, 204, 204));
        r5c2.setFont(new java.awt.Font("Segoe UI Black", 1, 28)); // NOI18N
        r5c2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        r5c2.setText("A");
        r5c2.setOpaque(true);
        r5.add(r5c2);

        r5c3.setBackground(new java.awt.Color(204, 204, 204));
        r5c3.setFont(new java.awt.Font("Segoe UI Black", 1, 28)); // NOI18N
        r5c3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        r5c3.setText("A");
        r5c3.setOpaque(true);
        r5.add(r5c3);

        r5c4.setBackground(new java.awt.Color(204, 204, 204));
        r5c4.setFont(new java.awt.Font("Segoe UI Black", 1, 28)); // NOI18N
        r5c4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        r5c4.setText("A");
        r5c4.setOpaque(true);
        r5.add(r5c4);

        jLabel26.setBackground(new java.awt.Color(204, 204, 204));
        jLabel26.setFont(new java.awt.Font("Segoe UI Black", 1, 28)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("A");
        jLabel26.setOpaque(true);
        r5.add(jLabel26);

        gridPanel.add(r5);

        javax.swing.GroupLayout centerPanelLayout = new javax.swing.GroupLayout(centerPanel);
        centerPanel.setLayout(centerPanelLayout);
        centerPanelLayout.setHorizontalGroup(
            centerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(centerPanelLayout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addComponent(gridPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(86, Short.MAX_VALUE))
        );
        centerPanelLayout.setVerticalGroup(
            centerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, centerPanelLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(gridPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        gamePanel.add(centerPanel, java.awt.BorderLayout.CENTER);

        topPanel.setBackground(new java.awt.Color(0, 204, 51));
        topPanel.setPreferredSize(new java.awt.Dimension(440, 33));
        topPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        backButton.setBackground(new java.awt.Color(30, 215, 96));
        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });
        topPanel.add(backButton);

        diffLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        diffLabel.setText("Difficulty: -");
        diffLabel.setPreferredSize(new java.awt.Dimension(122, 14));
        topPanel.add(diffLabel);

        chanceLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chanceLabel.setText("Chances Left: -");
        chanceLabel.setPreferredSize(new java.awt.Dimension(122, 20));
        topPanel.add(chanceLabel);

        scoreLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        scoreLabel.setText("Score: -");
        scoreLabel.setPreferredSize(new java.awt.Dimension(122, 17));
        topPanel.add(scoreLabel);

        gamePanel.add(topPanel, java.awt.BorderLayout.PAGE_START);

        bottomPanel.setBackground(new java.awt.Color(255, 255, 153));

        submitButton.setBackground(new java.awt.Color(255, 255, 153));
        submitButton.setText("Submit");
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButtonActionPerformed(evt);
            }
        });

        guessField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                guessFieldKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                guessFieldKeyTyped(evt);
            }
        });

        guessLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        guessLabel.setText("Guess the word!");

        javax.swing.GroupLayout bottomPanelLayout = new javax.swing.GroupLayout(bottomPanel);
        bottomPanel.setLayout(bottomPanelLayout);
        bottomPanelLayout.setHorizontalGroup(
            bottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bottomPanelLayout.createSequentialGroup()
                .addGap(178, 178, 178)
                .addGroup(bottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(guessField)
                    .addComponent(guessLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(submitButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(179, Short.MAX_VALUE))
        );
        bottomPanelLayout.setVerticalGroup(
            bottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bottomPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(guessLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(guessField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(submitButton)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        gamePanel.add(bottomPanel, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(gamePanel, "card3");

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    //generate a number (n) from 0 - 19 then get the (n)th line from the file (20 keywords/file)
    public String getKeyword(int difficulty){
        int min = 0;
        int max = 19;
        int range = max - min + 1;
        int rand = (int)(Math.random() * range) + min;
        
        return keyFiles.get(difficulty).get(rand);
    }
    
    private void playButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playButtonActionPerformed
        //incase no diff selection
        if(difficulty == 0)
            JOptionPane.showMessageDialog(this, "Select a difficulty first.", "Missing Input Error", JOptionPane.ERROR_MESSAGE);
        else{
            //gamePanel setup/reset
            keyword = getKeyword(difficulty - 1);//get a random word from 3 files varying according to difficulty
            System.out.println(keyword);
            String diffStr = "";
            switch(difficulty){
                case 1: diffStr = "Easy"; break;
                case 2: diffStr = "Normal"; break;
                case 3: diffStr = "Hard"; break;
            }
            
            //reset labels
            diffLabel.setText("Difficulty: " + diffStr);
            chances = 5;
            chanceLabel.setText("Chances Left: "+ chances);
            score = 0;
            scoreLabel.setText("Score: " + score);
            
            JPanel panel;
            JLabel label;
            //reset grid
            for(Component cPanel: gridPanel.getComponents()){
                panel = (JPanel)cPanel;
                for(Component cLabel: panel.getComponents()){
                    label = (JLabel)cLabel;
                    label.setText("");
                    label.setBackground(Color.decode("#CCCCCC"));
                }
            }
            
            guessField.setText("");
            guessField.setEditable(true);
            
            startPanel.setVisible(false);
            gamePanel.setVisible(true);
            
            for(Component c: gridPanel.getComponents())
                c.setVisible(false);
        }
    }//GEN-LAST:event_playButtonActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        startPanel.setVisible(true);
        gamePanel.setVisible(false);
    }//GEN-LAST:event_backButtonActionPerformed
    
    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitButtonActionPerformed
        submitAnswer();
    }//GEN-LAST:event_submitButtonActionPerformed

    
    public void submitAnswer(){
        if(!guessField.getText().isEmpty() && guessField.getText().length() == 5 && !dialogPresence){ 
            String guessWord = guessField.getText().toUpperCase();
            int correctLetters = 0;
            int panelIndex = 5 - chances;
            
            //typecasting
            JPanel currentPanel = (JPanel)gridPanel.getComponent(panelIndex);
            JLabel cell;
            
            //checking each cell and repainting components
            char[] unguessedKeys = keyword.toCharArray();
            //checking for green
            int rowScore = 1;
            for(int i = 0; i < keyword.length(); i++){
                if(guessWord.charAt(i) == keyword.charAt(i)){
                    
                    currentPanel.getComponent(i).setBackground(Color.decode("#1ED760"));
                    correctLetters++;
                    
                    //------Scoring------
                    float multiplier = 0f;
                    switch(chances){
                        case 1: multiplier = 1.00f; break;
                        case 2: multiplier = 1.25f; break;
                        case 3: multiplier = 1.50f; break;
                        case 4: multiplier = 1.75f; break;
                        case 5: multiplier = 2.00f; break;
                    }
                    
                    //formulas
                    int baseScore = 2 + difficulty;
                    rowScore  *= baseScore * multiplier;
                    unguessedKeys[i] = '-';
                }
                //user input -> display in cell
                cell = (JLabel)currentPanel.getComponent(i);
                cell.setText("" + guessWord.charAt(i));
            }
            if(rowScore != 1)
                score += rowScore;
            scoreLabel.setText("Score: " + score);
            
            //checking for yellow | Algorithm: go through unguessedKeys, if guessKey found a match, turn yellow (will not affect green ones due to filer)
            for(int i = 0; i < keyword.length(); i++){
                if(!(guessWord.charAt(i) == keyword.charAt(i))) {
                    for(int j = 0; j < unguessedKeys.length; j++){
                        if(guessWord.charAt(i) == unguessedKeys[j])
                            currentPanel.getComponent(i).setBackground(Color.decode("#FFFF00"));
                    }
                }
            }
            
//            for(char c: unguessedKeys)
//                System.out.print(c);
//            System.out.println();
            
            gridPanel.getComponent(5 - chances).setVisible(true);
            
            chances--;
            chanceLabel.setText("Chances Left: " + chances);
            
            //player won
            if(correctLetters == 5){
                score *= chances + 3;
                if(chances == 4)
                    score += 77777; //bonus points
                scoreLabel.setText("Score: " + score);
                JOptionPane.showMessageDialog(bottomPanel, "You guessed the word!", "Player Won", JOptionPane.OK_OPTION);
                gamePanel.setVisible(false);
                startPanel.setVisible(true);

                return;
            }
            //player lost
            else if(chances == 0) {
                JOptionPane.showMessageDialog(bottomPanel, "Unfortunately that is incorrect. The correct word is " + keyword + ".", "Player Lost", JOptionPane.OK_OPTION);

                gamePanel.setVisible(false);
                startPanel.setVisible(true);

                    return;
                }
        } else if(guessField.getText().isEmpty()){
            dialogPresence = true;
            JOptionPane.showMessageDialog(this, "No input, please try again.", "Invalid Input Error", JOptionPane.ERROR_MESSAGE);
            dialogPresence = false;
        } else if(guessField.getText().length() != 5)
            JOptionPane.showMessageDialog(this, "Input must be exactly 5 letters.", "Invalid Input Error", JOptionPane.ERROR_MESSAGE);

        updateScore();
    }

    private void updateScore() {
        acc.updateGameScore("WordGuessr", score);
    }
    
    private void diffButtonsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_diffButtonsItemStateChanged
        if(evt.getStateChange() == 1){
            if(evt.getSource() == easyDiff)
                difficulty = 1; //easy
            else if(evt.getSource() == normalDiff)
                difficulty = 2; //normal
            else
                difficulty = 3; //hard
        }
    }//GEN-LAST:event_diffButtonsItemStateChanged

    private void guessFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_guessFieldKeyPressed
        char c = evt.getKeyChar();
        
        if(!dialogPresence && evt.getKeyChar() == KeyEvent.VK_ENTER)
            submitAnswer();
        else if((guessField.getText().length() <= 4 && //if no more than 4, let key be typed
                Character.isLetter(c)) ||
                Character.isISOControl(c))//    (4max && isletter) || isISO
            guessField.setEditable(true);
        else
            guessField.setEditable(false);
    }//GEN-LAST:event_guessFieldKeyPressed

    private void guessFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_guessFieldKeyTyped
        //must not exceed 5 letters and is a-z or backspace
        
    }//GEN-LAST:event_guessFieldKeyTyped

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
            java.util.logging.Logger.getLogger(WordGuessr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WordGuessr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WordGuessr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WordGuessr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new WordGuessr().setVisible(true);
//            }
//        });
    }
    //<editor-fold defaultstate="collapsed" desc=" var declaration ">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JPanel bottomPanel;
    private javax.swing.JPanel centerPanel;
    private javax.swing.JLabel chanceLabel;
    private javax.swing.JLabel chooseLabel;
    private javax.swing.ButtonGroup diffButtons;
    private javax.swing.JLabel diffLabel;
    private javax.swing.JRadioButton easyDiff;
    private javax.swing.JLabel gameLabel;
    private javax.swing.JLabel gameLabel1;
    private javax.swing.JLabel gameLabel2;
    private javax.swing.JPanel gamePanel;
    private javax.swing.JPanel gridPanel;
    private javax.swing.JTextField guessField;
    private javax.swing.JLabel guessLabel;
    private javax.swing.JRadioButton hardDiff;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton normalDiff;
    private javax.swing.JButton playButton;
    private javax.swing.JPanel r1;
    private javax.swing.JLabel r1c1;
    private javax.swing.JLabel r1c2;
    private javax.swing.JLabel r1c3;
    private javax.swing.JLabel r1c4;
    private javax.swing.JLabel r1c5;
    private javax.swing.JPanel r2;
    private javax.swing.JLabel r2c1;
    private javax.swing.JLabel r2c2;
    private javax.swing.JLabel r2c3;
    private javax.swing.JLabel r2c4;
    private javax.swing.JLabel r2c5;
    private javax.swing.JPanel r3;
    private javax.swing.JLabel r3c1;
    private javax.swing.JLabel r3c2;
    private javax.swing.JLabel r3c3;
    private javax.swing.JLabel r3c4;
    private javax.swing.JLabel r3c5;
    private javax.swing.JPanel r4;
    private javax.swing.JLabel r4c1;
    private javax.swing.JLabel r4c2;
    private javax.swing.JLabel r4c3;
    private javax.swing.JLabel r4c4;
    private javax.swing.JLabel r4c5;
    private javax.swing.JPanel r5;
    private javax.swing.JLabel r5c1;
    private javax.swing.JLabel r5c2;
    private javax.swing.JLabel r5c3;
    private javax.swing.JLabel r5c4;
    private javax.swing.JLabel scoreLabel;
    private javax.swing.JPanel startPanel;
    private javax.swing.JButton submitButton;
    private javax.swing.JPanel topPanel;
    // End of variables declaration//GEN-END:variables
    //</editor-fold>
}
