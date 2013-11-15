package psirights.adapter;

import java.util.ArrayList;
import java.util.List;

import psirights.dom.IRightsRepository;
import psirights.model.Xawd;

public class RightsRepositoryMock implements IRightsRepository {

	@Override
	public List<Xawd> findUsersForOperations(String psiObject,
			String psiOperations) {
		
		List<Xawd> users = new ArrayList<Xawd>();
		
		Xawd xawd = new Xawd();
		xawd.setXawdbez("ha001");
		xawd.setXawdname("Huber Hans");
		xawd.setXawdphysseq("1");
		users.add(xawd);

		xawd = new Xawd();
		xawd.setXawdbez("ha002");
		xawd.setXawdname("Meier Sepp");
		xawd.setXawdphysseq("2");
		users.add(xawd);
		
		return users;
	}

}
