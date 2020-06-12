package jdk;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Test;

public class MainTest {

	@Test
	public void testInteger() {
		//System.out.println(Integer.max(1, -2));
		
		Integer a = 128;
		Integer b = 128;
		System.out.println(a == b); //false
		System.out.println(a.equals(b)); //true
		
		System.out.println(Integer.toString(-4, 2));
	}
	
	
	@Test
	public void testTransent() {
		User user = new User("老王", 25);
		
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("D:\\user.txt"));
			out.writeObject(user);
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("D:\\user.txt"));
			User user1 = (User) in.readObject();
			in.close();
			System.out.println(user1.toString());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testClass() throws ClassNotFoundException {
		Class clazz = Class.forName("test.User");
	}
}
