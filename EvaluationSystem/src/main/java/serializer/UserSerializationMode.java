package serializer;

public enum UserSerializationMode{
    DEFAULT,
}

//public class UserSerializationConfig {
//    SerializationMode notifications;
//    SerializationMode classes;
//    SerializationMode groups;
//    SerializationMode submissions;
//
//    public UserSerializationConfig(){
//        this.notifications = SerializationMode.EXCLUDE;
//        this.classes = SerializationMode.EXCLUDE;
//        this.groups = SerializationMode.EXCLUDE;
//        this.submissions = SerializationMode.EXCLUDE;
//    }
//
//    public UserSerializationConfig setTeacherDefault(){
//        this.notifications = SerializationMode.INCLUDE;
//        this.classes = SerializationMode.INCLUDE;
//        this.groups = SerializationMode.EXCLUDE;
//        this.submissions = SerializationMode.EXCLUDE;
//        return this;
//    }
//
//    public UserSerializationConfig setStudentDefault(){
//        this.notifications = SerializationMode.INCLUDE;
//        this.classes = SerializationMode.EXCLUDE;
//        this.groups = SerializationMode.INCLUDE;
//        this.submissions = SerializationMode.REFERENCE_ONLY;
//        return this;
//    }
//
//    public SerializationMode getNotifications() {
//        return notifications;
//    }
//
//    public UserSerializationConfig setNotifications(SerializationMode notifications) {
//        this.notifications = notifications;
//        return this;
//    }
//
//    public SerializationMode getClasses() {
//        return classes;
//    }
//
//    public UserSerializationConfig setClasses(SerializationMode classes) {
//        this.classes = classes;
//        return this;
//    }
//
//    public SerializationMode getGroups() {
//        return groups;
//    }
//
//    public UserSerializationConfig setGroups(SerializationMode groups) {
//        this.groups = groups;
//        return this;
//    }
//
//    public SerializationMode getSubmissions() {
//        return submissions;
//    }
//
//    public UserSerializationConfig setSubmissions(SerializationMode submissions) {
//        this.submissions = submissions;
//        return this;
//    }
//}
