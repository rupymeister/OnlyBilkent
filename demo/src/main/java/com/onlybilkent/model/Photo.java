package com.onlybilkent.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "photos")
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Photo {

    @Id
    private String id;
    private String title;
    private Binary photo;
    private String userId;
    

    public Photo(String title) {
        this.title = title;
    }

    public Binary getImage() {
        return photo;
    }

    public void setImage(Binary image) {
        this.photo = image;
    }
}