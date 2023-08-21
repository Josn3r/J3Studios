package club.j3studios.system.utils;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tools {
	
        public static Thread acceptThread;
	public static ServerSocket serverSocket;
	private static Socket clientSocket;
	private static InputStreamReader inputStreamReader;
	private static BufferedReader bufferedReader;
	private static String message="";
	
	public void debug (String str) {
		System.out.print("[J3Studios] � " + str + "\n");
	}
	
	public Double calcPercent (Double value, Double percent) {
            double impuestos = -(100-percent);
            double calculo = (value * (100 + (impuestos)) / 100);
            return calculo;
	}
	
	public boolean isInt(String s) {
	    try {
	        Integer.parseInt(s);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
	
	public boolean isDouble(String s) {
	    try {
	        Double.parseDouble(s);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
	        
	public void startSocketServer() {
            acceptThread = new Thread (() -> {
                try {
                    serverSocket = new ServerSocket(4444);
                } catch (IOException e) {
                    System.out.println("Could not listen on port: 4444");
                }
                System.out.println("Server started. Listening to the port 4444");
                
                Runtime.getRuntime().addShutdownHook(new Thread() {
                    @Override
                    public void run() {
                        try {
                            serverSocket.close();
                        } catch (IOException e) {
                            System.out.println("Could not close ServerSocket");
                        }
                    }
                });
                
                while (!message.equalsIgnoreCase("over")) {
                    try {
                        clientSocket = serverSocket.accept();
                        inputStreamReader = new InputStreamReader(clientSocket.getInputStream());
                        bufferedReader = new BufferedReader(inputStreamReader);
                        message = bufferedReader.readLine();
                        if (!message.equalsIgnoreCase("connected")) {
                            String code = "";
                            Boolean copyPaste = false;
                            Boolean autoEnter = false;
                            if (message.contains("COPY&PASTE") && message.contains("AUTO_ENTER")) {
                                String[] splitStr = message.split(" : ");
                                code = splitStr[2];
                                copyPaste = true;
                                autoEnter = true;
                            } else {
                                if (message.contains("COPY&PASTE")) {
                                    String[] splitStr = message.split(" : ");
                                    code = splitStr[1];
                                    copyPaste = true;
                                } else {
                                    debug("Copy & Paste = FALSE | Auto ENTER = FALSE");
                                    code = message;
                                }
                            }
                            debug(code);
                            
                            if (copyPaste) {
                                Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
                                StringSelection strSel = new StringSelection(code);
                                clip.setContents(strSel, null);
                                
                                Robot robot;
                                try {
                                    robot = new Robot();
                                    robot.keyPress(KeyEvent.VK_CONTROL);
                                    robot.keyPress(KeyEvent.VK_V);
                                    robot.keyRelease(KeyEvent.VK_V);
                                    robot.keyRelease(KeyEvent.VK_CONTROL);
                                    if (autoEnter) {
                                        robot.keyPress(KeyEvent.VK_ENTER);
                                        robot.keyRelease(KeyEvent.VK_ENTER);
                                    }
                                } catch (AWTException e) {
                                }
                            }
                        } else {
                            debug(message);
                        }
                        inputStreamReader.close();
                        clientSocket.close();
                    } catch (IOException ex) {
                        //System.out.println("Problem in message reading");
                    }
                }
            });
            acceptThread.start();
	}  
        
        
        public void createFile (String fileName){
            File file = new File(fileName);
            if (file.exists()) {
                debug("El archivo " + file + " ya existe.");
                return;
            }            
            try {
                PrintWriter exit = new PrintWriter(file);
                exit.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
	  
        public void writeFile (String fileName, String content){
            File file = new File(fileName);
            if (!file.exists()) {
                debug("El archivo " + file + " no existe.");
                return;
            }            
            try {
                PrintWriter exit = new PrintWriter(file);
                exit.println(content);
                exit.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        public Properties getConfig(){
            try {
                String configFilePath = "config.properties";
                FileInputStream propsInput = new FileInputStream(configFilePath);
                Properties prop = new Properties();
                prop.load(propsInput);
                return prop;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        
        public void readFile (String fileName){
            File file = new File(fileName);
            if (!file.exists()) {
                debug("El archivo " + file + " no existe.");
                return;
            }            
            try {
                BufferedReader read = new BufferedReader(new FileReader(file));
                String lectura = read.readLine();
                while (lectura != null) {
                    debug("Lectura: " + lectura);
                    lectura = read.readLine();
                }
                read.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                ex.printStackTrace(System.out);
            }
        }
	  
        
        
}
