package com.biblioteca.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.biblioteca.model.Autor;

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
import java.time.format.DateTimeFormatter;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.FlowLayout;

public class ABMAutores extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	DefaultTableModel model = new DefaultTableModel(new Object[][] {},
			new String[] { "Id", "Nombre", "Nacionalidad", "Fecha Nac." });

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ABMAutores frame = new ABMAutores();
					frame.pack();
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
	public ABMAutores() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {
				model.setRowCount(0);

				for (Autor autor : Biblioteca.daoAutor.getAll()) {
					model.addRow(new String[] { autor.getId().toString(), autor.getNombre(), autor.getNacionalidad(),
							String.valueOf(autor.getFechaNac().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
									.toString()) });
				}
			}
		});

		setTitle("ABM Autores");
		setBounds(100, 100, 780, 446);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 5, 670, 393);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));

		table.setModel(model);
		contentPane.add(scrollPane);

		// Boton eliminar
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(685, 47, 69, 34);
		contentPane.add(btnEliminar);
		
				JButton btnEditar = new JButton("Editar");
				btnEditar.setBounds(685, 11, 69, 34);
				contentPane.add(btnEditar);
				
						JButton btnNuevo = new JButton("Nuevo");
						btnNuevo.setBounds(685, 83, 69, 34);
						contentPane.add(btnNuevo);
						btnNuevo.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								frmAutor autor = new frmAutor();
								autor.setVisible(true);
							}
						});
				btnEditar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						int fila = table.getSelectedRow();
						if (fila == -1) {
							JOptionPane.showMessageDialog(ABMAutores.this, "Debe seleccionar un autor.");
						} else {
							Integer id = Integer.valueOf(
									table.getModel().getValueAt(fila, table.getColumn("Id").getModelIndex()).toString());
							Autor autor = Biblioteca.daoAutor.getById(id);
							frmAutor frmautor = new frmAutor(autor);
							frmautor.setVisible(true);
						}
					}
				});
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int fila = table.getSelectedRow();
				if (fila == -1) {
					JOptionPane.showMessageDialog(ABMAutores.this, "Debe seleccionar un autor.");
				} else {

					// Confirmar eliminación
					final JOptionPane optionPane = new JOptionPane("¿Eliminar Autor?", JOptionPane.QUESTION_MESSAGE,
							JOptionPane.YES_NO_OPTION);

					final JDialog dialog = new JDialog(ABMAutores.this, "Click a button", true);
					dialog.setContentPane(optionPane);
					dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
					optionPane.addPropertyChangeListener(new PropertyChangeListener() {
						public void propertyChange(PropertyChangeEvent e) {
							String prop = e.getPropertyName();

							if (dialog.isVisible() && (e.getSource() == optionPane)
									&& (prop.equals(JOptionPane.VALUE_PROPERTY))) {
								// If you were going to check something
								// before closing the window, you'd do
								// it here.
								dialog.pack();
								dialog.setVisible(false);
							}
						}
					});
					dialog.pack();
					dialog.setVisible(true);

					int value = ((Integer) optionPane.getValue()).intValue();

					if (value == JOptionPane.YES_OPTION) {
						// int fila = table.getSelectedRow();
						Integer id = Integer.valueOf(
								table.getModel().getValueAt(fila, table.getColumn("Id").getModelIndex()).toString());
						Biblioteca.daoAutor.remove(id);
						// AutoresViewModel.getInstance().removeAutor(nombre);
					}
				}

			}
		});
	}
}
