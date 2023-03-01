package com.zik.springsecurityDatabase.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Data
@Getter
@Setter
public class Colors implements Serializable {
    private String primary;
    private String accent;
    private String button;
    private String icon;

}
