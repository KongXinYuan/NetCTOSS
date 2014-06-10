package org.tarena.netctoss.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.tarena.netctoss.pojo.Privilege;

public class PrivilegeReader {
	
	private static List<Privilege> modules = 
			new ArrayList<Privilege>();
	
	static {
		InputStream xml = 
			PrivilegeReader.class.getClassLoader()
			.getResourceAsStream("privileges.xml");
		modules = toModuleList(xml);
	}

	public static List<Privilege> getModules(){
		return modules;
	}
	
	public static String getModuleNameById(int id){
		List<Privilege> modules = getModules();
		for(Privilege module : modules){
			if(module.getId() == id){
				return module.getModuleName();
			}
		}
		return "";
	}
	
	public static List<String> getModulUrlsById(int id){
		for(Privilege module : modules){
			if(module.getId() == id){
				return module.getUrls();
			}
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	protected static List<Privilege> toModuleList(
			InputStream xml){		
		List<Privilege> modules = new ArrayList<Privilege>();		
		try {			
			SAXReader reader = new SAXReader();
			Document doc = reader.read(xml);
			Element root = doc.getRootElement();			
			List<Element> moduleElements = root.elements("privilege");			
			for (Element moduleElement : moduleElements) {				
				Privilege module = new Privilege();				
				module.setId(Integer.parseInt(moduleElement.attributeValue("id")));
				module.setModuleName(moduleElement.elementText("name"));				
				Element urlElement = moduleElement.element("urls");
				List<Element> urlElements = urlElement.elements();
				List<String> urls = new ArrayList<String>();				
				for (Element element : urlElements) {
					urls.add(element.getText());
				}
				module.setUrls(urls);				
				modules.add(module);				
			}			
			return modules;			
		} catch (DocumentException e) {
			e.printStackTrace();
			throw new RuntimeException("", e);
		}
	}
	
	public static void main(String[] args){
		List<Privilege> list = 
				PrivilegeReader.getModules();
		for(Privilege p : list){
			System.out.println(p.getId()+" "+p.getModuleName()+","+p.getUrls());
		}
		System.out.println(getModuleNameById(1));
	}
	
	
}
