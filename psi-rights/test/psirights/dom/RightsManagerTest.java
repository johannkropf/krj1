package psirights.dom;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import psirights.adapter.RightsRepositoryMock;
import psirights.adapter.SwingViewAdapter;

public class RightsManagerTest {

	IView view;
	IRightsRepository rightsRepo;
	RightsManager rightsmanager;

	@Before
	public void setUp() throws Exception {
		view = new SwingViewAdapter();
		rightsRepo = new RightsRepositoryMock();
		rightsmanager = new RightsManager();

		rightsmanager.uses(view, rightsRepo);
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
		int ret = rightsmanager.retrieveUsers("XOPR", "Sys_Informieren");
		assertEquals("Retrieved lines must be 2", 2, ret);
	}

	
}
