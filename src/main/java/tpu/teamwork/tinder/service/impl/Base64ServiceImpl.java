package tpu.teamwork.tinder.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;
import tpu.teamwork.tinder.exception.Base64OperationException;
import tpu.teamwork.tinder.service.Base64Service;

import java.util.Base64;

@Service
public class Base64ServiceImpl implements Base64Service {
    private final ObjectMapper objectMapper;

    public Base64ServiceImpl(
            @Qualifier("customObjectMapper") ObjectMapper objectMapper
    ) {
        this.objectMapper = objectMapper;
    }

    @Override
    public <T> T decode(String data, Class<T> to) {
        try {

            byte[] decodedBytes = Base64.getDecoder().decode(data);

            String jsonData = new String(decodedBytes);

            return objectMapper.readValue(jsonData,to);

        } catch (Exception e) {
            throw new Base64OperationException(String.format("Не получилось декодировать информацию %s", e.getMessage()));
        }
    }

    @Override
    public <T> String encode(T t) {
        String jsonData = null;
        jsonData = objectMapper.writeValueAsString(t);
        return Base64.getEncoder().encodeToString(jsonData.getBytes());
    }
}
