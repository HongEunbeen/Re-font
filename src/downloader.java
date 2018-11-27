

import java.util.regex.Matcher;

import java.util.regex.Pattern;

import java.io.*;
import java.net.URL;

public class downloader {
	public static int num;
	public static String font;
	downloader(int num){		
		this.num = num;
		 try {
	            URL u = new URL("https://www.naver.com");
	            switch(num) {
		            case 1: font = "NanumGothic.zip"; break;
		            case 2: font = "NanumMyeongjo.zip"; break;
		            case 3: font = "NanumBarunGothic.zip"; break;
		            case 4: font = "NanumBarunpen.zip"; break;
		            case 5: font = "NanumSquare.zip"; break;
		            case 6: font = "NanumSquareRound.zip"; break;
	            }
	            File filePath = new File("file\\"+font);
				File fileDir = filePath.getParentFile();
				filePath.mkdirs();
				FileOutputStream fos = new FileOutputStream("C:\\Users\\Public\\Downloads\\"+font);
				InputStream is = u.openStream();
				byte[] buf = new byte[1024];
				double len = 0;
				double tmp = 0;
				double percent = 0;
				while ((len = is.read(buf)) > 0) {
					fos.write(buf, 0, (int) len);
				}
				fos.close();
				is.close();
				Runtime rt = Runtime.getRuntime();
				System.out.println("다운로드 완료\r\n폴더를 띄워드립니다.");
				rt.exec("explorer.exe C:\\Users\\Public\\Downloads\\");
	        }catch (Exception e) {
	            System.out.println("다운로드 오류입니다. 나중에 다시 받아보세요.");
	        }
	}
}

