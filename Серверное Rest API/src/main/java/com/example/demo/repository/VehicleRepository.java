package com.example.demo.repository;

import com.example.demo.entity.Admission;
import com.example.demo.entity.Vehicle;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.ArrayList;

@Repository
public class VehicleRepository implements IRestRepository<Vehicle>{
    protected final JdbcOperations jdbcOperations;

    public VehicleRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    private static String selectQuery = "SELECT \"id\", \"brand_of_transport\", \"model\", \"state_number\" , \"color\" , \"worker_id\" " +
            "FROM \"vehicle\" " +
            "ORDER BY \"id\"";

    private static String selectByIdQuery = "SELECT \"id\", \"brand_of_transport\", \"model\", \"state_number\" , \"color\" , \"worker_id\" " +
            "FROM \"vehicle\" " +
            "WHERE \"id\" = ?";

    private static String selectByWorkerIdQuery = "SELECT \"id\", \"brand_of_transport\", \"model\", \"state_number\" , \"color\" , \"worker_id\" " +
            "FROM \"vehicle\" " +
            "WHERE \"worker_id\" = ?";

    private static String insertQuery = "INSERT INTO \"vehicle\"(\"brand_of_transport\", \"model\", \"state_number\" , \"color\" , \"worker_id\") " +
            "VALUES (?, ?, ?, ?, ?) " +
            "RETURNING \"id\", \"brand_of_transport\", \"model\", \"state_number\" , \"color\" , \"worker_id\" ";

    private static String updateQuery = "UPDATE \"vehicle\" " +
            "SET \"brand_of_transport\" = ?, \"model\" = ?, \"state_number\" = ?, \"color\" = ? , \"worker_id\" = ? " +
            "WHERE \"id\" = ? " +
            "RETURNING \"id\", \"brand_of_transport\", \"model\", \"state_number\" , \"color\" , \"worker_id\" ";

    private static String deleteQuery = "DELETE FROM \"vehicle\" " +
            "WHERE \"id\" = ? " +
            "RETURNING \"id\", \"brand_of_transport\", \"model\", \"state_number\" , \"color\" , \"worker_id\" ";


    @Override
    public Vehicle [] select() {
        ArrayList<Vehicle> values = new ArrayList<Vehicle>();
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectQuery);
        while (rowSet.next()) {
            values.add(new Vehicle(
                    rowSet.getInt(1),
                    rowSet.getString(2),
                    rowSet.getString(3),
                    rowSet.getString(4),
                    rowSet.getString(5),
                    rowSet.getInt(6)
            ));
        }
        Vehicle[] result = new Vehicle[values.size()];
        result = values.toArray(result);
        return result;
    }

    @Override
    public Vehicle select(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByIdQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Vehicle(
                rowSet.getInt(1),
                rowSet.getString(2),
                rowSet.getString(3),
                rowSet.getString(4),
                rowSet.getString(5),
                rowSet.getInt(6)
        );
    }

    public Vehicle[] selectByWorkerIdQuery(Integer worker_id) {
        ArrayList<Vehicle> values = new ArrayList<Vehicle>();
        Object[] params = new Object[] { worker_id };
        int[] types = new int[] { Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByWorkerIdQuery, params, types);
        while (rowSet.next()) {
            values.add(new Vehicle(
                    rowSet.getInt(1),
                    rowSet.getString(2),
                    rowSet.getString(3),
                    rowSet.getString(4),
                    rowSet.getString(5),
                    rowSet.getInt(6)
            ));
        }
        Vehicle[] result = new Vehicle[values.size()];
        result = values.toArray(result);
        return result;
    }

    @Override
    public Vehicle insert(Vehicle entity) {
        Object[] params = new Object[] { entity.getBrand_of_transport(), entity.getModel(), entity.getState_number(), entity.getColor(), entity.getWorker_id() };
        int[] types = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(insertQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Vehicle(
                rowSet.getInt(1),
                rowSet.getString(2),
                rowSet.getString(3),
                rowSet.getString(4),
                rowSet.getString(5),
                rowSet.getInt(6)
        );
    }

    @Override
    public Vehicle update(Integer id, Vehicle entity) {
        Object[] params = new Object[] { entity.getBrand_of_transport(), entity.getModel(), entity.getState_number(), entity.getColor(), entity.getWorker_id(), id };
        int[] types = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER, Types.INTEGER};
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(updateQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Vehicle(
                rowSet.getInt(1),
                rowSet.getString(2),
                rowSet.getString(3),
                rowSet.getString(4),
                rowSet.getString(5),
                rowSet.getInt(6)
        );
    }


    @Override
    public Vehicle delete(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(deleteQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Vehicle(
                rowSet.getInt(1),
                rowSet.getString(2),
                rowSet.getString(3),
                rowSet.getString(4),
                rowSet.getString(5),
                rowSet.getInt(6)
        );
    }
}
