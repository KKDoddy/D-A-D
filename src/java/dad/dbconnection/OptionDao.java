package dad.dbconnection;

import dad.model.Option;

import java.sql.SQLException;
import java.util.List;

public interface OptionDao {
    Option getOption(int id);
    Option getOptionByName(String name) throws SQLException;
    List<Option> getAllOptions();
    boolean insertOption(Option option);
    boolean updateOption(Option option);
    boolean deleteOption(int id);
}
