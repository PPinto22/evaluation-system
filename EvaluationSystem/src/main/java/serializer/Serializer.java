package serializer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.util.*;

/**
 * @param <T> Class to serialize
 * @param <S> Serialization mode
 */
public abstract class Serializer<T,S> {

    public abstract String serialize(T obj, S... mode) throws IOException;
    public abstract T deserialize(String json) throws IOException;

    // TODO - Testar isto
    public List<T> deserializeList(String jsonList) throws IOException {
        List<T> list = new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(jsonList);
        for(JsonNode objNode: root){
            list.add(this.deserialize(objNode.toString()));
        }

        return list;
    }

    // TODO - Testar isto
    public Map<String, T> deserializeMap(String jsonMap) throws IOException {
        Map<String, T> map = new HashMap();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(jsonMap);
        for (Iterator<Map.Entry<String, JsonNode>> it = root.fields(); it.hasNext(); ) {
            Map.Entry<String, JsonNode> entry = it.next();
            map.put(entry.getKey(), this.deserialize(entry.getValue().toString()));
        }

        return map;
    }

    public String serialize(List<? extends T> objs, S... mode) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode arrayNode = mapper.createArrayNode();
        for(T obj: objs){
            JsonNode jsonNode = mapper.readTree(this.serialize(obj,mode));
            arrayNode = arrayNode.add(jsonNode);
        }
        return arrayNode.toString();
    }

    public String serialize(Map<?, ? extends T> objs, S... mode) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();
        for(Object key: objs.keySet()){
            JsonNode jsonNode = mapper.readTree(this.serialize(objs.get(key), mode));
            objectNode.set(String.valueOf(key), jsonNode);
        }
        return objectNode.toString();
    }
}
