package domain.bean;

import java.io.Serializable;
import java.util.List;

public class OptionSet implements Serializable {
    private int id;
    private String name;
    private List<Option> choices;

    public OptionSet() {
    }

    public OptionSet(String name) {
        this.name = name;
    }

    public OptionSet(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Option> getChoices() {
        return choices;
    }

    public void setChoices(List<Option> choices) {
        this.choices = choices;
    }
}
