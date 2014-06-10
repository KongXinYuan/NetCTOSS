package org.tarena.netctoss.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tarena.netctoss.dao.BillDAO;
import org.tarena.netctoss.dao.DAOException;
import org.tarena.netctoss.pojo.Bill;

public class TestBillDAO {
	public static BillDAO dao;
	static{ 
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext-component.xml");
		dao = (BillDAO) ac.getBean("billDAO");
	}
	@Test
	public void findPage() throws DAOException{
		List<Bill> list = (List<Bill>)dao.findPage(null, "xl18z60", null, 2013, null, 1, 2);
		if (list != null) {
			for (Bill obj:list) {
				System.out.println(obj);
			}
		}		
	}
	
//	@Test
	public void totalPage() throws DAOException{
		System.out.println(dao.totalPages(null, "xl18z60", null, 2013, null, 3));
	}
}
