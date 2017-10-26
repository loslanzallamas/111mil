package com.biblioteca.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.biblioteca.model.Cliente;

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

public class ABMClientes extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	DefaultTableModel model = new DefaultTableModel(new Object[][] {},
			new String[] { "Id", "Nombre", "Apellido", "Dirección", "Teléfono", "Prestamos" });

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ABMClientes frame = new ABMClientes();
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
	public ABMClientes() {

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {
				model.setRowCount(0);
				//for(Cliente cliente: Biblioteca.daoCliente.getAll()) {
				/*for(Cliente cliente : ClientesViewModel.getInstance().getClientes()) {
					model.addRow(new String[] {
							cliente.getNombre(),
							cliente.getApellido(),
							cliente.getDireccion(),
							cliente.getTelefono()
					});
				}*/
				for(Cliente cliente : Biblioteca.daoCliente.getAll()) {
					model.addRow(new String[] {
							cliente.getId().toString(),
							cliente.getNombre(),
							cliente.getApellido(),
							cliente.getDireccion(),
							cliente.getTelefono(),
							String.valueOf(cliente.getPrestamos().size())
					});
				}

			}
		});
		
		setTitle("ABM Clientes");
		setBounds(100, 100, 747, 467);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		JButton btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmCliente cliente = new frmCliente();
				cliente.setVisible(true);
			}
		});
		btnNuevo.setBounds(649, 11, 72, 23);
		contentPane.add(btnNuevo);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 629, 407);
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
				if(fila == -1) {
					JOptionPane.showMessageDialog(ABMClientes.this, "Debe seleccionar un cliente.");
				} else {
					
					// Confirmar eliminación
					final JOptionPane optionPane = new JOptionPane("¿Eliminar Cliente?", JOptionPane.QUESTION_MESSAGE,
							JOptionPane.YES_NO_OPTION);
	
					final JDialog dialog = new JDialog(ABMClientes.this, "Click a button", true);
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
						//int fila = table.getSelectedRow();
						Integer id = Integer.valueOf(table.getModel().getValueAt(fila, table.getColumn("Id").getModelIndex()).toString());
						Biblioteca.daoCliente.remove(id);
					}
				}

			}
		});
		btnEliminar.setBounds(649, 45, 69, 23);
		contentPane.add(btnEliminar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int fila = table.getSelectedRow();
				if(fila == -1) {
					JOptionPane.showMessageDialog(ABMClientes.this, "Debe seleccionar un Cliente.");
				} else {
					//int fila = table.getSelectedRow();
					Integer id = Integer.valueOf(table.getModel().getValueAt(fila, table.getColumn("Id").getModelIndex()).toString());
					Cliente cliente = Biblioteca.daoCliente.getById(id);
					frmCliente frmcliente = new frmCliente(cliente);
					frmcliente.setVisible(true);
				}
			}
		});
		btnEditar.setBounds(652, 74, 69, 23);
		contentPane.add(btnEditar);
	}
}
