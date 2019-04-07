/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ninemensmorrisclient;

import game.Message;
import static ninemensmorrisclient.Client.sInput;

import game.Message;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import static ninemensmorrisclient.Client.sInput;
import game.Game;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import static ninemensmorrisclient.Client.sOutput;

/**
 *
 * @author INSECT
 */
// serverdan gelecek mesajları dinleyen thread
class Listen extends Thread {

    public static int isEnabled = 1;
    public static Client ThisClient;
    public int pieces = 9;

    public void run() {

//        dizi[1] = new ImageIcon("/game/images/black.png");
        //soket bağlı olduğu sürece dön
        while (Client.socket.isConnected()) {
            try {
                //mesaj gelmesini bloking olarak dinyelen komut
                Message received = (Message) (sInput.readObject());

                //mesaj gelirse bu satıra geçer
                //mesaj tipine göre yapılacak işlemi ayır.
                switch (received.type) {
                    case Name:
                        break;
                    case RivalConnected:

                        String name = received.content.toString();
                        Game.ThisGame.txt_rival_name.setText(name);
                        Game.ThisGame.information.setText("OYUN BAŞLADI");
                        break;
                    case Text:
                        Game.ThisGame.txt_receive.setText(received.content.toString());
                        break;
                    case Selected:

                        if (received.isEnabled == true) {
                            for (int i = 0; i < Game.ThisGame.labels.size(); i++) {
                                if (Game.ThisGame.labels.get(i).getName().compareTo(((JLabel) received.content).getName()) == 0) {
                                    if (((JLabel) received.content).getIcon() == null) {
                                        Game.ThisGame.labels.get(i).setIcon(new ImageIcon(getClass().getResource("/game/images/black_1.png")));
                                        pieces--;
                                        for (int j = 0; j < Game.ThisGame.black.size(); j++) {
                                            if (Game.ThisGame.black.get(j) == Game.ThisGame.black.get(pieces)) {
                                                Game.ThisGame.black.get(j).setIcon(null);
                                            }
                                        }

                                        Game.ThisGame.labels.get(i).setEnabled(false);
                                        Game.ThisGame.labels.get(i).setBorder(BorderFactory.createMatteBorder(
                                                0, 60, 60, 0, new ImageIcon(getClass().getResource("/game/images/black_1.png"))));
                                        for (JLabel labels : Game.ThisGame.labels) {
                                            labels.setEnabled(true);
                                        }
                                        Game.ThisGame.information.setText("SIRA SİZDE");
                                        break;
                                    }
                                    if (((JLabel) received.content).getIcon() != null) {
                                        Game.ThisGame.labels.get(i).setIcon(null);
                                        Game.ThisGame.labels.get(i).setBorder(null);
                                        for (JLabel labels : Game.ThisGame.labels) {
                                            labels.setEnabled(true);
                                        }
                                        Game.ThisGame.information.setText("SIRA SİZDE");
                                        break;
                                    }
                                }
                            }
                        }
                        break;

                    case Disconnect:

                        break;

                    case Bitis:
                        break;

                }

            } catch (IOException ex) {

                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                //Client.Stop();
                break;
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                //Client.Stop();
                break;
            }
        }

    }

}

public class Client {

    //her clientın bir soketi olmalı
    public static Socket socket;

    //verileri almak için gerekli nesne
    public static ObjectInputStream sInput;
    //verileri göndermek için gerekli nesne
    public static ObjectOutputStream sOutput;
    //serverı dinleme thredi 
    public static Listen listenMe;

    public static void Start(String ip, int port) {
        try {
            // Client Soket nesnesi
            Client.socket = new Socket(ip, port);
            Client.Display("Servera bağlandı");
            // input stream
            Client.sInput = new ObjectInputStream(Client.socket.getInputStream());
            // output stream
            Client.sOutput = new ObjectOutputStream(Client.socket.getOutputStream());
            Client.listenMe = new Listen();
            Client.listenMe.start();

            //ilk mesaj olarak isim gönderiyorum
            Message msg = new Message(Message.Message_Type.Name);
            msg.content = Game.ThisGame.txt_name.getText();
            Client.Send(msg);
            Game.ThisGame.information.setText("RAKİP BEKLENİYOR. .");
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //client durdurma fonksiyonu
    public static void Stop() {
        try {
            if (Client.socket != null) {
                Client.listenMe.stop();
                Client.socket.close();
                Client.sOutput.flush();
                Client.sOutput.close();

                Client.sInput.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void Display(String msg) {

        System.out.println(msg);

    }

    //mesaj gönderme fonksiyonu
    public static void Send(Message msg) {
        try {
            Client.sOutput.writeObject(msg);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
