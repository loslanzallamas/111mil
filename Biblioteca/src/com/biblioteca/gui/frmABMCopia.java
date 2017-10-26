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
import com.biblioteca.model.Libro;

import java.beans.PropertyChangeListener;
import java.util.List;
import java.beans.PropertyChangeEvent;

public class frmABMCopia extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textId;
	private JTextField textIdentificador;
	private JComboBox cmbLibros;
	private JComboBox cmbEstado;
	private Copia copia;
	private Libro libro;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmABMCopia frame = new frmABMCopia();
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
	public frmABMCopia(Copia copia) {
		this();
		this.copia = copia;
		textId.setText(copia.getId().toString());
		textIdentificador.setText(copia.getIdentificador());
		cmbLibros.setSelectedItem(copia.getLibro());
		cmbLibros.setEnabled(false);
		cmbEstado.setSelectedItem(Estado.valueOf(copia.getEstado().toString()));
	}
	
	public frmABMCopia() {
		setTitle("Cliente");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 543, 296);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLibro = new JLabel("Libro");
		lblLibro.setBounds(10, 60, 89, 14);
		contentPane.add(lblLibro);
		
		JLabel lblIdentificador = new JLabel("Identificador");
		lblIdentificador.setBounds(10, 91, 93, 14);
		contentPane.add(lblIdentificador);
		
		JButton btnAceptarContinuar = new JButton("Aceptar y continuar");
		btnAceptarContinuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(guardar()) {
					frmABMCopia.this.dispose();
				}
			}
		});
		btnAceptarContinuar.setBounds(6, 181, 171, 23);
		contentPane.add(btnAceptarContinuar);
		
		JButton btnCancela = new JButton("Cancelar");
		btnCancela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmABMCopia.this.dispose();
			}
		});
		btnCancela.setBounds(355, 181, 117, 23);
		contentPane.add(btnCancela);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setBounds(10, 122, 46, 14);
		contentPane.add(lblEstado);
		
		JLabel lblId = new JLabel("Id");
		lblId.setBounds(10, 24, 46, 14);
		contentPane.add(lblId);
		
		textId = new JTextField();
		textId.setEditable(false);
		textId.setBounds(112, 21, 86, 20);
		contentPane.add(textId);
		textId.setColumns(10);
		
		List<Libro> libros = Biblioteca.daoLibro.getAll();
		cmbLibros = new JComboBox(libros.toArray(new Libro[libros.size()]));
		cmbLibros.setVisible(true);
		cmbLibros.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				textIdentificador.setEditable(true);
			}
		});
		cmbLibros.setBounds(109, 57, 144, 20);
		contentPane.add(cmbLibros);
		
		textIdentificador = new JTextField();
		textIdentificador.setBounds(109, 88, 144, 20);
		contentPane.add(textIdentificador);
		textIdentificador.setColumns(10);
		textIdentificador.requestFocusInWindow();
		
		cmbEstado = new JComboBox();
		cmbEstado.setModel(new DefaultComboBoxModel(Estado.values()));
		cmbEstado.setBounds(109, 119, 144, 20);
		contentPane.add(cmbEstado);
		
		JButton btnAceptarNuevo = new JButton("Aceptar y nuevo");
		btnAceptarNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(guardar()) {
					copia = new Copia();
					textId.setText("");
					textIdentificador.setText("");
					textIdentificador.requestFocusInWindow();
				}
			}
		});
		btnAceptarNuevo.setBounds(187, 181, 158, 23);
		contentPane.add(btnAceptarNuevo);
		
		pack();
		setBounds(100, 100, 543, 296);
		textIdentificador.requestFocusInWindow();
	
	}
	
	private boolean guardar() {
		if(textIdentificador.getText().isEmpty()) {
			JOptionPane.showMessageDialog(frmABMCopia.this, "Debe completar todos los campos.");
			return false;
		}else {
			if(textId.getText().isEmpty()) {
				libro = (Libro) cmbLibros.getSelectedItem();
				copia = new Copia((Estado)cmbEstado.getSelectedItem(), textIdentificador.getText());
				libro.addCopia(copia);
			} else {
				copia.setEstado((Estado)cmbEstado.getSelectedItem());
				copia.setIdentificador(textIdentificador.getText());
				libro = copia.getLibro();
			}
			Biblioteca.daoLibro.save(libro);
			return true;
		}
	}
}
