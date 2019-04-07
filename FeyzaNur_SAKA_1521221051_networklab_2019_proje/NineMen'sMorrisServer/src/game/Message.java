/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Button;
import java.awt.Color;
import javax.swing.JButton;

/**
 *
 * @author INSECT
 */

public class Message implements java.io.Serializable {
    //mesaj tipleri enum 
    public static enum Message_Type {None, Name, Disconnect,RivalConnected, Text, Selected, Bitis,Start}// bu tiplerin dışına çıkmasınlar diye
    //mesajın tipi
    public Message_Type type;
    //mesajın içeriği obje tipinde ki istenilen tip içerik yüklenebilsin
    public Object content;
      public Color color;
      public JButton button;
      public Boolean isEnabled;
    public Message(Message_Type t)
    {
        this.type=t;
    }
 

    
    
}

