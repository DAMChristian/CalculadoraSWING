import javax.swing.WindowConstants;

public class main {

	public static void main(String[] args) {
		CalculadoraSwing app = new CalculadoraSwing();
		app.setVisible(true);
		app.setSize(350, 520);
		app.setResizable(false);
		app.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		app.setTitle("CalculadoraSWING");
	}

}
