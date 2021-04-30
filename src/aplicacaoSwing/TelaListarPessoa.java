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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import fachada.Fachada;
import modelo.Pessoa;
import modelo.Reuniao;
import modelo.Telefone;
import modelo.Viagem;
import javax.swing.border.EtchedBorder;

public class TelaListarPessoa {
	private JFrame frame;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton button;
	private JButton button_1;
	private JButton button_2;
	private JButton button_3;
	private JButton button_4;
	private JButton button_5;
	private JLabel label;
	private JTextField textField;
	private JTextField textField_1;
	private JRadioButton radioButton;
	private JRadioButton radioButton_1;
	private JRadioButton radioButton_2;
	private JRadioButton radioButton_3;
	private JRadioButton radioButton_4;
	private JPanel panel;
	private ButtonGroup grupobotoes;

	private DateTimeFormatter formatador1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private DateTimeFormatter formatador2 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	private JPanel panel_1;
	private JLabel label_1;

	/**
	 * Launch the application.
	 */
	//	public static void main(String[] args) {
	//		EventQueue.invokeLater(new Runnable() {
	//			public void run() {
	//				try {
	//					TelaListarReuniao window = new TelaListarReuniao();
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
	public TelaListarPessoa() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				button.doClick();
			}
		});
		frame.setTitle("Listar pessoas e relacionamentos");
		frame.setBounds(100, 100, 837, 411);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 11, 640, 206);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					String nome = (String) table.getValueAt( table.getSelectedRow(), 0);
					Pessoa p = Fachada.localizarPessoa(nome);
					if(p.getFoto()!=null) {
						//exibe a imagem lida do banco
						InputStream in = new ByteArrayInputStream(p.getFoto());
						BufferedImage buffer = ImageIO.read(in);
						ImageIcon icon = new  ImageIcon(buffer.getScaledInstance(buffer.getWidth(), buffer.getHeight(),Image.SCALE_DEFAULT));
						icon.setImage(icon.getImage().getScaledInstance(label_1.getWidth(), label_1.getHeight(), 1) );
						label_1.setIcon(icon);
					}
					else
						label_1.setIcon(null);
				}
				catch(Exception erro) {
					label.setText(erro.getMessage());
				}

			}
		});

		table.setGridColor(Color.BLACK);
		table.setRequestFocusEnabled(false);
		table.setFocusable(false);
		table.setBackground(Color.WHITE);
		table.setFillsViewportHeight(true);
		table.setRowSelectionAllowed(true);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(table);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(
				new Object[][] {},
				new String[] {"", "", "", "", "", "", ""}
				));
		table.setShowGrid(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		button_1 = new JButton("Apagar");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					if (table.getSelectedRow() >= 0){
						String nome = (String) table.getValueAt( table.getSelectedRow(), 0); //0=nome
						Fachada.excluirPessoa(nome);
						label.setText("exclusão realizada"); 
						button.doClick(); 	//listagem
					}
					else
						JOptionPane.showMessageDialog(null, "selecione uma linha");
				}
				catch(Exception erro) {
					label.setText(erro.getMessage());
				}
			}
		});
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_1.setBounds(671, 194, 143, 23);
		frame.getContentPane().add(button_1);

		label = new JLabel("");
		label.setForeground(Color.RED);
		label.setBounds(21, 336, 640, 14);
		frame.getContentPane().add(label);

		button = new JButton("Listar pessoas com nome:");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Pessoa> lista = Fachada.consultarPessoas(textField.getText());
				listagem(lista);
			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button.setBounds(336, 239, 237, 23);
		frame.getContentPane().add(button);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField.setColumns(10);
		textField.setBounds(575, 239, 86, 20);
		frame.getContentPane().add(textField);

		button_2 = new JButton("Listar pessoas com N telefones:");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int n = Integer.parseInt(textField_1.getText());
					List<Pessoa> lista = Fachada.consultarPessoasNTelefones(n);
					listagem(lista);
				}catch(NumberFormatException ex) {
					label.setText(ex.getMessage());
				}
			}
		});
		button_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_2.setBounds(336, 273, 237, 23);
		frame.getContentPane().add(button_2);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_1.setColumns(10);
		textField_1.setBounds(575, 274, 35, 20);
		frame.getContentPane().add(textField_1);

		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Exibir:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(21, 230, 305, 79);
		panel.setLayout(new GridLayout(2, 2));
		frame.getContentPane().add(panel);

		radioButton = new JRadioButton("apelidos");
		radioButton_1 = new JRadioButton("telefones");
		radioButton_2 = new JRadioButton("viagens");
		radioButton_3 = new JRadioButton("reunioes");
		radioButton_4 = new JRadioButton("ocultar");
		radioButton_4.setSelected(true);

		panel.add(radioButton);
		panel.add(radioButton_1);
		panel.add(radioButton_2);
		panel.add(radioButton_3);
		panel.add(radioButton_4);

		grupobotoes = new ButtonGroup();	//controla selecao unicao
		grupobotoes.add(radioButton);
		grupobotoes.add(radioButton_1);
		grupobotoes.add(radioButton_2);
		grupobotoes.add(radioButton_3);
		grupobotoes.add(radioButton_4);

		button_3 = new JButton("Adicionar telefone");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					if (table.getSelectedRow() >= 0){
						String nome = (String) table.getValueAt( table.getSelectedRow(), 0); //0=nome
						TelaCriarTelefone tela = new TelaCriarTelefone(nome);
						button.doClick(); 	//listagem
					}
					else
						JOptionPane.showMessageDialog(null, "selecione uma linha");
				}
				catch(Exception erro) {
					label.setText(erro.getMessage());
				}
			}
		});
		button_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_3.setBounds(671, 123, 143, 23);
		frame.getContentPane().add(button_3);

		button_4 = new JButton("Adicionar viagem");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					if (table.getSelectedRow() >= 0){
						String nome = (String) table.getValueAt( table.getSelectedRow(), 0); //0=nome
						TelaCriarViagem tela = new TelaCriarViagem(nome);
						button.doClick(); 	//listagem
					}
					else
						JOptionPane.showMessageDialog(null, "selecione uma linha");
				}
				catch(Exception erro) {
					label.setText(erro.getMessage());
				}
			}
		});
		button_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_4.setBounds(671, 147, 143, 23);
		frame.getContentPane().add(button_4);

		button_5 = new JButton("Adicionar reuni\u00E3o");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					if (table.getSelectedRow() >= 0){
						String nome = (String) table.getValueAt( table.getSelectedRow(), 0); //0=nome
						TelaCriarReuniao tela = new TelaCriarReuniao(nome);
						button.doClick(); 	//listagem
					}
					else
						JOptionPane.showMessageDialog(null, "selecione uma linha");
				}
				catch(Exception erro) {
					label.setText(erro.getMessage());
				}

			}
		});
		button_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_5.setBounds(671, 170, 143, 23);
		frame.getContentPane().add(button_5);

		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Foto", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(671, 0, 143, 121);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		label_1 = new JLabel("");
		label_1.setBounds(10, 22, 123, 88);
		panel_1.add(label_1);

		frame.setVisible(true);
	}

	public void listagem(List<Pessoa> lista) {
		try{
			DefaultTableModel model = new DefaultTableModel();
			model.addColumn("Nome");
			if(radioButton.isSelected()) 
				model.addColumn("Apelidos");
			if(radioButton_1.isSelected()) 
				model.addColumn("Telefones");
			if(radioButton_2.isSelected()) 
				model.addColumn("Viagens");
			if(radioButton_3.isSelected()) 
				model.addColumn("Reunioes");
			if(radioButton_4.isSelected()) {
				model.addColumn("Sexo");
				model.addColumn("Nascimento");
			}
			String texto;
			for(Pessoa p : lista) {
				if(radioButton.isSelected()) {
					texto="";
					for(String s : p.getApelidos())	
						texto = texto + s + ",";		//concatena
					model.addRow(new Object[]{p.getNome(), texto});
				}
				if(radioButton_1.isSelected())
					for(Telefone t : p.getTelefones())	{
						texto = t.getNumero() ;
						model.addRow(new Object[]{p.getNome(), texto});
					}
				if(radioButton_2.isSelected())
					for(Viagem v : p.getViagens()) {
						texto = v.getData().format(formatador1)+" - "+v.getLocal() ;
						model.addRow(new Object[]{p.getNome(), texto});
					}
				if(radioButton_3.isSelected())
					for(Reuniao r : p.getReunioes()) {
						texto = r.getDatahora().format(formatador2) +" - "+r.getAssunto() ;
						model.addRow(new Object[]{p.getNome(), texto});
					}
				if(radioButton_4.isSelected()) 
					model.addRow(new Object[]{p.getNome(), p.getSexo(),p.getDtnascimento().format(formatador1)});
			}
			table.setModel(model);
			if(!radioButton_4.isSelected()) {
				table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 
				table.getColumnModel().getColumn(1).setMinWidth(200);	//coluna concatenada
				table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); 
			}
		}
		catch(Exception erro){
			JOptionPane.showMessageDialog(frame,erro.getMessage());
		}
	}
}
