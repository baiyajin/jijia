package com.baiyajin.pagedata.entity;


import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class MaterialAndClass extends PageMaterialClass {

    private List<PageMaterial> children;

    public MaterialAndClass(PageMaterialClass pageMaterialClass,List<PageMaterial> children){
        super.setId(pageMaterialClass.getId());
        super.setName(pageMaterialClass.getName());
        super.setAreaID(pageMaterialClass.getAreaID());
        super.setStatusID(pageMaterialClass.getStatusID());
        super.setCreateTime(pageMaterialClass.getCreateTime());
        super.setUpdateTime(pageMaterialClass.getUpdateTime());
        this.children = children;
    }

}
