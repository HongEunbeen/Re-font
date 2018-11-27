import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Ranking extends JDialog{
	Container contentPane = new Container();
	FontDAO dao = new FontDAO();
	FontDTO dto = new FontDTO();	
	int[] data = new int[6]; // 차트의 값 저장배열
	int[] arcAngle = new int[6]; 
 
	Color[] color = {Color.RED, Color.BLUE, Color.MAGENTA, Color.ORANGE, Color.BLACK, Color.darkGray};
 
	String[] itemName = {"나눔고딕","나눔명조","나눔바른고딕","나눔바른펜","나눔스퀘어","나눔스퀘어라운드"};
	ChartPanel chartPanel = new ChartPanel(); // 차트패널
 
	public Ranking(){ // 생성자
		setTitle("아이돌 인기도");
		contentPane = getContentPane(); // 컨테이너 갯
		//contentPane.add(new InputPanel(), BorderLayout.NORTH);
		contentPane.add(chartPanel, BorderLayout.CENTER);
		setSize(800,400);
		setVisible(true);
		
		drawChart(); // 차트 메소드 호출
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
 
	void drawChart(){ // 차트를 그린다
		int sum=0; // 초기값 0
		for(int i = 0;  i < 6; i++) {
			dto = dao.getUser(itemName[i]);
			data[i] = dto.getLike();
		}
		
		for(int i=0;i<data.length;i++){ // 데이터 값만큼 루프
			sum+=data[i];
		}
		//
		if(sum == 0) 
			return;
 
		for(int i=0;i<data.length;i++){ 
			arcAngle[i] = (int)Math.round((double)data[i]/(double)sum*360);
			chartPanel.repaint(); // 차트패널의 PAINT호출
		}
	}
	class ChartPanel extends JPanel{ // 차트 표시 패널
 
		public void paintComponent(Graphics g){
 
			super.paintComponent(g);//부모 패인트호출
 
			int startAngle = 0;
 
			for(int i=0;i<data.length;i++){
				g.setColor(color[i]);
				g.drawString(itemName[i]+""+Math.round(arcAngle[i]*100/360)+"%", 50+i*100,20);
			}
 
			for(int i=0;i<data.length;i++){
				g.setColor(color[i]);
				g.fillArc(150,50,200,200,startAngle,arcAngle[i]);
				startAngle = startAngle + arcAngle[i];
			}
		}
}}