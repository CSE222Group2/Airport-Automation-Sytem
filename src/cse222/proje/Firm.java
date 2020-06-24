package cse222.proje;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Firm {
    /**
     * Holds Firm's administrators
     */
    Administrator administrator;
    /**
     * Holds Firm's flights
     */
    ArrayList<Flight> flights; // avl tree
    /**
     * Holds Firm's planes 
     */
    ArrayList<Plane> planes; // set
    /**
     * Holds Firm's hostesses
     */
    ArrayList<Hostess> hostesses; // skip list
    /**
     * Holds Firm's Pilots
     */
    ArrayList<Pilot> pilots; // avl tree
    /**
     * Holds Firm's name
     */
    String firmName;
    /**
     * Save old flight in a stack
     */
    MinHeap<Flight> oldFlights;

    public class Administrator extends Employee{
        /**
         * Creates an Administrator of Firm object
         * @param name will be set
         * @param surname will be set
         * @param ID will be set
         * @param password will be set
         */
        public Administrator(String name, String surname, int ID, String password) {
            super(name, surname, ID, password);
        }

        /**
         * Returns true if successfully add new pilot, otherwise false
         * @param newPilot will be added
         * @return true if successfully add new pilot, otherwise false
         * @throws NullPointerException if given parameter is null
         */
        public boolean addPilot(Pilot newPilot){
        	if (newPilot.equals(null)) throw new NullPointerException("Pilot cannot be null!");
            return pilots.add(newPilot);
        }

        /**
         * Returns true if successfully add new plane, otherwise false
         * @param newPlane will be added
         * @return true if successfully add new plane, otherwise false
         * @throws NullPointerException if given parameter is null
         */
        public boolean addPlane(Plane newPlane){
        	if (newPlane.equals(null)) throw new NullPointerException("Plane cannot be null!");
            return planes.add(newPlane);
        }

        /**
         * Returns true if successfully add new flight, otherwise false
         * @param newFlight will be added
         * @return true if successfully add new flight, otherwise false
         * @throws NullPointerException if given parameter is null
         */
        public boolean addFlight(Flight newFlight){
        	if (newFlight.equals(null)) throw new NullPointerException("Flight cannot be null!");
        	return flights.add(newFlight);
        }

        /**
         * Returns true if successfully add new hostess, otherwise false
         * @param newHostess will be added
         * @return true if successfully add new hostess, otherwise false
         * @throws NullPointerException if given parameter is null
         */
        public boolean addHostess(Hostess newHostess){
        	if (newHostess.equals(null)) throw new NullPointerException("Hostess cannot be null!");
            return hostesses.add(newHostess);
        }

        /**
         * Returns true if successfully remove given pilot, otherwise false
         * @param removePilot will be removed
         * @return true if successfully remove given pilot, otherwise false
         * @throws NullPointerException if given parameter is null
         */
        public boolean removePilot(Pilot removePilot){
        	if (removePilot.equals(null)) throw new NullPointerException("Pilot cannot be null!");
        	
        	int index = pilots.indexOf(removePilot);
        	
        	if(index >= 0 && pilots.get(index).flights.size() > 0) {
        		Scanner scInt = new Scanner(System.in);
        		Scanner scStr = new Scanner(System.in);
        		System.out.printf("\n Please give an ID to change the pilot: ");
				int tempID = scInt.nextInt();
				System.out.printf("\n Please give a password to change the pilot: ");
				String tempPassword = scStr.nextLine();
				
        		Pilot newPilot = findPilot(tempID, tempPassword);
        		
        		for(Flight it : removePilot.flights) {
        			it.setPilot(newPilot);
        			newPilot.addFlight(it);
        		}
        	}
            return pilots.remove(removePilot);
        }

        /**
         *  Returns true if successfully remove given plane, otherwise false
         * @param removePlane will be removed
         * @return true if successfully remove given plane, otherwise false
         * @throws NullPointerException if given parameter is null
         */
        public boolean removePlane(Plane removePlane){
        	if (removePlane.equals(null)) throw new NullPointerException("Plane cannot be null!");
        	
        	for(Flight it : flights) {
        		if(it.plane.equals(removePlane)) {
        			Scanner sc = new Scanner(System.in);
        			System.out.printf("\n Given plane has a flight: " + it.flightID);
            		System.out.printf("\n Please give an ID to change the plane: ");
    				int tempID = sc.nextInt();
    				Plane newPlane = findPlane(tempID);
    				it.setPlane(newPlane); 				
        		}
        	}
        	
            return planes.remove(removePlane);
        }

        /**
         *  Returns true if successfully remove given flight, otherwise false
         *  add removed flight to oldFlights
         * @param removeFlight will be removed
         * @return true if successfully remove given flight, otherwise false
         * @throws NullPointerException if given parameter is null
         */
        public boolean removeFlight(Flight removeFlight){
        	if (removeFlight.equals(null)) throw new NullPointerException("Flight cannot be null!");
        	
        	int index = flights.indexOf(removeFlight);
        	Flight tmp = flights.remove(index);
        	tmp.getHostess().removeFlight(removeFlight);
        	tmp.getPilot().removeFlight(removeFlight);
        	return true;
        }

        /**
         *  Returns true if successfully remove given hostess, otherwise false
         * @param removeHostess will be removed
         * @return true if successfully remove given hostess, otherwise false
         * @throws NullPointerException if given parameter is null
         */
        public boolean removeHostess(Hostess removeHostess){
        	if (removeHostess.equals(null)) throw new NullPointerException("Hostess cannot be null!");
        	
        	int index = hostesses.indexOf(removeHostess);
        	
        	if(index >= 0 && hostesses.get(index).flights.size() > 0) {
        		Scanner scInt = new Scanner(System.in);
        		Scanner scStr = new Scanner(System.in);
        		System.out.printf("\n Please give an ID to change the hostess: ");
				int tempID = scInt.nextInt();
				System.out.printf("\n Please give a password to change the hostess: ");
				String tempPassword = scStr.next();
				
        		Hostess newHostess = findHostess(tempID, tempPassword);
        		
        		for(Flight it : removeHostess.flights) {
        			it.setHostess(newHostess);
        			newHostess.addFlight(it);
        		}
        	}
            return hostesses.remove(removeHostess);
        }

        /**
         * Returns all pilots that firm has as StringBuilder
         * @return all pilots that firm has as StringBuilder
         */
        public StringBuilder displayPilots(){
        	StringBuilder str = new StringBuilder();
        	for(Pilot it : pilots) str.append(it.toString());        	
            return str;
        }

        /**
         * Returns all planes that firm has as StringBuilder
         * @return all planes that firm has as StringBuilder
         */
        public StringBuilder displayPlanes(){
        	StringBuilder str = new StringBuilder();
        	for(Plane it : planes) str.append(it.toString());        	
            return str;
        }

        /**
         * Returns all flights that firm has as StringBuilder
         * @return all flights that firm has as StringBuilder
         */
        public StringBuilder displayFlights(){
        	StringBuilder str = new StringBuilder();
        	for(Flight it : flights) str.append(it.toString());        	
            return str;
        }

        /**
         * Returns all hostesses that firm has as StringBuilder
         * @return all hostesses that firm has as StringBuilder
         */
        public StringBuilder displayHostesses(){
        	StringBuilder str = new StringBuilder();
        	for(Hostess it : hostesses) str.append(it.toString());        	
            return str;
        }

        /**
         * Returns true if successfully add given flight to oldFlights, otherwise false
         * @param oldFlight will be added
         * @return true if successfully add new flight, otherwise false
         * @throws NullPointerException if given parameter is null
         */
        public boolean addOldFlight(Flight oldFlight){
        	oldFlights.insert(oldFlight);
            return true;
        }    
        
        public boolean removeOldFlights(Date date){
        	/////////////
            return true;
        }
        
        /**
         * Returns all old flights that firm has as StringBuilder
         * @return all old flights that firm has as StringBuilder
         */
        public StringBuilder displayOldFlights(){
            StringBuilder str = new StringBuilder();
            Iterator<Flight> it = oldFlights.iterator();
            while(it.hasNext()) {
            	str.append(it.next().toString());
            }
            return str;
        }
    }

    /**
     * Creates a Firm object and creates first Administrator of that Firm
     * @param firmName will be set
     */
    public Firm(String firmName){
        this.firmName = firmName;
        this.administrator = new Administrator("", "", 0, "");
        this.flights = new ArrayList<Flight>();
        this.hostesses = new ArrayList<Hostess>();
        this.pilots = new ArrayList<Pilot>();
        this.planes = new ArrayList<Plane>();
    }
    
    public String getFirmName() {
		return firmName;
	}
    
    public void setFirmName(String firmName) {
		this.firmName = firmName;
	}
    
     /**
     * Returns Pilot which has given ID and password, if not exist returns null
     * @param ID will be checked
     * @param password will be checked
     * @return Pilot which has given ID and password, if not exist returns null
     */
    public Pilot findPilot(int ID, String password){
    	for(Pilot it : pilots) {
        	if (it.getID() == ID && it.getPassword().equals(password)) return it;
        }
        
        System.out.printf("No pilot has found!");
    	return null;
    }

    /**
     * Returns Hostess which has given ID and password, if not exist returns null
     * @param ID will be checked
     * @param password will be checked
     * @return Hostess which has given ID and password, if not exist returns null
     */
    public Hostess findHostess(int ID, String password){
    	for(Hostess it : hostesses) {
        	if (it.getID() == ID && it.getPassword().equals(password)) return it;
        }
        
        System.out.printf("No hostess has found!");
    	return null;
    }

    /**
     * Returns Plane which has given ID and password, if not exist returns null
     * @param planeID will be checked
     * @return Plane which has given ID and password, if not exist returns null
     */
    public Plane findPlane(int planeID){
    	for(Plane it : planes) {
        	if (it.getPlaneID() == planeID) return it;
        }
    	
        System.out.printf("No plane has found!");
    	return null;
    }

    /**
     * Returns Flight which has given flightID, if not exist returns null
     * @param flightID will be checked
     * @return Flight which has given ID and password, if not exist returns null
     */
    public Flight findFlight(int flightID){
    	for(Flight it : flights) {
        	if (it.getFlightID() == flightID) return it;
        }
        
        System.out.printf("No flight has found!");
    	return null;
    }

    public String toString() {
    	return firmName;
    }
}
