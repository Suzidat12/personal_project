package com.zik.springsecurityDatabase.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Data
@Getter
@Setter
public class Bank implements Serializable {
    private String _id;
    private Colors colors;
    private String icon;
    private String logo;
    private String name;
    private String v2_icon;
    private String v2_logo;

}
