package com.example.demo.repository;

import com.example.demo.entity.AcademicBuilding;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.ArrayList;

@Repository
public class AcademicBuildingRepository implements IRestRepository<AcademicBuilding>{
    protected final JdbcOperations jdbcOperations;

    public AcademicBuildingRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    private static String selectQuery = "SELECT \"id\", \"name\", \"address\" " +
            "FROM \"academic_building\" " +
            "ORDER BY \"id\"";

    private static String selectByIdQuery = "SELECT \"id\", \"name\", \"address\" " +
            "FROM \"academic_building\" " +
            "WHERE \"id\" = ?";

    private static String insertQuery = "INSERT INTO \"academic_building\"(\"name\", \"address\") " +
            "VALUES (?, ?) " +
            "RETURNING \"id\", \"name\", \"address\"";

    private static String updateQuery = "UPDATE \"academic_building\" " +
            "SET \"name\" = ?, \"address\" = ? " +
            "WHERE \"id\" = ? " +
            "RETURNING \"id\", \"name\", \"address\"";

    private static String deleteQuery = "DELETE FROM \"academic_building\" " +
            "WHERE \"id\" = ? " +
            "RETURNING \"id\", \"name\", \"address\"";


    @Override
    public AcademicBuilding[] select() {
        ArrayList<AcademicBuilding> values = new ArrayList<AcademicBuilding>();
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectQuery);
        while (rowSet.next()) {
            values.add(new AcademicBuilding(
                    rowSet.getInt(1),
                    rowSet.getString(2),
                    rowSet.getString(3)
            ));
        }
        AcademicBuilding[] result = new AcademicBuilding[values.size()];
        result = values.toArray(result);
        return result;
    }

    @Override
    public AcademicBuilding select(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByIdQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new AcademicBuilding(
                rowSet.getInt(1),
                rowSet.getString(2),
                rowSet.getString(3)
        );
    }

    @Override
    public AcademicBuilding insert(AcademicBuilding entity) {
        Object[] params = new Object[] { entity.getName(), entity.getAddress() };
        int[] types = new int[] { Types.VARCHAR, Types.VARCHAR };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(insertQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new AcademicBuilding(
                rowSet.getInt(1),
                rowSet.getString(2),
                rowSet.getString(3)
        );
    }

    @Override
    public AcademicBuilding update(Integer id, AcademicBuilding entity) {
        Object[] params = new Object[] { entity.getName(), entity.getAddress(), id };
        int[] types = new int[] {Types.VARCHAR, Types.VARCHAR, Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(updateQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new AcademicBuilding(
                rowSet.getInt(1),
                rowSet.getString(2),
                rowSet.getString(3)
        );
    }

    @Override
    public AcademicBuilding delete(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(deleteQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new AcademicBuilding(
                rowSet.getInt(1),
                rowSet.getString(2),
                rowSet.getString(3)
        );
    }
}
