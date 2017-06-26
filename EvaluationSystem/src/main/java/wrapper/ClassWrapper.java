package wrapper;

import model.persistent.Class;

public class ClassWrapper implements Comparable<ClassWrapper>{

    private int ID;
    private String name;
    private String abbreviation;

    public ClassWrapper(){}

    public ClassWrapper(Class cl){
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

    @Override
    public int compareTo(ClassWrapper o) {
        if(this.ID > o.ID)
            return 1;
        else if(this.ID < o.ID)
            return -1;
        else
            return 0;
    }
}
