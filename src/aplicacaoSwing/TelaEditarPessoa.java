/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Pesist~encia de Objetos
 * Prof. Fausto Maranhão Ayres
 **********************************/
package aplicacaoSwing;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.format.DateTimeFormatter;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import fachada.Fachada;
import modelo.Pessoa;
import modelo.Sexo;

public class TelaEditarPessoa {
	private JFrame frame;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel label_5;
	private JLabel label_6;
	private JTextField textField;
	private JTextField textField_1;
	private JButton button;
	private JButton button_1;
	private JButton button_2;
	private JButton button_3;
	private JPanel panel;
	private JRadioButton radioButton;
	private JRadioButton radioButton_1;
	private ButtonGroup grupobotoes;
	private JTextArea textArea;
	private JScrollPane scrollPane;

	private BufferedImage buffer;	//armazena a imagem temporariamente na memória
	private DateTimeFormatter formatador1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private DateTimeFormatter formatador2 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

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
	public TelaEditarPessoa() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setFont(new Font("Dialog", Font.PLAIN, 12));
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 12));
		frame.setTitle("Editar pessoa");
		frame.setBounds(100, 100, 430, 307);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		label = new JLabel("nome:");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setFont(new Font("Dialog", Font.PLAIN, 12));
		label.setBounds(10, 14, 71, 14);
		frame.getContentPane().add(label);

		label_2 = new JLabel("");
		label_2.setBounds(21, 230, 372, 14);
		frame.getContentPane().add(label_2);

		textField = new JTextField();
		textField.setBackground(Color.LIGHT_GRAY);
		textField.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField.setBounds(91, 11, 134, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		button = new JButton("Salvar");
		button.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(textField.getText().isEmpty()) {
						label_2.setText("nome vazio");
						return;
					}

					String nome = textField.getText();
					String nascimento = textField_1.getText();

					if(radioButton.isSelected())
						Fachada.alterarSexo(nome, Sexo.MASCULINO);
					else
						Fachada.alterarSexo(nome, Sexo.FEMININO);

					Fachada.alterarNascimento(nome, nascimento);

					String apelidos[] = textArea.getText().split("\n");
					Fachada.alterarApelidos(nome, apelidos);

					if(buffer!=null) {
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						ImageIO.write(buffer, "jpg", baos );
						byte[] bytesfoto = baos.toByteArray();
						baos.close();
						Fachada.alterarFoto(nome, bytesfoto);
					}
					else
						Fachada.alterarFoto(nome, null);

					label_2.setText("dados salvos");
				}
				catch(IOException ex1) {
					label_2.setText("problema na conversão da imagem em bytes");
				}
				catch(Exception ex2) {
					label_2.setText(ex2.getMessage());
				}
			}
		});
		button.setBounds(333, 10, 71, 23);
		frame.getContentPane().add(button);

		button_1 = new JButton("Buscar");
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(textField.getText().isEmpty()) {
						label_2.setText("nome vazio");
						return;
					}

					Pessoa p = Fachada.localizarPessoa(textField.getText());
					textField_1.setText(p.getDtnascimento().format(formatador1));
					if(p.getSexo().equals(Sexo.MASCULINO))
						radioButton.setSelected(true);
					else
						radioButton_1.setSelected(true);

					textArea.setText("");
					for(String a : p.getApelidos())
						textArea.append(a +"\n");

					label_5.setIcon(null);	//limpa a exibicao da imagem
					buffer=null;			//limpa a imagem
					if(p.getFoto()!=null) {
						//exibe a imagem lida do banco
						InputStream in = new ByteArrayInputStream(p.getFoto());
						buffer = ImageIO.read(in);
						ImageIcon icon = new  ImageIcon(buffer.getScaledInstance(buffer.getWidth(), buffer.getHeight(),Image.SCALE_DEFAULT));
						icon.setImage(icon.getImage().getScaledInstance(label_5.getWidth(), label_5.getHeight(), 1) );
						label_5.setIcon(icon);
					}
					label_2.setText("busca concluida");
				}
				catch(Exception ex) {
					label_2.setText(ex.getMessage());
				}
			}
		});
		button_1.setBounds(235, 11, 88, 23);
		frame.getContentPane().add(button_1);

		label_3 = new JLabel("nascimento");
		label_3.setFont(new Font("Dialog", Font.PLAIN, 12));
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setBounds(10, 39, 71, 14);
		frame.getContentPane().add(label_3);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField_1.setColumns(10);
		textField_1.setBounds(91, 36, 86, 20);
		frame.getContentPane().add(textField_1);

		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Sexo:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(21, 149, 201, 37);
		panel.setLayout(new GridLayout(1, 0));
		frame.getContentPane().add(panel);

		radioButton = new JRadioButton("masculino");
		radioButton.setSelected(true);
		radioButton_1 = new JRadioButton("feminino");
		panel.add(radioButton);
		panel.add(radioButton_1);

		grupobotoes = new ButtonGroup();	//controla selecao unicao
		grupobotoes.add(radioButton);
		grupobotoes.add(radioButton_1);


		scrollPane = new JScrollPane();
		scrollPane.setBounds(91, 71, 89, 67);
		frame.getContentPane().add(scrollPane);

		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);

		label_1 = new JLabel("Apelidos");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setBounds(10, 76, 71, 14);
		frame.getContentPane().add(label_1);
		label_6 = new JLabel("um por linha");
		label_6.setHorizontalAlignment(SwingConstants.RIGHT);
		label_6.setBounds(0, 101, 86, 14);
		frame.getContentPane().add(label_6);

		label_4 = new JLabel("Foto");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setBounds(285, 44, 71, 14);
		frame.getContentPane().add(label_4);

		label_5 = new JLabel("");
		label_5.setOpaque(true);
		label_5.setBackground(Color.GRAY);
		label_5.setBounds(259, 59, 134, 127);
		frame.getContentPane().add(label_5);

		button_2 = new JButton("Carregar");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();   
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"Imagens", "jpg", "gif");
				chooser.setFileFilter(filter);
				chooser.setCurrentDirectory(new File("c:"));  
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				chooser.showOpenDialog(null);
				File file = chooser.getSelectedFile();	
				if (file==null) return;		//nao foi selecionado

				try {
					buffer = ImageIO.read(file);
					ImageIcon icon = new  ImageIcon(buffer.getScaledInstance(buffer.getWidth(), buffer.getHeight(),Image.SCALE_DEFAULT));
					icon.setImage(icon.getImage().getScaledInstance(label_5.getWidth(), label_5.getHeight(), 1) );
					label_5.setIcon(icon);
				} catch (IOException ex) {
					label_2.setText(ex.getMessage());
				}	
			}
		});
		button_2.setBounds(234, 197, 89, 23);
		frame.getContentPane().add(button_2);

		button_3 = new JButton("Limpar");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buffer=null;
				label_5.setIcon(null);
			}
		});
		button_3.setBounds(328, 197, 76, 23);
		frame.getContentPane().add(button_3);

		frame.setVisible(true);
	}

}
