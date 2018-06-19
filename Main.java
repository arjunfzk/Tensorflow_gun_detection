import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Main {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MainWindow();
			}
		});

	}

}

class MainWindow extends JFrame {
	
	private JTextField uname;
	private JTextField upass;
	private JButton login;

	
	public MainWindow() {
		super("Smart Surviellance");
		setLayout(null);
		uname = new JTextField("Username");
		add(uname);
		uname.setBounds(30, 60, 200, 30);
		upass = new JPasswordField("password");
		upass.setBounds(30, 120, 200, 30);
		add(upass);
		login = new JButton("Login");
		login.setBounds(97, 170, 80, 35);
		add(login);
		
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (uname.getText().equals("uk") && upass.getText().equals("1234")) {
					new SceneWindow(uname.getText());
					setVisible(false);
				}
			}
		});
		
		BufferedImage myPicture;
		try {
			myPicture = ImageIO.read(new File("Logo.png"));
			JLabel picLabel = new JLabel(new ImageIcon(myPicture));
			add(picLabel);
			picLabel.setBounds(240, 35, 970, 500);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(30, 40, 1000, 600);
	}
}

class SceneWindow extends JFrame {
	
	//Attrs
	private String connBtnStatus = "Disconnect";
	
	
	//Left Components
	private JButton personDetection;
	private JButton childDetection;
	private JButton weaponDetection;
	private JButton LRDetection;
	private JButton objectDetection;
	private JButton logout;
	
	private JLabel infoLabel;
	
	
	//Right Components
	private JLabel nodeid;
	private JTextField t_nodeid;
	
	private JLabel uname;
	private JTextField t_uname;
	
	private JLabel internalIp;
	private JTextField t_internalIp;
	
	private JLabel externalIp;
	private JTextField t_externalIp;
	
	private JLabel hash;
	private JTextField t_hash;
	
	private JLabel instanceId;
	private JTextField t_instanceId;
	
	private JButton connect;
	private JLabel connStatus;
	
	private JLabel nodes;
	private JTextField t_nodes;
	
	private JLabel connectionQuality;
	private JTextField t_connectionQuality;
	
