package serializer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.databind.node.ObjectNode;
import model.Exam;
import model.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ExamSerializer extends Serializer<Exam, ExamSerializationMode>{

    private GroupSerializer groupSerializer;

    @Autowired
    public void setGroupSerializer(GroupSerializer groupSerializer) {
        this.groupSerializer = groupSerializer;
    }

    @Override
    public String serialize(Exam exam, ExamSerializationMode... serializationMode) throws IOException {
        ExamSerializationMode mode = ExamSerializationMode.EXAM;
        if(serializationMode.length >= 1){
            mode = serializationMode[0];
        }

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode examNode = mapper.createObjectNode();
        examNode.put("id", exam.getID());
        examNode.put("date", exam.getBeginDateAsString());
        examNode.put("duration", exam.getDurationAsString());

        switch (mode){
            case CLASS:
                serializeClass(mapper, examNode, exam);
        }

        return examNode.toString();
    }

    private void serializeClass(ObjectMapper mapper, ObjectNode examNode, Exam exam) throws IOException {
        Group group = exam.get_group();
        JsonNode groupNode = mapper.readTree(groupSerializer.serialize(group, GroupSerializationMode.CLASS));
        examNode.set("group", groupNode);
    }

    @Override
    public Exam deserialize(String json) throws IOException {
        return null;
    }
}
