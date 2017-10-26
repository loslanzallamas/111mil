package com.biblioteca.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.biblioteca.model.Cliente;
import com.biblioteca.model.Copia;
import com.biblioteca.model.Estado;
import com.biblioteca.model.Prestamo;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.awt.event.ActionEvent;

public class frmPrestamo extends JFrame {

	private JPanel contentPane;
	private JTextField textClienteId;
	private JTextField textCopiaId;
	private JLabel lblCliente;
	private JLabel lblCopia;
	private Cliente cliente;
	private Copia copia;
	private JButton btnCancelar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmPrestamo frame = new frmPrestamo();
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
	public frmPrestamo() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 328, 196);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textClienteId = new JTextField();
		textClienteId.setBounds(5, 5, 45, 20);
		contentPane.add(textClienteId);
		textClienteId.setColumns(10);
		
		JButton btnBucarCliente = new JButton("Cliente");
		btnBucarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!textClienteId.getText().isEmpty()) {
					cliente = Biblioteca.daoCliente.getById(Integer.parseInt(textClienteId.getText()));
					if(cliente != null) {
						if(cliente.getMulta().getFechaFin().isBefore(LocalDate.now())) {
							lblCliente.setText(cliente.getNombre() + " " + cliente.getApellido());
						}
					} else {
						JOptionPane.showMessageDialog(frmPrestamo.this, "No se encontró ningún cliente con el Id ingresado.");
					}
				} else {
					JOptionPane.showMessageDialog(frmPrestamo.this, "Debe ingresar un Id.");
				}
			}
		});
		btnBucarCliente.setBounds(60, 4, 89, 23);
		contentPane.add(btnBucarCliente);
		
		textCopiaId = new JTextField();
		textCopiaId.setBounds(5, 70, 45, 20);
		contentPane.add(textCopiaId);
		textCopiaId.setColumns(10);
		
		JButton btnCopia = new JButton("Copia");
		btnCopia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!textCopiaId.getText().isEmpty()) {
					copia = Biblioteca.daoCopia.getById(Integer.parseInt(textCopiaId.getText()));
					if(copia != null) {
						if(copia.getEstado().equals(Estado.En_biblioteca)) {
							lblCopia.setText(copia.getIdentificador()  + " " + copia.getLibro().getNombre());
						} else {
							JOptionPane.showMessageDialog(frmPrestamo.this, "La copia no está disponible.");
						}
						
					} else {
						JOptionPane.showMessageDialog(frmPrestamo.this, "No se encontró ninguna copia con el Id ingresado.");
					}
				} else {
					JOptionPane.showMessageDialog(frmPrestamo.this, "Debe ingresar un Id.");
				}
			}
		});
		btnCopia.setBounds(60, 69, 89, 23);
		contentPane.add(btnCopia);
		
		JButton btnÇReservar = new JButton("Reservar");
		btnÇReservar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!lblCliente.getText().isEmpty() && !lblCopia.getText().isEmpty()) {
					copia.setEstado(Estado.Prestado);
					Biblioteca.daoCopia.save(copia);
					Prestamo prestamo = new Prestamo(LocalDate.now(), LocalDate.now().plusDays(3), copia, null);
					Biblioteca.daoPrestamo.save(prestamo);
					cliente.addPrestamo(prestamo);
					Biblioteca.daoCliente.save(cliente);
					textCopiaId.setText("");
					lblCopia.setText("");
					copia = null;
				} else {
					JOptionPane.showMessageDialog(frmPrestamo.this, "Debe completar todos los datos");
				}
			}
		});
		btnÇReservar.setBounds(213, 124, 89, 23);
		contentPane.add(btnÇReservar);
		
		lblCliente = new JLabel("");
		lblCliente.setBounds(60, 38, 162, 14);
		contentPane.add(lblCliente);
		
		JLabel lblCliente_1 = new JLabel("Cliente:");
		lblCliente_1.setBounds(5, 36, 46, 14);
		contentPane.add(lblCliente_1);
		
		lblCopia = new JLabel("");
		lblCopia.setBounds(60, 103, 168, 14);
		contentPane.add(lblCopia);
		
		JLabel lblCopia_1 = new JLabel("Copia:");
		lblCopia_1.setBounds(4, 101, 46, 14);
		contentPane.add(lblCopia_1);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmPrestamo.this.dispose();
			}
		});
		btnCancelar.setBounds(14, 124, 89, 23);
		contentPane.add(btnCancelar);
	}
}
