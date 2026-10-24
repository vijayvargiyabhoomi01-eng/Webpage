
import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(5000);
        System.out.println("Server is waiting for client...");

        Socket s = ss.accept();
        System.out.println("Client connected.");

        DataInputStream in = new DataInputStream(s.getInputStream());
        DataOutputStream out = new DataOutputStream(s.getOutputStream());

        String msg = in.readUTF();
        System.out.println("Client says: " + msg);

        out.writeUTF("Hello Client, message received!");

        in.close();
        out.close();
        s.close();
        ss.close();
    }
}
