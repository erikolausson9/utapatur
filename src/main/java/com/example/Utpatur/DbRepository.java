package com.example.Utpatur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Service
@Repository
public class DbRepository {

    @Autowired
    private DataSource dataSource;

    private List<Route> routes;

    public DbRepository() {
        routes = new ArrayList<>();
    }


    public void addRoute(CreateNewRoute route){
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO Route(RouteName, RouteType, Height, Difficulty, Length, Days, Hours, Description, DateOfCompletion, RouteCreated, RouteLastUpdated, MemberID) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)")) {
            ps.setString(1, route.getRouteName());
            ps.setString(2, route.getRouteType());
            ps.setInt(3, route.getHeight());
            ps.setString(4, route.getDifficulty());
            ps.setInt(5, route.getLength());
            ps.setInt(6, route.getDays());
            ps.setDouble(7, route.getHours());
            ps.setString(8, route.getDescription());
            ps.setString(9, route.getDateOfCompletion());
            ps.setString(10, route.getRouteCreated());
            ps.setString(11, route.getRouteLastUpdated());
            ps.setInt(12, route.getMemberId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Nu ska vi ha laddat in till databasen");

    }

    public void addPosition(Position position){

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO Position(Longitude, Latitude, Altitude, RouteID) VALUES(?,?,?,?)")) {
            ps.setFloat(1, (float)position.getLongitude());
            ps.setFloat(2, (float)position.getLatitude());
            ps.setInt(3, position.getAltitude());
            ps.setInt(4, position.getRouteId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Position borde vara laddad till databasen nu?");
    }


    public List<Route> getAllRoutes(){ //Todo: testa om denna funktion gör vad den ska!
        List<Route> routes = new ArrayList();
        try(Connection conn = dataSource.getConnection();
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM Route")){
            while(rs.next()){
                System.out.println("en rad till");
                routes.add(rsRoute(rs));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return routes;
    }

    //Denna metod funkar!
    public Route getRoute(int routeID){

        Route route = new Route();
        try(Connection conn = dataSource.getConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Route WHERE RouteID=" + routeID)){
            if(rs.next()){
                route= rsRoute(rs);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    return route;
    }

    //TODO: make sure this method does what it's supposed to!
    public List<Position> getPositions(int routeID){
        List<Position> positionsToReturn = new ArrayList();
        try(Connection conn = dataSource.getConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Position WHERE RouteID=" + routeID)){
            while(rs.next()){
                positionsToReturn.add(rsPosition(rs));
                System.out.println("hittade rad");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return positionsToReturn;
    }



    //public helper method(s) used in this class and in the ServiceLayer class
    public int getLastRouteID(){
        int id=0;
        try {Connection conn = dataSource.getConnection();
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery("select top 1 RouteID as C from Route order by RouteID desc;");
            if(rs.next()){
                id = rs.getInt("C");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
    //private helper methods used by other methods in this class
    //TODO: make sure these methods can be private without any problems
    private Route rsRoute(ResultSet rs) throws SQLException {
        Route route = new Route();
        route.setRouteId(rs.getInt("routeId"));
        route.setRouteName(rs.getString("routeName"));
        route.setRouteType(rs.getString("routeType"));
        route.setHeight(rs.getInt("height"));
        route.setDifficulty(rs.getString("difficulty"));
        route.setLength(rs.getInt("length"));
        route.setDays(rs.getInt("days"));
        route.setHours(rs.getDouble("hours"));
        route.setDescription(rs.getString("description"));
        route.setDateOfCompletion(rs.getString("dateOfCompletion"));
        route.setRouteCreated(rs.getString("routeCreated"));
        route.setRouteLastUpdated(rs.getString("routeLastUpdated"));
        return route;
    }

    //TODO: make sure this function does what it's supposed to, column labels are correct etc.
    private Position rsPosition(ResultSet rs) throws SQLException {
        Position position = new Position();
        position.setPositionId(rs.getInt("routeId"));
        position.setLatitude(rs.getDouble("latitude"));
        position.setLongitude(rs.getDouble("longitude"));
        position.setAltitude(rs.getInt("altitude"));
        position.setRouteId(rs.getInt("routeID"));

        return position;
    }

    //Method to Test DB-connection
    public boolean testDB() throws SQLException {
        int two = 0;
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT 1+1")) {
            rs.next();
            two = rs.getInt(1);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return two == 2;
    }


    public void testCreateNewRouteObject() {

        System.out.println("Inne i metoden");

        Route testRoute = new Route(null, "testname", "hike", 0, null, 0, null, null, null, null, null, null, 1);

        System.out.println("Objekt skapat");

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO Route(RouteName, RouteType, Height, Difficulty, Length, Days, Hours, Description, DateOfCompletion, RouteCreated, RouteLastUpdated, MemberID) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)")) {
            ps.setString(1, testRoute.getRouteName());
            ps.setString(2, testRoute.getRouteType());
            ps.setInt(3, testRoute.getHeight());
            ps.setString(4, testRoute.getDifficulty());
            ps.setInt(5, testRoute.getLength());
            ps.setInt(6, testRoute.getDays());
            ps.setDouble(7, testRoute.getHours());
            ps.setString(8, testRoute.getDescription());
            ps.setString(9, testRoute.getDateOfCompletion());
            ps.setString(10, testRoute.getRouteCreated());
            ps.setString(11, testRoute.getRouteLastUpdated());
            ps.setInt(12, testRoute.getMemberId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Borde vara sparat i databasen?");

    }

    public List<Position> getAllPositions() {

        List<Position> positions = new ArrayList();
        try(Connection conn = dataSource.getConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Position")){
            while(rs.next()){
                System.out.println("en rad till");
                positions.add(rsPosition(rs));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return positions;

    }
}
