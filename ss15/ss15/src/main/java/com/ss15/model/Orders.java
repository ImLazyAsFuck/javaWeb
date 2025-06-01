package com.ss15.model;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Orders {
    private int id;
    private int userId;
    private String recipientName;
    private String address;
    private String phoneNumber;
}
