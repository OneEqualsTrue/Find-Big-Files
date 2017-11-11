package main;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    @SuppressWarnings("unused")
    private static Scanner reader = new Scanner(System.in);
    private static int upper = 100; // MB
    private static int mb = 1024 * 1024;

    public static void main(String[] args) {

	if (args.length >= 1)
	    upper = Integer.parseInt(args[0]);

	File[] roots = File.listRoots();
	Arrays.stream(roots).forEach(p -> listFiles(p, 0));
    }

    static void listFiles(File p, int level) {
	String indent = "";

	for (int i = 0; i < level; i++)
	    indent += "\t";
	final String indent_f = indent;
	final int level_f = level + 1;

	if (p.isDirectory()) {
	    try {
		File[] children = p.listFiles();
		Arrays.stream(children).forEach(c -> {
		    if (c.isDirectory())
			listFiles(c, level_f);
		    else
			check(c, indent_f, c);
		});
	    } catch (Exception e) {
	    }
	}
    }

    @SuppressWarnings("resource")
    static void check(File dir, String indent, File p2) {

	if (dir.length() > mb) {
	    if (dir.length() > mb * upper) {
		System.out.println(
			"\nFile found greater than " + upper + "MB, " + dir.length() / 1024 / 1024 + "MB " + p2);
		System.out.println("press ENTER to continue: ");
		reader.nextLine();
	    } else
		System.out.println(dir.length() / 1024 / 1024 + "MB " + indent + p2);
	}
    }

}
