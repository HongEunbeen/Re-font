import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MyFtpServer{
	MyFtpServer() throws IOException{
		ServerSocket serverSocket = new ServerSocket(5678);
		Socket clientSocket = serverSocket.accept();
	}
}
