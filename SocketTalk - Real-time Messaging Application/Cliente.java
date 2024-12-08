package com.proyectoSocket;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

import javax.swing.*;

public class Cliente {
	
	public static void main(String[] args) {
		laminaCliente indexCliente = new laminaCliente();
		indexCliente.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}

class laminaCliente extends JFrame{
	
	public laminaCliente() {
		setBounds(200,100,300,400);
		setTitle("Cliente");
		setVisible(true);
		elementos laminaElement = new elementos();
		add(laminaElement);
		addWindowListener(new EventoVentana());
	}

	
}
// Capturing window events------------
class EventoVentana extends WindowAdapter{
	public void windowOpened(WindowEvent event) {
		try{
			Socket conextion = new Socket("192.168.15.10", 9999);
			DatosEmp datosEnviar = new DatosEmp();
			ObjectOutputStream PaquetEviado = new ObjectOutputStream(conextion.getOutputStream());
			datosEnviar.setMessagem("online");
			PaquetEviado.writeObject(datosEnviar);
			conextion.close();
		}catch(Exception e) {
			System.out.println("No fue posible establecer conexion");
		}
		
	}
}
//-----------------------------------------------------

class elementos extends JPanel implements Runnable {
    public elementos() {
    	
    	// Interface ChatBox
    	
    	JLabel nick_n = new JLabel("Nick: ");
    	add(nick_n);
    	String nickCliente = JOptionPane.showInputDialog("Nick");
    	nickP = new JLabel();
    	nickP.setText(nickCliente);
    	add(nickP);
        add(new JLabel("Online: "));
        ipsMenu = new JComboBox();
        add(ipsMenu);
        add(campoChat = new JTextArea(15,50));
        add(text = new JTextField(20));
        //-------------------------------------------------------------
        
        //Button --> Listener
        enviar = new JButton("Enviar");
        enviar.addActionListener(e -> {
        	
        	campoChat.append(text.getText());
        		
        	try {
        		
        		Socket conextion = new Socket("192.168.15.10", 9999);
            	DatosEmp myDatos = new DatosEmp();
            	campoChat.append("\n" +"Nick: " + myDatos.getName() + ": " + myDatos.getMessagem());
            	myDatos.setName(nickP.getText());
            	myDatos.setIp(ipsMenu.getSelectedItem().toString());
            	myDatos.setMessagem(text.getText());
            	
                ObjectOutputStream salida = new ObjectOutputStream(conextion.getOutputStream());
                
                salida.writeObject(myDatos);
                conextion.close();
                
            } catch (IOException e1) {
                System.out.println("No se pudo establecer la conexión.");
                e1.printStackTrace();
            }
        	
        	text.setText("");
        });
        add(enviar);
        
        Thread myHilo = new Thread(this);
        myHilo.start();
    }
   
	@Override
	public void run() {
    	try (ServerSocket portServer = new ServerSocket(9090)) {
			DatosEmp entradaServidor;
			Socket myPort;
			
			while(true) {
				myPort = portServer.accept();
				ObjectInputStream entradaDatos = new ObjectInputStream(myPort.getInputStream());
				entradaServidor = (DatosEmp) entradaDatos.readObject();
				
				
				if(!entradaServidor.getMessagem().equals("online")) {
					campoChat.append("\n" + entradaServidor.getName() + " :" + entradaServidor.getMessagem());
					
				}else {
					
					ArrayList<String> myListIps = new ArrayList<String>();
					myListIps = entradaServidor.getMyListIps();
					
					ipsMenu.removeAllItems();
					
					for (String e : myListIps) {
						ipsMenu.addItem(e);
					}
				}
				
				entradaDatos.close();
				myPort.close();
			}
			
			
		} catch (IOException | ClassNotFoundException e) {


			e.printStackTrace();
		}
		
	}
    
    
    // Variables
    private JTextField text;
    private JLabel nickP;
    private JComboBox ipsMenu;
    private JTextArea campoChat;
    private JButton enviar;
}

// class of methods
class DatosEmp implements Serializable{
	private String name, ip, messagem;
	private ArrayList<String> myListIps;

	public String getName() {
		return name;
	}

	public ArrayList<String> getMyListIps() {
		return myListIps;
	}

	public void setMyListIps(ArrayList<String> myListIps) {
		this.myListIps = myListIps;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMessagem() {
		return messagem;
	}

	public void setMessagem(String messagem) {
		this.messagem = messagem;
	}

	
	
	
}