	public SceneWindow(String name) {
		super("Smart Surviellance");
		setLayout(null);

		
		//Right Side Components
		infoLabel = new JLabel("Welcome to Smart Survelliance");
		infoLabel.setBounds(600, 30, 250, 40);
		
		nodeid = new JLabel("Node id :  ");
		nodeid.setBounds(550, 70, 100, 40);
		
		t_nodeid = new JTextField();
		t_nodeid.setEditable(false);
		t_nodeid.setText("");
		t_nodeid.setBounds(700, 75, 150, 30);
		
		
		uname = new JLabel("Client Name : ");
		uname.setBounds(550, 110, 100, 40);
		
		t_uname = new JTextField(name);
		t_uname.setEditable(false);
		t_uname.setText("");
		t_uname.setBounds(700, 115, 150, 30);
		
		internalIp = new JLabel("Internal IP Address : ");
		internalIp.setBounds(550, 147, 150, 30);
		
		t_internalIp = new JTextField("NULL");
		t_internalIp.setEditable(false);
		t_internalIp.setText("");
		t_internalIp.setBounds(700, 150, 150, 30);
		
		externalIp = new JLabel("External IP Address : ");
		externalIp.setBounds(550, 190, 150, 30);
		
		
		t_externalIp = new JTextField("NULL");
		t_externalIp.setEditable(false);
		t_externalIp.setText("");
		t_externalIp.setBounds(700, 190, 150, 30);
		
		hash = new JLabel("Hash : ");
		hash.setBounds(550, 225, 150, 30);
		
		t_hash = new JTextField("NULL");
		t_hash.setEditable(false);
		t_hash.setText("");
		t_hash.setBounds(700, 225, 150, 30);
		
		instanceId = new JLabel("Instance ID : ");
		instanceId.setBounds(550, 265, 150, 30);
		
		
		t_instanceId = new JTextField("NULL");
		t_instanceId.setEditable(false);
		t_instanceId.setText("");
		t_instanceId.setBounds(700, 265, 150, 30);
		
		
		
		add(infoLabel);
		add(nodeid);
		add(t_nodeid);
		add(uname);
		add(t_uname);
		add(internalIp);
		add(t_internalIp);
		add(externalIp);
		add(t_externalIp);
		add(hash);
		add(t_hash);
		add(instanceId);
		add(t_instanceId);
		
		//Connection Status
		connStatus = new JLabel("You are not connected");
		connStatus.setBounds(560, 365, 200, 40);
		add(connStatus);
		
		//Connect
		connect = new JButton("Connect");
		connect.setBounds(580, 450, 100, 60);
		add(connect);
		
		nodes = new JLabel("The number of connected Nodes are : ");
		nodes.setBounds(370, 540, 250, 40);
		add(nodes);
		
		t_nodes = new JTextField("");
		t_nodes.setEditable(false);
		t_nodes.setText("0");
		t_nodes.setBounds(620, 545, 40, 30);
		add(t_nodes);
		
		//Connection Quality stuff
		connectionQuality = new JLabel("Connection quality : ");
		connectionQuality.setBounds(550, 300, 200, 40);
		add(connectionQuality);
	
		t_connectionQuality = new JTextField("");
		t_connectionQuality.setEditable(false);
		t_connectionQuality.setText("");
		t_connectionQuality.setBounds(700, 300, 150, 30);
		add(t_connectionQuality);
		
		//End of right side components
		
		//Beginning of Left Side components
		
		
		logout = new JButton("Logout");
		logout.setBounds(60, 450, 90, 40);
		add(logout);
		
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e2) {
				setVisible(false);
				new MainWindow();
			}
		});
		
		objectDetection = new JButton("Object Detection");
		add(objectDetection);
		objectDetection.setBounds(45, 110, 120, 35);
		
		objectDetection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e4) {
				System.out.println("Hello");
				String response = executeCommand("python ac.py --person");
				

				System.out.println(response);
				System.out.println("After Command");
			}
		});
		
		
		personDetection = new JButton("Person Detection");
		add(personDetection);
		personDetection.setBounds(45, 160, 120, 35);
		personDetection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = executeCommand("python ac-Copy.py");
				System.out.println(s);
				System.out.println("After command");
			}
		});
		
		childDetection = new JButton("Child Detection");
		add(childDetection);
		childDetection.setBounds(45, 210, 120, 35);
		
		weaponDetection = new JButton("Weapon Detection");
		add(weaponDetection);
		weaponDetection.setBounds(45, 260, 120, 35);
		weaponDetection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executeCommand("Python xxxx.py");
			}
		});
		
		LRDetection = new JButton("LR Detection");
		//add(LRDetection);
		LRDetection.setBounds(45, 310, 120, 35);
		
		//End of Left Side components
		
		//ActionListeners of the buttons
		
		connect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Action of the Button to connect to the server
				if(connBtnStatus.equals("Disconnect")) {
					
					
					//All this looks nice only when I get a positive response from the Server
					
//					try {
//						if(new Client().connect(uname.getText(), "1234")) {
//							System.out.println("Login Successful");
//						}
//					} catch(Exception e3) {
//						e3.printStackTrace();
//					}
					
					
					connStatus.setText("You are now connected");
					connect.setText("Disconnect");
					connBtnStatus = "Connect";
					t_nodes.setText("0");
					//Username Area
					
					t_uname.setText(name);
					
					//NodeId
					t_nodeid.setText(String.valueOf((int)(Math.random() * 10)));
					
					
					//Instance Id
					t_instanceId.setText(String.valueOf(((int)(Math.random() * 10000))));
					//IP address Area
					float networkLatency = doPing();
					if(networkLatency < 400) {
						t_connectionQuality.setText("Excellent");
					}
					else if(networkLatency < 700) {
						t_connectionQuality.setText("Average");
					}
					else if(networkLatency < 1000) {
						t_connectionQuality.setText("Poor");
					}
					else {
						t_connectionQuality.setText("Very Poor");
					}
					
					
					InetAddress localhost;
					try {
						localhost = InetAddress.getLocalHost();
						String display_internal = localhost.toString().substring(26, localhost.toString().length());
						t_internalIp.setText(display_internal);
						System.out.println(display_internal);
				        
				        String systemipaddress = "";
				        try {
				            URL url_name = new URL("http://bot.whatismyipaddress.com");
				 
				            BufferedReader sc =
				            new BufferedReader(new InputStreamReader(url_name.openStream()));
				 
				            // reads system IPAddress
				            systemipaddress = sc.readLine().trim();
				            t_externalIp.setText(systemipaddress.toString());
				        }
				        catch (Exception e1) {
				            systemipaddress = "Null";
				            connStatus.setText("No Internet Connection");
				            connect.setText("Connect");
				            t_nodeid.setText("");
				            t_uname.setText("");
				            t_nodes.setText("0");
				            
				        }
					} catch (UnknownHostException e2) {
						
					}
				}
				
				//To disconnect from the server
				else {
					connStatus.setText("Not connected");
					connect.setText("Connect");
					connBtnStatus = "Disconnect";
					
					//Setting all the values to NULL
					t_nodes.setText("0");
					t_uname.setText("");
					t_nodeid.setText("");
					t_externalIp.setText("");
					t_internalIp.setText("");
					t_instanceId.setText("");
					t_connectionQuality.setText("");
				}
				
			}
		});
		
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(30, 40, 1000, 600);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
        Line2D lin = new Line2D.Float(250, 10, 250, 600);
        g2.draw(lin);
	}
	
	public float doPing() {
		Socket s = new Socket();
		SocketAddress a = new InetSocketAddress("www.google.com", 80);
		int timeoutMillis = 2000;
		long start = System.currentTimeMillis();
		float latency = 0;
		try {
		    s.connect(a, timeoutMillis);
		    long stop = System.currentTimeMillis();
			latency = (stop - start);
		} catch (SocketTimeoutException e) {
		    // timeout
		} catch (IOException e) {
		    // some other exception
		}
		
		try {
			s.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return latency;
		
	}
	private String executeCommand(String command) {

		StringBuffer output = new StringBuffer();

		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			BufferedReader reader = 
                            new BufferedReader(new InputStreamReader(p.getInputStream()));

                        String line = "";			
			while ((line = reader.readLine())!= null) {
				output.append(line + "\n");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return output.toString();

	}

}

class Dialog extends JFrame {
	private JTextField field;
	private JButton btn;
	public Dialog() {
		setLayout(null);
		field = new JTextField();
		field.setBounds(20, 20, 70, 30);
		add(field);

		btn = new JButton("Secure");
		add(btn);
		btn.setBounds(100, 20, 50, 30);

		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("ACCOPY called");
				System.out.println("Command : " + "python ac-copy.py --" + field.getText());
				String s = executeCommand("python ac-Copy.py");
				System.out.println(s);
				System.out.println("After command");

			}
		});

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(40, 70, 200, 80);
	}
	private String executeCommand(String command) {

		StringBuffer output = new StringBuffer();

		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			BufferedReader reader = 
                            new BufferedReader(new InputStreamReader(p.getInputStream()));

                        String line = "";			
			while ((line = reader.readLine())!= null) {
				output.append(line + "\n");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return output.toString();

	}
}