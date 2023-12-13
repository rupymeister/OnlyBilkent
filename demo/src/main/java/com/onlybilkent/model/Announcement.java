package com.onlybilkent.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.springframework.data.mongodb.core.mapping.Document;

import org.bson.types.ObjectId;

@Document(collection = "announcements")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Announcement extends Sendable {
    private String announcementID;
    private String title;
    private String image;

}
