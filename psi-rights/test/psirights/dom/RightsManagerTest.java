package psirights.dom;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import psirights.adapter.RepositoryMock;
import psirights.adapter.SwingViewAdapter;

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
		int ret = rightsmanager.showUsers("XOPR", "Sys_Informieren");
		assertEquals("Retrieved lines must be 2", 2, ret);
	}

    @Test
    public void testgetOperations() {
        int ret = rightsmanager.showOperations("XOPR");
        assertEquals("Retrieved must be 2", 2, ret);
    }
	
}
