package com.onlybilkent.model;

import java.util.ArrayList;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



import org.bson.types.ObjectId;

@Document(collection="sendables")
@Data
@AllArgsConstructor
@NoArgsConstructor


public class Sendable {
    private User sender;
    private String type;
    private String content;
    @Id
    private ObjectId _id;

    public Sendable(User sender, String type, String content, long id){
        setSender(sender);
        setType(type);
        setContent(content);
    }

    public void setSender(User sender){
        this.sender = sender;
    }

    public void setType(String type){
        this.type = type;
    }

    public void setContent(String content){
        this.content = content;
    }
    
    public User getSender(){
        return this.sender;
    }
    
    public String getType(){
        return this.type;
    }

    public String getContent(){
        return this.content;
    }

    public ObjectId getId(){
        return this._id;
    }
    
}
