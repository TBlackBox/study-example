package xml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class SaxTest {
	public void parserXml(String fileName) {
		SAXParserFactory saxfac = SAXParserFactory.newInstance();
		try {
			SAXParser saxparser = saxfac.newSAXParser();
			InputStream is = new FileInputStream(fileName);
			
			DefaultHandler defaultHandler = new MySAXHandler();
			
			saxparser.parse(is, defaultHandler);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		SaxTest saxTest = new SaxTest();
		String path = Dom4jTest.class.getClassLoader().getResource("user.xml").getPath();
		saxTest.parserXml(path);
	}
}

class MySAXHandler extends DefaultHandler {
	boolean hasAttribute = false;
	Attributes attributes = null;

	public void startDocument() {
		 System.out.println("文档开始打印了");
	}

	public void endDocument() {
		 System.out.println("文档打印结束了");
	}

	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (qName.equals("users")) {
			return;
		}
		if (qName.equals("user")) {
			return;
		}
		if (attributes.getLength() > 0) {
			this.attributes = attributes;
			this.hasAttribute = true;
		}
	}

	public void endElement(String uri, String localName, String qName) {
		if (hasAttribute && (attributes != null)) {
			for (int i = 0; i < attributes.getLength(); i++) {
				System.out.print(attributes.getQName(0) + ":" + attributes.getValue(0));
			}
		}
	}

	public void characters(char[] ch, int start, int length) {
		System.out.print(new String(ch, start, length));
	}
}
