package org.tarena.netctoss.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tarena.netctoss.dao.DAOException;
import org.tarena.netctoss.dao.ReportDAO;
import org.tarena.netctoss.pojo.ReportSumDuration;

public class TestReportDAO {
	public static ReportDAO dao;
	static{ 
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext-component.xml");
		dao = (ReportDAO) ac.getBean("reportDAO");
	}

	@Test
	public void findHostPage() throws DAOException{
		System.out.println(dao.findHostPage(2, 1));
	}
	
	@Test
	public void totalPagesHost() throws DAOException{
		System.out.println(dao.totalPagesHost(1));
	}
	
	@Test
	public void findSumDuration() throws DAOException{
		List<ReportSumDuration> list = dao.findSumDurationPage(1,4);
		for (ReportSumDuration r :list) {
			System.out.println(r.getId()+" "+r.getRealName()+" "+r.getMonth()+" "+r.getDuration());
		}		
	}
	
	@Test
	public void totalPagesSumDuration() throws DAOException {
		System.out.println(dao.totalPagesSumDuration(3));
	}
	@Test
	public void findThreeDuration() throws DAOException{
		List<ReportSumDuration> list = dao.findThreeDuration("192.168.0.20");
		for (ReportSumDuration r :list) {
			System.out.println(r.getUnixHost()+" "+r.getRealName()+" "+r.getDuration());
		}	
	}
	@Test
	public void findAllHost() throws DAOException{
		System.out.println(dao.findAllHost());
	}

}
