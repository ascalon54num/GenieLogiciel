package fr.ul.miage.GenieLogiciel.utils;

import java.util.HashMap;

public class Consts {
	
	private static Consts instance = new Consts();
	private HashMap<String,HashMap<String, Integer>> constants;
	private Consts() {
		constants = new HashMap<String, HashMap<String,Integer>>();
		constants.put("ROLE", new HashMap<String, Integer>());
		constants.get("ROLE").put("DIRECTOR", 1);
		constants.get("ROLE").put("HOTELMASTER", 2);
		constants.get("ROLE").put("SERVER", 3);
		constants.get("ROLE").put("COOK", 4);
	}
	public static HashMap<String,HashMap<String, Integer>> getConstants() {
		// TODO Auto-generated method stub
		return getInstance().constants;
	}
	private static Consts getInstance() {
		// TODO Auto-generated method stub
		return instance;
	}

}
