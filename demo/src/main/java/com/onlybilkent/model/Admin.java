package com.onlybilkent.model;

import java.util.List;

public class Admin extends User{
    List<User> users;
    List<BoardRequest> boardRequests;
    List<Chat> chats;
    List<User> reports;

    public Admin(List<User> users, List<BoardRequest> boardRequests, List<Chat> chats, List<User> reports) {
        this.users = users;
        this.boardRequests = boardRequests;
        this.chats = chats;
        this.reports = reports;
    }

    public Admin(String name, String surname, String email, String password, String bio, int role, String imageId, String emailVerificationToken, List<User> users, List<BoardRequest> boardRequests, List<Chat> chats, List<User> reports) {
        super(name, surname, email, password, bio, role = 5, imageId, emailVerificationToken);
        this.users = users;
        this.boardRequests = boardRequests;
        this.chats = chats;
        this.reports = reports;

    }



}
