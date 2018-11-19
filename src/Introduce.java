import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

public class Introduce extends JPanel{
	JLabel Image_Introduce_home, Introcude_MainText,Introcude_SubText1,Introcude_SubText2;
	Introduce(){
		setLayout(null);
		setBounds(14, 90, 377, 234);
		Image_Introduce_home = new JLabel(new ImageIcon("image\\Introduce_home.jpg"));
		Image_Introduce_home.setBounds(218, 31, 315, 168);
		add(Image_Introduce_home);
		
		Introcude_MainText = new JLabel("WELCOM MY HOMEPAGE BY EUNBIN");
		Introcude_MainText.setBounds(218, 211, 315, 67);
		add(Introcude_MainText);
		Introcude_SubText1 = new JLabel("This is my second grade java project!!");
		Introcude_SubText1.setBounds(228, 250, 305, 94);
		add(Introcude_SubText1);
		Introcude_SubText2 = new JLabel("please dont shut me,......xD");
		Introcude_SubText2.setBounds(228, 270, 305, 94);
		add(Introcude_SubText2);
	}
}
class Introduce_my extends JPanel{
	JLabel Image_introduce_myself, introduce_Text;
	String text[] = {"HONG EUN BEEN","MIRIM MASTER HIGH SCHOOL SUTDENT","SECOND GRADE", "DEVELOPER"};
	int h = 360;
	int hp = 20;
	Introduce_my(){
		setLayout(null);
		setBounds(14, 90, 377, 234);
		Image_introduce_myself = new JLabel(new ImageIcon("image\\hongeunbeen.PNG"));
		Image_introduce_myself.setBounds(218, 31, 315, 300);
		add(Image_introduce_myself);
		for(int i = 0 ; i < 4; i ++) {
			introduce_Text = new JLabel(text[i]);
			introduce_Text.setBounds(218, (h+=hp), 315, 67);
			add(introduce_Text);
		}
		
	}
}

