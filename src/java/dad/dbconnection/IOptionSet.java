package dad.dbconnection;

import dao.connectionfactory.ConnectionFactory;
import dad.model.OptionSet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IOptionSet implements OptionSet {
    private static final String TABLE_NAME= "optionset";
    private Connection connection;

    public IOptionSet() {
        connection = ConnectionFactory.getConnection();
    }

    @Override
    public OptionSet getOptionSet(int id) {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM "+ TABLE_NAME +" WHERE id=" + id);
            if(rs.next()) {
                OptionSet optionset = new OptionSet(rs.getInt("id"),rs.getString("name"));
                return optionset;
            }
            return null;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<OptionSet> getAllOptionSets() {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + TABLE_NAME);
            List<OptionSet> optionSets = new ArrayList<>();
            while(rs.next()) {
                OptionSet optionSet = new OptionSet(rs.getInt("id"),rs.getString("name"));
                optionSets.add(optionSet);
            }
            return optionSets;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean insertOptionSet(OptionSet optionSet) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO "+ TABLE_NAME +" VALUES (NULL, ?)");

            ps.setString(1, optionSet.getName());
            int i = ps.executeUpdate();

            return i == 1 ? true : false;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
