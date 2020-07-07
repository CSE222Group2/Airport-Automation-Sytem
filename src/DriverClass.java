import cse222.proje.*;

import java.io.File;
import java.util.Scanner;

public class DriverClass {

	public static void main(String[] args) throws Exception {

		DbConnection.connect();
		DbConnection.DeleteAllTable();
		DbConnection.CreateTable();

		//FLIGHT INFORMATION
		DbConnection.InsertFlightTable(84,1,24,6,2020,12.30);
		DbConnection.InsertFlightTable(84,2,9,7,2020,14.55);
		DbConnection.InsertFlightTable(86,3,8,8,2020,09.30);
		DbConnection.InsertFlightTable(86,4,10,8,2020,09.30);
		DbConnection.InsertFlightTable(88,5,12,8,2020,09.30);
		DbConnection.InsertFlightTable(88,6,10,8,2020,10.30);

		//HOSTESS INFORMATION
		DbConnection.InsertHostessTable("Emine","Düzen",84,1,"11","84H1");
		DbConnection.InsertHostessTable("Rukiye","Ozbuz",84,2,"87","84H2");
		DbConnection.InsertHostessTable("Merve","Su",86,3,"26","86H1");
		DbConnection.InsertHostessTable("Demir","Nazlı",86,4,"28","86H2");
		DbConnection.InsertHostessTable("Ahmet","Simsek",88,5,"21","88H1");
		DbConnection.InsertHostessTable("Can","Deniz",88,6,"32","88H2");

		//PILOT INFORMATION
		DbConnection.InsertPilotTable("Vecihi", "As",84, 1,"8749",84001);
		DbConnection.InsertPilotTable("Ahmet", "Sait",84, 2,"1122",84002);
		DbConnection.InsertPilotTable("Arzu", "Demir",86, 3,"0000",86003);
		DbConnection.InsertPilotTable("Can", "Sevgi",86, 4,"0012",86004);
		DbConnection.InsertPilotTable("İlker", "Oztekin",88,5, "3584",88005);
		DbConnection.InsertPilotTable("Sevda", "Oz",88,6, "8745",88006);

		//PLANE INFORMATION
		DbConnection.InsertPlaneTable(84,1,8401,"ReadyToFly");
		DbConnection.InsertPlaneTable(84,2,8402,"ReadyToFly");
		DbConnection.InsertPlaneTable(86,3,8601,"ReadyToFly");
		DbConnection.InsertPlaneTable(86,4,8602,"ReadyToFly");
		DbConnection.InsertPlaneTable(88,5,8801,"ReadyToFly");
		DbConnection.InsertPlaneTable(88,6,8802,"ReadyToFly");

		//FIRM INFORMATION
		DbConnection.InsertFirmTable(84,"GtuAir");
		DbConnection.InsertFirmTable(86,"Pegasus");
		DbConnection.InsertFirmTable(88,"THY");

		//SECTOR INFORMATION
		DbConnection.InsertSectorTable(100);
		DbConnection.InsertSectorTable(101);
		DbConnection.InsertSectorTable(102);

		//CLEANING PERSONNEL INFORMATION
		DbConnection.InsertCleaningPersonelTable("Berna", "Dal",100,"58");
		DbConnection.InsertCleaningPersonelTable("Merve", "Fener",101,"00");
		DbConnection.InsertCleaningPersonelTable("Merve", "Isık",102,"01");

		//SECURITY PERSONNEL INFORMATION
		DbConnection.InsertSecurityPersonelTable("Merve", "Can",100,"25");
		DbConnection.InsertSecurityPersonelTable("Ahmet", "Bas",101,"87");
		DbConnection.InsertSecurityPersonelTable("Kemal", "Gecer",102,"77");

		//Firm deneme = new Firm("GtuAir");
		//Airport deneme2 = new Airport();
		//deneme.findHostess(84,"11");
		//deneme.findHostess(84,"11");
		//Airport deneme = new Airport();

		Airport airport = new Airport(); ;//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!new Airport(new File("information.txt")); // create an Airport object
		int choice;
		boolean exitSystem = false;
		Scanner sc = new Scanner(System.in);

		do {
			System.out.printf("\n !!! Welcome to Airport System !!! \n");
			System.out.printf("\n Proceed as :\n" +
					"\n 1- Airport Personnel " +
					"\n 2- Firm Personnel " +
					"\n 3- Passenger" +
					"\n 4- Exit Airport System\n");

			choice = sc.nextInt();

			switch (choice) {
				case 1:
					System.out.printf("\n You have chosen to proceed as Airport Personnel: \n");
					System.out.printf("\n Proceed as : \n" +
							"\n 1- Airport Administrator " +
							"\n 2- Airport Security Personnel " +
							"\n 3- Airport Cleaning Personnel");

					int tempAirportChoice = sc.nextInt();

					switch (tempAirportChoice) {
						case 1:
							System.out.printf("\n You have chosen to proceed as Airport Administrator: \n");
							System.out.printf("\n Please give your Airport Administrator ID: ");
							int tempAirportAdminID = sc.nextInt();
							System.out.printf("\n Please give your Airport Administrator Password: ");
							String tempAirportAdminPassword = sc.next();

							Airport.Administrator tempAirportAdmin = airport.findAdministrator(tempAirportAdminID, tempAirportAdminPassword);
							if (tempAirportAdmin == null)
								System.out.printf("\n There is no Airport Administrator with that ID and password in the system \n");
							else{
								System.out.printf("\n Select you want to do as Firm Administrator \n" +
										"\n 1- Add Firm" +
										"\n 2- Remove Firm" +
										"\n 3- Display Firms" +
										"\n 4- Add Security Personel" +
										"\n 5- Remove Security Personel" +
										"\n 6- Display Security Personnel" +
										"\n 7- Add Cleaning Personel" +
										"\n 8- Remove Cleaning Personel" +
										"\n 9- Display Cleaning Personnel" +
										"\n 10- Add Sector" +
										"\n 11- Remove Sector" +
										"\n 12- Display Sectors\n" );

								int tempAdminChoice = sc.nextInt();
								switch (tempAdminChoice){
									case 1:
										System.out.printf("\n Please enter a new Firm name to add: ");
										String tempFirmName = sc.next();
										tempAirportAdmin.addFirm(new Firm(tempFirmName));
										break;
									case 2:
										System.out.printf("\n Please enter the Firm name you want to remove: ");
										tempFirmName = sc.next();
										if (tempAirportAdmin.removeFirm(new Firm(tempFirmName)) == null)
											System.out.printf("\n There is no Firm at that given name \n ");
										break;
									case 3:
										System.out.printf("\n%s\n", tempAirportAdmin.displayAllFirms());
										break;
									case 4:
										System.out.printf("\n Please enter a name for new Security Personel: ");
										String tempName = sc.next();
										System.out.printf("\n Please enter a surname for new Security Personel: ");
										String tempSurname = sc.next();
										System.out.printf("\n Please enter an ID for new Security Personel: ");
										int tempID = sc.nextInt();
										System.out.printf("\n Please enter a Password for new Security Personel: ");
										String tempPassword = sc.next();

										tempAirportAdmin.addSecurityPersonel(new SecurityPersonel(tempName, tempSurname, tempID, tempPassword));
										break;
									case 5:
										System.out.printf("\n Please enter the Security Personel ID you want to remove: ");
										tempID = sc.nextInt();
										if (tempAirportAdmin.removeSecurityPersonel(new SecurityPersonel(null, null, tempID, null)) == null)
											System.out.printf("\n There is no Security Personel at that given ID \n ");
										break;
									case 6:
										System.out.printf("\n%s\n", tempAirportAdmin.displaySecurityPersonnel());
										break;
									case 7:
										System.out.printf("\n Please enter a name for new Cleaning Personel: ");
										tempName = sc.next();
										System.out.printf("\n Please enter a surname for new Cleaning Personel: ");
										tempSurname = sc.next();
										System.out.printf("\n Please enter an ID for new Cleaning Personel: ");
										tempID = sc.nextInt();
										System.out.printf("\n Please enter a Password for new Cleaning Personel: ");
										tempPassword = sc.next();

										tempAirportAdmin.addSecurityPersonel(new SecurityPersonel(tempName, tempSurname, tempID, tempPassword));
										break;
									case 8:
										System.out.printf("\n Please enter the Cleaning Personel ID you want to remove: ");
										tempID = sc.nextInt();
										if (tempAirportAdmin.removeCleanningPersonel(new CleanningPersonel(null, null, tempID, null)) == null)
											System.out.printf("\n There is no Cleaning Personel at that given ID \n ");
										break;
									case 9:
										System.out.printf("\n%s\n", tempAirportAdmin.displayCleaningPersonnel());
										break;
									case 10:
										System.out.printf("\n Please enter the Sector ID you want to add: ");
										tempID = sc.nextInt();
										tempAirportAdmin.addSector(new Sector(tempID, Sector.CleanningStates.CLEAN, Sector.SecurityStates.SECURE));
										break;
									case 11:
										System.out.printf("\n Please enter the Sector ID you want to remove: ");
										tempID = sc.nextInt();
										if (tempAirportAdmin.removeSector(new Sector(tempID, null, null)) == null)
											System.out.printf("\n There is no Sector at that given ID \n ");
										break;
									case 12:
										System.out.printf("\n%s\n", tempAirportAdmin.displayCleanSectors());
										System.out.printf("\n%s\n", tempAirportAdmin.displayDirtySectors());
										break;
									default:
										System.out.printf("\n You entered an invalid input \n");
								}
							}

							break;
						case 2:
							System.out.printf("\n You have chosen to proceed as Airport Security Personnel: \n");
							System.out.printf("\n Please give your Security Personnel ID: ");
							int tempSPID = sc.nextInt();
							System.out.printf("\n Please give your Security Personnel Password: ");
							String tempSPPassword = sc.next();

							SecurityPersonel tempSP = airport.findSecurityPersonel(tempSPID, tempSPPassword);
							if (tempSP == null)
								System.out.printf("\n There is no Security Personnel with that ID and password in the system \n");
							else {
								System.out.printf("\n Please give Sector ID you want to update: ");
								int tempSectorID = sc.nextInt();
								Sector tempSector = airport.findSector(tempSectorID);

								if (tempSector == null)
									System.out.printf("\n There is no Sector with that ID in the system \n");
								else {
									tempSP.updateSector(tempSector);
									//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!airport.updateSectorSecurity(tempSector);
								}
							}
							break;
						case 3:
							System.out.printf("\n You have chosen to proceed as Airport Cleaning Personnel: \n");

							System.out.printf("\n Please give your Cleaning Personnel ID: ");
							int tempCPID = sc.nextInt();
							System.out.printf("\n Please give your Cleaning Personnel Password: ");
							String tempCPPassword = sc.next();

							CleanningPersonel tempCP = airport.findCleanningPersonel(tempCPID, tempCPPassword);
							if (tempCP == null)
								System.out.printf("\n There is no Cleaning Personnel with that ID and password in the system \n");
							else {
								System.out.printf("\n Select you want to do as Cleaning Personnel \n" +
										"\n 1- Display dirty sectors" +
										"\n 2- Display clean sectors" +
										"\n 3- Update a sector\n");

								tempAirportChoice = sc.nextInt();

								switch (tempAirportChoice) {
									case 1:
										//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!System.out.printf("\n%s\n", airport.displayDirtySectors());
										break;
									case 2:
										//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!System.out.printf("\n%s\n", airport.displayCleanSectors());
										break;
									case 3:
										System.out.printf("\n Please enter sector ID you want to update \n");
										int tempSectorID = sc.nextInt();
										Sector tempSector = airport.findSector(tempSectorID);

										if (tempSector == null)
											System.out.printf("\n There is no Sector with that ID in the system \n");
										else {
											tempCP.updateSector(tempSector);
											//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!airport.updateSectorCleaning(tempSector);
										}
										break;
									default:
										System.out.printf("\n You entered an invalid input \n");
								}
							}

							break;
						default:
							System.out.printf("\n You entered an invalid input \n");
					}

				case 2:
					System.out.printf("\n You have chosen to proceed as Firm Personnel: \n");
					System.out.printf("\n Please select the Firm you are working on:\n  ");
					//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!airport.displayAllFirms();

					System.out.printf("\n Please can give the name of Firm : ");
					String firmName = sc.next();

					Firm tempFirm = airport.findFirm(firmName);
					if (tempFirm == null)
						System.out.printf("\n The Firm name you entered is not defined in the system \n");
					else {
						System.out.printf("\n You are in the %s Firm, Proceed as :\n" +
								"\n 1- Firm Administrator " +
								"\n 2- Firm Pilot " +
								"\n 3- Firm Hostess", tempFirm.getFirmName());

						int tempChoice = sc.nextInt();

						switch (tempChoice) {
							case 1:
								System.out.printf("\n You have chosen to proceed as Firm Administrator: \n");
								System.out.printf("\n Please give your Administrator ID: ");
								int tempAdminID = sc.nextInt();
								System.out.printf("\n Please give your Administrator Password: ");
								String tempAdminPassword = sc.next();

								Firm.Administrator tempAdmin = tempFirm.findAdmin(tempAdminID, tempAdminPassword);
								if (tempAdmin == null)
									System.out.printf("\n There is no Administrator with ID: %d Password: %s \n", tempAdminID, tempAdminPassword);
								else {
									System.out.printf("\n Select you want to do as Firm Administrator \n" +
											"\n 1- Add Pilot" +
											"\n 2- Remove Pilot" +
											"\n 3- Display Pilots" +
											"\n 4- Add Hostess" +
											"\n 5- Remove Hostesses" +
											"\n 6- Display Hostesses" +
											"\n 7- Add Plane" +
											"\n 8- Remove Plane" +
											"\n 9- Display Planes" +
											"\n 10- Add Flight" +
											"\n 11- Remove Flight" +
											"\n 12- Display Flights" +
											"\n 13- Display old Flights" +
											"\n 14- Remove old Flights");

									int tempAdminChoice = sc.nextInt();
									int tempID;
									int tempPilot_Id,tempPlaneId;
									String tempName, tempSurname, tempPassword,tempHosId,tempHost_Id;
									switch (tempAdminChoice) {
										case 1:
											System.out.printf("\n Please give a name to add an Pilot: ");
											tempName = sc.next();
											System.out.printf("\n Please give a surname to add an Pilot: ");
											tempSurname = sc.next();
											System.out.printf("\n Please give a firm Id to add an Pilot: ");
											tempID = sc.nextInt();
											System.out.printf("\n Please give a password to add an Pilot: ");
											tempPassword = sc.next();
											System.out.printf("\n Please give a pilot ıd to add an Pilot: ");
											tempPilot_Id = sc.nextInt();
											Pilot tempPilot = new Pilot(tempName, tempSurname, tempID, tempPassword);
											tempPilot.setPilotId(tempPilot_Id);
											tempAdmin.addPilot(tempPilot);
											break;
										case 2:
											System.out.printf("\n Please give an exist Pilot ID to remove an Pilot: ");
											tempPilot_Id = sc.nextInt();
											Pilot tempPilotRemove = new Pilot(null, null, 0, null);
											tempPilotRemove.setPilotId(tempPilot_Id);
											tempAdmin.removePilot(tempPilotRemove);
											break;
										case 3:
											System.out.printf("\n%s\n", tempAdmin.displayPilots());
											break;
										case 4:
											System.out.printf("\n Please give a name to add an Hostess: ");
											tempName = sc.next();
											System.out.printf("\n Please give a surname to add an Hostess: ");
											tempSurname = sc.next();
											System.out.printf("\n Please give an new ID to add an Hostess: ");
											tempID = sc.nextInt();
											System.out.printf("\n Please give a password to add an Hostess: ");
											tempPassword = sc.next();
											System.out.printf("\n Please give a hostess ıd to add an Hostess: ");
											tempHosId = sc.next();
											Hostess tempHostess = new Hostess(tempName, tempSurname, tempID, tempPassword);
											tempHostess.setHostId(tempHosId);
											tempAdmin.addHostess(tempHostess);
											break;
										case 5:
											System.out.printf("\n Please give an exist hostess Id to remove an Hostess: ");
											tempHost_Id = sc.next();
											Hostess tempHostessRemove = new Hostess(null, null, 0, null);
											tempHostessRemove.setHostId(tempHost_Id);
											tempAdmin.removeHostess(tempHostessRemove);
											break;
										case 6:
											System.out.printf("\n%s\n", tempAdmin.displayHostesses());
											break;
										case 7:
											System.out.printf("\n Please give an ID to add an Plane: ");
											tempID = sc.nextInt();
											System.out.printf("\n Please give a uniqe plane ıd to add an Plane: ");
											tempPlaneId = sc.nextInt();
											Plane tempPlane= new Plane(tempID,tempPlaneId);
											tempAdmin.addPlane(tempPlane);
											break;
										case 8:
											/*System.out.printf("\n Please give an exist ID to remove an Plane: ");
											tempID = sc.nextInt();
											tempAdmin.removePlane(new Plane(tempID));*/
											break;
										case 9:
											System.out.printf("\n%s\n", tempAdmin.displayPlanes());
											break;
										case 10:
											System.out.printf("\n Please give a new ID to add an Flight: ");
											tempID = sc.nextInt();
											System.out.printf("\n Please give a exist Pilot ID to adjust Flight's Pilot: ");
											int tempPilotID = sc.nextInt();
											System.out.printf("\n Please give a exist Hostess ID to adjust Flight's Hostess: ");
											int tempHostessID = sc.nextInt();
											System.out.printf("\n Please give a exist Plane ID to adjust Flight's Plane: ");
											int tempPlaneID = sc.nextInt();

											System.out.printf("\n Please give Flight Date to adjust Flight's Date: \n");
											System.out.printf("\n Year: ");
											int tempYear = sc.nextInt();
											System.out.printf("\n Month: ");
											int tempMonth = sc.nextInt();
											System.out.printf("\n Day: ");
											int tempDay = sc.nextInt();
											System.out.printf("\n Time: ");
											int tempTime = sc.nextInt();

											//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!Pilot tempPilot = tempAdmin.findPilot(tempPilotID);
											//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!Hostess tempHostess = tempAdmin.findHostess(tempHostessID);
											//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!Plane tempPlane = tempAdmin.findPlane(tempPlaneID);
											Date tempDate = new Date(tempYear, tempMonth, tempDay, tempTime);

											//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!tempAdmin.addFlight(new Flight(tempID, tempPlane, tempPilot, tempHostess, tempDate));

											break;
										case 11:
											System.out.printf("\n Please give an exist ID to remove an Flight: ");
											tempID = sc.nextInt();
											tempAdmin.removeFlight(new Flight(tempID, null, null, null, null));
											break;
										case 12:
											System.out.printf("\n%s\n", tempAdmin.displayFlights());
											break;
										case 13:
											System.out.printf("\n%s\n", tempAdmin.displayOldFlights());
											break;
										case 14:
											System.out.printf("\n Please give a Flight Date to remove previous Flights from that date: \n");
											System.out.printf("\n Year: ");
											tempYear = sc.nextInt();
											System.out.printf("\n Month: ");
											tempMonth = sc.nextInt();
											System.out.printf("\n Day: ");
											tempDay = sc.nextInt();
											System.out.printf("\n Time: ");
											tempTime = sc.nextInt();

											tempDate = new Date(tempYear, tempMonth, tempDay, tempTime);
											tempAdmin.removeOldFlights(tempDate);
											break;

										default:
											System.out.printf("\n You entered an invalid input \n");
									}

								}
								break;
							case 2:
								System.out.printf("\n You have chosen to proceed as Firm Pilot: \n");
								System.out.printf("\n Please give your Pilot ID to see your flights: ");
								int tempPilotID = sc.nextInt();
								System.out.printf("\n Please give your Pilot Password to see your flights: ");
								String tempPilotPassword = sc.next();

								Pilot tempPilot = tempFirm.findPilot(tempPilotID, tempPilotPassword);
								if (tempPilot == null)
									System.out.printf("\n There is no Pilot with ID: %d Password: %s \n", tempPilotID, tempPilotPassword);
								else {
									System.out.printf("\n Select you want to do as Firm Pilot \n" +
											"\n 1- Display my flights " +
											"\n 2- Change a plane status ");

									int tempPilotChoice = sc.nextInt();
									switch (tempPilotChoice) {
										case 1:
											System.out.println("Please give your pilots's id");
											int pilotId = sc.nextInt();
											tempPilot.addedFlightsOnWorkPilot(pilotId);
											System.out.printf("\n%s\n", tempPilot.getFlights());
											break;
										case 2:
											System.out.println("Please give your pilots's id");
											int pilotId2 = sc.nextInt();
											tempPilot.addedFlightsOnWorkPilot(pilotId2);
											System.out.printf("\n%s\n", tempPilot.getFlights());
											System.out.printf("\n Please give the Flight ID you want to change its plane status: ");
											int tempPlaneID = sc.nextInt();
											tempPilot.setReadinessOfPlane(tempPlaneID);
											break;
										default:
											System.out.printf("\n You entered an invalid input \n");
									}

								}
								break;
							case 3:
								System.out.printf("\n You have chosen to proceed as Firm Hostess: \n");
								System.out.printf("\n Please give your Firm ID to see your flights: ");
								int tempHostessID = sc.nextInt();
								System.out.printf("\n Please give your Hostess Password to see your flights: ");
								String tempHostessPassword = sc.next();

								Hostess tempHostess = tempFirm.findHostess(tempHostessID, tempHostessPassword);

								System.out.print("\n Please give your Hostess ID: ");
								String hostId = sc.next();
								tempHostess.addedFlightsOnWorkHostess(hostId);

								if (tempHostess == null)
									System.out.printf("\n There is no Hostess with ID: %d Password: %s \n", tempHostessID, tempHostessPassword);
								else
									System.out.printf("\n%s\n", tempHostess.getFlights());
								break;
							default:
								System.out.printf("\n You entered an invalid input \n");
						}

					}

					break;
				case 3:
					System.out.printf("\n You have chosen to proceed as Passenger: \n");
					System.out.printf("\n Please give a flight ID to display information:  ");

					int flightID = sc.nextInt();
					System.out.printf("\n Flight data: \n %s", airport.getFlightData(flightID));
					break;
				case 4:
					exitSystem = true;
					break;
				default:
					System.out.printf("\n You entered an invalid input \n");

			}

		}while (exitSystem) ;

	}

}