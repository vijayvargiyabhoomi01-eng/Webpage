
import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws Exception {
        Socket s = new Socket("localhost", 5000);

        DataOutputStream out = new DataOutputStream(s.getOutputStream());
        DataInputStream in = new DataInputStream(s.getInputStream());

        out.writeUTF("Hello Server!");

        String reply = in.readUTF();
        System.out.println("Server says: " + reply);

        out.close();
        in.close();
        s.close();
    }
}
