package io.swagger.petstore.dtos.pet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PetJson {
    private Long id;
    private String name;
    private String status;
    private List<String> photoUrls;
    private PetCategory category;
    private List<PetCategory> tags;
}
