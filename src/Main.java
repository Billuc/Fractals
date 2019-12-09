import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Main extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int nbPanelsW = 3;
	int nbPanelsH = 2;
	
	JPanel contentPane;
	JPanel panelPane;
	FractalPanel[] panels;
	
	JPanel buttonPanel;
	JButton startButton;
	JLabel displayIters;
	JButton endButton;
	
	final int nbIterMax = 7;
	int nbIterAct = 0;
	
	Timer t;
	
	public Main() {
		super("Fractals");
		
		this.setLocationByPlatform(true);
		this.setSize((int) Math.min(500*nbPanelsW, Toolkit.getDefaultToolkit().getScreenSize().getWidth()),
				(int) Math.min(500*nbPanelsH, Toolkit.getDefaultToolkit().getScreenSize().getHeight()));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPane = new JPanel(new BorderLayout());
		
		panelPane = new JPanel(new GridLayout(nbPanelsH, nbPanelsW));
		panelPane.setBackground(Color.white);
		
		panels = new FractalPanel[nbPanelsW*nbPanelsH];
		
		panels[0] = new FractalPanel(new CustomFractal1());
		panels[1] = new FractalPanel(new Von_Koch_Snowflake());
		panels[2] = new FractalPanel(new Sierpensky_Triangle());
		panels[3] = new FractalPanel(new CustomFractal3());
		panels[4] = new FractalPanel(new CustomFractal4());
		
		for (JPanel pane : panels) {
			if (pane != null)
				panelPane.add(pane);
		}
		
		buttonPanel = new JPanel();
		
		startButton = new JButton("Start Animation");
		
		displayIters = new JLabel();
		displayIters.setText("Nombre d'iterations : " + nbIterAct + "/" + nbIterMax);
		
		endButton = new JButton("End Animation");
		startButton.addActionListener(this);
		endButton.addActionListener(this);
		
		buttonPanel.add(startButton);
		buttonPanel.add(displayIters);
		buttonPanel.add(endButton);
		
		contentPane.add(panelPane, BorderLayout.CENTER);
		contentPane.add(buttonPanel, BorderLayout.SOUTH);
		
		this.setContentPane(contentPane);
		this.setVisible(true);
		
		t = new Timer(1000, this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==(startButton)) {
			if (!t.isRunning()) {
				nbIterAct = 0;
				t.start();
			}
		}
		if (e.getSource()==(endButton)) {
			t.stop();
		}
		if (e.getSource()==t) {
			if (t.isRunning()) {
				anim();
				repaint();
				if (nbIterAct == nbIterMax) {
					t.stop();
				}
			}
		}
	}
	
	public void anim() {
		nbIterAct = (nbIterAct+1)%(nbIterMax+1);
		displayIters.setText("Nombre d'iterations : " + nbIterAct + "/" + nbIterMax);
		for (FractalPanel p : panels) {
			if (p != null)
				p.f.iterate();
		}
	}

	public static void main(String[] args) {
		new Main();
	}
}
