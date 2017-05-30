package serializer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import model.Class;
import model.Group;
import model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ClassSerializer extends Serializer<Class, ClassSerializationMode> {

    UserSerializer userSerializer;
    GroupSerializer groupSerializer;

    @Autowired
    public void setGroupSerializer(GroupSerializer groupSerializer) {
        this.groupSerializer = groupSerializer;
    }

    @Autowired
    public void setUserSerializer(UserSerializer userSerializer) {
        this.userSerializer = userSerializer;
    }

    @Override
    public String serialize(Class cl, ClassSerializationMode... serializationMode) throws IOException{
        ClassSerializationMode mode = ClassSerializationMode.CLASS;
        if(serializationMode.length >= 1){
            mode = serializationMode[0];
        }

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode classNode = mapper.createObjectNode();
        classNode.put("id", cl.getID());
        classNode.put("name", cl.getName());
        classNode.put("abbreviation", cl.getAbbreviation());
        if(mode != ClassSerializationMode.CLASS){
            switch (mode){
                case TEACHER:
                    serializeTeacher(mapper, classNode, cl);
                    break;
                case GROUPS:
                    serializeGroups(mapper, classNode, cl);
            }
        }

        return classNode.toString();
    }

    private void serializeGroups(ObjectMapper mapper, ObjectNode classNode, Class cl) throws IOException{
        Group[] groups = cl._groups.toArray();
        ArrayNode groupsNode = classNode.putArray("groups");
        for(Group group: cl._groups.toArray()){
            JsonNode groupNode = mapper.readTree(groupSerializer.serialize(group,GroupSerializationMode.GROUP));
            groupsNode.add(groupNode);
        }
    }

    private void serializeTeacher(ObjectMapper mapper, ObjectNode classNode, Class cl) throws IOException{
        Teacher teacher = cl.get_teacher();
        JsonNode teacherNode = mapper.readTree(userSerializer.serialize(teacher,UserSerializationMode.USER));
        classNode.set("teacher", teacherNode);
    }

    @Override
    public Class deserialize(String json) throws IOException {
        return null;
    }
}
