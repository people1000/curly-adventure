package com.example.demo.repository;

import com.example.demo.entity.Worker;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.ArrayList;

@Repository
public class WorkerRepository implements IRestRepository<Worker>{
    protected final JdbcOperations jdbcOperations;

    public WorkerRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    private static String selectQuery = "SELECT \"id\", \"name\", \"post\", \"phone_number\" " +
            "FROM \"worker\" " +
            "ORDER BY \"id\"";

    private static String selectByIdQuery = "SELECT \"id\", \"name\", \"post\", \"phone_number\" " +
            "FROM \"worker\" " +
            "WHERE \"id\" = ?";

    private static String insertQuery = "INSERT INTO \"worker\"(\"name\", \"post\", \"phone_number\") " +
            "VALUES (?, ?, ?) " +
            "RETURNING \"id\", \"name\", \"post\", \"phone_number\" ";

    private static String updateQuery = "UPDATE \"worker\" " +
            "SET \"name\" = ?, \"post\" = ?, \"phone_number\" = ?" +
            "WHERE \"id\" = ? " +
            "RETURNING \"id\", \"name\", \"post\", \"phone_number\" ";

    private static String deleteQuery = "DELETE FROM \"worker\" " +
            "WHERE \"id\" = ? " +
            "RETURNING \"id\", \"name\", \"post\", \"phone_number\" ";

    @Override
    public Worker [] select() {
        ArrayList<Worker> values = new ArrayList<Worker>();
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectQuery);
        while (rowSet.next()) {
            values.add(new Worker(
                    rowSet.getInt(1),
                    rowSet.getString(2),
                    rowSet.getString(3),
                    rowSet.getString(4)
            ));
        }
        Worker[] result = new Worker[values.size()];
        result = values.toArray(result);
        return result;
    }

    @Override
    public Worker select(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByIdQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Worker(
                rowSet.getInt(1),
                rowSet.getString(2),
                rowSet.getString(3),
                rowSet.getString(4)
        );
    }

    @Override
    public Worker insert(Worker entity) {
        Object[] params = new Object[] { entity.getName(), entity.getPost(), entity.getPhone_number()};
        int[] types = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(insertQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Worker(
                rowSet.getInt(1),
                rowSet.getString(2),
                rowSet.getString(3),
                rowSet.getString(4)
        );
    }

    @Override
    public Worker update(Integer id, Worker entity) {
        Object[] params = new Object[] { entity.getName(), entity.getPost(), entity.getPhone_number(), id };
        int[] types = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER};
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(updateQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Worker(
                rowSet.getInt(1),
                rowSet.getString(2),
                rowSet.getString(3),
                rowSet.getString(4)
        );
    }

    @Override
    public Worker delete(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(deleteQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Worker(
                rowSet.getInt(1),
                rowSet.getString(2),
                rowSet.getString(3),
                rowSet.getString(4)
        );
    }
}
