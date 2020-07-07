package cse222.proje;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;

public class Firm {
    /**
     * Holds Firm's administrators
     */
    Administrator administrator;
    /**
     * Holds Firm's flights
     */
    AVLTree<Flight> flights;
    /**
     * Holds Firm's planes
     */
    Set<Plane> planes;//ArrayList<Plane> planes;
    /**
     * Holds Firm's hostesses
     */
    ConcurrentSkipListSet<Hostess> hostesses;
    /**
     * Holds Firm's Pilots
     */
    ArrayList<Pilot> pilots;
    /**
     * Holds Firm's name
     */
    String firmName;
    /**
     * Save old flight in a heap
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

        public Administrator() {
            super();
        }

        /**
         * Returns true if successfully add new pilot, otherwise false
         * @param newPilot will be added
         * @return true if successfully add new pilot, otherwise false
         * @throws NullPointerException if given parameter is null
         */
        public boolean addPilot(Pilot newPilot){
            if (newPilot.equals(null)) throw new NullPointerException("Pilot cannot be null!");
            if(DbConnection.ControlExistPilot(newPilot.getID(),newPilot.getPilotId()) == 0){
                DbConnection.InsertPilotTable(newPilot.getName(), newPilot.getSurname(),newPilot.getID(),0, newPilot.getPassword(),newPilot.getPilotId());
                pilots.add(newPilot);
                return true;
            }
            else
                return false;
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
            flights.add(newFlight);
            return true;
        }

        /**
         * Returns true if successfully add new hostess, otherwise false
         * @param newHostess will be added
         * @return true if successfully add new hostess, otherwise false
         * @throws NullPointerException if given parameter is null
         */
        public boolean addHostess(Hostess newHostess){
            if (newHostess.equals(null)) throw new NullPointerException("Hostess cannot be null!");
            if(DbConnection.ControlExistHostess(newHostess.getID(),newHostess.getHostId()) == 0){
                DbConnection.InsertHostessTable(newHostess.getName(), newHostess.getSurname(),newHostess.getID(),0, newHostess.getPassword(),newHostess.getHostId());
                hostesses.add(newHostess);
                return true;
            }
            else
                return false;
        }

        /**
         * Returns true if successfully remove given pilot, otherwise false
         * @param removePilot will be removed
         * @return true if successfully remove given pilot, otherwise false
         * @throws NullPointerException if given parameter is null
         */
        public boolean removePilot(Pilot removePilot){
            if (removePilot.equals(null)) throw new NullPointerException("Pilot cannot be null!");
            Pilot temp = null;
            int firmId = DbConnection.SelectFirmAccordingToPilotId(removePilot.getPilotId());

            if(DbConnection.ControlExistPilots(firmId) == 0)
            {
                System.out.println("No other pilots, pilot cannot be deleted");
                return false;
            }

            DbConnection.DeletePilotRow(removePilot.getPilotId());
            Scanner scInt = new Scanner(System.in);
            System.out.println("select one of the flight numbers provided : ");
            for(int i= 0 ; i < DbConnection.SelectFlightIdFromFirmIdForPilot(firmId).size() ;++i )
                 System.out.println(DbConnection.SelectFlightIdFromFirmIdForPilot(firmId).get(i));
            int tempID = scInt.nextInt();
            DbConnection.UpdatePilotAccordingTıFlightId(firmId,tempID);
            return true;
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

            Flight tmp = flights.remove(removeFlight);
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
            if (removeHostess.equals(null)) throw new NullPointerException("Pilot cannot be null!");
            Pilot temp = null;
            int firmId = DbConnection.SelectFirmAccordingToHostId(removeHostess.getHostId());

            if(DbConnection.ControlExistHostess(firmId) == 0)
            {
                System.out.println("No other pilots, pilot cannot be deleted");
                return false;
            }

            DbConnection.DeleteHostessRow(removeHostess.getHostId());
            Scanner scanner = new Scanner(System.in);
            System.out.println("select one of the flight numbers provided : ");
            for(int i= 0 ; i < DbConnection.SelectFlightIdFromFirmIdForHostess(firmId).size() ;++i )
                System.out.println(DbConnection.SelectFlightIdFromFirmIdForHostess(firmId).get(i));
            String tempID = scanner.next();
            DbConnection.UpdateHostAccordingTıFlightId(firmId,tempID);
            return true;

        }

        /**
         * Returns all pilots that firm has as StringBuilder
         * @return all pilots that firm has as StringBuilder
         */
        public StringBuilder displayPilots(){
            StringBuilder str = new StringBuilder();
            for(Employee it : pilots) str.append(it.toString());
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
            while(date.compareTo(oldFlights.heap.get(0).flightDate) < 0) oldFlights.remove();
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

    public Firm(){
        super();
    }
    /**
     * Creates a Firm object and creates first Administrator of that Firm
     * @param firmName will be set
     */
    public Firm(String firmName){
        this.firmName = firmName;
        this.administrator = new Administrator("", "", 0, "");
        this.flights = new AVLTree<Flight>();
        this.hostesses = new ConcurrentSkipListSet<Hostess>();
        this.pilots = new ArrayList<Pilot>();
        this.planes = new HashSet<Plane>();
        addedArrayLists();
    }

    /**
     * Returns Administrator which has given ID and password, if not exist returns null
     * @param ID will be checked
     * @param password will be checked
     * @return Administrator which has given ID and password, if not exist returns null
     */
    public Administrator findAdmin(int ID, String password){
        return new Administrator();
    }

    public String getFirmName() {
        return firmName;
    }

    public void setFirmName(String firmName) {
        this.firmName = firmName;
    }

    public void addedArrayLists(){
        LinkedList firmList = DbConnection.SelectFirm();
    /*for (Object item: firmList) {
        System.out.println(item.toString());
    }*/
    }
    /**
     * Returns Pilot which has given ID and password, if not exist returns null
     * @param ID will be checked
     * @param password will be checked
     * @return Pilot which has given ID and password, if not exist returns null
     */
    public Pilot findPilot(int ID, String password){
        for(Pilot it : pilots) {
            if (it.getPilotId() == ID && it.getPassword().equals(password)) return (Pilot) it;
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
