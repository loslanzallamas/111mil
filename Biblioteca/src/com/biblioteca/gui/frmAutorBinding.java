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
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;

public class frmAutorBinding extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textNombre;
	private JTextField textNacionalidad;
	private JTextField textId;
	private JDatePickerImpl datePicker;
	
	private Autor autor;
	
	public Autor getAutor() {
		return autor;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmAutorBinding frame = new frmAutorBinding();
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
	public frmAutorBinding(Autor autor) {
		this();
		initDataBindings();
		this.autor = autor;
		textId.setText(autor.getId().toString());
		//textNombre.setText(autor.getNombre());
		textNacionalidad.setText(autor.getNacionalidad());
		datePicker.getJFormattedTextField().setText(autor.getFechaNac().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
	}
	
	
	public frmAutorBinding() {
		setTitle("Autor");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 342, 300);
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
		datePicker.getJFormattedTextField().setEditable(true);
		datePicker.setShowYearButtons(true);
		datePicker.setTextEditable(true);
		
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
				System.out.println(datePicker.getJFormattedTextField().getText());
				if(!textNombre.getText().equals("") && textNombre.getText() != null && 
						!textNacionalidad.getText().equals("") && textNacionalidad.getText() != null) {
					Autor autor = new Autor(textNombre.getText(), textNacionalidad.getText(), LocalDate.parse(datePicker.getJFormattedTextField().getText(), DateTimeFormatter.ofPattern("dd-MM-yyyy")));
					if(!textId.getText().isEmpty()) {
						autor.setId(Integer.valueOf(textId.getText()));
					}
					Biblioteca.daoAutor.save(autor);
					//AutoresViewModel.getInstance().getAutores().add(autor);
				} else {
					JOptionPane.showMessageDialog(frmAutorBinding.this,
						    "Debe completar todos los campos.");
				}
			}
		});
		btnGuardar.setBounds(10, 163, 89, 23);
		contentPane.add(btnGuardar);
		
		JButton btnCancela = new JButton("Cancelar");
		btnCancela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frmAutorBinding.this, autor.getNombre());
				frmAutorBinding.this.dispose();
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
	protected void initDataBindings() {
		BeanProperty<Autor, String> autorBeanProperty = BeanProperty.create("nombre");
		BeanProperty<JTextField, String> jTextFieldBeanProperty = BeanProperty.create("text");
		AutoBinding<Autor, String, JTextField, String> autoBinding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, autor, autorBeanProperty, textNombre, jTextFieldBeanProperty);
		autoBinding.bind();
	}
}
