package aplicacaoSwing;


import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import fachada.Fachada;

public class TelaPrincipal {

	private JFrame frame;
	private JMenu mnVideo;
	private JMenu mnVisualizacao;
	private JMenu mnConsulta;
	private JMenuItem mntmCadastrarVideo;
	private JMenuItem mntmListarVideo;
	private JMenuItem mntmRegistrarVisualizacao;
	private JMenuItem mntmApagarVisualizacao;
	private JMenuItem mntmListarVisualizacao;
	private JLabel label;
	private ImageIcon imagem;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal window = new TelaPrincipal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();

		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

				Fachada.iniciar();

				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				label.setIcon(imagem);
			}
			@Override
			public void windowClosing(WindowEvent e) {
				Fachada.finalizar();
				JOptionPane.showMessageDialog(frame, "banco fechado !");
			}
		});
		frame.setTitle("Repy");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		label = new JLabel("");
		label.setFont(new Font("Tahoma", Font.PLAIN, 26));
		label.setHorizontalAlignment(SwingConstants.CENTER);
	//	label.setText("Inicializando...");
		label.setBounds(0, 0, frame.getWidth(), frame.getHeight());
	//	imagem = new ImageIcon(getClass().getResource("/imagens/imagem.jpg"));
	//	imagem = new ImageIcon(imagem.getImage().getScaledInstance(label.getWidth(),label.getHeight(), Image.SCALE_DEFAULT));
	//	label.setIcon(imagem);
		frame.getContentPane().add(label);
		frame.setResizable(false);
		
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		mnVideo = new JMenu("Video");
		menuBar.add(mnVideo);

		mntmCadastrarVideo = new JMenuItem("Cadastrar");
		mntmCadastrarVideo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaCadastrarVideo tela = new TelaCadastrarVideo();
			}
		});
		mnVideo.add(mntmCadastrarVideo);
		
		mntmListarVideo = new JMenuItem("Listar");
		mntmListarVideo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaListar tela = new TelaListar();
			}
		});
		mnVideo.add(mntmListarVideo);  
		
		//-----------------------------------------------------------------
		
		mnVisualizacao = new JMenu("Visualizacao");
		menuBar.add(mnVisualizacao);
		mntmRegistrarVisualizacao = new JMenuItem("Registrar");
		mntmRegistrarVisualizacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaRegistrarView tela = new TelaRegistrarView();
			}
		});
		mnVisualizacao.add(mntmRegistrarVisualizacao);
		
		mntmApagarVisualizacao = new JMenuItem("Apagar");
		mntmApagarVisualizacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaApagarView tela = new TelaApagarView();
			}
		});
		mnVisualizacao.add(mntmApagarVisualizacao);
		
		mntmListarVisualizacao = new JMenuItem("Listar");
		mntmListarVisualizacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaListar tela = new TelaListar();
			}
		});
		mnVisualizacao.add(mntmListarVisualizacao);
		
		//-----------------------------------------------------------------
		
		mnConsulta = new JMenu("Consultas");
		menuBar.add(mnConsulta);
		mnConsulta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				TelaConsultar tela = new TelaConsultar();
			}
		});
	}
}
