package com.biblioteca.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Optional;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.biblioteca.model.Autor;
import com.biblioteca.model.Copia;
import com.biblioteca.model.Libro;
import com.biblioteca.model.TipoLibro;
import com.biblioteca.utils.Utiles;

public class frmLibro extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	List<Autor> autores;
	private Libro libro = new Libro();
	private JPanel contentPane;
	private JTextField textNombre;
	private JTextField textEditorial;
	private JFormattedTextField textAnio;
	private JComboBox<TipoLibro> cmbTipo;
	private JComboBox<Autor> cmbAutor;
	private JTextField textId;
	private JTable tableCopias;
	
	private DefaultTableModel modelCopias = new DefaultTableModel(new Object[][] {
	},
	new String[] {
		"Id", "Identificador", "Estado"
	});

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmLibro frame = new frmLibro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public frmLibro(Libro libro) {
		this();
		this.libro = libro;
		this.textId.setText(libro.getId().toString());
		this.textNombre.setText(libro.getNombre());
		this.textEditorial.setText(libro.getEditorial());
		this.textAnio.setText(String.valueOf(libro.getAnio()));
		this.cmbTipo.setSelectedItem(libro.getTipo());
		//this.cmbAutor.setSelectedItem((autores.stream().filter(a -> a.getId().equals(libro.getAutor().getId())).findFirst()).get());
		this.cmbAutor.setSelectedItem(libro.getAutor());
	}
	
	public frmLibro() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {
				updateTableModel();
			}
		});
		setTitle("Libro");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 453, 463);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 55, 46, 14);
		contentPane.add(lblNombre);
		
		textNombre = new JTextField();
		textNombre.setBounds(66, 52, 162, 20);
		contentPane.add(textNombre);
		textNombre.setColumns(10);
		
		JLabel lblEditorial = new JLabel("Editorial");
		lblEditorial.setBounds(10, 117, 46, 14);
		contentPane.add(lblEditorial);
		
		textEditorial = new JTextField();
		textEditorial.setBounds(66, 114, 162, 20);
		contentPane.add(textEditorial);
		textEditorial.setColumns(10);
		
		JLabel lblAo = new JLabel("A\u00F1o");
		lblAo.setBounds(10, 148, 46, 14);
		contentPane.add(lblAo);
		
		// NumberFormat longFormat = NumberFormat.getIntegerInstance();
