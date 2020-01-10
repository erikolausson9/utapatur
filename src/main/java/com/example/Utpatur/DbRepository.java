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



    public Route rsRoute(ResultSet rs) throws SQLException {
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
}
