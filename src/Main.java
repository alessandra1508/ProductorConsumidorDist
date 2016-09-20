import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JLabel;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.awt.Color;

public class Main extends JFrame {

	private JPanel contentPane;
	private static Thread productor;
	private static Thread consumidor;
	
	final static int PUERTO=5001;
	
	static BufferedReader entrada;
	static DataOutputStream salida;
	
	
	JLabel l_numero = new JLabel("0");
	JLabel l_Productor = new JLabel("Productor >>");
	JLabel l_Consumidor = new JLabel("<< Consumidor");
	private final static JLabel l_conexion = new JLabel("-");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws IOException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				try {
					Main frame = new Main();
					frame.setVisible(true);
					
					Bodega bodega = new Bodega();
					
					ServerSocket s1= new ServerSocket(PUERTO);
					l_conexion.setText("Esperando una conexión...");
					
					Socket so = s1.accept();		
					System.out.println("Un cliente se ha conectado");
					
					entrada = new BufferedReader(new InputStreamReader(so.getInputStream()));
					salida = new DataOutputStream(so.getOutputStream());
					
					salida.writeUTF("Conexion exitosa.\r\n Quien eres?\r\n");
					
					String mensajeRecibido = entrada.readLine();
					
					System.out.println(mensajeRecibido);
					
					if(mensajeRecibido.equals("Productor"))
					{
						productor = new Thread(new Productor(bodega,frame));
						productor.start();
					}else{
						consumidor = new Thread(new Consumidor(bodega, frame));
						consumidor.start();						
					}
					
					salida.writeUTF("Terminando conexion...\r\n");
					salida.writeUTF("Gracias por conectarte.");
					
					System.out.println("Cerrando conexión...");
					
					s1.close();	
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 504, 365);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);	
		
		l_numero.setFont(new Font("Tahoma", Font.PLAIN, 29));
		l_numero.setBounds(218, 89, 48, 47);
		contentPane.add(l_numero);
		
		JLabel lblProductos = new JLabel("Productos:");
		lblProductos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblProductos.setBounds(191, 59, 80, 19);
		contentPane.add(lblProductos);
		
		l_Productor.setVisible(false);
		l_Productor.setForeground(Color.BLUE);
		l_Productor.setFont(new Font("Tahoma", Font.PLAIN, 20));
		l_Productor.setBounds(21, 94, 139, 30);
		contentPane.add(l_Productor);
		
		
		l_Consumidor.setForeground(Color.RED);
		l_Consumidor.setFont(new Font("Tahoma", Font.PLAIN, 20));
		l_Consumidor.setBounds(313, 94, 165, 30);
		contentPane.add(l_Consumidor);
		l_conexion.setBounds(203, 11, 46, 14);
		
		contentPane.add(l_conexion);
	}
	
	public void setLabelTextProductor(String txt)
	{
		 l_Consumidor.setVisible(false);
		 l_Productor.setVisible(true);
	     l_numero.setText(txt);
	     l_numero.repaint();
	}
	
	public void setLabelTextConsumidor(String txt)
	{
		 l_Productor.setVisible(false);
		 l_Consumidor.setVisible(true);
	     l_numero.setText(txt);
	     l_numero.repaint();
	}
}
