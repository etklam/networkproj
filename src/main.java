import java.io.File;
import java.util.Date;
import java.util.Scanner;
import java.io.IOException;
import java.lang.management.ManagementPermission;
import java.util.Scanner;


public class main {

	public static void main(String[] args) {
		
		/*
		 * The FileServer*/
		
		Thread fs = new Thread(() -> {
		try {
			new FileServer(9999);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}});
		fs.start();
		
		//ask name
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please input");
		System.out.print("Your Name: ");
		String username = scanner.nextLine();
		scanner.close();
		//end name
		
		System.out.printf("Hello, %s\n", username);
		
		//FileMan man = new FileMan();
		
		/*
		 * 
		 * */
		
		//System.out.println("Test Thread");
		
		
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
	
	
	//file manager
	private void dir(String arg) {
		File f = new File(arg);
		if (f.exists()) {
			if (f.isDirectory()) {
				File[] list = f.listFiles();
				for (File c : list)
					info(c);
			} else
				info(f);
		} else
			System.out.println("File not found.");
	}

	private void info(File f) {
		if (f.isFile())
			System.out.printf("%s %10d\t%s\n", new Date(f.lastModified()), f.length(), f.getName());
		else
			System.out.printf("%s %10s\t%s\n", new Date(f.lastModified()), "<DIR>", f.getName());
	}

	private void md(String arg) {
		File f = new File(arg);
		if (f.exists())
			System.out.println("File/directory exists, unable to create directory.");
		else
			f.mkdirs();
	}

	private void rd(String arg) {
		File f = new File(arg);
		
		if (!f.exists())
			System.out.println("File not found.");
		else if (f.isFile()) 
			System.out.println("Use del to delete file.");
		else if (f.list().length > 0) 
			System.out.println("Directory is not empty.");
		else
			delFile(f);
	}

	private void del(String arg) {
		File f = new File(arg);
		if (!f.exists())
			System.out.println("Directory not found.");
		else if (f.isDirectory())
			System.out.println("Use rd to remove directory.");
		else
			delFile(f);
	}
	
	private void delFile(File f) {
		try {
			f.delete();
		} catch (SecurityException ex) {
			System.out.println("Unable to delete " + f.getName());
		}		
	}
	

}
