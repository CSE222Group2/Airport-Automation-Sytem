package cse222.proje;

import cse222.proje.*;
import cse222.proje.Date;

import java.sql.*;
import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;

public class DbConnection {
    public static Connection connect() {
        Connection con = null;
        try{
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:AirportProject.db");
            System.out.println("Veri tabanına bağlandınız");
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return con;
    }
    public static void DeleteAllTable() throws SQLException {
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        String sql = "";
        try {
            sql = "DROP TABLE IF EXISTS CLEANNING_PERSONEL";
            ps = con.prepareStatement(sql);
            ps.execute();
            sql = "DROP TABLE IF EXISTS FIRM";
            ps = con.prepareStatement(sql);
            ps.execute();
            sql = "DROP TABLE IF EXISTS FLIGHT";
            ps = con.prepareStatement(sql);
            ps.execute();
            sql = "DROP TABLE IF EXISTS HOSTESS";
            ps = con.prepareStatement(sql);
            ps.execute();
            sql = "DROP TABLE IF EXISTS PILOT";
            ps = con.prepareStatement(sql);
            ps.execute();
            sql = "DROP TABLE IF EXISTS PLANE";
            ps = con.prepareStatement(sql);
            ps.execute();
            sql = "DROP TABLE IF EXISTS SECURITY_PERSONEL";
            ps = con.prepareStatement(sql);
            ps.execute();
            sql = "DROP TABLE IF EXISTS OLD_FLIGHT";
            ps = con.prepareStatement(sql);
            ps.execute();
            sql = "DROP TABLE IF EXISTS SECTOR";
            ps = con.prepareStatement(sql);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void CreateTable()
    {
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        String sql = "";
        try{
           /* String sql = "CREATE TABLE \"ADMINISTRATOR\" ( \"Name\" TEXT, \"Surname\" TEXT, \"Id\" INTEGER, \"Password\" TEXT )";
            ps = con.prepareStatement(sql);
            ps.execute();*/
            sql = "CREATE TABLE IF NOT EXISTS \"CLEANNING_PERSONEL\" ( \"Name\" TEXT, \"Surname\" TEXT, \"Id\" INTEGER, \"Password\" TEXT )";
            ps = con.prepareStatement(sql);
            ps.execute();
            sql = "CREATE TABLE IF NOT EXISTS \"FIRM\" ( \"Id\" INTEGER, \"FirmName\" TEXT )";
            ps = con.prepareStatement(sql);
            ps.execute();
            sql = "CREATE TABLE IF NOT EXISTS \"FLIGHT\" ( \"Id\" INTEGER, \"FlightId\" INTEGER, \"Day\" INTEGER, \"Month\" INTEGER, \"Year\" INTEGER , \"Time\" REAL)";
            ps = con.prepareStatement(sql);
            ps.execute();
            sql = "CREATE TABLE IF NOT EXISTS \"HOSTESS\" ( \"Name\" TEXT, \"Surname\" TEXT, \"Id\" INTEGER, \"FlightId\" INTEGER, \"Password\" TEXT , \"HostId\" TEXT  )";
            ps = con.prepareStatement(sql);
            ps.execute();
            sql = "CREATE TABLE IF NOT EXISTS \"PILOT\" ( \"Name\" TEXT, \"Surname\" TEXT, \"Id\" INTEGER, \"FlightId\" INTEGER, \"Password\" TEXT , \"PilotId\" INTEGER )";
            ps = con.prepareStatement(sql);
            ps.execute();
            sql = "CREATE TABLE IF NOT EXISTS \"PLANE\" ( \"Id\" INTEGER  ,\"FlightId\" INTEGER, \"PlaneId\" INTEGER, \"Status\" TEXT)";
            ps = con.prepareStatement(sql);
            ps.execute();
            sql = "CREATE TABLE IF NOT EXISTS \"SECTOR\" ( \"Id\" INTEGER )";
            ps = con.prepareStatement(sql);
            ps.execute();
            sql = "CREATE TABLE IF NOT EXISTS \"SECURITY_PERSONEL\" ( \"Name\" TEXT, \"Surname\" TEXT, \"Id\" INTEGER, \"Password\" TEXT )";
            ps = con.prepareStatement(sql);
            ps.execute();
            sql = "CREATE TABLE IF NOT EXISTS \"OLD_FLIGHT\" ( \"Id\" INTEGER )";///nasıl yapılacak bilemedim
            ps = con.prepareStatement(sql);
            ps.execute();
            System.out.println("Create table");
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        finally {
            try {
                ps.close();
                con.close();
            }
            catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }

    }
    public static void InsertFirmTable(int id,String firmName)
    {
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        try{
            String sql = "INSERT INTO FIRM(Id,FirmName) VALUES(?,?) ";
            ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            ps.setString(2,firmName);
            ps.execute();
            System.out.println("Insert firm table");
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        finally {
            try {
                ps.close();
                con.close();
            }
            catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }
    public static void InsertCleaningPersonelTable(String name, String surname, int id, String password)
    {
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        try{
            String sql = "INSERT INTO CLEANNING_PERSONEL(Name,Surname,Id,Password) VALUES(?,?,?,?) ";
            ps = con.prepareStatement(sql);
            ps.setString(1,name);
            ps.setString(2,surname);
            ps.setInt(3,id);
            ps.setString(4,password);
            ps.execute();
            System.out.println("Insert cleaning personel table");
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        finally {
            try {
                ps.close();
                con.close();
            }
            catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }
    public static void InsertFlightTable(int id, int flightId,int day, int month, int year,double time)
    {
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        try{
            String sql = "INSERT INTO FLIGHT(Id,FlightId,Day,Month,Year,Time) VALUES(?,?,?,?,?,?) ";
            ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            ps.setInt(2,flightId);
            ps.setInt(3,day);
            ps.setInt(4,month);
            ps.setInt(5,year);
            ps.setDouble(6,time);
            ps.execute();
            System.out.println("Insert flight table");
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        finally {
            try {
                ps.close();
                con.close();
            }
            catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }
    public static void InsertOldFlightTable(int id)
    {
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        try{
            String sql = "INSERT INTO OLD_FLIGHT(Id) VALUES(?) ";
            ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            ps.execute();
            System.out.println("Insert old flight table");
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        finally {
            try {
                ps.close();
                con.close();
            }
            catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }
    public static void InsertHostessTable(String name, String surname, int id,int flightId, String password,String hostId)
    {
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        try{
            String sql = "INSERT INTO HOSTESS(Name,Surname,Id,FlightId,Password,HostId) VALUES(?,?,?,?,?,?) ";
            ps = con.prepareStatement(sql);
            ps.setString(1,name);
            ps.setString(2,surname);
            ps.setInt(3,id);
            ps.setInt(4,flightId);
            ps.setString(5,password);
            ps.setString(6,hostId);
            ps.execute();
            System.out.println("Insert hostess table");
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        finally {
            try {
                ps.close();
                con.close();
            }
            catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }
    public static void InsertPilotTable(String name, String surname,int id,int flightId, String password,int pilotId) {
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        try {
            String sql = "INSERT INTO PILOT(Name,Surname,Id,FlightId,Password,PilotId) VALUES(?,?,?,?,?,?) ";
            ps = con.prepareStatement(sql);
            ps = con.prepareStatement(sql);
            ps.setString(1,name);
            ps.setString(2,surname);
            ps.setInt(3,id);
            ps.setInt(4,flightId);
            ps.setString(5,password);
            ps.setInt(6,pilotId);
            ps.execute();
            System.out.println("Insert pilot table");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public static void InsertPlaneTable(int id,int flightId,int planeId, String status)
    {
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        try{
            String sql = "INSERT INTO PLANE(Id,FlightId,PlaneId,Status) VALUES(?,?,?,?) ";
            ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            ps.setInt(2,flightId);
            ps.setInt(3,planeId);
            ps.setString(4,status);
            ps.execute();
            System.out.println("Insert plane table");
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        finally {
            try {
                ps.close();
                con.close();
            }
            catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }
    public static void InsertSectorTable(int id)
    {
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        try{
            String sql = "INSERT INTO SECTOR(Id) VALUES(?) ";
            ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            ps.execute();
            System.out.println("Insert sector table");
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        finally {
            try {
                ps.close();
                con.close();
            }
            catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }
    public static void InsertSecurityPersonelTable(String name, String surname, int id, String password)
    {
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        try{
            String sql = "INSERT INTO SECURITY_PERSONEL(Name,Surname,Id,Password) VALUES(?,?,?,?) ";
            ps = con.prepareStatement(sql);
            ps.setString(1,name);
            ps.setString(2,surname);
            ps.setInt(3,id);
            ps.setString(4,password);
            ps.execute();
            System.out.println("Insert security personel table");
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        finally {
            try {
                ps.close();
                con.close();
            }
            catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }
    public static int ControlExistPilot(int firmID,int pilotID)
    {
        int count = 0;
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            String sql = "SELECT DISTINCT * FROM PILOT WHERE Id = ? and PilotId = ? ";
            ps = con.prepareStatement(sql);
            ps.setInt(1,firmID);
            ps.setInt(2,pilotID);
            rs = ps.executeQuery();
            count = rs.getInt(1);
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        finally {
            try {
                rs.close();
                ps.close();
                con.close();
            }
            catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
        return count;
    }
    public static int ControlExistHostess(int firmID,String hostID)
    {
        int count = 0;
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            String sql = "SELECT DISTINCT * FROM HOSTESS WHERE Id = ? and HostId = ? ";
            ps = con.prepareStatement(sql);
            ps.setInt(1,firmID);
            ps.setString(2,hostID);
            rs = ps.executeQuery();
            count = rs.getInt(1);
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        finally {
            try {
                rs.close();
                ps.close();
                con.close();
            }
            catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
        return count;
    }
    public static ConcurrentSkipListSet<Hostess> SelectHostessTableFirmId(int ID)
    {
        ConcurrentSkipListSet<Hostess> hostes = new ConcurrentSkipListSet<Hostess>();
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            String sql = "SELECT DISTINCT * FROM HOSTESS WHERE Id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1,ID);
            rs = ps.executeQuery();
            while(rs.next())
            {
                Hostess host = new Hostess();
                String name = rs.getString("Name");
                String surName = rs.getString("Surname");
                int id = rs.getInt("Id");
                String password = rs.getString("Password");
                host.setName(name);
                host.setSurname(surName);
                host.setID(id);
                host.setPassword(password);
                hostes.add(host);
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        finally {
            try {
                rs.close();
                ps.close();
                con.close();
            }
            catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
        return hostes;
    }
    public static Hostess SelectHostessTableFlightId(int ID)
    {
        Hostess host = new Hostess();
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            String sql = "SELECT DISTINCT * FROM HOSTESS WHERE FlightId = ? ";
            ps = con.prepareStatement(sql);
            ps.setInt(1,ID);
            rs = ps.executeQuery();
            while(rs.next())
            {
                String name = rs.getString("Name");
                String surName = rs.getString("Surname");
                int id = rs.getInt("FlightId");
                String password = rs.getString("Password");
                host.setName(name);
                host.setSurname(surName);
                host.setID(id);
                host.setPassword(password);
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        finally {
            try {
                rs.close();
                ps.close();
                con.close();
            }
            catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
        return host;
    }
    public static AVLTree<Employee> SelectCleanningPersonel()
    {
        AVLTree<Employee> cleanPersonel = new AVLTree<Employee>();
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            String sql = "SELECT DISTINCT * FROM CLEANNING_PERSONEL";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next())
            {
                CleanningPersonel clean = new CleanningPersonel();
                String name = rs.getString("Name");
                String surName = rs.getString("Surname");
                int id = rs.getInt("Id");
                String password = rs.getString("Password");
                clean.setName(name);
                clean.setSurname(surName);
                clean.setID(id);
                clean.setPassword(password);
                cleanPersonel.add(clean);
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        finally {
            try {
                rs.close();
                ps.close();
                con.close();
            }
            catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
        return cleanPersonel;
    }
    public static AVLTree<Employee> SelectSecurityPersonel()
    {
        AVLTree<Employee> securityPersonel = new AVLTree<Employee>();
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            String sql = "SELECT DISTINCT * FROM SECURITY_PERSONEL";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next())
            {
                SecurityPersonel security = new SecurityPersonel();
                String name = rs.getString("Name");
                String surName = rs.getString("Surname");
                int id = rs.getInt("Id");
                String password = rs.getString("Password");
                security.setName(name);
                security.setSurname(surName);
                security.setID(id);
                security.setPassword(password);
                securityPersonel.add(security);
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        finally {
            try {
                rs.close();
                ps.close();
                con.close();
            }
            catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
        return securityPersonel;
    }
    public static ArrayList<Pilot> SelectPilotFirmId(int ID)
    {
        ArrayList<Pilot> listPilot = new ArrayList<Pilot>();
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            String sql = "SELECT DISTINCT * FROM PILOT WHERE Id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1,ID);
            rs = ps.executeQuery();
            while(rs.next())
            {
                Pilot pilot = new Pilot();
                String name = rs.getString("Name");
                String surName = rs.getString("Surname");
                int id = rs.getInt("Id");
                int pilotId = rs.getInt("PilotId");
                String password = rs.getString("Password");
                pilot.setPilotId(pilotId);
                pilot.setName(name);
                pilot.setSurname(surName);
                pilot.setID(id);
                pilot.setPassword(password);
                listPilot.add(pilot);
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        finally {
            try {
                rs.close();
                ps.close();
                con.close();
            }
            catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
        return listPilot;
    }
    public static Pilot SelectPilotFlightId(int ID)
    {
        Pilot pilot = new Pilot();
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            String sql = "SELECT DISTINCT  * FROM PILOT WHERE FlightId = ? ";
            ps = con.prepareStatement(sql);
            ps.setInt(1,ID);
            rs = ps.executeQuery();
            while(rs.next())
            {
                String name = rs.getString("Name");
                String surName = rs.getString("Surname");
                int id = rs.getInt("FlightId");
                String password = rs.getString("Password");
                pilot.setName(name);
                pilot.setSurname(surName);
                pilot.setID(id);
                pilot.setPassword(password);
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        finally {
            try {
                rs.close();
                ps.close();
                con.close();
            }
            catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
        return pilot;
    }
    public static LinkedList<Firm> SelectFirm()
    {
        LinkedList<Firm> firmList = new LinkedList<Firm>();
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            String sql = "SELECT DISTINCT * FROM FIRM";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next())
            {
                Firm firm = new Firm();
                String name = rs.getString("FirmName");
                firm.setFirmName(name);
                int id =SelectFirmAccordingToId(name);
                firm.flights= SelectFlight(id);
                firm.hostesses = SelectHostessTableFirmId(id);
                firm.pilots= SelectPilotFirmId(id);
                firm.planes = SelectPlaneFirmId(id);
                firmList.add(firm);
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        finally {
            try {
                rs.close();
                ps.close();
                con.close();
            }
            catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
        return firmList;
    }
    public static int SelectFirmAccordingToId(String name)
    {
        int id = 0;
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            String sql = "SELECT DISTINCT * FROM FIRM WHERE FirmName = ? LIMIT  1 ";
            ps = con.prepareStatement(sql);
            ps.setString(1,name);
            rs = ps.executeQuery();
            id = rs.getInt("Id");
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        finally {
            try {
                rs.close();
                ps.close();
                con.close();
            }
            catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
        return id;
    }
    public static int SelectFirmAccordingToPilotId(int pilotId)
    {
        int id = 0;
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            String sql = "SELECT DISTINCT * FROM PILOT WHERE PilotId = ?  ";
            ps = con.prepareStatement(sql);
            ps.setInt(1,pilotId);
            rs = ps.executeQuery();
            id = rs.getInt("Id");
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        finally {
            try {
                rs.close();
                ps.close();
                con.close();
            }
            catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
        return id;
    }
    public static int SelectFirmAccordingToHostId(String hostId)
    {
        int id = 0;
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            String sql = "SELECT DISTINCT * FROM HOSTESS WHERE HostId = ?  ";
            ps = con.prepareStatement(sql);
            ps.setString(1,hostId);
            rs = ps.executeQuery();
            id = rs.getInt("Id");
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        finally {
            try {
                rs.close();
                ps.close();
                con.close();
            }
            catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
        return id;
    }
    public static int SelectFlightIdAccordingToPilotId(int pilotId)
    {
        int id = 0;
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            String sql = "SELECT DISTINCT * FROM PILOT WHERE PilotId = ? LIMIT  1 ";
            ps = con.prepareStatement(sql);
            ps.setInt(1,pilotId);
            rs = ps.executeQuery();
            id = rs.getInt("FlightId");
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        finally {
            try {
                rs.close();
                ps.close();
                con.close();
            }
            catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
        return id;
    }
    public static int SelectFlightIdAccordingToHostessId(String hostID)
    {
        int id = 0;
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            String sql = "SELECT DISTINCT * FROM HOSTESS WHERE HostId = ? LIMIT  1 ";
            ps = con.prepareStatement(sql);
            ps.setString(1,hostID);
            rs = ps.executeQuery();
            id = rs.getInt("FlightId");
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        finally {
            try {
                rs.close();
                ps.close();
                con.close();
            }
            catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
        return id;
    }
    public static AVLTree<Flight> SelectFlight(int ID) {
        AVLTree<Flight> list = new AVLTree<Flight>();
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT DISTINCT * FROM FLIGHT WHERE Id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1,ID);
            rs = ps.executeQuery();
            while (rs.next()) {
                Flight flight = new Flight();

                int flightId = rs.getInt("FlightId");
                int day = rs.getInt("Day");
                int month = rs.getInt("Month");
                int year = rs.getInt("Year");
                double time = rs.getInt("Time");
                Date date = new Date(day,month,year,time);
                date.setDay(day);
                date.setMonth(month);
                date.setYear(year);
                date.setTime(time);
                flight.setFlightID(flightId);
                flight.setFlightDate(date);
                flight.plane= SelectPlaneFlightId(flightId);
                flight.hostess = SelectHostessTableFlightId(flightId);
                flight.pilot = SelectPilotFlightId(flightId);
                list.add(flight);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return list;
    }
    public static ArrayList SelectFlightIdFromFirmIdForPilot(int FirmtId)
    {
        int id = 0;
        ArrayList list = new ArrayList();
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            String sql = "SELECT DISTINCT PilotId FROM PILOT WHERE Id = ? ";
            ps = con.prepareStatement(sql);
            ps.setInt(1,FirmtId);
            rs = ps.executeQuery();

            while (rs.next()) {
                id = rs.getInt("FlightId");
                list.add(id);
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        finally {
            try {
                rs.close();
                ps.close();
                con.close();
            }
            catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
        return list;
    }
    public static ArrayList SelectFlightIdFromFirmIdForHostess(int FirmtId)
    {
        int id = 0;
        ArrayList list = new ArrayList();
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            String sql = "SELECT DISTINCT HostId FROM HOSTESS WHERE Id = ? ";
            ps = con.prepareStatement(sql);
            ps.setInt(1,FirmtId);
            rs = ps.executeQuery();

            while (rs.next()) {
                id = rs.getInt("FlightId");
                list.add(id);
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        finally {
            try {
                rs.close();
                ps.close();
                con.close();
            }
            catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
        return list;
    }
    public static ArrayList<Flight> SelectFlightAccordingToFlightId(int ID) {
        ArrayList<Flight> list = new ArrayList<Flight>();
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT DISTINCT * FROM FLIGHT WHERE FlightId = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1,ID);
            rs = ps.executeQuery();
            while (rs.next()) {
                Flight flight = new Flight();

                int flightId = rs.getInt("FlightId");
                int day = rs.getInt("Day");
                int month = rs.getInt("Month");
                int year = rs.getInt("Year");
                double time = rs.getInt("Time");
                Date date = new Date(day,month,year,time);
                date.setDay(day);
                date.setMonth(month);
                date.setYear(year);
                date.setTime(time);
                flight.setFlightID(flightId);
                flight.setFlightDate(date);
                flight.plane= SelectPlaneFlightId(flightId);
                flight.hostess = SelectHostessTableFlightId(flightId);
                flight.pilot = SelectPilotFlightId(flightId);
                list.add(flight);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return list;
    }
    public static ConcurrentSkipListSet<Flight> SelectFlightAccordingToFlightIdForHost(int ID) {
        ConcurrentSkipListSet<Flight> list = new ConcurrentSkipListSet<Flight>();
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT DISTINCT * FROM FLIGHT WHERE FlightId = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1,ID);
            rs = ps.executeQuery();
            while (rs.next()) {
                Flight flight = new Flight();

                int flightId = rs.getInt("FlightId");
                int day = rs.getInt("Day");
                int month = rs.getInt("Month");
                int year = rs.getInt("Year");
                double time = rs.getInt("Time");
                Date date = new Date(day,month,year,time);
                date.setDay(day);
                date.setMonth(month);
                date.setYear(year);
                date.setTime(time);
                flight.setFlightID(flightId);
                flight.setFlightDate(date);
                flight.plane= SelectPlaneFlightId(flightId);
                flight.hostess = SelectHostessTableFlightId(flightId);
                flight.pilot = SelectPilotFlightId(flightId);
                list.add(flight);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return list;
    }
    public static Set<Plane> SelectPlaneFirmId(int ID)
    {
        Set<Plane> list = new HashSet<Plane>();
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            String sql = "SELECT DISTINCT * FROM PLANE WHERE Id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1,ID);
            rs = ps.executeQuery();
            while(rs.next())
            {
                Plane plane = new Plane();
                int id = rs.getInt("Id");
                plane.setPlaneID(id);
                list.add(plane);
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        finally {
            try {
                rs.close();
                ps.close();
                con.close();
            }
            catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
        return list;
    }
    public static Plane SelectPlaneFlightId(int ID)
    {
        Plane plane = new Plane();
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            String sql = "SELECT DISTINCT * FROM PLANE WHERE FlightId = ? ";
            ps = con.prepareStatement(sql);
            ps.setInt(1,ID);
            rs = ps.executeQuery();
            while(rs.next())
            {
                int id = rs.getInt("PlaneId");
                plane.setPlaneID(id);
                plane.setReadinessOfPlane(Plane.StateOfPlane.ReadyToFly);
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        finally {
            try {
                rs.close();
                ps.close();
                con.close();
            }
            catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
        return plane;
    }
    public static LinkedList<Sector> SelectSector()
    {
        LinkedList<Sector> list = new LinkedList<Sector>();
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            String sql = "SELECT DISTINCT * FROM SECTOR";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next())
            {
                Sector sector = new Sector();
                int id = rs.getInt("Id");
                sector.setSectorID(id);
                list.add(sector);
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        finally {
            try {
                rs.close();
                ps.close();
                con.close();
            }
            catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
        return list;
    }
    public static void DeleteCleaningPersonelRow(int id)
    {
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        try{
            String sql = "delete from CLEANNING_PERSONEL where Id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            ps.execute();
            System.out.println("Data " + id +" has been deleted!");
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
        finally {
            try {
                ps.close();
                con.close();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
    public static void DeleteFirmRow(String firmName)
    {
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        try{
            String sql = "delete from FIRM where FirmName = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1,firmName);
            ps.execute();
            System.out.println("Data " + firmName +" has been deleted!");
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
        finally {
            try {
                ps.close();
                con.close();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
    public static void DeleteFlightRow(int id)
    {
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        try{
            String sql = "delete from FLIGHT where Id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            ps.execute();
            System.out.println("Data " + id +" has been deleted!");
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
        finally {
            try {
                ps.close();
                con.close();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
    public static void DeleteHostessRow(String id)
    {
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        try{
            String sql = "delete from HOSTESS where HostId = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1,id);
            ps.execute();
            System.out.println("Hostess number  " + id +" has been deleted!");
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
        finally {
            try {
                ps.close();
                con.close();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
    public static int ControlExistPilots(int firmID)
    {
        int count = 0;
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            String sql = "SELECT DISTINCT * FROM PILOT WHERE Id = ? ";
            ps = con.prepareStatement(sql);
            ps.setInt(1,firmID);
            rs = ps.executeQuery();
            count = rs.getInt(1);
            while(rs.next())
            {
                ++count;
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        finally {
            try {
                rs.close();
                ps.close();
                con.close();
            }
            catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
        return count;
    }
    public static int ControlExistHostess(int firmID)
    {
        int count = 0;
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            String sql = "SELECT DISTINCT * FROM HOSTESS WHERE Id = ? ";
            ps = con.prepareStatement(sql);
            ps.setInt(1,firmID);
            rs = ps.executeQuery();
            count = rs.getInt(1);
            while(rs.next())
            {
                ++count;
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        finally {
            try {
                rs.close();
                ps.close();
                con.close();
            }
            catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
        return count;
    }
    public static void DeletePilotRow(int id)
    {
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        try{
            String sql = "delete from PILOT where PilotId = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            ps.execute();
            System.out.println("Pilot Number " + id +" has been deleted!");
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
        finally {
            try {
                ps.close();
                con.close();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
    public static void DeletePlaneRow(String id)
    {
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        try{
            String sql = "delete from PLANE where Id = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1,id);
            ps.execute();
            System.out.println("Data " + id +" has been deleted!");
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
        finally {
            try {
                ps.close();
                con.close();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
    public static void DeletSectorRow(int id)
    {
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        try{
            String sql = "delete from SECTOR where Id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            ps.execute();
            System.out.println("Data " + id +" has been deleted!");
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
        finally {
            try {
                ps.close();
                con.close();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
    public static void DeletSecurityPersonelRow(int id)
    {
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        try{
            String sql = "delete from SECURITY_PERSONEL where Id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            ps.execute();
            System.out.println("Data " + id +" has been deleted!");
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
        finally {
            try {
                ps.close();
                con.close();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
    public static void DeleteOldFlightRow(int id)
    {
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        try{
            String sql = "delete from OLD_FLIGHT where Id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            ps.execute();
            System.out.println("Data " + id +" has been deleted!");
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
        finally {
            try {
                ps.close();
                con.close();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
    public static void UpdatePilotAccordingTıFlightId(int flightId,int pilotId)
    {
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        try{
            String sql = "UPDATE PILOT set FlightId = ? where PilotId = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1,flightId);
            ps.setInt(2,pilotId);
            ps.execute();
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
        finally {
            try {
                ps.close();
                con.close();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
    public static void UpdateHostAccordingTıFlightId(int flightId,String hostId)
    {
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        try{
            String sql = "UPDATE HOSTESS set FlightId = ? where HostId = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1,flightId);
            ps.setString(2,hostId);
            ps.execute();
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
        finally {
            try {
                ps.close();
                con.close();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}
