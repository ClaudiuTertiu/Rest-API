package model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class NewProduct {
    private String name;
    private String price;
    private String category;
}

