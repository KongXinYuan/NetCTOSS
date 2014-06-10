package org.tarena.netctoss.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tarena.netctoss.dao.BillItemDAO;
import org.tarena.netctoss.dao.DAOException;
import org.tarena.netctoss.pojo.BillItem;

public class TestBillItemDAO {
	public static BillItemDAO dao;
	static{ 
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext-component.xml");
		dao = (BillItemDAO) ac.getBean("billItemDAO");
	}
	
	@Test
	public void findpage() throws DAOException{
		List<BillItem> list = dao.findPage(1, 1, 10);
		for (BillItem bt:list) {
			System.out.println(bt);
		}
	}
	
	@Test
	public void totalPages() throws DAOException{
		System.out.println(dao.totalPage(1,2));
	}
	@Test
	public void duration() throws DAOException{
		System.out.println(dao.duraction(2001, "201305"));
	}
}
