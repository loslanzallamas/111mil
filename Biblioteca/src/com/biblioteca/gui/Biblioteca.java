package com.biblioteca.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.biblioteca.dao.DaoImpl;
import com.biblioteca.model.Autor;
import com.biblioteca.model.Cliente;
import com.biblioteca.model.Copia;
import com.biblioteca.model.Libro;
import com.biblioteca.model.Prestamo;
import com.biblioteca.repositories.AutorRepository;
import com.biblioteca.viewmodel.AutoresViewModel;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenu;

public class Biblioteca extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	public static DaoImpl<Autor> daoAutor = new DaoImpl<>(Autor.class);
	public static DaoImpl<Libro> daoLibro = new DaoImpl<>(Libro.class);
	public static DaoImpl<Cliente> daoCliente = new DaoImpl<>(Cliente.class);
	public static DaoImpl<Copia> daoCopia = new DaoImpl<>(Copia.class);
	public static DaoImpl<Prestamo> daoPrestamo = new DaoImpl<>(Prestamo.class);
	
	private static SessionFactory sessionFactory;
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				 .configure("hibernate.cfg.xml") // obtiene los valores de hibernate.cfg.xml
				 .build();
		
		try {
			 sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
			}
			catch (Exception e) {
			 // La variable registry será destruída al crear el SessionFactory,
			 // pero como han surgido problemas en el proceso de creación lo hacemos
			//manualmente
			 StandardServiceRegistryBuilder.destroy(registry );
			 System.out.println(e.getCause());
			 System.out.println(e.getMessage());
			} 

		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Biblioteca frame = new Biblioteca();
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
	public Biblioteca() {
		setTitle("Biblioteca");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 434, 21);
		contentPane.add(menuBar);
		
		JMenu mnAdministracin = new JMenu("Administraci\u00F3n");
		menuBar.add(mnAdministracin);
		
		JMenuItem mntmAbmLibro = new JMenuItem("ABM Libro");
		mnAdministracin.add(mntmAbmLibro);
		
		JMenuItem mntmAbmAutor = new JMenuItem("ABM Autor");
		mntmAbmAutor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ABMAutores abmAutores = new ABMAutores();
				abmAutores.setVisible(true);
			}
		});
		mnAdministracin.add(mntmAbmAutor);
		
		JMenuItem mntmAbmCliente = new JMenuItem("ABM Cliente");
		mntmAbmCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ABMClientes abmClientes = new ABMClientes();
				abmClientes.setVisible(true);
			}
		});
		mnAdministracin.add(mntmAbmCliente);
		
		JMenuItem mntmAbmCopia = new JMenuItem("ABM Copia");
		mntmAbmCopia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ABMCopias abmCopias = new ABMCopias();
				abmCopias.setVisible(true);
			}
		});
		mnAdministracin.add(mntmAbmCopia);
		
		JMenu mnPrestamos = new JMenu("Prestamos");
		menuBar.add(mnPrestamos);
		
		JMenuItem mntmReservarPrstamo = new JMenuItem("Reservar Pr\u00E9stamo");
		mntmReservarPrstamo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmPrestamo frmprestamo = new frmPrestamo();
				frmprestamo.setVisible(true);
			}
		});
		mnPrestamos.add(mntmReservarPrstamo);
		
		JMenuItem mntmDevolverLibro = new JMenuItem("Devolver Libro");
		mnPrestamos.add(mntmDevolverLibro);
		
		JMenuItem mntmVerPrstamos = new JMenuItem("Ver Pr\u00E9stamos");
		mntmVerPrstamos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ABMPrestamos abmPrestamos = new ABMPrestamos();
				abmPrestamos.setVisible(true);
			}
		});
		mnPrestamos.add(mntmVerPrstamos);
		mntmAbmLibro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ABMLibros abmLibros = new ABMLibros();
				abmLibros.setVisible(true);
			}
		});
	}
}
