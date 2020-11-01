import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

import static java.lang.System.exit;

public class Client {
    Controller controller;

    public static void main(String[] args) {
        Client gui = new Client();
        gui.go();
    }

    public void go() {
        try {
            Scanner obj = new Scanner(System.in);
            System.out.println("\nPlease enter the server Ip to begin the game (type 'home' for 127.0.0.1, or make sure you enter it correctly)");
            System.out.print("> ");
            String ip = obj.nextLine();
            if (ip.equals("home")) {
                ip = "127.0.0.1";
            }
            controller = new Controller();
            controller.sock = new Socket(ip, 5000);
        }catch (SocketException e) {
            exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
