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
import psirights.model.Rights;
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

		Query q = session.createQuery("select f.xawdname as xawdname " +
				"from Xopz a join a.xopg b join b.xrgz c join c.xrol d join d.xarz e join e.xawd f " +
		 		"where a.xoprobj = :object and a.xoprmethode = :methode " +
		 		"and f.xawdtype = 1 and f.xawdname not in ('rbob', 'system') and f.xawdname = 'krj' ");
        q.setResultTransformer(Transformers.aliasToBean(Rights.class));
/*  
		Query q = session.createSQLQuery("SELECT " + 
				"XAWD.XAWDNAME, XAWD.XAWDBEZ, " +
				"XARZ.NAME, " +
				"XRGZ.XOPGNAME " +
				"FROM XAWD " +
				"INNER JOIN XARZ ON XAWD.XAWDNAME = XARZ.XAWDNAME " +
				"INNER JOIN XRGZ ON XARZ.NAME = XRGZ.NAME " +
				"inner join XOPZ on xrgz.xopgname = xopz.xopgname " +
				"WHERE XAWD.XAWDNAME = 'krj' " +
				"AND XOPZ.XOPROBJ = :object " +
				"and xopz.xoprmethode = :methode "); 
*/
		q.setString("object", "PART");
		q.setString("methode", "Sys_Korrigieren");

		
		int i=1;
		List<Object[]> objects = q.list();
		
		System.out.println(objects.size());
/*		
		for (Object[] object : objects) {
			
//			Xawd xawd = (Xawd) object[5];
//			System.out.println(i + ": " + xawd.toString());
			i++;
			
			for (Object o : object){
				System.out.println("Object: " + o);
			}
			
			System.out.println(object[0] + " and " + object[1]);
		}
*/		
		List<Rights> results = q.list();
		for (Rights result : results) {
			System.out.println(result.toString());
		}

		session.getTransaction().commit();

		return null;
	}

}
