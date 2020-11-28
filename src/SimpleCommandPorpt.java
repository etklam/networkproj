import java.io.*;
import java.net.*;
import java.util.*;

public class SimpleCommandPorpt {
	public SimpleCommandPorpt(String server, int port) throws IOException {
		Socket socket = new Socket(server, port);
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

		Scanner scanner = new Scanner(System.in);

		System.out.println("Please input your name:");
		String name = scanner.nextLine().trim();
		
		out.writeInt(name.length());
		out.write(name.getBytes());

		System.out.println("Please input messages:");
		
		while (true) {
//			String str = scanner.nextLine();
			String str = name + ": " + scanner.nextLine();
			out.writeInt(str.length());
			out.write(str.getBytes(), 0, str.length());
		}
	}
	
	public void menu() {
		
		System.out.println("1. Read file list.");
		System.out.println("2. Create subdirectories");
		System.out.println("3. Upload and download files.");
		System.out.println("4. Delete files.");
		System.out.println("5. Delete subdirectories.");
		System.out.println("6. Change file/target name.");
		System.out.println("7. Read the file’s detail information including the size, last modified time, etc.");
		System.out.println("8. Exit");
		
	}

}
