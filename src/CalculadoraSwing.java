import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class CalculadoraSwing extends JFrame {
	
	//Declaracion de variables	
	private String[] botones = {" "," ","←","÷","7","8","9","x","4","5","6","-","1","2","3","+","CE","0",",","="};
	
	private JButton[] teclasNumericas;
	
	private JTextField display;
	
	private JPanel panelTexto;
	
	private JPanel panelTeclas;
	
	private JPanel panelPrincipal;
	
	//private int resultado;
	
	private double resultado;
	
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
	
	// Metodo que inicializa todo el apartado grafico y se les aplica estilos y eventos
	private void inicializarBotones() {
		//Panel principal que ocupara todo el JFRAME
		panelPrincipal = new JPanel(new BorderLayout());
		this.add(panelPrincipal);
		
		//Panel que contiene el display situado en la parte norte
		panelTexto = new JPanel(new BorderLayout());
		panelTexto.setPreferredSize(new Dimension(320, 75));
		display = new JTextField("0", 9);
		panelTexto.add(display, BorderLayout.CENTER);
		panelPrincipal.add(panelTexto, BorderLayout.NORTH);
		
		//panel que contiene los botones
		panelTeclas = new JPanel(new GridLayout(5, 4));
		panelPrincipal.add(panelTeclas, BorderLayout.CENTER);
		
		//Creacion de los botones y se les aplica el estilo
		teclasNumericas = new JButton[botones.length];
		
		for (int i = 0; i < botones.length; i++) {
			teclasNumericas[i] = new JButton(botones[i]);
			teclasNumericas[i].setBackground(new Color(31, 31, 31));
			teclasNumericas[i].setForeground(new Color(255, 255, 255));
			teclasNumericas[i].setFont(new Font("Microsoft JhengHei UI", 1, 36));
			teclasNumericas[i].setBorder(null);
			teclasNumericas[i].setFocusable(false);
		}
		
		//Se aplican los eventos
		for (JButton boton : teclasNumericas) {
			boton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					accionBoton(boton);
				}
			});
			eventoCambioColor(boton);
			panelTeclas.add(boton);
		}
		
		//Estilos a los paneles
		panelTexto.setBackground(new Color(31, 31, 31));
		panelTeclas.setBackground(new Color(31, 31, 31));
		
		//Estilo al display
		display.setBackground(null);
		display.setForeground(new Color(255, 255, 255));
		display.setHorizontalAlignment(JTextField.RIGHT);
		display.setFont(new Font("Microsoft JhengHei UI", 1, 45));
		display.setBorder(null);
	}
	
	// Metodo que filtra la funcion que debe hacer cada boton
	private void accionBoton(JButton boton) {
		String aCompararString = "0123456789";
		if (aCompararString.contains(boton.getText())) {
			numeros(boton);
		}
		else if (boton.getText().equals("←")) {
			borrar();
		}
		else if (boton.getText().equals("CE")) {
			borrarTodo();
		}
		else if (boton.getText().equals("÷")) {
			dividir();
		}
		else if (boton.getText().equals("x")) {
			multiplicar();
		}
		else if (boton.getText().equals("-")) {
			restar();
		}
		else if (boton.getText().equals("+")) {
			suma();
		}
		else if (boton.getText().equals(",")) {
			addComa();
		}
		else {
			igual();
		}
	}
	
	// Introduccion de numeros en el display
	private void numeros(JButton boton) {		
		if (display.getText().equals("0")) {
			display.setText(boton.getText());
		}
		else {
			display.setText(display.getText() + boton.getText());
		}
	}
	
	//Te permite añadir una coma para hacer operaciones con numeros decimales
	private void addComa() {
		if (!display.getText().contains(",")) {
			display.setText(display.getText() + ",");
		}
	}
	
	// Borrar de uno en uno
	private void borrar() {
		if (display.getText().length() > 0) {
			if (display.getText().length() == 1) {
				display.setText("0");
			}
			else {
				display.setText(display.getText().substring(0, display.getText().length() - 1));
			}
		}
	}
	
	//Te permite borrar todo el display de botones y de la operacion guardada
	public void borrarTodo() {
		display.setText("0");
		resultado = 0;
	}
	
	// Guarda el numero en la variable resultado
	private void guardarNumero() {
		resultado = Double.parseDouble(display.getText().replaceAll(",", "."));
		display.setText("0");
	}
	
	// Division
	private void dividir() {
		division = true;
		guardarNumero();
	}
	
	// Multiplicacion
	private void multiplicar() {
		multiplicacion = true;
		guardarNumero();
	}
	
	// Resta
	private void restar() {
		resta = true;
		guardarNumero();
	}
	
	// Suma
	private void suma() {
		suma = true;
		guardarNumero();
	}
	
	// Al presionar el boton IGUAL
	private void igual() {
		if (suma) {
			resultado += Double.parseDouble(display.getText().replaceAll(",", "."));
			suma = false;
		}
		else if (resta) {
			resultado -= Double.parseDouble(display.getText().replaceAll(",", "."));
			resta = false;
		}
		else if (multiplicacion) {
			resultado *= Double.parseDouble(display.getText().replaceAll(",", "."));
			multiplicacion = false;
		}
		else if (division) {
			resultado /= Double.parseDouble(display.getText().replaceAll(",", "."));
			division = false;
		}
		display.setText(Double.toString(resultado).replaceAll(".", ","));		
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
