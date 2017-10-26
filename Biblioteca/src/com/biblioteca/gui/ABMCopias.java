package com.biblioteca.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.biblioteca.model.Copia;
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

public class ABMCopias extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	DefaultTableModel model = new DefaultTableModel(new Object[][] {},
			new String[] { "Id", "Libro", "Identificador", "Estado" });

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ABMCopias frame = new ABMCopias();
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
	public ABMCopias() {

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {
				updateTableModel();
			}
		});

		setTitle("ABM Copias");
		setBounds(100, 100, 748, 477);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		JButton btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmABMCopia copia = new frmABMCopia();
				copia.setVisible(true);
			}
		});
		btnNuevo.setBounds(650, 11, 72, 23);
		contentPane.add(btnNuevo);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 630, 417);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));

		table.setModel(model);

		// Boton eliminar
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int fila = table.getSelectedRow();
				if (fila == -1) {
					JOptionPane.showMessageDialog(ABMCopias.this, "Debe seleccionar una copia.");
				} else {

					// Confirmar eliminación
					final JOptionPane optionPane = new JOptionPane("¿Eliminar Copia?", JOptionPane.QUESTION_MESSAGE,
							JOptionPane.YES_NO_OPTION);

					final JDialog dialog = new JDialog(ABMCopias.this, "Click a button", true);
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
								dialog.setVisible(false);
							}
						}
					});
					dialog.pack();
					dialog.setVisible(true);

					int value = ((Integer) optionPane.getValue()).intValue();

					if (value == JOptionPane.YES_OPTION) {
						Integer id = Integer.valueOf(
								table.getModel().getValueAt(fila, table.getColumn("Id").getModelIndex()).toString());
						Copia copia = Biblioteca.daoCopia.getById(id);
						Libro libro = copia.getLibro();
						libro.removeCopia(copia);
						Biblioteca.daoLibro.save(libro);
						updateTableModel();
					}
				}

			}
		});
		btnEliminar.setBounds(650, 45, 69, 23);
		contentPane.add(btnEliminar);

		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int fila = table.getSelectedRow();
				if (fila == -1) {
					JOptionPane.showMessageDialog(ABMCopias.this, "Debe seleccionar un copia.");
				} else {
					// int fila = table.getSelectedRow();
					Integer id = Integer.valueOf(
							table.getModel().getValueAt(fila, table.getColumn("Id").getModelIndex()).toString());
					Copia copia = Biblioteca.daoCopia.getById(id);
					frmABMCopia frmABMcopia = new frmABMCopia(copia);
					frmABMcopia.setVisible(true);
				}
			}
		});
		btnEditar.setBounds(650, 79, 69, 23);
		contentPane.add(btnEditar);
	}

	private void updateTableModel() {
		model.setRowCount(0);
		for (Libro libro : Biblioteca.daoLibro.getAll()) {
			for (Copia copia : libro.getCopias()) {
				model.addRow(new String[] { copia.getId().toString(), libro.getNombre(), copia.getIdentificador(),
						copia.getEstado().toString() });
			}
		}
	}
}
