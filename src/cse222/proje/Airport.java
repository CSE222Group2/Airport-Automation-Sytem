package cse222.proje;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

public class Airport {

	 Administrator administrator;
	 LinkedList<Firm> firms;
	 AVLTree<Employee> cleanningPersonels;
	 AVLTree<Employee> securityPersonels;
	 LinkedList<Sector> sectors;
	 String airportName;

	public Airport() {
		administrator = new Administrator("murat","yildiz",1801042004, "cse222");
		sectors = new LinkedList<>();
		cleanningPersonels = new AVLTree<>();
		securityPersonels = new AVLTree<>();
		firms = new LinkedList<>();
	}

	/**
	 * Creates an Airport object and creates first Administrator of Airport
	 * @param airpotName will be set
	 */
	public Airport(String airpotName){
		this.airportName = airpotName;
		administrator = new Administrator("murat","yildiz",1801042004, "cse222");
		sectors = new LinkedList<>();
		cleanningPersonels = new AVLTree<>();
		securityPersonels = new AVLTree<>();
		firms = new LinkedList<>();

	}

	public class Administrator extends Employee{

		public Administrator(String name, String surname, int ID, String password) {
			super(name, surname, ID, password);
		}

		/**
		 * Returns true if successfully add new SecurityPersonel, otherwise false
		 * @param sp will be added
		 * @return true if successfully add new SecurityPersonel, otherwise false
		 * @throws NullPointerException if given parameter is null
		 */
		public boolean addSecurityPersonel(SecurityPersonel sp){
			if (sp.equals(null))
				throw new NullPointerException("Security Personel cannot be null!");

			else {
				securityPersonels.add(sp);
				return true;
			}
		}

		/**
		 * Returns true if successfully add new CleanningPersonel, otherwise false
		 * @param cp will be added
		 * @return true if successfully add new CleanningPersonel, otherwise false
		 * @throws NullPointerException if given parameter is null
		 */
		public boolean addCleanningPersonel(CleanningPersonel cp){

			if (cp.equals(null))
				throw new NullPointerException("Cleaning Personel cannot be null!");

			else{
				cleanningPersonels.add(cp);
				return true;
			}
		}

		/**
		 * Returns true if successfully add new Sector, otherwise false
		 * @param sector will be added
		 * @return true if successfully add new Sector, otherwise false
		 * @throws NullPointerException if given parameter is null
		 */
		public boolean addSector(Sector sector){

			if (sector.equals(null))
				throw new NullPointerException("Sector cannot be null!");

			else{
				sectors.add(sector);
				return true;
			}
		}

		/**
		 * Returns true if successfully add new Firm, otherwise false
		 * @param firm will be added
		 * @return true if successfully add new Firm, otherwise false
		 * @throws NullPointerException if given parameter is null
		 */
		public boolean addFirm(Firm firm){

			if (firm.equals(null))
				throw new NullPointerException("Firm cannot be null!");

			else{
				firms.add(firm);
				return true;
			}
		}


		/**
		 *  Returns true if successfully remove given SecurityPersonel, otherwise false
		 * @param personel will be removed
		 * @return true if successfully remove given SecurityPersonel, otherwise false
		 * @throws NullPointerException if given parameter is null
		 */
		public SecurityPersonel removeSecurityPersonel(SecurityPersonel personel){

			if (personel.equals(null))
				throw new NullPointerException("Security Personel can not be null!");

			else{

				if (!securityPersonels.contains(personel))
					throw new NoSuchElementException("There is no such Personel in the list!");

				else
					return (SecurityPersonel) securityPersonels.remove(personel);

			}
		}

		/**
		 *  Returns true if successfully remove given CleanningPersonel, otherwise false
		 * @param personel will be removed
		 * @return true if successfully remove given CleanningPersonel, otherwise false
		 * @throws NullPointerException if given parameter is null
		 */

