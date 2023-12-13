package com.onlybilkent.model;

import lombok.Data;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import org.bson.types.ObjectId;

@Document(collection = "sendables")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Sendable {
    private User sender;
    private String content;

    @Id
    private ObjectId id;

    public Sendable(User sender, String content) {
        this.sender = sender;
        this.content = content;
    }
}
