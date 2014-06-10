package org.tarena.netctoss.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tarena.netctoss.dao.DAOException;
import org.tarena.netctoss.dao.ServiceDAO;
import org.tarena.netctoss.pojo.Service;


public class TestService {
	public static ServiceDAO dao;
	static{ 
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext-component.xml");
		dao = (ServiceDAO) ac.getBean("serviceDAO");
	}
	@Test
	public void testFind() throws DAOException{
		List<Service> list = dao.findPage(null, null, "4", "-1", 1, 5);
		for (Service s:list)
		{
			System.out.println(s);
		}		
	}
	
	public void totalPages() throws DAOException{
		System.out.println(dao.totalPages(null, null, "4", "-1", 1));
	}

}
