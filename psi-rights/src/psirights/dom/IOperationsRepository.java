package psirights.dom;

import psirights.model.Operations;

import java.util.List;

/**
 * User: krj
 * Date: 21.11.13
 * Time: 13:42
 */
public interface IOperationsRepository {
    List<Operations> findOperationsForObject(String psiObject);
}
