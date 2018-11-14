import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

public class Introduce extends JPanel{
	JLabel Image_Introduce_home, Introcude_MainText;
	Introduce(){
		setLayout(null);
		setBounds(14, 90, 377, 234);
		setLayout(null);
		Image_Introduce_home = new JLabel(new ImageIcon("image/Introduce_home.jpg"));
		Image_Introduce_home.setBounds(218, 31, 315, 168);
		add(Image_Introduce_home);
		
		Introcude_MainText = new JLabel("WELCOM MY HOMEPAGE BY EUNBIN");
		Introcude_MainText.setBounds(218, 211, 315, 67);
		add(Introcude_MainText);
		JTextPane textPane = new JTextPane();
		textPane.setBounds(228, 279, 305, 94);
		add(textPane);
	}
}
class Introduce_my extends JPanel{
	Introduce_my(){
		
	}
}

