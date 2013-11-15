package psirights.dom;

import java.util.List;

import psirights.model.Xawd;

public interface IRightsRepository {

	List<Xawd> findUsersForOperations(String psiObject, String psiOperations);

}
