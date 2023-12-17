package com.onlybilkent.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "boardrequests")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoardRequest {
    @Id
    private String requestId;

    private String userId;

    private String clubName;

    private String reason;

    public BoardRequest(String userId, String clubName, String reason) {
        this.userId = userId;
        this.clubName = clubName;
        this.reason = reason;
    }
}
