package psirights.adapter;

import java.util.ArrayList;
import java.util.List;

import psirights.dom.IRightsRepository;
import psirights.model.Rights;

public class RightsRepositoryMock implements IRightsRepository {

	@Override
	public List<Rights> findUsersForOperations(String psiObject,
			String psiOperations) {
		
		List<Rights> users = new ArrayList<Rights>();
		
		Rights rights = new Rights();
		rights.setXawdbez("ha001");
		rights.setXawdname("Huber Hans");
		users.add(rights);

		rights = new Rights();
		rights.setXawdbez("ha002");
		rights.setXawdname("Meier Sepp");
		users.add(rights);
		
		return users;
	}

}
