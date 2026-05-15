package m3;

import java.awt.Color;

import javax.swing.JFrame;

public class VentanaInicio extends JFrame {
	public static void main(String[] args) {
		new VentanaInicio();
	}
	
	public VentanaInicio() {
		super();
		setLayout(null);
		setBounds(300,100,700,435);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Dominion");
		setResizable(false);

		PanelInicio panelInicio = new PanelInicio();
		panelInicio.setBounds(0, 0, 700, 400);
		panelInicio.setBackground(Color.ORANGE);
		
		add(panelInicio);
		
		setVisible(true);
	}

}
