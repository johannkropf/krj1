package psirights.dom;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import psirights.adapter.RepositoryMock;
import psirights.adapter.SwingViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class RightsManagerTest {

	IView view;
	IRepository repository;
	RightsManager rightsmanager;

	@Before
	public void setUp() throws Exception {
		view = new SwingViewAdapter();
		repository = new RepositoryMock();
		rightsmanager = new RightsManager();

		rightsmanager.uses(view, repository);
		view.uses(rightsmanager);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testUses(){
	    assertNotNull(rightsmanager.getView());	
	    assertNotNull(rightsmanager.getRepo());	
	}
	
	@Test
	public void testgetUsers() {
        List<String> methode = new ArrayList();
        methode.add("Sys_Informieren");
		int ret = rightsmanager.showUsers("XOPR", methode);
		assertEquals("Retrieved lines must be 2", 2, ret);
	}

    @Test
    public void testgetOperations() {
        int ret = rightsmanager.showOperations("XOPR");
        assertEquals("Retrieved must be 2", 2, ret);
    }
	
}
