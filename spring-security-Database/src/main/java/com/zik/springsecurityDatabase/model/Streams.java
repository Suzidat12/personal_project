package com.zik.springsecurityDatabase.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Data
@Getter
@Setter
public class Streams implements Serializable {
    private String _id;
    private String stream_id;
    private Long __v;
    private List<String> account;
    private Bank bank;
    private String created_at;
    private String last_updated;
    private Double monthly_amount;
    private Integer number_of_transactions;
    private Boolean primary_stream;
    private Source source;
    private Integer stream_rank;
    private String type;
    private Integer working_days_this_year;


}
