package InterfazGrafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Actores.AsesorComercial;
import Actores.Supervisor;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import ClasesAbstractas.Usuario;
import ProcesoComercial.Empresa;
import ProcesoComercial.ProcesoComercialMain;

public class FrmProcesoComercial extends JFrame {

	private Empresa oEmpresa;
	private Image frameIcono;
	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu mnInicio;
	private JMenu mnVentas;
	private JMenuItem mntnNuevoFormulario;
	private JMenuItem mntnSalir;
	private JMenuItem mntnMatrizIngresoVenta;
	private JMenuItem mntnMatrizConsultas;
	private JMenuItem mntnReportes;
	private JMenuItem mntnIniciarSesion;
	private JDesktopPane desktopPane;
	private JDialog jDialogLogin;
	private JButton bLogin;
	private JLabel label_user;
	private JLabel label_pass;
	private JTextField campo_user;
	private JPasswordField campo_pass;
	private JMenuItem mntnCrearUsuario;
	private JDialog jDialogCrear;
	private JButton bCrear;
	private JLabel label_newUsuario;
	private JLabel label_newPass;
	private JTextField campo_newUsuario;
	private JPasswordField campo_newPass;
	private JDUsuario oJDIniciarSesion;
	private JDUsuario oJDCrearUsuario;
	private ItnFrmFormularioIngresoVenta oItnFrmFormularioIngresoVenta;
	private ItnFrmMatrizVentas oItnFrmMatrizVentas;
	private JDReporte oJDReporte;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public FrmProcesoComercial() {
		
		this.oEmpresa = new Empresa();
		this.oEmpresa.setoFrmProcesoComercial(getThis());
		this.setoEmpresa(oEmpresa);
		
		this.oJDReporte = new JDReporte();
		
		
		//Se agrega usuarios para prueba
		Usuario oUsuario0 = new Usuario();
		AsesorComercial oAsesorComercial0 = new AsesorComercial();
		oAsesorComercial0.setNombre("ORDO�EZ LOOR SALVADOR ISAI");
		oAsesorComercial0.setCorreoCorporativo("salvador.ordonez@claronegocios.ec");
		oUsuario0.setoAsesorComercial(oAsesorComercial0);
		oAsesorComercial0.setoUsuario(oUsuario0);
		
		Usuario oUsuario1 = new Usuario();
		Supervisor oSupervisor = new Supervisor();
		oSupervisor.setNombre("QUI�ONEZ BIANCA");
		oSupervisor.setCorreoCorporativo("bianca.quinonez@claronegocios.ec");
		oUsuario1.setoSupervisor(oSupervisor);
		oSupervisor.setoUsuario(oUsuario1);
		
		
		oSupervisor.addAsesorComercial(oAsesorComercial0);
		oAsesorComercial0.setoSupervisor(oSupervisor);
		
		this.getoEmpresa().getLstUsuarios().add(oUsuario0);
		this.getoEmpresa().getLstUsuarios().add(oUsuario1);
		this.getoEmpresa().getLstAsesoresComerciales().add(oAsesorComercial0);
		this.getoEmpresa().getLstSupervisores().add(oSupervisor);
		
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		
		// this.frameIcono = new
		// ImageIcon(getClass().getResource("/Recursos/jXFQWIbk_400x400.jpg")).getImage();
		setIconImage(this.frameIcono);

		this.eventoIniciarSesion();
		
		this.setExtendedState(MAXIMIZED_BOTH);
		this.construirMenu();
		this.construirDesktopPane();
		
		this.oItnFrmMatrizVentas = new ItnFrmMatrizVentas();
		this.setoItnFrmMatrizVentas(this.oItnFrmMatrizVentas);
		this.getoItnFrmMatrizVentas().setoFrmProcesoComercial(getThis());
		this.desktopPane.add(this.oItnFrmMatrizVentas);
		
		
	}

