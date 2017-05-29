package serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.util.RawValue;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

/**
 * @param <T> Class to serialize
 * @param <S> Serialization mode
 */
public abstract class Serializer<T,S> {

    public abstract String serialize(T obj, S mode);
    public abstract T deserialize(String json);

    public String serialize(Collection<? extends T> objs, S mode){
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode arrayNode = mapper.createArrayNode();
        for(T obj: objs){
            try {
                JsonNode jsonNode = mapper.readTree(this.serialize(obj,mode));
                arrayNode = arrayNode.add(jsonNode);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return arrayNode.toString();
    }

    public String serialize(Map<?, ? extends T> objs, S mode){
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();
        for(Object key: objs.keySet()){
            try {
                JsonNode jsonNode = mapper.readTree(this.serialize(objs.get(key), mode));
                objectNode.set((String) key, jsonNode);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return objectNode.toString();
    }
}
