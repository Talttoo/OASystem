package com.julong.oasystem.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class CriticalValueVO {

    private String  itemCode;
    private String itemName;
    private String upBound;
    private String downBound;
    private Boolean disable;

}
