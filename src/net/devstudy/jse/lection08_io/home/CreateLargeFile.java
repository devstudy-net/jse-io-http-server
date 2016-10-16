package net.devstudy.jse.lection08_io.home;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * 
 * @author devstudy
 * @see http://devstudy.net
 */
public class CreateLargeFile {

	public static void main(String[] args) throws IOException {
		String s = "Java is a general-purpose computer programming language that is concurrent, class-based, object-oriented, and specifically designed to have as few implementation dependencies as possible.";
		try(Writer wr = new BufferedWriter(new FileWriter("test/large.txt"))){
			for(int i=0;i<20000000;i++){
				wr.write(s+"\r\n");
			}
			wr.flush();
		}
		
		System.out.println("File created: "+new File("test/large.txt").length()+" bytes");
	}

}
