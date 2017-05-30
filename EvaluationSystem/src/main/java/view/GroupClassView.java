package view;

import model.Group;

public class GroupClassView extends GroupView {

    private ClassView _class;

    public GroupClassView(){
        super();
    }

    public GroupClassView(Group group) {
        super(group);
        this.set_class(new ClassTeacherView(group.get_class()));
    }

    public ClassView get_class() {
        return _class;
    }

    public void set_class(ClassView _class) {
        this._class = _class;
    }
}
