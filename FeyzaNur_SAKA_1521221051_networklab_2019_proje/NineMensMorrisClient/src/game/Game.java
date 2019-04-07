/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import static game.Message.Message_Type.None;
import java.awt.Button;
import java.awt.Color;
import java.awt.Image;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;
import ninemensmorrisclient.Client;

/**
 *
 * @author Feyza Nur Saka
 */
public class Game extends javax.swing.JFrame {

    public static int pieces = 9;
    public static int sayi = 0;
    public static Game ThisGame;
    public static ArrayList<JLabel> labels;
    public static ArrayList<JLabel> white;
    public static ArrayList<JLabel> black;
    public static int order = 1;
    public static ImageIcon blackPiece = new ImageIcon();
    public static ImageIcon whitePiece = new ImageIcon();
    public static int hak = 0;

    /**
     * Creates new form Game
     */
    public Game() throws IOException {
        initComponents();

        ThisGame = this;
        labels = new ArrayList<>();
        whitePiece = new ImageIcon(getClass().getResource("/game/images/white_1.png"));
        blackPiece = new ImageIcon(getClass().getResource("/game/images/black_1.png"));
        this.getContentPane().setBackground(Color.white);
        txt_rival_name.setEnabled(false);
        addLabels();
        blackPieces();
        whitePieces();
    }

    public void addLabels() {
        labels.add(Game.ThisGame.jLabel20);
        labels.add(Game.ThisGame.jLabel21);
        labels.add(Game.ThisGame.jLabel22);
        labels.add(Game.ThisGame.jLabel23);
        labels.add(Game.ThisGame.jLabel24);
        labels.add(Game.ThisGame.jLabel25);
        labels.add(Game.ThisGame.jLabel26);
        labels.add(Game.ThisGame.jLabel27);
        labels.add(Game.ThisGame.jLabel28);
        labels.add(Game.ThisGame.jLabel29);
        labels.add(Game.ThisGame.jLabel30);
        labels.add(Game.ThisGame.jLabel31);
        labels.add(Game.ThisGame.jLabel32);
        labels.add(Game.ThisGame.jLabel33);
        labels.add(Game.ThisGame.jLabel34);
        labels.add(Game.ThisGame.jLabel35);
        labels.add(Game.ThisGame.jLabel36);
        labels.add(Game.ThisGame.jLabel37);
        labels.add(Game.ThisGame.jLabel38);
        labels.add(Game.ThisGame.jLabel39);
        labels.add(Game.ThisGame.jLabel40);
        labels.add(Game.ThisGame.jLabel41);
        labels.add(Game.ThisGame.jLabel42);
        labels.add(Game.ThisGame.jLabel43);

        for (JLabel label : labels) {
            label.setEnabled(true);
        }
    }
//sağ tarafta duran siyah taşları göstermek için arraye labelları ekleyip black imageleri gösterir

    public void blackPieces() {
        black = new ArrayList<>();
        black.add(jLabel11);
        black.add(jLabel12);
        black.add(jLabel13);
        black.add(jLabel14);
        black.add(jLabel15);
        black.add(jLabel16);
        black.add(jLabel17);
        black.add(jLabel18);
        black.add(jLabel19);
        for (JLabel black : black) {
            black.setIcon(blackPiece);
        }
    }
//sol tarafta duran beyaz taşları göstermek için arraye labelları ekleyip white imageleri gösterir

    public void whitePieces() {
        white = new ArrayList<>();
        white.add(jLabel1);
        white.add(jLabel51);
        white.add(jLabel46);
        white.add(jLabel45);
        white.add(jLabel52);
        white.add(jLabel48);
        white.add(jLabel47);
        white.add(jLabel49);
        white.add(jLabel50);
        for (JLabel white : white) {
            white.setIcon(whitePiece);
        }
    }
//her taş koyulduğunda yan taraftaki taşların azalmasını sağlıyor.

