package psirights.adapter;

import java.util.ArrayList;
import java.util.List;

import psirights.dom.IRepository;
import psirights.model.Operations;
import psirights.model.Rights;

public class RepositoryMock implements IRepository {

	@Override
	public List<Rights> findUsersForOperations(String psiObject,
			List<String> psiOperations) {
		
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

    @Override
    public List<Operations> findOperationsForObject(String psiObject) {

        List<Operations> operations = new ArrayList<Operations>();

        Operations operation = new Operations();
        operation.setMethode("Sys_Informieren");
        operations.add(operation);

        operation = new Operations();
        operation.setMethode("Sys_Korrigieren");
        operations.add(operation);

        return operations;
    }

}
