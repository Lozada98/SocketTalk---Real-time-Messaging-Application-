package com.proyectoSocket;

import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

import javax.swing.*;

public class servidor {

	public static void main(String[] args) {
		
		laminaServidor indexServidor = new laminaServidor();
		indexServidor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
class laminaServidor extends JFrame implements Runnable{
	
	public laminaServidor() {
		setBounds(800,100,300,400);
		JPanel miLamina=  new JPanel();
		miLamina.setLayout(new BorderLayout());
		text = new JTextArea();
		miLamina.add(text,BorderLayout.CENTER);
		add(miLamina);
		setTitle("Servidor");
		setVisible(true);
		
		Thread actionHilo = new Thread(this);
		actionHilo.start();
		
		
	}
	
	@Override
	public void run() {
	    try (ServerSocket portServer = new ServerSocket(9999)) {
	    	DatosEmp paquetes_envio;
	    	String nick, ip, msg;
	        while (true) {
	        	
	            try (Socket myPort = portServer.accept();
	                 ObjectInputStream entrada = new ObjectInputStream(myPort.getInputStream())) {
	            	 paquetes_envio = (DatosEmp) entrada.readObject();
                	 nick =  paquetes_envio.getName();
	                 ip = paquetes_envio.getIp();
	                 msg = paquetes_envio.getMessagem();
	                 
	                 text.append("\n" + nick + ": " + msg + " Para" + ip);
	                 
	                 ArrayList<String>ListIp = new ArrayList<String>();
	                 
	                 if(msg.equals("online")) {
	                	
		                 text.append("\n" + nick + ": " + msg);
		                 
		                 //Packing the package for shipping
		                 
		                 Socket enviarPaquete = new Socket(ip,9090);
		                 ObjectOutputStream salidaPaquete = new ObjectOutputStream(enviarPaquete.getOutputStream());
		                 salidaPaquete.writeObject(paquetes_envio);
		                 salidaPaquete.close();
		                 enviarPaquete.close();
		                 //---------------------------------------------
	                 }else {
	                	// Code to detect online clients
	     	        	
	 	    	        InetAddress ReceptorIp = myPort.getInetAddress();
	 	    	        
	 	    	        String addIp = ReceptorIp.getHostAddress();
	 	    	        ListIp.add(addIp);
	 	    	        paquetes_envio.setMyListIps(ListIp);
	 	    	        
	 	    	        
	 	    	        // Test IP online
	 	    	        
	 	    	        for(String e: ListIp) {
	 	    	        	System.out.println("Online: " + e);
	 	    	        	 Socket enviarPaquete = new Socket(e,9090);
			                 ObjectOutputStream salidaPaquete = new ObjectOutputStream(enviarPaquete.getOutputStream());
			                 salidaPaquete.writeObject(paquetes_envio);
			                 salidaPaquete.close();
			                 enviarPaquete.close();
	 	    	        }
	 	    	        //----------------------------------------------
	                 }
	                 
	            } catch (IOException | ClassNotFoundException e) {
	                System.out.println("Error procesando la conexión.");
	                e.printStackTrace();
	            }
	        }
	    } catch (IOException e) {
	        System.out.println("No se pudo establecer el servidor.");
	        e.printStackTrace();
	    }
	}

	
	private JTextArea text;

	
}