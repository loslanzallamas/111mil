package com.biblioteca.gui.binding;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.BindingGroup;
import org.jdesktop.beansbinding.Bindings;

public class Biblioteca extends JPanel {

	private BindingGroup m_bindingGroup;
	private com.biblioteca.model.Autor autor = new com.biblioteca.model.Autor();
	private JSlider idJSlider;
	private JTextField nacionalidadJTextField;
	private JTextField nombreJTextField;

	public Biblioteca(com.biblioteca.model.Autor newAutor) {
		this();
		setAutor(newAutor);
	}

	public Biblioteca() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 1.0E-4 };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0E-4 };
		setLayout(gridBagLayout);

		JLabel idLabel = new JLabel("Id:");
		GridBagConstraints labelGbc_0 = new GridBagConstraints();
		labelGbc_0.insets = new Insets(5, 5, 5, 5);
		labelGbc_0.gridx = 0;
		labelGbc_0.gridy = 0;
		add(idLabel, labelGbc_0);

		idJSlider = new JSlider();
		GridBagConstraints componentGbc_0 = new GridBagConstraints();
		componentGbc_0.insets = new Insets(5, 0, 5, 5);
		componentGbc_0.fill = GridBagConstraints.HORIZONTAL;
		componentGbc_0.gridx = 1;
		componentGbc_0.gridy = 0;
		add(idJSlider, componentGbc_0);

		JLabel nacionalidadLabel = new JLabel("Nacionalidad:");
		GridBagConstraints labelGbc_1 = new GridBagConstraints();
		labelGbc_1.insets = new Insets(5, 5, 5, 5);
		labelGbc_1.gridx = 0;
		labelGbc_1.gridy = 1;
		add(nacionalidadLabel, labelGbc_1);

		nacionalidadJTextField = new JTextField();
		GridBagConstraints componentGbc_1 = new GridBagConstraints();
		componentGbc_1.insets = new Insets(5, 0, 5, 5);
		componentGbc_1.fill = GridBagConstraints.HORIZONTAL;
		componentGbc_1.gridx = 1;
		componentGbc_1.gridy = 1;
		add(nacionalidadJTextField, componentGbc_1);

		JLabel nombreLabel = new JLabel("Nombre:");
		GridBagConstraints labelGbc_2 = new GridBagConstraints();
		labelGbc_2.insets = new Insets(5, 5, 5, 5);
		labelGbc_2.gridx = 0;
		labelGbc_2.gridy = 2;
		add(nombreLabel, labelGbc_2);

		nombreJTextField = new JTextField();
		GridBagConstraints componentGbc_2 = new GridBagConstraints();
		componentGbc_2.insets = new Insets(5, 0, 5, 5);
		componentGbc_2.fill = GridBagConstraints.HORIZONTAL;
		componentGbc_2.gridx = 1;
		componentGbc_2.gridy = 2;
		add(nombreJTextField, componentGbc_2);

		if (autor != null) {
			m_bindingGroup = initDataBindings();
		}
	}

	protected BindingGroup initDataBindings() {
		BeanProperty<com.biblioteca.model.Autor, java.lang.Integer> idProperty = BeanProperty.create("id");
		BeanProperty<javax.swing.JSlider, java.lang.Integer> valueProperty = BeanProperty.create("value");
		AutoBinding<com.biblioteca.model.Autor, java.lang.Integer, javax.swing.JSlider, java.lang.Integer> autoBinding = Bindings
				.createAutoBinding(AutoBinding.UpdateStrategy.READ, autor, idProperty, idJSlider, valueProperty);
		autoBinding.bind();
		//
		BeanProperty<com.biblioteca.model.Autor, java.lang.String> nacionalidadProperty = BeanProperty
				.create("nacionalidad");
		BeanProperty<javax.swing.JTextField, java.lang.String> textProperty = BeanProperty.create("text");
		AutoBinding<com.biblioteca.model.Autor, java.lang.String, javax.swing.JTextField, java.lang.String> autoBinding_1 = Bindings
				.createAutoBinding(AutoBinding.UpdateStrategy.READ, autor, nacionalidadProperty, nacionalidadJTextField,
						textProperty);
		autoBinding_1.bind();
		//
		BeanProperty<com.biblioteca.model.Autor, java.lang.String> nombreProperty = BeanProperty.create("nombre");
		BeanProperty<javax.swing.JTextField, java.lang.String> textProperty_1 = BeanProperty.create("text");
		AutoBinding<com.biblioteca.model.Autor, java.lang.String, javax.swing.JTextField, java.lang.String> autoBinding_2 = Bindings
				.createAutoBinding(AutoBinding.UpdateStrategy.READ, autor, nombreProperty, nombreJTextField,
						textProperty_1);
		autoBinding_2.bind();
		//
		BindingGroup bindingGroup = new BindingGroup();
		bindingGroup.addBinding(autoBinding);
		bindingGroup.addBinding(autoBinding_1);
		bindingGroup.addBinding(autoBinding_2);
		//
		return bindingGroup;
	}

	public com.biblioteca.model.Autor getAutor() {
		return autor;
	}

	public void setAutor(com.biblioteca.model.Autor newAutor) {
		setAutor(newAutor, true);
	}

	public void setAutor(com.biblioteca.model.Autor newAutor, boolean update) {
		autor = newAutor;
		if (update) {
			if (m_bindingGroup != null) {
				m_bindingGroup.unbind();
				m_bindingGroup = null;
			}
			if (autor != null) {
				m_bindingGroup = initDataBindings();
			}
		}
	}

}
