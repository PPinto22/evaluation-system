package serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import model.Student;
import model.Teacher;
import model.User;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;

@Service
public class UserSerializer extends Serializer<User>{

    private enum ModeImpl implements Mode {
        BASIC,
    }

    @Override
    public String serialize(User user, Mode mode) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();

        objectNode.put("id", user.getID());
        objectNode.put("email", user.getEmail());
        objectNode.put("firstName", user.getFirstName());
        objectNode.put("lastName", user.getLastName());
        if(user instanceof Teacher)
            objectNode.put("type", "Teacher");

        else if(user instanceof Student)
            objectNode.put("type", "Student");
        return objectNode.toString();
    }


    @Override
    public User deserialize(String userJSON) {
        return null;
    }
}
