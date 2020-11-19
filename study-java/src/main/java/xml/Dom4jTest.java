package xml;

import java.io.File;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Dom4jTest {

	public void parserXml(String fileName) {
	    File inputXml = new File(fileName);
	    SAXReader saxReader = new SAXReader();
	 
	    try {
	      Document document = saxReader.read(inputXml);
	      Element users = document.getRootElement();
	      for (Iterator i = users.elementIterator(); i.hasNext();) {
	        Element user = (Element) i.next();
	        System.out.println("id:" + user.attributeValue("id"));
	        for (Iterator j = user.elementIterator(); j.hasNext();) {
	          Element node = (Element) j.next();
	          System.out.println(node.getName() + ":" + node.getText());
	        }
	        System.out.println();
	      }
	    } catch (DocumentException e) {
	      System.out.println(e.getMessage());
	    }
	  }
	
	public static void main(String[] args) {
		Dom4jTest domj4= new Dom4jTest();
		
		String path = Dom4jTest.class.getClassLoader().getResource("user.xml").getPath();
	    System.out.println(path);
	    //获取文件路径
	    domj4.parserXml(path);
	}
}
