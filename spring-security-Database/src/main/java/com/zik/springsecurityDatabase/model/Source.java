package com.zik.springsecurityDatabase.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Data
@Getter
@Setter
public class Source implements Serializable {
    private String name;
    private String type;
}