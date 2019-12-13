package dad.dbconnection;

import dad.connectionfactory.ConnectionFactory;
import dad.model.Option;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IOption implements Option {
    private static final String TABLE_NAME= "option";
    private Connection connection;

    public IOption() {
        connection = ConnectionFactory.getConnection();
    }

    @Override
    public Option getOption(int id) {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM "+ TABLE_NAME +" WHERE id=" + id);
            if(rs.next()) {
                Option option = new Option(rs.getInt("id"),rs.getString("name"), rs.getDouble("price"));
                return option;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Option getOptionByName(String name) throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM "+ TABLE_NAME +" WHERE name='" + name + "'");
        if(rs.next()) {
            Option option = new Option(rs.getInt("id"),rs.getString("name"), rs.getDouble("price"));
            return option;
        }
        return null;
    }

    @Override
    public List<Option> getAllOptions() {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + TABLE_NAME);
            List<Option> options = new ArrayList<>();
            while(rs.next()) {
                Option option = new Option(rs.getInt("id"),rs.getString("name"), rs.getDouble("price"));
                options.add(option);
            }
            return options;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean insertOption(Option option) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO "+ TABLE_NAME +" VALUES (NULL, ?, ?)");

            ps.setString(1, option.getName());
            ps.setDouble(2, option.getPrice());

            int i = ps.executeUpdate();

            return i == 1 ? true : false;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateOption(Option option) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE " + TABLE_NAME +" SET name=?, price=? WHERE id=?");

            ps.setString(1, option.getName());
            ps.setDouble(2, option.getPrice());
            ps.setInt(3, option.getId());

            int i = ps.executeUpdate();

            return i == 1 ? true : false;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteOption(int id) {
        try {
            Statement stmt = connection.createStatement();
            int i = stmt.executeUpdate("DELETE FROM " + TABLE_NAME + " WHERE id=" + id);
            return i == 1 ? true : false;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
