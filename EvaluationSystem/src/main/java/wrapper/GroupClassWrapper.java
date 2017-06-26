package wrapper;

import model.persistent.Group;

public class GroupClassWrapper extends GroupWrapper {

    private ClassWrapper _class;

    public GroupClassWrapper(){
        super();
    }

    public GroupClassWrapper(Group group) {
        super(group);
        this.set_class(new ClassTeacherWrapper(group.get_class()));
    }

    public ClassWrapper get_class() {
        return _class;
    }

    public void set_class(ClassWrapper _class) {
        this._class = _class;
    }
}
