package psirights.dom;

import java.util.List;

import psirights.model.Rights;
import psirights.model.Xawd;

public interface IRightsRepository {

	List<Rights> findUsersForOperations(String psiObject, String psiOperations);

}
