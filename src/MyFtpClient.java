import java.io.*;

import com.oroinc.net.ftp.*;
import com.oroinc.net.*;

public class MyFtpClient {
    static String server = "xxxxx";
    static int port = 21;
    static String id = "xxxxx";
    static String password = "xxxxx";
    FTPClient ftpClient;

    public MyFtpClient(String server, int port, String id, String password) {
        this.server = server;
        this.port = port;
        ftpClient = new FTPClient();
    }

    public static void main(String args[]) {
        MyFtpClient ftp = new MyFtpClient(server, port, id, password);
        ftp.connect();
        ftp.login(ftp.id, ftp.password);
        // 로그파일이 있는 디렉토리로 이동한다
        ftp.cd("/home/ems/emsprj/project/wos/WosLog/log");
        FTPFile[] files = ftp.list();
        for (int i = 0; i < files.length ; i++) {
            String fileName = files[i].getName();
            // 파일 이름에서 확장자만 추출
            String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
            long size = files[i].getSize();
            // 파일 사이즈가 0보다 크고 로그 파일만 가져온다
            if ( (size > 0) && (extension.equalsIgnoreCase("log")) ) {
                File file = ftp.get(fileName, fileName);
            }
        }
        ftp.logout();
        ftp.disconnect();
        System.exit(1);
    }

    // 계정과 패스워드로 로그인
    public boolean login(String user, String password) {
        try {
            this.connect();
            return ftpClient.login(user, password);
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return false;
    }

    // 서버로부터 로그아웃
    private boolean logout() {
        try {
            return ftpClient.logout();
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return false;
    }

    // 서버로 연결
    public void connect() {
        try {
            ftpClient.connect(server, port);
            int reply;
            // 연결 시도후, 성공했는지 응답 코드 확인
            reply = ftpClient.getReplyCode();
            if(!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                System.err.println("서버로부터 연결을 거부당했습니다");
                System.exit(1);
            }
        }
        catch (IOException ioe) {
            if(ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch(IOException f) {
                    //
                }
            }
            System.err.println("서버에 연결할 수 없습니다");
            System.exit(1);
        }
    }

    // FTP의 ls 명령, 모든 파일 리스트를 가져온다
    public FTPFile[] list() {
        FTPFile[] files = null;
        try {
            files = this.ftpClient.listFiles();
            return files;
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return null;
    }

    // 파일을 전송 받는다
    public File get(String source, String target) {
        OutputStream output = null;
        try {
            File local = new File(source);
            output = new FileOutputStream(local);
        }
        catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
        File file = new File(source);
        try {
            if (ftpClient.retrieveFile(source, output)) {
                return file;
            }
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return null;
    }

    // 서버 디렉토리 이동
    public void cd(String path) {
        try {
            ftpClient.changeWorkingDirectory(path);
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    // 서버로부터 연결을 닫는다
    private void disconnect() {
        try {
            ftpClient.disconnect();
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

}
