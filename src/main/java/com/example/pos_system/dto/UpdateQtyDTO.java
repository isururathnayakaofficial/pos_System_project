package com.example.pos_system.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateQtyDTO {

    private String itemId;
    private int qty;

}
