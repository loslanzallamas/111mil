package com.biblioteca.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import com.biblioteca.model.Autor;
import com.biblioteca.utils.DateLabelFormatter;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.awt.event.ActionEvent;

public class frmAutor extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textNombre;
	private JTextField textNacionalidad;
	private JTextField textId;
	private JDatePickerImpl datePicker;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmAutor frame = new frmAutor();
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
	public frmAutor(Autor autor) {
		this();
		textId.setText(autor.getId().toString());
		textNombre.setText(autor.getNombre());
		textNacionalidad.setText(autor.getNacionalidad());
		datePicker.getJFormattedTextField().setText(autor.getFechaNac().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
	}
	
	
	public frmAutor() {
		setTitle("Autor");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 387, 274);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Hoy");
        p.put("text.month", "Mes");
        p.put("text.year", "Año");
        model.setSelected(true);
        JDatePanelImpl datePanel = new JDatePanelImpl(model,p);
		JPanel panel = new JPanel();
		panel.setBounds(109, 114, 212, 40);
		contentPane.add(panel);
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		panel.add(datePicker);
		datePicker.setShowYearButtons(true);
		datePicker.setTextEditable(false);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(6, 64, 89, 14);
		contentPane.add(lblNombre);
		
		textNombre = new JTextField();
		textNombre.setBounds(113, 61, 162, 20);
		contentPane.add(textNombre);
		textNombre.setColumns(10);
		
		JLabel lblNacionalidad = new JLabel("Nacionalidad:");
		lblNacionalidad.setBounds(6, 89, 93, 14);
		contentPane.add(lblNacionalidad);
		
		textNacionalidad = new JTextField();
		textNacionalidad.setBounds(113, 86, 162, 20);
		contentPane.add(textNacionalidad);
		textNacionalidad.setColumns(10);
		
		JLabel lblAo = new JLabel("Fecha Nac.:");
		lblAo.setBounds(6, 125, 89, 14);
		contentPane.add(lblAo);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textNombre.getText().isEmpty() || 
						textNacionalidad.getText().isEmpty()) {
					JOptionPane.showMessageDialog(frmAutor.this,
						    "Debe completar todos los campos.");
				}else {
					Autor autor = new Autor(textNombre.getText(), textNacionalidad.getText(), LocalDate.parse(datePicker.getJFormattedTextField().getText(), DateTimeFormatter.ofPattern("dd-MM-yyyy")));
					if(!textId.getText().isEmpty()) {
						autor.setId(Integer.valueOf(textId.getText()));
					}
					Biblioteca.daoAutor.save(autor);
					//AutoresViewModel.getInstance().getAutores().add(autor);
					frmAutor.this.dispose();
				}
			}
		});
		btnGuardar.setBounds(10, 163, 89, 23);
		contentPane.add(btnGuardar);
		
		JButton btnCancela = new JButton("Cancelar");
		btnCancela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmAutor.this.dispose();
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
		
	}
}
