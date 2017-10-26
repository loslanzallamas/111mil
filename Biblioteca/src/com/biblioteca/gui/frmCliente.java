package com.biblioteca.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.biblioteca.model.Cliente;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class frmCliente extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textNombre;
	private JTextField textApellido;
	private JTextField textDireccion;
	private JTextField textTelefono;
	private JTextField textId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmCliente frame = new frmCliente();
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
	public frmCliente(Cliente cliente) {
		this();
		this.textId.setText(cliente.getId().toString());
		this.textNombre.setText(cliente.getNombre());
		this.textApellido.setText(cliente.getApellido());
		this.textDireccion.setText(cliente.getDireccion());
		this.textTelefono.setText(cliente.getTelefono());
	}
	
	public frmCliente() {
		setTitle("Cliente");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 342, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 60, 89, 14);
		contentPane.add(lblNombre);
		
		textNombre = new JTextField();
		textNombre.setBounds(113, 57, 162, 20);
		contentPane.add(textNombre);
		textNombre.setColumns(10);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(10, 91, 93, 14);
		contentPane.add(lblApellido);
		
		textApellido = new JTextField();
		textApellido.setBounds(113, 88, 162, 20);
		contentPane.add(textApellido);
		textApellido.setColumns(10);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textNombre.getText().isEmpty() || textApellido.getText().isEmpty() || textDireccion.getText().isEmpty() || textTelefono.getText().isEmpty()) {
					JOptionPane.showMessageDialog(frmCliente.this, "Debe completar todos los campos.");
				} else {
					Cliente cliente = new Cliente(textNombre.getText(), textApellido.getText(), textDireccion.getText(), textTelefono.getText());
					if(!textId.getText().isEmpty()) {
						cliente.setId(Integer.valueOf(textId.getText()));
					}
					Biblioteca.daoCliente.save(cliente);
					frmCliente.this.dispose();
				}
				
			}
		});
		btnGuardar.setBounds(6, 181, 89, 23);
		contentPane.add(btnGuardar);
		
		JButton btnCancela = new JButton("Cancelar");
		btnCancela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmCliente.this.dispose();
			}
		});
		btnCancela.setBounds(109, 181, 89, 23);
		contentPane.add(btnCancela);
		
		textDireccion = new JTextField();
		textDireccion.setBounds(113, 119, 162, 20);
		contentPane.add(textDireccion);
		textDireccion.setColumns(10);
		
		textTelefono = new JTextField();
		textTelefono.setBounds(113, 150, 162, 20);
		contentPane.add(textTelefono);
		textTelefono.setColumns(10);
		
		JLabel lblDireccion = new JLabel("Direcci\u00F3n");
		lblDireccion.setBounds(10, 122, 46, 14);
		contentPane.add(lblDireccion);
		
		JLabel lblTelefono = new JLabel("Tel\u00E9fono");
		lblTelefono.setBounds(10, 153, 46, 14);
		contentPane.add(lblTelefono);
		
		JLabel lblId = new JLabel("Id");
		lblId.setBounds(10, 24, 46, 14);
		contentPane.add(lblId);
		
		textId = new JTextField();
		textId.setEditable(false);
		textId.setBounds(112, 21, 86, 20);
		contentPane.add(textId);
		textId.setColumns(10);
	
	}
}
