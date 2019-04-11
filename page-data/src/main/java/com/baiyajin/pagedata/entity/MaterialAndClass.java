package com.baiyajin.pagedata.entity;


import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class MaterialAndClass extends PageMaterialClass {

    private List<PageMaterial> childrenList;





}
