package com.biblioteca.utils;

import java.awt.Component;

import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import com.biblioteca.model.Autor;

public class AutorCmbRender extends BasicComboBoxRenderer {

	private static final long serialVersionUID = 1L;
	
	 public Component getListCellRendererComponent(
	            JList list, Object value, int index,
	            boolean isSelected, boolean cellHasFocus)
	        {
	            super.getListCellRendererComponent(list, value, index,
	                isSelected, cellHasFocus);

	            if (value != null)
	            {
	                Autor item = (Autor)value;
	                setText( item.getNombre() );
	            }

	            if (index == -1)
	            {
	                Autor item = (Autor)value;
	                setText( "" + item.getId() );
	            }


	            return this;
	        }
	
}
