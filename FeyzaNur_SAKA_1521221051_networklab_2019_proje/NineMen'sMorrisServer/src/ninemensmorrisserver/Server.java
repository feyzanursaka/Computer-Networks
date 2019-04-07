/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ninemensmorrisserver;

import game.Message;
import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author INSECT
 */
//client gelişini dinleme threadi
class ServerThread extends Thread {

    public void run() {
        //server kapanana kadar dinle
        while (!Server.serverSocket.isClosed()) {
            try {
                Server.Display("Client Bekleniyor...");
                // clienti bekleyen satır
                //bir client gelene kadar bekler
                Socket clientSocket = Server.serverSocket.accept();//burdan soket dönüyo
                //client gelirse bu satıra geçer
                Server.Display("Client Geldi...");
                //gelen client soketinden bir sclient nesnesi oluştur
                //bir adet id de kendimiz verdik
                SClient nclient = new SClient(clientSocket, Server.IdClient);
                
                Server.IdClient++;//her clienta farklı bir id vermiş oluyo
                //clienti listeye ekle.
                Server.Clients.add(nclient);//listeye ekledim
                //client mesaj dinlemesini başlat
                nclient.listenThread.start();//dinlemeye başlıyo client mesaj attığında server anlasın her clientın listen threadı var

            } catch (IOException ex) {
                Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

public class Server {

    //server soketi eklemeliyiz
    public static ServerSocket serverSocket;
    public static int IdClient = 0;
    // Serverın dileyeceği port
    public static int port = 0;
    //Serverı sürekli dinlemede tutacak thread nesnesi
    public static ServerThread runThread;
    //public static PairingThread pairThread;

    public static ArrayList<SClient> Clients = new ArrayList<>();

    //semafor nesnesi
    public static Semaphore pairTwo = new Semaphore(1, true);//lock mekanizması birden çok client kritik bölgeye gircek çünkü

    // başlaşmak için sadece port numarası veriyoruz
    public static void Start(int openport) {
        try {
            Server.port = openport;//port veriyorum
            Server.serverSocket = new ServerSocket(Server.port);//port nodan server soketi oluşturuyorum
//kendi ipsini biliyo ona bağlananın ipsini öğrencek
            Server.runThread = new ServerThread();
            Server.runThread.start();

        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void Display(String msg) {

        System.out.println(msg);

    }

    // serverdan clietlara mesaj gönderme
    //clieti alıyor ve mesaj olluyor
    public static void Send(SClient cl, Message msg) {

        try {
            cl.sOutput.writeObject(msg);
        } catch (IOException ex) {
            Logger.getLogger(SClient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