	public void construirMenu() {
		this.menuBar = new JMenuBar();
		this.setJMenuBar(this.menuBar);

		this.mnInicio = new JMenu("INICIO");
		this.menuBar.add(mnInicio);

		this.mnVentas = new JMenu("VENTAS");
		this.menuBar.add(mnVentas);

		// MenuItems del menu Inicio (inutilizable)
		this.mntnIniciarSesion = new JMenuItem("INICIAR SESION");
		mntnIniciarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				getThis().oJDIniciarSesion = new JDUsuario("INICIAR_SESION");
				getThis().oJDIniciarSesion.setLocationRelativeTo(getThis());
				getThis().oJDIniciarSesion.setVisible(true);
				getThis().oJDIniciarSesion.setoFrmProcesoComercial(getThis());
				getThis().setoJDIniciarSesion(getThis().oJDIniciarSesion);
				
			}
		});
		//this.mnInicio.add(this.mntnIniciarSesion);

		
		
		this.mntnCrearUsuario = new JMenuItem("CREAR USUARIO");
		mntnCrearUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	//creando el jdialog de crear usuario
				getThis().oJDCrearUsuario = new JDUsuario("CREAR_USUARIO");
				getThis().setoJDCrearUsuario(getThis().oJDCrearUsuario);
				getThis().oJDCrearUsuario.setoFrmProcesoComercial(getThis());
				getThis().oJDCrearUsuario.setVisible(true);
				
				
				
			}
		});
		this.mnInicio.add(this.mntnCrearUsuario);
		this.mntnCrearUsuario.setEnabled(true);	//solo el admin podra crear usuarios
		
		
		this.mntnSalir = new JMenuItem("SALIR");
		mntnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		this.mnInicio.add(this.mntnSalir);

		// MenuItems del menu Ventas
		this.mntnNuevoFormulario = new JMenuItem("INGRESAR NUEVA VENTA");
		mntnNuevoFormulario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				getThis().addItnFrmFormularioIngresoVenta();
			}
		});
		this.mnVentas.add(this.mntnNuevoFormulario);

		this.mntnMatrizIngresoVenta = new JMenuItem("MATRIZ DE VENTAS");
		mntnMatrizIngresoVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addItnFrmMatrizVentas();
			}
		});
		this.mnVentas.add(this.mntnMatrizIngresoVenta);

		this.mntnMatrizConsultas = new JMenuItem("CONSULTA ESTADO DE VENTAS");
		//this.mnVentas.add(this.mntnMatrizConsultas);

		this.mntnReportes = new JMenuItem("REPORTES");
		mntnReportes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getThis().addJDReporte();
			}
		});
		this.mnVentas.add(this.mntnReportes);

		
	}

	public void construirDesktopPane() {
		desktopPane = new JDesktopPane();
		contentPane.add(desktopPane, BorderLayout.CENTER);
	}

	public void addJDReporte() {
		this.oJDReporte.getTxtVentasIngresadas().setText(this.contarVentasIngresadas());
		this.oJDReporte.getTxtVentasOperativas().setText(this.contarVentasOperativas());
		this.oJDReporte.getTxtTotalVentas().setText(this.calcularTotalVentas());
		this.oJDReporte.setVisible(true);
		
	}
	
	public String contarVentasIngresadas() {
		
		int ventasIngresadas = 0;
		
		for (int i=0;i<this.getoEmpresa().getLstVentas().size();i++) {
			ventasIngresadas++;
		}
		
		return Integer.toString(ventasIngresadas);
		
	}
	
	public String contarVentasOperativas() {
		
		int ventasOperativas = 0;
		
		for (int i=0;i<this.getoEmpresa().getLstVentas().size();i++) {
			if (this.getoEmpresa().getLstVentas().get(i).getEstadoVenta().equals("OPERATIVO/ALTA")) {
				ventasOperativas++;
			}
		}
			
		return Integer.toString(ventasOperativas);
	}
	
	public String calcularTotalVentas() {
		int totalVentas = 0;
		
		for (int i=0;i<this.getoEmpresa().getLstVentas().size();i++) {
			if (this.getoEmpresa().getLstVentas().get(i).getEstadoVenta().equals("OPERATIVO/ALTA")) {
				totalVentas += this.getoEmpresa().getLstVentas().get(i).getValorVenta();
			}
		}
			
		return Integer.toString(totalVentas);
	}
	
	// Funciones que agregan internal frames al desktop pane
	public void addItnFrmFormularioIngresoVenta() {
		
		this.oItnFrmFormularioIngresoVenta = new ItnFrmFormularioIngresoVenta(getThis());
		
		this.setoItnFrmFormularioIngresoVenta(this.oItnFrmFormularioIngresoVenta);
		this.oItnFrmFormularioIngresoVenta.setoFrmProcesoComercial(getThis());
		
		this.desktopPane.add(oItnFrmFormularioIngresoVenta);
		
		Dimension desktopSize = this.desktopPane.getSize();
		Dimension FrameSize = oItnFrmFormularioIngresoVenta.getSize();
		
		oItnFrmFormularioIngresoVenta.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
		oItnFrmFormularioIngresoVenta.setVisible(true);
		
		/*if(this.getoItnFrmFormularioIngresoVenta()!=null) {
			JOptionPane.showMessageDialog(null, "Solo puede ingresar una venta a la vez.\nCierre la anterior ventana para ingresar una nueva venta.", 
					"Error ingreso de venta", JOptionPane.ERROR_MESSAGE);
		} else {
			this.oItnFrmFormularioIngresoVenta = new ItnFrmFormularioIngresoVenta(getThis());
			
			this.setoItnFrmFormularioIngresoVenta(this.oItnFrmFormularioIngresoVenta);
			this.oItnFrmFormularioIngresoVenta.setoFrmProcesoComercial(getThis());
			
			this.desktopPane.add(oItnFrmFormularioIngresoVenta);
			
			Dimension desktopSize = this.desktopPane.getSize();
			Dimension FrameSize = oItnFrmFormularioIngresoVenta.getSize();
			
			oItnFrmFormularioIngresoVenta.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
			oItnFrmFormularioIngresoVenta.setVisible(true);
			
			
		}*/
		
		
	}
	
	public void cerrarFormularioIngresoVentas() {
		this.getoItnFrmFormularioIngresoVenta().setVisible(false);
		
		
	}
	
	public void cerrarCrearUsuario() {
		this.oJDCrearUsuario.setVisible(false);
	}

	public void addItnFrmMatrizVentas() {
		if(this.getoItnFrmMatrizVentas().isVisible()) {
			JOptionPane.showMessageDialog(null, "Solo puede tener una matriz de ventas abierta a la vez.", 
					"Error de matriz de ventas", JOptionPane.ERROR_MESSAGE);
		}else {
			
			this.oItnFrmMatrizVentas.setVisible(true);
			this.oItnFrmMatrizVentas.setSize(this.desktopPane.getSize());
			this.oItnFrmMatrizVentas.setLocation(0,0);
		}
	}
	
	public void cerrarMatrizVentas() {
		this.oItnFrmMatrizVentas.setVisible(false);
		
	}
	
	public void eventoIniciarSesion() {
		getThis().oJDIniciarSesion = new JDUsuario("INICIAR_SESION");
		getThis().setoJDIniciarSesion(getThis().oJDIniciarSesion);
		getThis().oJDIniciarSesion.setoFrmProcesoComercial(getThis());
		getThis().oJDIniciarSesion.setLocationRelativeTo(getThis());
		getThis().oJDIniciarSesion.setVisible(true);
		
	}
	
	
	
	public void almacenarDatosUsuario(String tipoUsuario) {
		switch (tipoUsuario) {
		case "SUPERVISOR":
			
			break;
			
		case "ASESOR":
			break;
		case "OPERADOR":
			break;
		}
	}
	
	
	public void eventoSesionAdministrador() {
		System.out.println("Si esta llamando a la webada");
		this.getoJDIniciarSesion().setVisible(false);
		
	}
	
	
	public JDUsuario getoJDIniciarSesion() {
		return oJDIniciarSesion;
	}

	public void setoJDIniciarSesion(JDUsuario oJDUsuario) {
		this.oJDIniciarSesion = oJDUsuario;
	}
	
	

	public JDUsuario getoJDCrearUsuario() {
		return oJDCrearUsuario;
	}

	public void setoJDCrearUsuario(JDUsuario oJDCrearUsuario) {
		this.oJDCrearUsuario = oJDCrearUsuario;
	}

	public FrmProcesoComercial getThis() {
		return this;
	}

	public Empresa getoEmpresa() {
		return oEmpresa;
	}

	public void setoEmpresa(Empresa oEmpresa) {
		this.oEmpresa = oEmpresa;
	}

	public ItnFrmFormularioIngresoVenta getoItnFrmFormularioIngresoVenta() {
		return oItnFrmFormularioIngresoVenta;
	}

	public void setoItnFrmFormularioIngresoVenta(ItnFrmFormularioIngresoVenta oItnFrmFormularioIngresoVenta) {
		this.oItnFrmFormularioIngresoVenta = oItnFrmFormularioIngresoVenta;
	}

	public ItnFrmMatrizVentas getoItnFrmMatrizVentas() {
		return oItnFrmMatrizVentas;
	}

	public void setoItnFrmMatrizVentas(ItnFrmMatrizVentas oItnFrmMatrizVentas) {
		this.oItnFrmMatrizVentas = oItnFrmMatrizVentas;
	}

	public JMenuItem getMntnReportes() {
		return mntnReportes;
	}

	public void setMntnReportes(JMenuItem mntnReportes) {
		this.mntnReportes = mntnReportes;
	}

	public JDReporte getoJDReporte() {
		return oJDReporte;
	}

	public void setoJDReporte(JDReporte oJDReporte) {
		this.oJDReporte = oJDReporte;
	}
	
	
	
	
	

}
