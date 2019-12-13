package dad.dbconnection;

import dad.model.OptionSet;

import java.util.List;

public interface OptionSet {
    OptionSet getOptionSet(int id);
    List<OptionSet> getAllOptionSets();
    boolean insertOptionSet(OptionSet optionSet);
}
