package com.biblioteca.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.biblioteca.model.Cliente;
import com.biblioteca.model.Libro;

import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ABMLibros extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	DefaultTableModel model = new DefaultTableModel(new Object[][] {},
			new String[] {"Id", "Nombre", "Autor", "Editorial", "A\u00F1o" });

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ABMLibros frame = new ABMLibros();
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
	public ABMLibros() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {
				model.setRowCount(0);
				//for (Libro libro : Biblioteca.daoLibro.getAll()) {
				/*for(Libro libro : LibrosViewModel.getInstance().getLibros()) {
					model.addRow(new String[] { libro.getNombre(), libro.getAutor().getNombre(), libro.getEditorial(),
							String.valueOf(libro.getAnio()) });
				}*/
				for(Libro libro : Biblioteca.daoLibro.getAll()) {
					String id = libro.getId().toString();
					String nombre = libro.getNombre();
					String autor = "Sin Autor";
					if(libro.getAutor() != null) {
						autor = libro.getAutor().getNombre();
					};
					String editorial = libro.getEditorial();
					String anio = String.valueOf(libro.getAnio());
					
					model.addRow(new String[] {
							id,
							nombre,
							autor,
							editorial,
							anio
					});
				}
			}
		});

		setTitle("ABM Libros");
		setBounds(100, 100, 780, 446);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		JButton btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmLibro libro = new frmLibro();
				libro.setVisible(true);
			}
		});
		btnNuevo.setBounds(682, 18, 72, 23);
		contentPane.add(btnNuevo);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 18, 662, 380);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));

		table.setModel(model);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int fila = table.getSelectedRow();
				if(fila == -1) {
					JOptionPane.showMessageDialog(ABMLibros.this, "Debe seleccionar un libro.");
				} else {
					
					// Confirmar eliminación
					final JOptionPane optionPane = new JOptionPane("¿Eliminar Autor?", JOptionPane.QUESTION_MESSAGE,
							JOptionPane.YES_NO_OPTION);
	
					final JDialog dialog = new JDialog(ABMLibros.this, "Click a button", true);
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
						Integer id = Integer.valueOf(table.getModel().getValueAt(fila, table.getColumn("Id").getModelIndex()).toString());
						Biblioteca.daoLibro.remove(id);
					}
				}
			}

		});
		btnEliminar.setBounds(682, 52, 72, 23);
		contentPane.add(btnEliminar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int fila = table.getSelectedRow();
				if(fila == -1) {
					JOptionPane.showMessageDialog(ABMLibros.this, "Debe seleccionar un libro.");
				} else {
					Integer id = Integer.valueOf(table.getModel().getValueAt(fila, table.getColumn("Id").getModelIndex()).toString());
					Libro libro = Biblioteca.daoLibro.getById(id);
					frmLibro frmlibro = new frmLibro(libro);
					frmlibro.setVisible(true);
				}
			}
		});
		btnEditar.setBounds(685, 86, 69, 23);
		contentPane.add(btnEditar);
	}
}
