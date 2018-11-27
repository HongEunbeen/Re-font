
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
 
public class ClientBackground {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private login gui;
    private FontPage FontGui;
    private String msg;
    private String nickName;
 
    public void setGui(login gui) {
        this.gui = gui;
    }
    public void setGui(FontPage FontGui) {
        this.FontGui = FontGui;
    }
    
    public static void main(String[] args) {
        ClientBackground clientBackground = new ClientBackground();
        clientBackground.connect();
    }
    //클라이언트 접속이 완료되면 
    public void connect(){
        try {
        	socket = new Socket("127.0.0.1", 1232);
        	System.out.println("서버에 연결됨"); 	  
	  		out = new DataOutputStream(socket.getOutputStream());  
	        //접속하자마자 닉네임 전송하면, 서버가 닉네임으로 인식 
	        out.writeUTF(nickName);
	        System.out.println("클라이언트 : 닉네임 전송완료 ");
  
        } catch (IOException e) {
            e.printStackTrace();
        }
    }  
    public void setNickname(String nickName){
        this.nickName = nickName;
    }

}