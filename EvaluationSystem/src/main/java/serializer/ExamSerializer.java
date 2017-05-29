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
    public String serialize(Exam exam, ExamSerializationMode mode) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode examNode = mapper.createObjectNode();
        examNode.put("id", exam.getID());
        // FIXME - Tempo nao funciona (00:00)
        examNode.put("date", exam.getBeginDateAsString());
        examNode.put("duration", exam.getDurationAsString());

        switch (mode){
            case CLASS:
                serializeClass(mapper, examNode, exam);
        }

        return examNode.toString();
    }

    private void serializeClass(ObjectMapper mapper, ObjectNode examNode, Exam exam) {
        Group group = exam.get_group();
        try {
            JsonNode groupNode = mapper.readTree(groupSerializer.serialize(group, GroupSerializationMode.DEFAULT));
            examNode.set("group", groupNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Exam deserialize(String json) {
        return null;
    }
}