    public void reducePieces() {
        for (int i = 0; i < white.size(); i++) {
            if (white.get(i) == white.get(pieces)) {
                white.get(i).setIcon(null);
            }
        }

    }

    public void removeOrSend(JLabel label) {
        if (label.getIcon() != null) {
            information.setText("TAŞ ÇIKARILDI SIRA RAKİPTE");
            Message msg = new Message(Message.Message_Type.Selected);
            msg.content = label;
            msg.isEnabled = true;
            Client.Send(msg);
            label.setIcon(null);
            label.setBorder(null);
            for (JLabel blacklabel : labels) {
                blacklabel.setEnabled(false);
            }

        } else {
            sendMessage(label);
        }
    }

    public void sendMessage(JLabel label) {

        label.setBackground(Color.yellow);
//        calculate();
        System.out.println(label.getBackground());
        Message msg = new Message(Message.Message_Type.Selected);
        if (pieces != 0) {//taşlar bitmemişse yapılan hamleler
            if (label.isEnabled() == true) {
                if (label.getIcon() == null) {//taş konulmamışsa
                    msg.content = label;
                    msg.isEnabled = true;
                    Client.Send(msg);
                    label.setIcon(new ImageIcon(getClass().getResource("/game/images/white_1.png")));

                    label.setBorder(BorderFactory.createMatteBorder(//labelda beyaz taş gösterimi
                            0, 60, 60, 0, new ImageIcon(getClass().getResource("/game/images/white_1.png"))));

                    for (JLabel labels : labels) {
                        labels.setEnabled(false);
                    }
                } //-------------------------------------------------------
                pieces--;
                information.setText("");
                //kurallar
                //3 lü set olmuşsa taş çıkartma hakkı sağlar
                if (jLabel20.getBackground() == Color.yellow && jLabel21.getBackground() == Color.yellow && jLabel22.getBackground() == Color.yellow) {
                    information.setText("ÇIKARTMAK İSTEDİĞİN TAŞI SEÇ");

                    for (JLabel blacklabel : labels) {
                        blacklabel.setEnabled(true);
                    }

                    jLabel21.setBackground(null);
                } else if (jLabel20.getBackground() == Color.yellow && jLabel23.getBackground() == Color.yellow && jLabel25.getBackground() == Color.yellow) {

                    information.setText("ÇIKARTMAK İSTEDİĞİN TAŞI SEÇ");
                    for (JLabel blacklabel : labels) {
                        blacklabel.setEnabled(true);
                    }
                    jLabel23.setBackground(null);
                    for (JLabel blacklabel : labels) {
                        blacklabel.setEnabled(false);
                    }
                } else if (jLabel25.getBackground() == Color.yellow && jLabel26.getBackground() == Color.yellow && jLabel27.getBackground() == Color.yellow) {
                    information.setText("ÇIKARTMAK İSTEDİĞİN TAŞI SEÇ");
                    for (JLabel blacklabel : labels) {
                        blacklabel.setEnabled(true);
                    }
                    jLabel26.setBackground(null);
                    for (JLabel blacklabel : labels) {
                        blacklabel.setEnabled(false);
                    }
                } else if (jLabel27.getBackground() == Color.yellow && jLabel24.getBackground() == Color.yellow && jLabel22.getBackground() == Color.yellow) {
                    information.setText("ÇIKARTMAK İSTEDİĞİN TAŞI SEÇ");
                    for (JLabel blacklabel : labels) {
                        blacklabel.setEnabled(true);
                    }
                    jLabel24.setBackground(null);
                    for (JLabel blacklabel : labels) {
                        blacklabel.setEnabled(false);
                    }
                } else if (jLabel28.getBackground() == Color.yellow && jLabel29.getBackground() == Color.yellow && jLabel30.getBackground() == Color.yellow) {
                    information.setText("ÇIKARTMAK İSTEDİĞİN TAŞI SEÇ");
                    for (JLabel blacklabel : labels) {
                        blacklabel.setEnabled(true);
                    }
                    jLabel29.setBackground(null);
                    for (JLabel blacklabel : labels) {
                        blacklabel.setEnabled(false);
                    }
                } else if (jLabel28.getBackground() == Color.yellow && jLabel31.getBackground() == Color.yellow && jLabel33.getBackground() == Color.yellow) {
                    information.setText("ÇIKARTMAK İSTEDİĞİN TAŞI SEÇ");
                    for (JLabel blacklabel : labels) {
                        blacklabel.setEnabled(true);
                    }
                    jLabel31.setBackground(null);
                    for (JLabel blacklabel : labels) {
                        blacklabel.setEnabled(false);
                    }
                } else if (jLabel33.getBackground() == Color.yellow && jLabel34.getBackground() == Color.yellow && jLabel35.getBackground() == Color.yellow) {
                    information.setText("ÇIKARTMAK İSTEDİĞİN TAŞI SEÇ");
                    for (JLabel blacklabel : labels) {
                        blacklabel.setEnabled(true);
                    }
                    jLabel35.setBackground(null);
                    for (JLabel blacklabel : labels) {
                        blacklabel.setEnabled(false);
                    }
                } else if (jLabel35.getBackground() == Color.yellow && jLabel32.getBackground() == Color.yellow && jLabel30.getBackground() == Color.yellow) {
                    information.setText("ÇIKARTMAK İSTEDİĞİN TAŞI SEÇ");
                    for (JLabel blacklabel : labels) {
                        blacklabel.setEnabled(true);
                    }
                    jLabel32.setBackground(null);
                    for (JLabel blacklabel : labels) {
                        blacklabel.setEnabled(false);
                    }
                } else if (jLabel36.getBackground() == Color.yellow && jLabel37.getBackground() == Color.yellow && jLabel38.getBackground() == Color.yellow) {
                    information.setText("ÇIKARTMAK İSTEDİĞİN TAŞI SEÇ");
                    for (JLabel blacklabel : labels) {
                        blacklabel.setEnabled(true);
                    }
                    jLabel37.setBackground(null);
                    for (JLabel blacklabel : labels) {
                        blacklabel.setEnabled(false);
                    }
                } else if (jLabel36.getBackground() == Color.yellow && jLabel39.getBackground() == Color.yellow && jLabel41.getBackground() == Color.yellow) {
                    information.setText("ÇIKARTMAK İSTEDİĞİN TAŞI SEÇ");
                    for (JLabel blacklabel : labels) {
                        blacklabel.setEnabled(true);
                    }
                    jLabel39.setBackground(null);
                    for (JLabel blacklabel : labels) {
                        blacklabel.setEnabled(false);
                    }
                } else if (jLabel41.getBackground() == Color.yellow && jLabel42.getBackground() == Color.yellow && jLabel43.getBackground() == Color.yellow) {
                    information.setText("ÇIKARTMAK İSTEDİĞİN TAŞI SEÇ");
                    for (JLabel blacklabel : labels) {
                        blacklabel.setEnabled(true);
                    }
                    jLabel42.setBackground(null);
                    for (JLabel blacklabel : labels) {
                        blacklabel.setEnabled(false);
                    }
                } else if (jLabel43.getBackground() == Color.yellow && jLabel40.getBackground() == Color.yellow && jLabel38.getBackground() == Color.yellow) {
                    information.setText("ÇIKARTMAK İSTEDİĞİN TAŞI SEÇ");
                    for (JLabel blacklabel : labels) {
                        blacklabel.setEnabled(true);
                    }
                    jLabel40.setBackground(null);
                    for (JLabel blacklabel : labels) {
                        blacklabel.setEnabled(false);
                    }
                } else {
                    information.setText("SIRA RAKİPTE");

                    if (pieces == 0) {
                        information.setText("TAŞLAR BİTTİ SIRA YER DEĞİŞTİRMEDE");
                    }
                }

                reducePieces();//soldaki beyaz taşları azaltmaya yarar
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton9 = new javax.swing.JButton();
        txt_name = new javax.swing.JTextField();
        txt_rival_name = new javax.swing.JTextField();
        information = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_send = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        txt_receive = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("9 MEN'S MORRIS");
        setAutoRequestFocus(false);
        setBackground(new java.awt.Color(255, 255, 255));
        setFocusCycleRoot(false);
        setForeground(new java.awt.Color(102, 102, 102));
        setPreferredSize(new java.awt.Dimension(630, 800));
        setResizable(false);
        setSize(new java.awt.Dimension(800, 800));
        getContentPane().setLayout(null);

        jButton9.setBackground(new java.awt.Color(0, 204, 102));
        jButton9.setText("CONNECT");
        jButton9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton9MouseClicked(evt);
            }
        });
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton9);
        jButton9.setBounds(260, 0, 100, 40);

        txt_name.setText("your name");
        txt_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nameActionPerformed(evt);
            }
        });
        getContentPane().add(txt_name);
        txt_name.setBounds(60, 0, 100, 40);

        txt_rival_name.setText("rival name");
        txt_rival_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_rival_nameActionPerformed(evt);
            }
        });
        getContentPane().add(txt_rival_name);
        txt_rival_name.setBounds(460, 0, 100, 40);

        information.setText("INFORMATION");
        getContentPane().add(information);
        information.setBounds(80, 40, 500, 40);
        getContentPane().add(jLabel4);
        jLabel4.setBounds(40, 220, 0, 0);
        getContentPane().add(jLabel5);
        jLabel5.setBounds(40, 280, 0, 0);
        getContentPane().add(jLabel6);
        jLabel6.setBounds(40, 340, 0, 0);
        getContentPane().add(jLabel7);
        jLabel7.setBounds(40, 400, 0, 0);
        getContentPane().add(jLabel8);
        jLabel8.setBounds(40, 460, 0, 0);
        getContentPane().add(jLabel9);
        jLabel9.setBounds(40, 520, 0, 0);
        getContentPane().add(jLabel10);
        jLabel10.setBounds(40, 580, 60, 0);
        getContentPane().add(jLabel11);
        jLabel11.setBounds(560, 80, 60, 60);
        getContentPane().add(jLabel12);
        jLabel12.setBounds(560, 140, 60, 60);
        getContentPane().add(jLabel13);
        jLabel13.setBounds(560, 200, 60, 60);
        getContentPane().add(jLabel14);
        jLabel14.setBounds(560, 260, 60, 60);
        getContentPane().add(jLabel15);
        jLabel15.setBounds(560, 320, 60, 60);
        getContentPane().add(jLabel16);
        jLabel16.setBounds(560, 380, 60, 60);
        getContentPane().add(jLabel17);
        jLabel17.setBounds(560, 440, 60, 60);
        getContentPane().add(jLabel18);
        jLabel18.setBounds(560, 500, 60, 60);
        getContentPane().add(jLabel19);
        jLabel19.setBounds(560, 560, 60, 60);

        jLabel20.setName("1"); // NOI18N
        jLabel20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel20MouseClicked(evt);
            }
        });
        jLabel20.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jLabel20KeyPressed(evt);
            }
        });
        getContentPane().add(jLabel20);
        jLabel20.setBounds(60, 80, 60, 60);

        jLabel21.setName("2"); // NOI18N
        jLabel21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel21MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel21);
        jLabel21.setBounds(280, 80, 60, 60);

        jLabel22.setName("3"); // NOI18N
        jLabel22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel22MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel22);
        jLabel22.setBounds(500, 80, 60, 60);

        jLabel23.setName("4"); // NOI18N
        jLabel23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel23MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel23);
        jLabel23.setBounds(60, 320, 60, 60);

        jLabel24.setName("5"); // NOI18N
        jLabel24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel24MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel24);
        jLabel24.setBounds(500, 320, 60, 60);

        jLabel25.setName("6"); // NOI18N
        jLabel25.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel25MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel25);
        jLabel25.setBounds(60, 520, 60, 60);

        jLabel26.setToolTipText("");
        jLabel26.setName("7"); // NOI18N
        jLabel26.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel26MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel26);
        jLabel26.setBounds(280, 520, 60, 60);

        jLabel27.setName("8"); // NOI18N
        jLabel27.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel27MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel27);
        jLabel27.setBounds(500, 520, 60, 60);

        jLabel28.setName("9"); // NOI18N
        jLabel28.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel28MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel28);
        jLabel28.setBounds(140, 160, 60, 60);

        jLabel29.setName("10"); // NOI18N
        jLabel29.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel29MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel29);
        jLabel29.setBounds(280, 160, 60, 60);

        jLabel30.setName("11"); // NOI18N
        jLabel30.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel30MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel30);
        jLabel30.setBounds(420, 160, 60, 60);

        jLabel31.setToolTipText("");
        jLabel31.setName("12"); // NOI18N
        jLabel31.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel31MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel31);
        jLabel31.setBounds(140, 320, 60, 60);

        jLabel32.setName("13"); // NOI18N
        jLabel32.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel32MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel32);
        jLabel32.setBounds(440, 320, 60, 60);

        jLabel33.setName("14"); // NOI18N
        jLabel33.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel33MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel33);
        jLabel33.setBounds(120, 460, 60, 60);

        jLabel34.setName("15"); // NOI18N
        jLabel34.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel34MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel34);
        jLabel34.setBounds(280, 460, 60, 60);

        jLabel35.setName("16"); // NOI18N
        jLabel35.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel35MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel35);
        jLabel35.setBounds(440, 460, 60, 60);

        jLabel36.setName("17"); // NOI18N
        jLabel36.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel36MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel36);
        jLabel36.setBounds(200, 240, 60, 60);

        jLabel37.setName("18"); // NOI18N
        jLabel37.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel37MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel37);
        jLabel37.setBounds(280, 240, 60, 60);

        jLabel38.setName("19"); // NOI18N
        jLabel38.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel38MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel38);
        jLabel38.setBounds(360, 240, 60, 60);

        jLabel39.setName("20"); // NOI18N
        jLabel39.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel39MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel39);
        jLabel39.setBounds(200, 320, 60, 60);

        jLabel40.setName("21"); // NOI18N
        jLabel40.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel40MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel40);
        jLabel40.setBounds(360, 320, 60, 60);

        jLabel41.setName("22"); // NOI18N
        jLabel41.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel41MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel41);
        jLabel41.setBounds(200, 380, 60, 60);

        jLabel42.setName("23"); // NOI18N
        jLabel42.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel42MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel42);
        jLabel42.setBounds(280, 380, 60, 60);

        jLabel43.setName("24"); // NOI18N
        jLabel43.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel43MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel43);
        jLabel43.setBounds(360, 380, 60, 60);

        jLabel44.setIcon(new javax.swing.ImageIcon(getClass().getResource("/game/images/9mm.gif"))); // NOI18N
        getContentPane().add(jLabel44);
        jLabel44.setBounds(60, 100, 500, 480);
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 80, 60, 60);
        getContentPane().add(jLabel45);
        jLabel45.setBounds(0, 260, 60, 60);
        getContentPane().add(jLabel46);
        jLabel46.setBounds(0, 200, 60, 60);
        getContentPane().add(jLabel47);
        jLabel47.setBounds(0, 440, 60, 60);
        getContentPane().add(jLabel48);
        jLabel48.setBounds(0, 380, 60, 60);
        getContentPane().add(jLabel49);
        jLabel49.setBounds(0, 500, 60, 60);
        getContentPane().add(jLabel50);
        jLabel50.setBounds(0, 560, 60, 60);
        getContentPane().add(jLabel51);
        jLabel51.setBounds(0, 140, 60, 60);
        getContentPane().add(jLabel52);
        jLabel52.setBounds(0, 320, 60, 60);

        txt_send.setColumns(20);
        txt_send.setRows(5);
        jScrollPane1.setViewportView(txt_send);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(60, 620, 160, 80);

        txt_receive.setColumns(20);
        txt_receive.setRows(5);
        jScrollPane2.setViewportView(txt_receive);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(400, 620, 160, 80);

        jButton1.setText("SEND");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(240, 640, 140, 23);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_rival_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_rival_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_rival_nameActionPerformed

    private void txt_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nameActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        Client.Start("127.0.0.1", 2000);
        //başlangıç durumları
        txt_name.setEnabled(false);
        jButton9.setEnabled(false);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton9MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9MouseClicked

    private void jLabel20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseClicked
        // TODO add your handling code here:
        removeOrSend(jLabel20);
    }//GEN-LAST:event_jLabel20MouseClicked

    private void jLabel21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel21MouseClicked
        // TODO add your handling code here:

        removeOrSend(jLabel21);
    }//GEN-LAST:event_jLabel21MouseClicked

    private void jLabel22MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel22MouseClicked
        // TODO add your handling code here:

        removeOrSend(jLabel22);
    }//GEN-LAST:event_jLabel22MouseClicked

    private void jLabel28MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel28MouseClicked
        // TODO add your handling code here:
        removeOrSend(jLabel28);
    }//GEN-LAST:event_jLabel28MouseClicked

    private void jLabel29MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel29MouseClicked
        // TODO add your handling code here:
        removeOrSend(jLabel29);

    }//GEN-LAST:event_jLabel29MouseClicked

    private void jLabel30MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel30MouseClicked
        // TODO add your handling code here:
        removeOrSend(jLabel30);
    }//GEN-LAST:event_jLabel30MouseClicked

    private void jLabel31MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel31MouseClicked
        // TODO add your handling code here:
        removeOrSend(jLabel31);
    }//GEN-LAST:event_jLabel31MouseClicked

    private void jLabel32MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel32MouseClicked
        // TODO add your handling code here:
        removeOrSend(jLabel32);
    }//GEN-LAST:event_jLabel32MouseClicked

    private void jLabel38MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel38MouseClicked
        // TODO add your handling code here:
        removeOrSend(jLabel38);
    }//GEN-LAST:event_jLabel38MouseClicked

    private void jLabel40MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel40MouseClicked
        // TODO add your handling code here:
        removeOrSend(jLabel40);
    }//GEN-LAST:event_jLabel40MouseClicked

    private void jLabel43MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel43MouseClicked
        // TODO add your handling code here:
        removeOrSend(jLabel43);
    }//GEN-LAST:event_jLabel43MouseClicked

    private void jLabel42MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel42MouseClicked
        // TODO add your handling code here:
        removeOrSend(jLabel42);
    }//GEN-LAST:event_jLabel42MouseClicked

    private void jLabel41MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel41MouseClicked
        // TODO add your handling code here:
        removeOrSend(jLabel41);
    }//GEN-LAST:event_jLabel41MouseClicked

    private void jLabel39MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel39MouseClicked
        // TODO add your handling code here:
        removeOrSend(jLabel39);
    }//GEN-LAST:event_jLabel39MouseClicked

    private void jLabel36MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel36MouseClicked
        // TODO add your handling code here:
        removeOrSend(jLabel36);
    }//GEN-LAST:event_jLabel36MouseClicked

    private void jLabel37MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel37MouseClicked
        // TODO add your handling code here:
        removeOrSend(jLabel37);
    }//GEN-LAST:event_jLabel37MouseClicked

    private void jLabel24MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel24MouseClicked
        // TODO add your handling code here:
        removeOrSend(jLabel24);
    }//GEN-LAST:event_jLabel24MouseClicked

    private void jLabel35MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel35MouseClicked
        // TODO add your handling code here:
        removeOrSend(jLabel35);
    }//GEN-LAST:event_jLabel35MouseClicked

    private void jLabel34MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel34MouseClicked
        // TODO add your handling code here:
        removeOrSend(jLabel34);
    }//GEN-LAST:event_jLabel34MouseClicked

    private void jLabel33MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel33MouseClicked
        // TODO add your handling code here:
        removeOrSend(jLabel33);
    }//GEN-LAST:event_jLabel33MouseClicked

    private void jLabel23MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel23MouseClicked
        // TODO add your handling code here:
        removeOrSend(jLabel23);

    }//GEN-LAST:event_jLabel23MouseClicked

    private void jLabel25MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel25MouseClicked
        // TODO add your handling code here:
        removeOrSend(jLabel25);
    }//GEN-LAST:event_jLabel25MouseClicked

    private void jLabel26MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel26MouseClicked
        // TODO add your handling code here:
        removeOrSend(jLabel26);
    }//GEN-LAST:event_jLabel26MouseClicked

    private void jLabel27MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel27MouseClicked
        // TODO add your handling code here:
        removeOrSend(jLabel27);
    }//GEN-LAST:event_jLabel27MouseClicked

    private void jLabel20KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLabel20KeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_jLabel20KeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        //metin mesajı gönder
        Message msg = new Message(Message.Message_Type.Text);
        String x = txt_send.getText();
        msg.content = txt_send.getText();
        Client.Send(msg);
        txt_send.setText("");
    }//GEN-LAST:event_jButton1ActionPerformed

    /*7
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
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Game().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel information;
    public javax.swing.JButton jButton1;
    public javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel10;
    public javax.swing.JLabel jLabel11;
    public javax.swing.JLabel jLabel12;
    public javax.swing.JLabel jLabel13;
    public javax.swing.JLabel jLabel14;
    public javax.swing.JLabel jLabel15;
    public javax.swing.JLabel jLabel16;
    public javax.swing.JLabel jLabel17;
    public javax.swing.JLabel jLabel18;
    public javax.swing.JLabel jLabel19;
    public javax.swing.JLabel jLabel20;
    public javax.swing.JLabel jLabel21;
    public javax.swing.JLabel jLabel22;
    public javax.swing.JLabel jLabel23;
    public javax.swing.JLabel jLabel24;
    public javax.swing.JLabel jLabel25;
    public javax.swing.JLabel jLabel26;
    public javax.swing.JLabel jLabel27;
    public javax.swing.JLabel jLabel28;
    public javax.swing.JLabel jLabel29;
    public javax.swing.JLabel jLabel30;
    public javax.swing.JLabel jLabel31;
    public javax.swing.JLabel jLabel32;
    public javax.swing.JLabel jLabel33;
    public javax.swing.JLabel jLabel34;
    public javax.swing.JLabel jLabel35;
    public javax.swing.JLabel jLabel36;
    public javax.swing.JLabel jLabel37;
    public javax.swing.JLabel jLabel38;
    public javax.swing.JLabel jLabel39;
    public javax.swing.JLabel jLabel4;
    public javax.swing.JLabel jLabel40;
    public javax.swing.JLabel jLabel41;
    public javax.swing.JLabel jLabel42;
    public javax.swing.JLabel jLabel43;
    public javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    public javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    public javax.swing.JLabel jLabel6;
    public javax.swing.JLabel jLabel7;
    public javax.swing.JLabel jLabel8;
    public javax.swing.JLabel jLabel9;
    public javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JTextField txt_name;
    public javax.swing.JTextArea txt_receive;
    public javax.swing.JTextField txt_rival_name;
    public javax.swing.JTextArea txt_send;
    // End of variables declaration//GEN-END:variables
}
