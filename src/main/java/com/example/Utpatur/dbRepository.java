package com.example.Utpatur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


@Service
@Repository
public class dbRepository {

    @Autowired
    private DataSource dataSource;

    private List<Route> routs;

    public dbRepository() {
        routs = new ArrayList<>();
    }


    public Route rsRoute(ResultSet rs) throws SQLException {
        Route route = new Route();
        route.setRouteId(rs.getInt("routeId"));
        route.setRouteName(rs.getString("routeName"));
        route.setRouteType(rs.getString("routeType"));
        route.setHeight(rs.getFloat("height"));
        route.setDifficulty(rs.getString("difficulty"));
        route.setLength(rs.getFloat("length"));
        route.setDuration(rs.getString("duration"));
        route.setSeason(rs.getString("season"));
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


}
