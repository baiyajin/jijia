package com.baiyajin.entity.pagedata;


import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class MaterialAndClass extends PageMaterialClass {

    private String level;
    private List<PageMaterial> childrenList;





}
