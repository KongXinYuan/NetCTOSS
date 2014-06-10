package org.tarena.netctoss.test;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tarena.netctoss.dao.CostDAO;
import org.tarena.netctoss.dao.DAOException;
import org.tarena.netctoss.pojo.Cost;

public class testCost {
	public static CostDAO dao;
	static{ 
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext-component.xml");
		dao = (CostDAO) ac.getBean("costDAO");
	}
	@Test
	public void findAll() throws DAOException {
		System.out.println(dao.findAll());
	}
	
	
	@Test
	public void findPage() throws DAOException {
		List<Cost> list = dao.findPage(2, 3, 0);
		for (Cost c:list){
			System.out.println(c.getId() +":"+c.getName());
		}
	}
	@Test
	public void update() throws DAOException{
		Cost cost = new Cost();
		cost.setName("¶Ù¾í¿¨Ê½´øºÃ");
		cost.setCostType(2);
		cost.setId(29);
		cost.setStatus("1");
		cost.setUnitCost(22.0);
		dao.update(cost);
	}
	@Test
	public void delete() throws DAOException {
		dao.delete(28);
	}


}
