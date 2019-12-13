package dad.dbconnection;

import models.OptionSet;

import java.util.List;

public interface OptionSetDao {
    OptionSet getOptionSet(int id);
    List<OptionSet> getAllOptionSets();
    boolean insertOptionSet(OptionSet optionSet);
}
