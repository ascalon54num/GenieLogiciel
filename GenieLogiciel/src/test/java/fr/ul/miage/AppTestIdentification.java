package fr.ul.miage;



import org.junit.jupiter.api.BeforeAll;
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
}
