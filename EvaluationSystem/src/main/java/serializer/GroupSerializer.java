package serializer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import model.Class;
import model.Group;
import model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class GroupSerializer extends Serializer<Group, GroupSerializationMode>{

    private ClassSerializer classSerializer;
    private UserSerializer userSerializer;

    @Autowired
    public void setClassSerializer(ClassSerializer classSerializer) {
        this.classSerializer = classSerializer;
    }

    @Autowired
    public void setUserSerializer(UserSerializer userSerializer) {
        this.userSerializer = userSerializer;
    }

    @Override
    public String serialize(Group group, GroupSerializationMode... serializationMode) throws IOException{
        GroupSerializationMode mode = GroupSerializationMode.GROUP;
        if(serializationMode.length >= 1){
            mode = serializationMode[0];
        }

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode groupNode = mapper.createObjectNode();

        groupNode.put("id", group.getID());
        groupNode.put("name",group.getName());
        switch (mode) {
            case CLASS_STUDENTS_EXAMS:
                serializeClass(mapper, groupNode, group);
                serializeStudents(mapper, groupNode, group);
                serializeExams(mapper, groupNode, group);
                break;
            case CLASS:
                serializeClass(mapper, groupNode, group);
        }

        return groupNode.toString();
    }

    private void serializeExams(ObjectMapper mapper, ObjectNode groupNode, Group group) throws IOException{
        //TODO
    }

    private void serializeStudents(ObjectMapper mapper, ObjectNode groupNode, Group group) throws IOException{
        //TODO
    }

    private void serializeClass(ObjectMapper mapper, ObjectNode groupNode, Group group) throws IOException{
        Class cl = group.get_class();
        try {
            JsonNode classNode = mapper.readTree(this.classSerializer.serialize(cl,ClassSerializationMode.TEACHER));
            groupNode.set("class", classNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Group deserialize(String json) throws IOException {
        return null;
    }
}
