import java.io.File;
import java.util.Date;
import java.util.Scanner;

public class FileMan {

	public FileMan() {
		Scanner scanner = new Scanner(System.in);
		loop: while (true) {
			System.out.print("> ");
			String input = scanner.nextLine().trim();
			String[] values = input.split("\\s\\s*");
			switch (values[0]) {
			case "dir":
				if (values.length > 1)
					dir(values[1]);
				else
					dir(".");
				break;
			case "md":
				if (values.length > 1)
					md(values[1]);
				else
					System.out.println("Missing argument");
				break;
			case "del":
				if (values.length > 1)
					del(values[1]);
				else
					System.out.println("Missing argument");
				break;
			case "rd":
				if (values.length > 1)
					rd(values[1]);
				else
					System.out.println("Missing argument");
				break;
			case "exit":
				break loop;
			default:
				System.out.println("Unknown command");
			}
		}
		scanner.close();
	}

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
	
//	public static void main(String[] args) {
//		new FileMan();
//	}

}