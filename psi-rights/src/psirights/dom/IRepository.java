package psirights.dom;

import java.util.List;

import psirights.model.Operations;
import psirights.model.Rights;
import psirights.model.Xawd;

public interface IRepository {

	List<Rights> findUsersForOperations(String psiObject, String psiOperations);

    List<Operations> findOperationsForObject(String psiObject);
}
