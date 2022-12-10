package site.stellarburgers.pojo;

import lombok.Data;

@Data
public class Ingredient {
    private final String _id;
    private final String name;
    private final String type;
    private final int proteins;
    private final int fat;
    private final int carbohydrates;
    private final int calories;
    private final int price;
    private final String image;
    private final String image_mobile;
    private final String image_large;
    private final int __v;
}
