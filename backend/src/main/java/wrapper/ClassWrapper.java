package wrapper;

import model.Class;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClassWrapper that = (ClassWrapper) o;

        return ID == that.ID;
    }

    @Override
    public int hashCode() {
        return ID;
    }

    @Override
    public int compareTo(ClassWrapper o) {
        int abbreviationCompare = abbreviation.toLowerCase().compareTo(o.getAbbreviation().toLowerCase());
        if(abbreviationCompare == 0)
            return name.toLowerCase().compareTo(o.getName().toLowerCase());
        else
            return abbreviationCompare;
    }
}
