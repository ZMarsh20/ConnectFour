import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Server {
    boolean flip;
    PrintWriter write;
    PrintWriter write2;
    ServerSocket client;
    Socket conn = null;
    Socket conn2 = null;
    InputStreamReader read;
    BufferedReader reader;
    InputStreamReader read2;
    BufferedReader reader2;

    public static void main(String[] args) {
        Server starter = new Server();
        starter.go();
    }

    public Server() {
        try {
            client = new ServerSocket(5000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void go() {
        while (true) {
            try {
                conn = client.accept();
                conn2 = client.accept();

                write = new PrintWriter(conn.getOutputStream());
                write.println("1");
                write.flush();

                write2 = new PrintWriter(conn2.getOutputStream());
                write2.println("-1");
                write2.flush();

                read = new InputStreamReader(conn.getInputStream());
                reader = new BufferedReader(read);

                read2 = new InputStreamReader(conn2.getInputStream());
                reader2 = new BufferedReader(read2);

                String line;
                String line2;
                flip = true;
                boolean Connection = true;
                while (Connection) {

                    if (flip) {
                        flip =false;
                        if ((line = reader.readLine()) == null) {
                            Connection = false;
                        } else {
                            int input = Integer.parseInt(line);
                            write = new PrintWriter(conn2.getOutputStream());
                            write.println(input);
                            write.flush();
                        }
                    } else {
                        flip = true;
                        if ((line2 = reader2.readLine()) == null) {
                            Connection = false;
                        } else {
                            int input2 = Integer.parseInt(line2);
                            write2 = new PrintWriter(conn.getOutputStream());
                            write2.println(input2);
                            write2.flush();
                        }
                    }
                }
            } catch (SocketException e) {
                while(true){
                    write.println("-1");
                    write2.println("-1");
                    write.flush();
                    write2.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
