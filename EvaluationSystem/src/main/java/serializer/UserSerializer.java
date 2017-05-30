package serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import model.*;
import model.Class;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class UserSerializer extends Serializer<User, UserSerializationMode>{

    private GroupSerializer groupSerializer;
    private ClassSerializer classSerializer;

    @Autowired
    public void setGroupSerializer(GroupSerializer groupSerializer) {
        this.groupSerializer = groupSerializer;
    }
    @Autowired
    public void setClassSerializer(ClassSerializer classSerializer) {
        this.classSerializer = classSerializer;
    }

    @Override
    public String serialize(User user, UserSerializationMode... serializationMode) throws IOException{
        UserSerializationMode mode = UserSerializationMode.USER;
        if(serializationMode.length >= 1){
            mode = serializationMode[0];
        }
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode userNode = mapper.createObjectNode();

        userNode.put("id", user.getID());
        userNode.put("email", user.getEmail());
        userNode.put("firstName", user.getFirstName());
        userNode.put("lastName", user.getLastName());
        if (user instanceof Teacher) {
            Teacher teacher = (Teacher) user;
            userNode.put("type", "teacher");
            switch (mode) {
                case NOTIF_GROUP_SUBMid:
                    serializeNotifications(mapper, userNode, teacher);
                    serializeClasses(mapper, userNode, teacher);
                    break;
                case USER:
                    break;
            }
        } else if (user instanceof Student) {
            Student student = (Student) user;
            userNode.put("type", "student");
            switch (mode) {
                case NOTIF_GROUP_SUBMid:
                    serializeNotifications(mapper, userNode, student);
                    serializeGroups(mapper, userNode, student);
                    serializeSubmissionIDs(mapper, userNode, student);
                    break;
                case USER:
                    break;
            }
        }
        return userNode.toString();
    }

    private void serializeSubmissionIDs(ObjectMapper mapper, ObjectNode userNode, Student student) throws IOException{
        ArrayNode submissionsNode = userNode.putArray("submissions");
        for(Submission submission: student._submissions.toArray()){
            submissionsNode.add(submission.getID());
        }
    }

    private void serializeGroups(ObjectMapper mapper, ObjectNode userNode, Student student) throws IOException{
        ArrayNode groupsNode = userNode.putArray("groups");
        for(GroupStudent groupStudent: student._groups.toArray()){
            Group group = groupStudent.get_group();
            JsonNode groupNode = mapper.readTree(groupSerializer.serialize(group,GroupSerializationMode.CLASS));
            groupsNode.add(groupNode);
        }
    }

    private void serializeClasses(ObjectMapper mapper, ObjectNode userNode, Teacher teacher) throws IOException{
        ArrayNode classesNode = userNode.putArray("classes");
        for(Class cl: teacher._classes.toArray()){
            JsonNode classNode = mapper.readTree(this.classSerializer.serialize(cl, ClassSerializationMode.GROUPS));
            classesNode.add(classNode);
        }
    }

    private void serializeNotifications(ObjectMapper mapper, ObjectNode userNode, User user) throws IOException{
        ArrayNode notifNode = userNode.putArray("notifications");
        for(Notification notification: user._notifications.toArray()){
            if(notification instanceof GroupInvitation){
                GroupInvitation inv = (GroupInvitation) notification;
                ObjectNode invNode = notifNode.addObject();
                invNode.put("type", "group invitation");
                Group group = inv.get_group();
                JsonNode groupNode = mapper.readTree(this.groupSerializer.serialize(group, GroupSerializationMode.CLASS));
                invNode.set("group", groupNode);
            }
        }
    }

    @Override
    public User deserialize(String userJSON) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        JsonNode jsonNode = mapper.readTree(userJSON);
        String firstName = jsonNode.get("firstName").asText();
        String lastName = jsonNode.get("lastName").asText();
        String email = jsonNode.get("email").asText();
        String password = jsonNode.get("password").asText();
        String type = jsonNode.get("type").asText();
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        if(type.equals("teacher")){
            Teacher teacher = new Teacher(user);
            return teacher;
        }
        else if(type.equals("student")){
            Student student = new Student(user);
            return student;
        }
        else {
            throw new IOException("Invalid user type");
        }
    }
}
