package psirights.adapter;

import psirights.dom.IOperationsRepository;
import psirights.model.Operations;

import java.util.List;

/**
 * User: krj
 * Date: 21.11.13
 * Time: 13:48
 */

public class DBOperationsRepository implements IOperationsRepository {
    @Override
    public List<Operations> findOperationsForObject(String psiObject) {
        return null;
    }
}
