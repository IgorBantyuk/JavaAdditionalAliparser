package edu.ntu.dp.sa.model;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;

@Data
@AllArgsConstructor
public class Item {
    private String id;
    private String name;
    private float price;
    private String currency;
}
