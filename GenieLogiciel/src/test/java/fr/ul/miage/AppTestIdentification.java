package fr.ul.miage;



import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.ul.miage.GenieLogiciel.controller.IdentificationController;
import fr.ul.miage.GenieLogiciel.utils.Consts;
import fr.ul.miage.GenieLogiciel.utils.Session;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Scanner;
/**
 * Unit test for identification system.
 */
public class AppTestIdentification 
{
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
    public void testConnexionLoginInconnu()
    {
    	idc.connect("martinM");
        assertEquals(Session.getInstance().getCurrentUser(),null);
    }
	
	@Test
    public void testConnexionLoginVide()
    {
    	idc.connect(" ");
        assertEquals(Session.getInstance().getCurrentUser(),null);
    }
    
    @Test
    public void testConnexionDirecteur()
    {
    	idc.connect("louisM");
        assertEquals(Session.getInstance().getCurrentUser().getRole(),Consts.getConstants().get("ROLE").get("DIRECTOR"));
    }
    
    @Test
    public void testConnexionMaitreHotel()
    {
    	idc.connect("annaV");
        assertEquals(Session.getInstance().getCurrentUser().getRole(),Consts.getConstants().get("ROLE").get("HOTELMASTER"));
    }
    
    @Test
    public void testConnexionServeur()
    {
    	idc.connect("johnR");
        assertEquals(Session.getInstance().getCurrentUser().getRole(),Consts.getConstants().get("ROLE").get("SERVER"));
    }
    
    @Test
    public void testConnexionCuisinier()
    {
    	idc.connect("heleneP");
        assertEquals(Session.getInstance().getCurrentUser().getRole(),Consts.getConstants().get("ROLE").get("COOK"));
    }
    
    @Test
    public void testConnexionParLaVue() {
    	System.out.println("Utilisez le login louisM");
    	idc.initIdentification();
    	assertEquals(Session.getInstance().getCurrentUser().getRole(),Consts.getConstants().get("ROLE").get("DIRECTOR"));
    }
}
