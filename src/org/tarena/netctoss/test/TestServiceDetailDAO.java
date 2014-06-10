package org.tarena.netctoss.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tarena.netctoss.dao.DAOException;
import org.tarena.netctoss.dao.ServiceDetailDAO;
import org.tarena.netctoss.pojo.ServiceDetail;

public class TestServiceDetailDAO {
	public static ServiceDetailDAO dao;
	static{ 
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext-component.xml");
		dao = (ServiceDetailDAO) ac.getBean("serviceDetailDAO");
	}
	
	@Test
	public void findPage() throws DAOException{
		List<ServiceDetail> list = dao.findPage(2001, "201305", 1, 5);
		for (ServiceDetail s:list) {
			System.out.println("id:"+s.getId());
		}
	}
	
	@Test
	public void totalPages() throws DAOException{
		System.out.println(dao.totalPages(2001, "201305", 5));
	}
}
