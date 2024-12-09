package io.swagger.petstore.dtos.store;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OrderJson {
    private Long id;
    private Long petId;
    private Long quantity;
    private String shipDate;
    private String status;
    private Boolean complete;
}
