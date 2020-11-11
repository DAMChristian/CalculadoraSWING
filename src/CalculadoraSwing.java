import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class CalculadoraSwing extends JFrame {
	
	private String[] botones = {" "," ","←","÷","7","8","9","x","4","5","6","-","1","2","3","+","CE","0",",","="};
	
	private JButton[] teclasNumericas;
	
	private JTextField texto;
	
	private JPanel panelTexto;
	
	private JPanel panelTeclas;
	
	private JPanel panelPrincipal;
	
	private int resultado;
	
	private boolean suma;
	
	private boolean resta;

	private boolean multiplicacion;
	
	private boolean division;
	
	// Actualmente solo hace operaciones de una en una tienes que pulsar igual para cambiar de operacion
	public CalculadoraSwing() {
		inicializarBotones();
		resultado = 0;
		suma = false;
		resta = false;
		multiplicacion = false;
		division = false;
	}
	
	private void inicializarBotones() {
		panelPrincipal = new JPanel(new BorderLayout());
		this.add(panelPrincipal);
		
		panelTexto = new JPanel(new BorderLayout());
		panelTexto.setPreferredSize(new Dimension(320, 75));
		texto = new JTextField("0", 9);
		panelTexto.add(texto, BorderLayout.CENTER);
		panelPrincipal.add(panelTexto, BorderLayout.NORTH);
		
		panelTeclas = new JPanel(new GridLayout(5, 4));
		panelPrincipal.add(panelTeclas, BorderLayout.CENTER);
		
		teclasNumericas = new JButton[botones.length];
		
		for (int i = 0; i < botones.length; i++) {
			teclasNumericas[i] = new JButton(botones[i]);
			//teclasNumericas[i].setContentAreaFilled(false);
			teclasNumericas[i].setBackground(new Color(31, 31, 31));
			teclasNumericas[i].setForeground(new Color(255, 255, 255));
			teclasNumericas[i].setFont(new Font("Microsoft JhengHei UI", 1, 36));
			teclasNumericas[i].setBorder(null);
			teclasNumericas[i].setFocusable(false);
		}
	
		for (JButton boton : teclasNumericas) {
			boton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					accionBoton(boton);
				}
			});
			eventoCambioColor(boton);
			panelTeclas.add(boton);
		}
		
		//Pruebas de aqui para abajo de visualizacion
		panelTexto.setBackground(new Color(31, 31, 31));
		panelTeclas.setBackground(new Color(31, 31, 31));
		
		//texto.setBackground(new Color(31, 31, 31));
		texto.setBackground(null);
		texto.setForeground(new Color(255, 255, 255));
		texto.setHorizontalAlignment(JTextField.RIGHT);
		texto.setFont(new Font("Microsoft JhengHei UI", 1, 45));
		texto.setBorder(null);
		
		setTitle("Calculadora");
		
		// Hasta aqui
	}
	
	// Donde se le aplica cada opcion al boton
	private void accionBoton(JButton boton) {
		String aCompararString = "0123456789";
		if (aCompararString.contains(boton.getText())) {
			numeros(boton);
		}
		else if (boton.getText().equals("←")) {
			borrar();
		}
		else if (boton.getText().equals("÷")) {
			dividir();
		}
		else if (boton.getText().equals("X")) {
			multiplicar();
		}
		else if (boton.getText().equals("-")) {
			restar();
		}
		else if (boton.getText().equals("+")) {
			suma();
		}
		else {
			igual();
		}
	}
	
	// Introduccion de numeros en el display(texto)
	private void numeros(JButton boton) {		
		if (texto.getText().equals("0")) {
			texto.setText(boton.getText());
		}
		else {
			texto.setText(texto.getText() + boton.getText());
		}
	}
	
	// Borrar de uno en uno
	private void borrar() {
		if (texto.getText().length() > 0) {
			if (texto.getText().length() == 1) {
				texto.setText("0");
			}
			else {
				texto.setText(texto.getText().substring(0, texto.getText().length() - 1));
			}
		}
	}
	
	// Division
	private void dividir() {
		division = true;
		resultado = Integer.parseInt(texto.getText());
		texto.setText("0");
	}
	
	// Multiplicacion
	private void multiplicar() {
		multiplicacion = true;
		resultado = Integer.parseInt(texto.getText());
		texto.setText("0");
	}
	
	// Resta
	private void restar() {
		resta = true;
		resultado = Integer.parseInt(texto.getText());
		texto.setText("0");
	}
	
	// Suma
	private void suma() {
		suma = true;
		resultado = Integer.parseInt(texto.getText());
		texto.setText("0");
	}
	
	// Al presionar el boton IGUAL
	private void igual() {
		if (suma) {
			resultado += Integer.parseInt(texto.getText());
			texto.setText(Integer.toString(resultado));
			suma = false;
		}
		else if (resta) {
			resultado -= Integer.parseInt(texto.getText());
			texto.setText(Integer.toString(resultado));
			resta = false;
		}
		else if (multiplicacion) {
			resultado *= Integer.parseInt(texto.getText());
			texto.setText(Integer.toString(resultado));
			multiplicacion = false;
		}
		else if (division) {
			resultado /= Integer.parseInt(texto.getText());
			texto.setText(Integer.toString(resultado));
			division = false;
		}
	}
	
	private void eventoCambioColor(JButton boton) {
		boton.addMouseListener(new MouseAdapter() {
	         public void mouseEntered(MouseEvent evt) {
	        	boton.setBackground(new Color(255, 87, 51));
	          }
	          public void mouseExited(MouseEvent evt) {
	        	  boton.setBackground(new Color(31, 31, 31));
	          }
		});
	}
	
}	
