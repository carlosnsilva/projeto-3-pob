package aplicacaoSwing;
/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Pesist~encia de Objetos
 * Prof. Fausto Maranhão Ayres
 **********************************/

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;

import fachada.Fachada;

public class TelaPrincipal {
	private JFrame frame;
	private JMenu mnPessoa;
	private JMenu mnTelefone;
	private JMenu mnReuniao;
	private JMenuItem mntmListarPessoa;
	private JMenuItem mntmListarTelefone;
	private JMenuItem mntmListarReuniao;
	private JMenuItem mntmCriarReuniao;
	private JMenuItem mntmCriarTelefone;
	private JMenuItem mntmEditarPessoa;
	private JLabel label;
	private ImageIcon imagem;		//label de fundo


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
				Fachada.inicializar();
				
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				label.setIcon(imagem);
			}
			@Override
			public void windowClosing(WindowEvent e) {
				Fachada.finalizar();
				//		JOptionPane.showMessageDialog(frame, "banco fechado !");
			}
		});
		frame.setTitle("Agenda");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		label = new JLabel("");
		label.setFont(new Font("Tahoma", Font.PLAIN, 26));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setText("Inicializando...");
		label.setBounds(0, 0, 450, 249);
		imagem = new ImageIcon(getClass().getResource("/imagens/agenda.jpg"));
		imagem = new ImageIcon(imagem.getImage().getScaledInstance(label.getWidth(),label.getHeight(), Image.SCALE_DEFAULT));//		label.setIcon(fotos);
		frame.getContentPane().add(label);
		frame.setResizable(false);


		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		mnPessoa = new JMenu("Pessoa");
		menuBar.add(mnPessoa);

		mntmEditarPessoa = new JMenuItem("Editar");
		mntmEditarPessoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaEditarPessoa tela = new TelaEditarPessoa();
			}
		});
		mnPessoa.add(mntmEditarPessoa);

		mntmListarPessoa = new JMenuItem("Listar");
		mntmListarPessoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaListarPessoa tela = new TelaListarPessoa();
			}
		});
		mnPessoa.add(mntmListarPessoa);

		//-----------------------------------------------------------------
		mnTelefone = new JMenu("Telefone");
		menuBar.add(mnTelefone);

		mntmListarTelefone = new JMenuItem("Listar");
		mntmListarTelefone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaListarTelefone tela = new TelaListarTelefone();
			}
		});

		mntmCriarTelefone = new JMenuItem("Criar");
		mnTelefone.add(mntmCriarTelefone);
		mntmCriarTelefone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaCriarTelefone tela = new TelaCriarTelefone("");
			}
		});
		mnTelefone.add(mntmListarTelefone);

		//-----------------------------------------------------------------
		mnReuniao = new JMenu("Reuniao");
		menuBar.add(mnReuniao);

		mntmListarReuniao = new JMenuItem("Listar");
		mntmListarReuniao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaListarReuniao tela = new TelaListarReuniao();
			}
		});

		mntmCriarReuniao = new JMenuItem("Criar");
		mnReuniao.add(mntmCriarReuniao);
		mntmCriarReuniao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCriarReuniao tela = new TelaCriarReuniao("");
			}
		});
		mnReuniao.add(mntmListarReuniao);
	}
}
