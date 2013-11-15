package psirights.adapter;

import java.util.List;

import org.hibernate.Session;

import psirights.dom.IRightsRepository;
import psirights.hibernate.MyHibernateUtil;
import psirights.model.Xawd;

public class DBRightsRepository implements IRightsRepository {

	@Override
	public List<Xawd> findUsersForOperations(String psiObject,
			String psiOperations) {

		Session session = MyHibernateUtil.getSessionFactory()
				.getCurrentSession();

		session.beginTransaction();
		
		List result = session.createQuery(
				" from Xawd where xawdname = 'krj'").list();

		for (Xawd xawd : (List<Xawd>) result) {
			System.out.println("Anwender: " + xawd.getXawdname() + " " + xawd.getXawdbez());		
		}

		session.getTransaction().commit();

		return result;
	}

}
