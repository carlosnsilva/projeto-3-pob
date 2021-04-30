/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Pesist~encia de Objetos
 * Prof. Fausto Maranhão Ayres
 **********************************/
package aplicacaoSwing;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import fachada.Fachada;

public class TelaCriarReuniao {
	private JFrame frame;
	private JButton button;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel label_5;
	private JLabel label_6;
	private JLabel label_7;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextArea textArea;
	private JScrollPane scrollPane;


	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TelaCriarTelefone window = new TelaCriarTelefone();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public TelaCriarReuniao(String nome) {
		initialize();
		textArea.append(nome + "");	//inicializa com o nome da pessoa
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setFont(new Font("Dialog", Font.PLAIN, 12));
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 12));
		frame.setTitle("Criar reuniao");
		frame.setBounds(100, 100, 421, 205);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		label_2 = new JLabel("");
		label_2.setBounds(28, 137, 367, 14);
		frame.getContentPane().add(label_2);
		
		button = new JButton("Cadastrar");
		button.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String data = textField.getText();
					String hora = textField_1.getText();
					String assunto = textField_2.getText();
					String nomes[] = textArea.getText().split("\n");
					Fachada.criarReuniao(data+" "+hora, assunto,  nomes);
					label_2.setText("cadastro realizado");
				}
				catch(Exception e) {
					label_2.setText(e.getMessage());
				}
			}
		});
		button.setBounds(145, 103, 108, 23);
		frame.getContentPane().add(button);
		
		label_3 = new JLabel("data");
		label_3.setFont(new Font("Dialog", Font.PLAIN, 12));
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setBounds(10, 17, 71, 14);
		frame.getContentPane().add(label_3);
		
		textField = new JTextField();
		textField.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField.setColumns(10);
		textField.setBounds(91, 14, 86, 20);
		frame.getContentPane().add(textField);
		
		label_1 = new JLabel("horario");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		label_1.setBounds(10, 45, 71, 14);
		frame.getContentPane().add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField_1.setColumns(10);
		textField_1.setBounds(91, 42, 86, 20);
		frame.getContentPane().add(textField_1);
		
		label_4 = new JLabel("assunto");
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setFont(new Font("Dialog", Font.PLAIN, 12));
		label_4.setBounds(10, 75, 71, 14);
		frame.getContentPane().add(label_4);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField_2.setColumns(10);
		textField_2.setBounds(91, 72, 86, 20);
		frame.getContentPane().add(textField_2);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(274, 34, 108, 64);
		frame.getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		label_5 = new JLabel("participantes");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setFont(new Font("Dialog", Font.PLAIN, 12));
		label_5.setBounds(274, 17, 108, 14);
		frame.getContentPane().add(label_5);
		
		label_6 = new JLabel("hh:mm");
		label_6.setBounds(182, 46, 71, 14);
		frame.getContentPane().add(label_6);
		
		label_7 = new JLabel("dd/mm/aaaa");
		label_7.setBounds(182, 18, 71, 14);
		frame.getContentPane().add(label_7);
		
		
		frame.setVisible(true);
	}
}
