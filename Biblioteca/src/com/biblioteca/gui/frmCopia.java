package com.biblioteca.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.biblioteca.model.Copia;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import com.biblioteca.model.Estado;
import com.biblioteca.model.TipoLibro;

public class frmCopia extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Copia copia;
	
	private JPanel contentPane;
	private JTextField textIdentificador;
	private JTextField textId;
	private JComboBox cmbEstado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmCopia frame = new frmCopia();
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
	public frmCopia(Copia copia) {
		this();
		this.copia = copia;
		if(copia.getId() != null) {
			textId.setText(copia.getId().toString());
		};
		if(copia.getIdentificador() != null) {
			textIdentificador.setText(copia.getIdentificador());
		}
	}
	
	
	public frmCopia() {
		setTitle("Copia");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 387, 274);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Identificador:");
		lblNombre.setBounds(6, 64, 89, 14);
		contentPane.add(lblNombre);
		
		textIdentificador = new JTextField();
		textIdentificador.setBounds(113, 61, 162, 20);
		contentPane.add(textIdentificador);
		textIdentificador.setColumns(10);
		
		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setBounds(6, 89, 93, 14);
		contentPane.add(lblEstado);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textIdentificador.getText().isEmpty()) {
					JOptionPane.showMessageDialog(frmCopia.this,
						    "Debe completar todos los campos.");
				}else {
					copia.setIdentificador(textIdentificador.getText());
					copia.setEstado( Estado.valueOf(cmbEstado.getSelectedItem().toString()));
					frmCopia.this.setVisible(false);
				}
			}
		});
		btnGuardar.setBounds(10, 163, 89, 23);
		contentPane.add(btnGuardar);
		
		JButton btnCancela = new JButton("Cancelar");
		btnCancela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmCopia.this.dispose();
			}
		});
		btnCancela.setBounds(109, 163, 89, 23);
		contentPane.add(btnCancela);
		
		JLabel lblId = new JLabel("Id:");
		lblId.setBounds(6, 39, 46, 14);
		contentPane.add(lblId);
		
		textId = new JTextField();
		textId.setEditable(false);
		textId.setBounds(112, 36, 63, 20);
		contentPane.add(textId);
		textId.setColumns(10);
		
		cmbEstado = new JComboBox();
		cmbEstado.setModel(new DefaultComboBoxModel(Estado.values()));
		cmbEstado.setBounds(113, 92, 162, 20);
		contentPane.add(cmbEstado);
		
	}
}
