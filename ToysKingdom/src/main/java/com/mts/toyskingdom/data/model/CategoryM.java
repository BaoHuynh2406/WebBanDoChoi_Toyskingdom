package com.mts.toyskingdom.data.model;


import com.mts.toyskingdom.data.entity.CategoryE;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryM {
    private String idCategory;
    private String categoryName;
    private String des;

    public static CategoryM convertCategoryEtoCategoryM(CategoryE categoryE) {
        return CategoryM.builder()
               .idCategory(categoryE.getIdCategory())
               .categoryName(categoryE.getCategoryName())
               .des(categoryE.getDes())
               .build();
    }

    public static List<CategoryM> convertListCategoryEtoCategoryM(List<CategoryE> categoryEList) {
        return categoryEList.stream().map(categoryE -> convertCategoryEtoCategoryM(categoryE)).collect(Collectors.toList());
    }
}
