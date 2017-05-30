package serializer;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class BasicSerializer<T> extends Serializer<T,Object> {

    Class<T> tClass;

    public BasicSerializer(Class<T> tClass){
        this.tClass = tClass;
    }

    @Override
    public String serialize(T obj, Object... mode) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }


    @Override
    public T deserialize(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, this.tClass);
    }
}
