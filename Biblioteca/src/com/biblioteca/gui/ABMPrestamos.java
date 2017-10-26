package com.biblioteca.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.biblioteca.model.Prestamo;

import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.format.DateTimeFormatter;

public class ABMPrestamos extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	DefaultTableModel model = new DefaultTableModel(new Object[][] {},
			new String[] { "Id", "Cliente", "Copia", "Fecha Prest.", "Fecha Dev." });

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ABMPrestamos frame = new ABMPrestamos();
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
	public ABMPrestamos() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {
				model.setRowCount(0);

				for (Prestamo prestamo : Biblioteca.daoPrestamo.getAll()) {
					model.addRow(new String[] { prestamo.getId().toString(), prestamo.getCliente().getNombre(), prestamo.getCopia().getIdentificador(),
							prestamo.getFechaPrestamo().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
									.toString(), prestamo.getFechaDevolucion().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
									.toString() });
				}
			}
		});

		setTitle("ABM Préstamo");
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
	}
}
