package fr.ul.miage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Scanner;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.ul.miage.GenieLogiciel.controller.AccessController;
import fr.ul.miage.GenieLogiciel.controller.IdentificationController;
import fr.ul.miage.GenieLogiciel.utils.Session;

public class AppAccessTest {
	private static AccessController subject;
	private static Scanner scan;
	private static IdentificationController idc;
	@BeforeAll
	public static void beforeAll () {
		scan = new Scanner(System.in);
		idc = new IdentificationController(scan);
	}
	@BeforeEach
	public void beforeEach () {
		Session.getInstance().setCurrentUser(null);
	}
	
	@Test
    public void testDirectorFullAccess()
    {
    	idc.connect("louisM");
    	subject= new AccessController();
        assertEquals(true,subject.checkCommandesAccess());
        assertEquals(true,subject.checkCategoriesAccess());
        assertEquals(true,subject.checkTablesAccess());
        assertEquals(true,subject.checkUtilisateursAccess());
        assertEquals(true,subject.checkIngredientsAccess());
        assertEquals(true,subject.checkPlatsAccess());
        assertEquals(true, subject.checkServiceAccess());
    }
	
	@Test
	public void testServerTableAccess()
    {
    	idc.connect("johnR");
    	subject= new AccessController();
        assertEquals(true,subject.checkTablesAccess());
    }
	
	@Test
	public void testServerTableOperationalAccess()
    {
    	idc.connect("johnR");
    	subject= new AccessController();
        assertEquals(true,subject.checkViewTablesAccess());
        assertEquals(true,subject.checkModifAdvancementMealAccess());
        assertEquals(true,subject.checkModifStatusTablesAccess());
        assertEquals(false,subject.checkCRUDTablesAccess());
        assertEquals(false,subject.checkAffectationTablesAccess());
    }
	
	@Test
	public void testServerCommandesAccess ()
	{
		idc.connect("johnR");
    	subject= new AccessController();
        assertEquals(true,subject.checkCommandesAccess());
	}
	
	@Test
	public void testServerCommandesOperationalAccess()
    {
    	idc.connect("johnR");
    	subject= new AccessController();
        assertEquals(true,subject.checkViewCommandesAccess());
        assertEquals(true,subject.checkCommandesServirAccess());
        assertEquals(true,subject.checkBillCommandesAccess());
        assertEquals(true,subject.checkEditionCommandesAccess());
        assertEquals(false,subject.checkPreparationCommandesAccess());
    }
	
	@Test
	public void testCookCommandesAccess ()
	{
		idc.connect("heleneP");
    	subject= new AccessController();
        assertEquals(true,subject.checkCommandesAccess());
	}
	
	@Test
	public void testCookCommandesOperationalAccess()
    {
    	idc.connect("heleneP");
    	subject= new AccessController();
        assertEquals(true,subject.checkViewCommandesAccess());
        assertEquals(false,subject.checkCommandesServirAccess());
        assertEquals(false,subject.checkBillCommandesAccess());
        assertEquals(false,subject.checkEditionCommandesAccess());
        assertEquals(true,subject.checkPreparationCommandesAccess());
    }
	
	@Test
	public void testHotelMasterTablesAccess ()
	{
		idc.connect("annaV");
    	subject= new AccessController();
        assertEquals(true,subject.checkTablesAccess());
	}
	
	@Test
	public void testHotelMasterNotOtherAccess ()
	{
		idc.connect("annaV");
    	subject= new AccessController();
        assertEquals(false,subject.checkCommandesAccess());
        assertEquals(false,subject.checkServiceAccess());
	}
	
	@Test
	public void testHotelMasterOperationalAccess()
    {
    	idc.connect("annaV");
    	subject= new AccessController();
    	 assertEquals(true,subject.checkViewTablesAccess());
         assertEquals(false,subject.checkModifAdvancementMealAccess());
         assertEquals(true,subject.checkModifStatusTablesAccess());
         assertEquals(true,subject.checkCRUDTablesAccess());
         assertEquals(true,subject.checkAffectationTablesAccess());
    }
	
	@Test
	public void testServiceAssistantTablesAccess ()
	{
		idc.connect("amyW");
    	subject= new AccessController();
        assertEquals(true,subject.checkTablesAccess());
	}
	
	@Test
	public void testServiceAssistantNotOtherAccess ()
	{
		idc.connect("amyW");
    	subject= new AccessController();
        assertEquals(false,subject.checkCommandesAccess());
        assertEquals(false,subject.checkServiceAccess());
	}
	
	@Test
	public void testServiceAssistantOperationalAccess()
    {
    	idc.connect("amyW");
    	subject= new AccessController();
    	 assertEquals(true,subject.checkViewTablesAccess());
         assertEquals(false,subject.checkModifAdvancementMealAccess());
         assertEquals(true,subject.checkModifStatusTablesAccess());
         assertEquals(false,subject.checkCRUDTablesAccess());
         assertEquals(false,subject.checkAffectationTablesAccess());
    }
}
