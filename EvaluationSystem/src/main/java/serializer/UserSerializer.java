package serializer;

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
    public String serialize(User user, UserSerializationMode mode) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode userNode = mapper.createObjectNode();

        userNode.put("id", user.getID());
        userNode.put("email", user.getEmail());
        userNode.put("firstName", user.getFirstName());
        userNode.put("lastName", user.getLastName());
        if(mode != UserSerializationMode.USER) {
            if (user instanceof Teacher) {
                userNode.put("type", "Teacher");
                switch (mode) {
                    case DEFAULT:
                        serializeTeacherDefault(mapper, userNode, (Teacher) user);
                }
            } else if (user instanceof Student) {
                userNode.put("type", "Student");
                switch (mode) {
                    case DEFAULT:
                        serializeStudentDefault(mapper, userNode, (Student) user);
                }
            }
        }
        return userNode.toString();
    }

    private void serializeStudentDefault(ObjectMapper mapper, ObjectNode userNode, Student student) {
        serializeNotifications(mapper, userNode, student);
        serializeGroups(mapper, userNode, student);
        serializeSubmissionIDs(mapper, userNode, student);
    }

    private void serializeSubmissionIDs(ObjectMapper mapper, ObjectNode userNode, Student student) {
        ArrayNode submissionsNode = userNode.putArray("submissions");
        for(Submission submission: student._submissions.toArray()){
            submissionsNode.add(submission.getID());
        }
    }

    private void serializeGroups(ObjectMapper mapper, ObjectNode userNode, Student student) {
        ArrayNode groupsNode = userNode.putArray("groups");
        for(GroupStudent groupStudent: student._groups.toArray()){
            Group group = groupStudent.get_group();
            try {
                JsonNode groupNode = mapper.readTree(groupSerializer.serialize(group,GroupSerializationMode.DEFAULT));
                groupsNode.add(groupNode);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void serializeTeacherDefault(ObjectMapper mapper, ObjectNode userNode, Teacher teacher) {
        serializeNotifications(mapper, userNode, teacher);
        serializeClasses(mapper, userNode, teacher);
    }

    private void serializeClasses(ObjectMapper mapper, ObjectNode userNode, Teacher teacher) {
        ArrayNode classesNode = userNode.putArray("classes");
        for(Class cl: teacher._classes.toArray()){
            try {
                JsonNode classNode = mapper.readTree(this.classSerializer.serialize(cl, ClassSerializationMode.GROUPS));
                classesNode.add(classNode);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void serializeNotifications(ObjectMapper mapper, ObjectNode userNode, User user) {
        ArrayNode notifNode = userNode.putArray("notifications");
        for(Notification notification: user._notifications.toArray()){
            if(notification instanceof GroupInvitation){
                GroupInvitation inv = (GroupInvitation) notification;
                ObjectNode invNode = notifNode.addObject();
                invNode.put("type", "Group Invitation");
                Group group = inv.get_group();
                try {
                    JsonNode groupNode = mapper.readTree(this.groupSerializer.serialize(group, GroupSerializationMode.DEFAULT));
                    invNode.set("group", groupNode);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public User deserialize(String userJSON) {
        // TODO
        return null;
    }
}
