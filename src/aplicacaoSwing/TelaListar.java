package aplicacaoSwing;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Desktop;
import java.net.URL;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import fachada.Fachada;
import modelo.Assunto;
import modelo.Video;
import modelo.Visualizacao;

public class TelaListar {

	private JFrame frmListar;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton button;
	private JButton button_1;
	private JButton buttonOpenLink;

	/**
	 * Launch the application.
	 */
	//	public static void main(String[] args) {
	//		EventQueue.invokeLater(new Runnable() {
	//			public void run() {
	//				try {
	//					TelaListar window = new TelaListar();
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
	public TelaListar() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmListar = new JFrame();
		frmListar.setTitle("Listar");
		frmListar.setBounds(100, 100, 505, 323);
		frmListar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmListar.getContentPane().setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(44, 33, 409, 116);
		frmListar.getContentPane().add(scrollPane);

		table = new JTable();
	
		table.setGridColor(Color.BLACK);
		table.setRequestFocusEnabled(false);
		table.setFocusable(false);
		table.setBackground(Color.YELLOW);
		table.setFillsViewportHeight(true);
		table.setRowSelectionAllowed(true);
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scrollPane.setViewportView(table);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(
				new Object[][] {},
				new String[] {"", "", ""}
				));
		table.setShowGrid(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		button = new JButton("Listar videos");
		button.setHorizontalAlignment(SwingConstants.LEFT);
		button.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					DefaultTableModel model = new DefaultTableModel();
					model.addColumn("Link");
					model.addColumn("Nome");
					model.addColumn("Classificação");
					model.addColumn("DataHora");
					model.addColumn("Assuntos");
					//					table.getColumnModel().getColumn(0).setPreferredWidth(10);
					//					table.getColumnModel().getColumn(1).setPreferredWidth(10);

					List<Video> lista = Fachada.listarVideos();
					for(Video v : lista)
						for(Assunto a : v.getAssuntos())
							model.addRow(new Object[]{ v.getLink(), v.getNome(), v.getMedia(), a.getPalavra() });

					table.setModel(model);
				}
				catch(Exception erro){
					JOptionPane.showMessageDialog(frmListar,erro.getMessage());
				}
			}
		});
		button.setBounds(44, 172, 200, 23);
		frmListar.getContentPane().add(button);
		
		button_1 = new JButton("Listar visualizações");
		button_1.setHorizontalAlignment(SwingConstants.LEFT);
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					DefaultTableModel model = new DefaultTableModel();
					model.addColumn("Id");
					model.addColumn("Video");
					model.addColumn("Usuario");
					model.addColumn("DataHora");
					model.addColumn("Nota");
					//					table.getColumnModel().getColumn(0).setPreferredWidth(10);
					//					table.getColumnModel().getColumn(1).setPreferredWidth(10);

					List<Visualizacao> lista = Fachada.listarVisualizacao();
					for(Visualizacao v : lista)
							model.addRow(new Object[]{v.getId(), v.getVideo(), v.getUsuario(), v.getDataHora(), v.getNota() });

					table.setModel(model);
				}
				catch(Exception erro){
					JOptionPane.showMessageDialog(frmListar,erro.getMessage());
				}
			}
		});
		button_1.setBounds(44, 210, 200, 23);
		frmListar.getContentPane().add(button_1);
		
		buttonOpenLink = new JButton("Abrir Video no navegador");
		buttonOpenLink.setHorizontalAlignment(SwingConstants.LEFT);
		
		buttonOpenLink.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() >= 0) {
					String url = (String) table.getValueAt(table.getSelectedRow(),0);
					try {
						Desktop.getDesktop().browse(new URL(url).toURI());
					} catch (Exception erro) {
					}
				} else {
					JOptionPane.showMessageDialog(null, "selecione um link");
				}}
		});
		
		buttonOpenLink.setFont(new Font("Tahoma", Font.PLAIN, 12));
		buttonOpenLink.setBounds(44, 245, 200, 23);
		frmListar.getContentPane().add(buttonOpenLink);

		frmListar.setVisible(true);
	}
}
