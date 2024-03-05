package com.org.makgol.stores.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class LikesDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private int user_id;
    private int shop_id;
}
