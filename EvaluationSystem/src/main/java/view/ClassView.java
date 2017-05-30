package view;

import model.Class;

public class ClassView {

    private int ID;
    private String name;
    private String abbreviation;

    public ClassView(){}

    public ClassView(Class cl){
     this.setID(cl.getID());
     this.setName(cl.getName());
     this.setAbbreviation(cl.getAbbreviation());
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }
}
