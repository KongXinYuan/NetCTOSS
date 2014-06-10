package org.tarena.netctoss.util;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
/**启动账单生成和service资费修改PL/SQL过程*/
public class CreateBillJob {
	private SessionFactory sf;

	public void setSessionFactory(SessionFactory sf) {
		this.sf = sf;
	}
	
	public void create() {
		
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		try {
			//启动数据库端GBILL_ALL过程
			SQLQuery query = session.createSQLQuery("{call GBILL_ALL()}");
			query.executeUpdate();
			//启动数据库端UPDATE_SERVICE_COST过程
			SQLQuery query1 = session
					.createSQLQuery("{call UPDATE_SERVICE_COST()}");
			query1.executeUpdate();
			tx.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			tx.rollback();
		}
	}
}