/*		NumberFormatter numberFormatter = new NumberFormatter(longFormat);
		numberFormatter.setValueClass(Long.class);
		numberFormatter.setAllowsInvalid(false);
		numberFormatter.setMinimum(0L);
		
		textAnio = new JFormattedTextField(numberFormatter);*/
		textAnio = new JFormattedTextField();
		textAnio.setBounds(66, 145, 162, 20);
		contentPane.add(textAnio);
		textAnio.setColumns(10);
		
		cmbTipo = new JComboBox<>();
		cmbTipo.setModel(new DefaultComboBoxModel<>(TipoLibro.values()));
		cmbTipo.setBounds(66, 176, 162, 20);
		contentPane.add(cmbTipo);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(10, 179, 46, 14);
		contentPane.add(lblTipo);
		
		JLabel lblAutor = new JLabel("Autor");
		lblAutor.setBounds(10, 86, 46, 14);
		contentPane.add(lblAutor);
		
		autores = Biblioteca.daoAutor.getAll();
		cmbAutor = new JComboBox(autores.toArray(new Autor[autores.size()]));
		//cmbAutor.setRenderer(new AutorCmbRender());
		cmbAutor.setBounds(66, 83, 162, 20);
		contentPane.add(cmbAutor);
		
		JButton btnOk = new JButton("Guardar");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textNombre.getText().isEmpty() || textEditorial.getText().isEmpty() || textAnio.getText().isEmpty()) {
						JOptionPane.showMessageDialog(frmLibro.this, "Debe completar todos los campos.");
				} else {
					if(!Utiles.validarEntero(textAnio.getText())) {
						JOptionPane.showMessageDialog(frmLibro.this, "El año sólo puede contener números y no puede ser negativo.");
					} else {
						libro.setNombre(textNombre.getText());
						libro.setTipo(TipoLibro.valueOf(cmbTipo.getSelectedItem().toString()));
						libro.setEditorial(textEditorial.getText());
						libro.setAnio(Integer.parseInt(textAnio.getText()));
						libro.setAutor(Biblioteca.daoAutor.getById(((Autor)cmbAutor.getSelectedItem()).getId()));
						/*if(!textId.getText().isEmpty()) {
						libro.setId(Integer.valueOf(textId.getText()));
						} */
						Biblioteca.daoLibro.save(libro);
						updateTableModel();
					}
				}
			}
		});
		btnOk.setBounds(0, 391, 89, 23);
		contentPane.add(btnOk);
		
		JButton btnCancela = new JButton("Cancelar");
		btnCancela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmLibro.this.dispose();
			}
		});
		btnCancela.setBounds(99, 391, 89, 23);
		contentPane.add(btnCancela);
		
		JLabel lblId = new JLabel("Id");
		lblId.setBounds(10, 23, 46, 14);
		contentPane.add(lblId);
		
		textId = new JTextField();
		textId.setEditable(false);
		textId.setBounds(66, 20, 86, 20);
		contentPane.add(textId);
		textId.setColumns(10);
		
		JScrollPane scrollPaneCopias = new JScrollPane();
		scrollPaneCopias.setBounds(20, 251, 296, 117);
		contentPane.add(scrollPaneCopias);
		
		tableCopias = new JTable();
		tableCopias.setModel(modelCopias);
		tableCopias.getColumnModel().getColumn(1).setPreferredWidth(113);
		tableCopias.getColumnModel().getColumn(2).setPreferredWidth(129);
		scrollPaneCopias.setViewportView(tableCopias);
		
		JButton btnAgregarCopia = new JButton("Agregar Copia");
		btnAgregarCopia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Copia copiaNueva = new Copia();
				libro.addCopia(copiaNueva);
				frmCopia frmcopia = new frmCopia(copiaNueva);
				frmcopia.setVisible(true);
			}
		});
		btnAgregarCopia.setBounds(326, 254, 101, 23);
		contentPane.add(btnAgregarCopia);
		
		JButton btnEditarCopia = new JButton("Editar Copia");
		btnEditarCopia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Copia copia = null;
				int fila = tableCopias.getSelectedRow();
				if(fila == -1) {
					JOptionPane.showMessageDialog(frmLibro.this, "Debe seleccionar una copia.");
				} else {
					Integer id = Integer.valueOf(tableCopias.getModel().getValueAt(fila, tableCopias.getColumn("Id").getModelIndex()).toString());
					Optional<Copia> optCopia = libro.getCopias().stream().filter(c->c.getId().equals(id)).findFirst();
					if(optCopia.isPresent()) {
						copia = optCopia.get();
					}
					frmCopia frmcopia = new frmCopia(copia);
					frmcopia.setVisible(true);
				}
			}
		});
		btnEditarCopia.setBounds(326, 288, 101, 23);
		contentPane.add(btnEditarCopia);
		
		JButton btnEliminarCopia = new JButton("Eliminar Copia");
		btnEliminarCopia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = tableCopias.getSelectedRow();
				if(fila == -1) {
					JOptionPane.showMessageDialog(frmLibro.this, "Debe seleccionar una copia.");
				} else {
					
					// Confirmar eliminación
					final JOptionPane optionPane = new JOptionPane("¿Eliminar Copia?", JOptionPane.QUESTION_MESSAGE,
							JOptionPane.YES_NO_OPTION);
	
					final JDialog dialog = new JDialog(frmLibro.this, "Confirmar", true);
					dialog.setContentPane(optionPane);
					dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
					optionPane.addPropertyChangeListener(new PropertyChangeListener() {
						public void propertyChange(PropertyChangeEvent e) {
							String prop = e.getPropertyName();
	
							if (dialog.isVisible() && (e.getSource() == optionPane)
									&& (prop.equals(JOptionPane.VALUE_PROPERTY))) {
								// If you were going to check something
								// before closing the window, you'd do
								// it here
								dialog.pack();
								dialog.setVisible(false);
							}
						}
					});
	
					dialog.pack();
					dialog.setVisible(true);
	
					int value = ((Integer) optionPane.getValue()).intValue();
	
					if (value == JOptionPane.YES_OPTION) {
						String valueSel = tableCopias.getModel().getValueAt(fila, tableCopias.getColumn("Id").getModelIndex()).toString(); 
						if(!valueSel.equals("N/D")) {
							Integer id = Integer.valueOf(valueSel);
							libro.removeCopia(id);
						} else {
							String identificador = tableCopias.getModel().getValueAt(fila, tableCopias.getColumn("Identificador").getModelIndex()).toString();
							libro.getCopias().removeIf(c->c.getIdentificador().equals(identificador));
							updateTableModel();
						}
						
					}
				}
			}
		});
		btnEliminarCopia.setBounds(326, 322, 101, 23);
		contentPane.add(btnEliminarCopia);
		
	}
	
	private void updateTableModel() {
		modelCopias.setRowCount(0);
		for(Copia copia : libro.getCopias()) {
			String copiaId = "N/D";
			if(copia.getId() != null) {
				copiaId = copia.getId().toString();
			};
			modelCopias.addRow(new String[] {
				copiaId,
				copia.getIdentificador(),
				copia.getEstado().toString()
			});
		}
	}
}
