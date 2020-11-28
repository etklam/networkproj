import java.awt.Choice;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import java.io.IOException;
import java.lang.management.ManagementPermission;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class networkProj {

	public static void main(String[] args) throws Exception, IOException {

		int TCPPort = 9999;
		int UDPPort = 9998;

		Scanner scanner = new Scanner(System.in);
		System.out.println("Please input");
		System.out.print("Your Name: ");
		String username = scanner.nextLine();
		System.out.printf("Hello, %s\n", username);
		// end name

		System.out.print("Target IP ");
		String IPAddr = scanner.nextLine();
		// scanner.close();

		Socket socket = new Socket(IPAddr, TCPPort);
		DataInputStream in = new DataInputStream(socket.getInputStream());
		DataOutputStream out = new DataOutputStream(socket.getOutputStream());

		Thread t = new Thread(() -> {
			byte[] buffer = new byte[1024];
			try {
				while (true) {
					int len = in.readInt();
					in.read(buffer, 0, len);
					System.out.println(new String(buffer, 0, len));
				}
			} catch (IOException ex) {
				System.err.println("Connection dropped!");
				System.exit(-1);
			}
		});
		t.start();

		boolean onGoing = true;

		while (onGoing) {
			int choice = 0;
			System.out.println("1. Read file list.");
			System.out.println("2. Create subdirectories");
			System.out.println("3. Upload and download files.");
			System.out.println("4. Delete files.");
			System.out.println("5. Delete subdirectories.");
			System.out.println("6. Change file/target name.");
			System.out.println("7. Read the file’s detail information including the size, last modified time, etc.");
			System.out.println("8. Exit");
			System.out.print(">");
			choice = scanner.nextInt();
			String str = "";
			switch (choice) {
			case 1:
				System.out.println("Which file list you want to read:");
				System.out.print(">");
				str = "dir " + scanner.nextLine();
				out.writeInt(str.length());
				out.write(str.getBytes(), 0, str.length());
				break;
			case 2:
				System.out.println("What directory you want to create:");
				System.out.print(">");
				str = "md " + scanner.nextLine();
				out.writeInt(str.length());
				out.write(str.getBytes(), 0, str.length());
				break;
			case 3:

//			System.out.println("What directory you want to create:");
//			System.out.print(">");
//			String str2 = "md "+ scanner.nextLine();
//			out.writeInt(str2.length());
//			out.write(str2.getBytes(), 0, str2.length());
				break;
			case 4:
				System.out.println("Which File you want to del?:");
				System.out.print(">");
				str = "delFile " + scanner.nextLine();
				out.writeInt(str.length());
				out.write(str.getBytes(), 0, str.length());
				break;
			case 5:
				System.out.println("Which subDirectory you want to del?:");
				System.out.print(">");
				str = "del " + scanner.nextLine();
				out.writeInt(str.length());
				out.write(str.getBytes(), 0, str.length());
				break;
			case 6:
				break;
			case 7:
				System.out.println("File info path:");
				System.out.print(">");
				str = "info " + scanner.nextLine();
				out.writeInt(str.length());
				out.write(str.getBytes(), 0, str.length());
				break;
			}

		}

		/*
		 * The FileServer
		 */
		/*
		 * Thread tcpListen = new Thread(() -> { try { new FileServer(9999); } catch
		 * (IOException e) { // TODO Auto-generated catch block e.printStackTrace();
		 * }}); tcpListen.start();
		 */
		// ask name

//		try {
//			SimpleCommandPorpt cmdPorpt = new SimpleCommandPorpt(IPAddr, TCPPort);
//			cmdPorpt.menu();
//		} catch (IOException e) {
//			System.err.printf("Unable to connect server %s:%d\n", IPAddr, TCPPort);
//			System.exit(-1);
//		}
//		
//        switch(choice) {
//        case 1:
//        	
//        	
//        
//        
//        default:
//        	System.out.println("Unknown, please input again");
//        }
//        
//		
//        scanner2.close();

		// FileMan man = new FileMan();

		/*
		 * 
		 * */

		// System.out.println("Test Thread");

	}

	public void sender() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please input");
		System.out.print("Server IP: ");
		String ip = scanner.nextLine().trim();

//		System.out.print("Port no:   ");
//		int port = Integer.parseInt(scanner.nextLine());
		int port = 9999;

		System.out.print("File:      ");
		String filename = scanner.nextLine().trim();
		scanner.close();
		FileClient.upload(ip, port, filename);
	}
}
