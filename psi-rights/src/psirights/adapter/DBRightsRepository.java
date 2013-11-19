package psirights.adapter;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import psirights.dom.IRightsRepository;
import psirights.hibernate.MyHibernateUtil;
import psirights.model.Xarz;
import psirights.model.Xawd;
import psirights.model.Xopz;

public class DBRightsRepository implements IRightsRepository {

	@Override
	public List<Xawd> findUsersForOperations(String psiObject,
			String psiOperations) {

		Session session = MyHibernateUtil.getSessionFactory()
				.getCurrentSession();

		session.beginTransaction();

		/*
		 * List result = session.createQuery(
		 * " from Xawd where xawdname = 'krj'").list();
		 */

		 Query q = session
		 .createQuery("from Xawd as xawd left join xawd.xarz as xarz where xawd.xawdname = :user and xarz.name = 'Keyuser'");

		 q = session.createQuery("from Xopz as xopz where xopz.xoprobj = 'PART' and xopz.xoprmethode = 'Sys_Korrigieren' ");
		 //q.setString("user", "ha111");

		//Query q =
		//		session.createQuery("from Xawd as xawd, Xarz as xarz where xawd.xawdname = :user and xarz.name = 'Keyuser' and xawd.xawdname = xarz.xawdname");

		
/*
		List<Object[]> objects = q.list();
		for (Object[] object : objects) {
			//Xawd xawd = (Xawd) object[0];
			//System.out.println(xawd.toString());
			System.out.println(object[0] + " and " + object[1]);
		}
	*/	
		List<Xopz> result = q.list();
		for (Xopz xopz : result) {
			System.out.println(xopz.toString());
		}

		session.getTransaction().commit();

		return null;
	}

}
