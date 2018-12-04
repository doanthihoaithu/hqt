package vnu.edu.hoaithu.controller;

import io.swagger.annotations.ApiOperation;
import oracle.kv.KVStore;
import oracle.kv.KVStoreConfig;
import oracle.kv.KVStoreFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vnu.edu.hoaithu.payload.SelectResponse;

import java.sql.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/sql")
public class SqlController {

    @PostMapping("/select")
    @ApiOperation("Nhập mysql query: select ")
    public ResponseEntity<SelectResponse> getSelectTime (@RequestParam("query") String query) throws Exception {
        SelectResponse response = new SelectResponse();
        try
        {
            // create our mysql database connection
            String myDriver = "com.mysql.cj.jdbc.Driver";
            String myUrl = "jdbc:mysql://localhost/hqt";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "root", "123456");

            // our SQL SELECT query.
            // if you only need a few columns, specify them by name instead of using "*"
//            String query = "SELECT * FROM users";

            // create the java statement
            Statement st = conn.createStatement();

            // execute the query, and get a java resultset
            long begin = System.currentTimeMillis();
            ResultSet rs = st.executeQuery(query);
            long end = System.currentTimeMillis();

            ArrayList<Object> rows = new ArrayList<>();
            // iterate through the java resultset
            int count = 0;

            ResultSetMetaData resultSetMetaData = rs.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();

            while (rs.next()) {
                count++;
                Object[] values = new Object[columnCount];
                for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                    values[i - 1] = rs.getObject(i);
                }
                rows.add(values);
            }

//            while (rs.next())
//            {
//                count++;
//                System.out.println(rs.toString());
//                rs.get
//                rows.add(rs.toString());
//                int id = rs.getInt("id");
//                String firstName = rs.getString("first_name");
//                String lastName = rs.getString("last_name");
//                Date dateCreated = rs.getDate("date_created");
//                boolean isAdmin = rs.getBoolean("is_admin");
//                int numPoints = rs.getInt("num_points");
//
//                // print the results
//                System.out.format("%s, %s, %s, %s, %s, %s\n", id, firstName, lastName, dateCreated, isAdmin, numPoints);
//            }
            response.setTime(end - begin);
            response.setResult(rows);
            response.setNumberOfRow(count);
            st.close();
            return new ResponseEntity<>(response, HttpStatus.OK);

        }
        catch (Exception e)
        {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
            throw new Exception(e.getMessage());
        }
        
    }


    @PostMapping("/update")
    @ApiOperation("Nhập mysql query: delete, update, insert")
    public ResponseEntity<Integer> getUpdateTime (@RequestParam("query") String query) throws Exception {
        SelectResponse response = new SelectResponse();
        try {
            // create our mysql database connection
            String myDriver = "com.mysql.cj.jdbc.Driver";
            String myUrl = "jdbc:mysql://localhost/hqt";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "root", "123456");

            // our SQL SELECT query.
            // if you only need a few columns, specify them by name instead of using "*"
//            String query = "SELECT * FROM users";

            // create the java statement
            Statement st = conn.createStatement();

            // execute the query, and get a java resultset
            long begin = System.currentTimeMillis();
            int number = st.executeUpdate(query);
            long end = System.currentTimeMillis();
            return new ResponseEntity<>(new Integer(number), HttpStatus.OK);
        } catch (Exception e) {
            throw  e;
        }
    }
}