			public CleanningPersonel removeCleanningPersonel(CleanningPersonel personel){

			if (personel.equals(null))
				throw new NullPointerException("Cleaning Personel can not be null!");

			else{

				if (!securityPersonels.contains(personel))
					throw new NoSuchElementException("There is no such personel in the list!");

				else
					return (CleanningPersonel) cleanningPersonels.remove(personel);

			}
		}

		/**
		 * @return remove sector
		 */
		public Sector removeSector(){
			return sectors.remove();
		}

		/**
		 *  Returns true if successfully remove given Firm, otherwise false
		 * @param firm will be removed
		 * @return true if successfully remove given Firm, otherwise false
		 * @throws NullPointerException if given parameter is null
		 */
		public Firm removeFirm(Firm firm){

			if (firm.equals(null))
				throw new NullPointerException("Firm can not be null!");

			else{

				if (!firms.contains(firm))
					throw new NoSuchElementException("There is no such firm in the list!");

				else
					return firms.remove(firms.indexOf(firm));

			}
		}

		/**
		 * @return String Builder that contains firms
		 */
		public StringBuilder displayAllFirms(){
			return new StringBuilder(firms.toString());
		}

		/**
		 * @return String Builder that contains dirty sectors
		 */
		public StringBuilder displayDirtySectors(){

			StringBuilder sb = new StringBuilder();
			for (Sector sec: sectors) {
				if (sec.getCleanningState() == Sector.CleanningStates.DIRTY) {
					sb.append(sec.toString());
					sb.append(" ");
				}
			}
			return sb;
		}

		/**
		 * @return String Builder that contains clean sectors
		 */
		public StringBuilder displayCleanSectors(){
			StringBuilder sb = new StringBuilder();
			for (Sector sec: sectors) {
				if (sec.getCleanningState() == Sector.CleanningStates.CLEAN) {
					sb.append(sec.toString());
					sb.append(" ");
				}
			}
			return sb;
		}

		/**
		 * @return String Builder that contains security personels
		 */

		public StringBuilder displaySecurityPersonnel(){
			return securityPersonels.toStringBuilder();
		}

		/**
		 * @return String Builder that contains cleaning personels
		 */
		public StringBuilder displayCleaningPersonnel(){
			return cleanningPersonels.toStringBuilder();
		}
	}

	/**
	 * Returns Administrator which has given ID and password, if not exist returns null
	 * @param ID will be checked
	 * @param password will be checked
	 * @return Administrator which has given ID and password, if not exist returns null
	 */
	public Administrator findAdministrator(int ID, String password){
		return new Administrator("a","b", 1, "a");
	}

	/**
	 * Returns SecurityPersonel which has given ID and password, if not exist returns null
	 * @param ID will be checked
	 * @param password will be checked
	 * @return SecurityPersonel which has given ID and password, if not exist returns null
	 */
	public SecurityPersonel findSecurityPersonel(int ID, String password){
		return new SecurityPersonel("a","b", 1, "a");
	}

	/**
	 * Returns CleanningPersonel which has given ID and password, if not exist returns null
	 * @param ID will be checked
	 * @param password will be checked
	 * @return CleanningPersonel which has given ID and password, if not exist returns null
	 */
	public CleanningPersonel findCleanningPersonel(int ID, String password){
		return new CleanningPersonel("a","b", 1, "a");
	}

	/**
	 * Returns Sector which has given ID and password, if not exist returns null
	 * @param ID will be checked
	 * @param password will be checked
	 * @return Sector which has given ID and password, if not exist returns null
	 */
	public Sector findSector(int ID, String password){
		return new Sector();
	}

	/**
	 * Returns Firm which has given ID and password, if not exist returns null
	 * @param ID will be checked
	 * @param password will be checked
	 * @return Firm which has given ID and password, if not exist returns null
	 */
	public Firm findFirm(int ID, String password){
		return new Firm("a");
	}

	/**
	 * Returns flight data which flight has given flight ID
	 * @param flighID will be searched
	 * @return flight data which flight has given flight ID, otherwise null
	 */
	public String getFlightData(int flighID){
		return "";
	}

	public Administrator getAdministrator() {
		return administrator;
	}
}
