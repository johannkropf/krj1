package psirights.adapter;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import psirights.dom.IRepository;
import psirights.hibernate.MyHibernateUtil;
import psirights.model.Operations;
import psirights.model.Rights;

public class DBRepository implements IRepository {

	@Override
	public List<Rights> findUsersForOperations(String psiObject,
			List<String> psiOperations) {

		Session session = MyHibernateUtil.getSessionFactory()
				.getCurrentSession();
		session.beginTransaction();

		Query q = session.createQuery("select f.xawdname as xawdname, " +
				"f.xawdbez as xawdbez, " +
				"d.name as rolle, " +
				"b.xopgname as kompetenz, " +
				"c.siteid as werk, " +
				"a.xoprobj as objekt, " +
                "a.xoprmethode as methode " +
				"from Xopz a join a.xopg b join b.xrgz c join c.xrol d join d.xarz e join e.xawd f " +
		 		"where a.xoprobj = :object and a.xoprmethode in :methode " +
		 		"and f.xawdtype = 1 and f.xawdname not in ('rbob', 'system') " +
                "order by f.xawdname, c.siteid");
        q.setResultTransformer(Transformers.aliasToBean(Rights.class));

		q.setString("object", psiObject);
		q.setParameterList("methode", psiOperations);

		List<Rights> results = q.list();

		session.getTransaction().commit();

		return results;
	}

    @Override
    public List<Operations> findOperationsForObject(String psiObject) {

        Session session = MyHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Query q = session.createQuery("select xoprmethode as methode from Xopr as xopr where xopr.xoprobj = :object order by xoprmethode");
        q.setResultTransformer(Transformers.aliasToBean(Operations.class));
        q.setString("object", psiObject);

        List<Operations> results = q.list();

        session.getTransaction().commit();
        return results;
    }
}
