package io.swagger.petstore.dtos.store;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@Data
@Builder
public class StoreJson {
    private final Map<String, Integer> inventory = new HashMap<>();

    @JsonAnySetter
    public void setInventoryItem(String key, Integer value) {
        this.inventory.put(key, value);
    }
}
