
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
 
public class ServerBackground {
	static String currentNick;
    private ServerSocket serverSocket; // 서버 소켓
    private Socket socket; // 받아올 소켓 저장
    /** XXX 03. 사용자들의 정보를 저장하는 맵 */ 
    private Map<String, DataOutputStream> clientMap = new HashMap<String, DataOutputStream>();
    public static void main(String[] args) {
        ServerBackground serverBackground = new ServerBackground();
        serverBackground.setting();
    }
    //서버가 생성될때 셋팅해주는 함수 
    public void setting() {
        try { 
            serverSocket = new ServerSocket(1232);
            while (true) {
                /** XXX 01.서버가 할일 : 방문자를 계속 받아서, 쓰레드 리시버를 계속 생성 */
                System.out.println("대기중.....");
                socket = serverSocket.accept(); // 여기서 클라이언트 받음
                System.out.println(socket.getInetAddress() + "에서 접속했습니다.");
                //여기서 새로운 사용자 스레드 클래스를 생성해서 소켓 정보를 넣어줘야한다.
                Receiver receiver = new Receiver(socket);
                receiver.start();
            }
 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    //맵의내용(클라이언트) 저장과 삭제 
    public void addClient(String nick,  DataOutputStream out) throws IOException{
    	currentNick = nick;
    	System.out.println(nick + "님이 접속하셨습니다.\n");
    	clientMap.put(nick, out);
        
    }
    
    public void removeClient(String nick){
    	System.out.println( nick + "님이 나가셨습니다. \n");
    	clientMap.remove(nick);
    }
    public void sendMessage (String msg){
        Iterator<String> iterator = clientMap.keySet().iterator(); //key셋으로 반복자지정
        String key = "";
        
        while(iterator.hasNext()){
            key = iterator.next();// 반복자에서 하나하나 키를 빼온다.
            try{
                clientMap.get(key).writeUTF(msg);
            } catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    // ------------------리시버---------------------------
    class Receiver extends Thread {
        /** XXX 리시버가 할일 : 네트워크 소켓을 받아서 계속듣고 보내는 일. */
        private DataInputStream din; // 데이터 입력 스트림
        private DataInputStream in; // 데이터 입력 스트림
        private DataOutputStream out;

        private String nick;
        public Receiver(Socket socket) {
            try {
            	out = new  DataOutputStream(socket.getOutputStream());
                din = new DataInputStream(socket.getInputStream());
                in = new DataInputStream(socket.getInputStream());
                nick = din.readUTF();
                addClient(nick,out);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
       
        @Override
        public void run() {
        	System.out.println("쓰레드 시작");
            try {
            	 OutputStream out = socket.getOutputStream(); 
                 InputStream fin = null;
//            	if(in != null) {
//            		switch(in.readInt()) {
//                 	case 1: fin = new FileInputStream("file\\NanumGothic.zip"); // 파일 경로
//                 	case 2: fin = new FileInputStream("file\\NanumMyeongjo.zip");
//                 	case 3: fin = new FileInputStream("file\\NanumBarunGothic.zip");
//                 	case 4: fin = new FileInputStream("file\\NanumBarunpen.zip");
//                 	case 5: fin = new FileInputStream("file\\NanumSquare.zip");
//                 	case 6: fin = new FileInputStream("file\\NanumSquareRound.zip");
//                 }	
//            	}
//            
//           	 while(true){		  
//           		   int data = fin.read();		   
//           		   out.write(data);		   
//           		   if(data == -1){
//           			   break;
//           		   }  		   
//           	  }
//           	  fin.close();		  
//           	  //flush
//           	  out.flush();
//           	  //close
//           	  out.close();
            } catch (Exception e) {
                //사용접속종료시 여기서 에러발생. 
                removeClient(nick);
            }
        }
    }
 
}