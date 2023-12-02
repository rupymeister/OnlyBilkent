package com.example;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.*;
import java.util.ArrayList;


public class DataSource {
    
    private static final String DB_NAME = "OnlyBilkent.db";
    private static final String CONNECTION_STRING = "jdbc:sqlite:" + "/Users/anilaltuncu/Desktop/demo/" + DB_NAME;
    
    private static final String TABLE_USER = "user";
    private static final String USER_COLUMN_ID = "_ID";
    private static final String USER_COLUMN_MAIL = "mail";
    private static final String USER_COLUMN_NAME = "name";
    private static final String USER_COLUMN_PASSWORD = "password";
    private static final String USER_COLUMN_USERTYPE = "user_type";
    private static final String USER_COLUMN_DESCRIPTION = "description";

    private static final String TABLE_POST = "post";
    private static final String POST_COLUMN_ID = "_ID";
    private static final String POST_COLUMN_SENDER_ID = "sender_ID";
    private static final String POST_COLUMN_CONTENT = "content";
    private static final String POST_COLUMN_CATEGORY = "category";
    private static final String POST_COLUMN_TITLE = "title";
    private static final String POST_COLUMN_IMAGE = "image";

    private static final String TABLE_ANNOUNCEMENT = "announcement";
    private static final String ANNOUNCEMENT_COLUMN_ID = "_ID";
    private static final String ANNOUNCEMENT_COLUMN_SENDER_ID = "sender_ID";
    private static final String ANNOUNCEMENT_COLUMN_CONTENT = "content";
    private static final String ANNOUNCEMENT_COLUMN_TITLE = "title";
    private static final String ANNOUNCEMENT_COLUMN_IMAGE = "image";

    private static final String TABLE_MESSAGE = "message";
    private static final String MESSAGE_COLUMN_ID = "_ID";
    private static final String MESSAGE_COLUMN_SENDER_ID = "sender_ID";
    private static final String MESSAGE_COLUMN_RECEIVER_ID = "receiver_ID";
    private static final String MESSAGE_COLUMN_CONTENT = "content";
    private static final String MESSAGE_COLUMN_ISSEEN = "isSeen";

    private static final String TABLE_NOTIFICATION = "notification";
    private static final String NOTIFICATION_COLUMN_ID = "_ID";
    private static final String NOTIFICATION_COLUMN_SENDER_ID = "sender_ID";
    private static final String NOTIFICATION_COLUMN_RECEIVER_ID = "receiver_ID";
    private static final String NOTIFICATION_COLUMN_CONTENT = "content";
    private static final String NOTIFICATION_COLUMN_ISSEEN = "isSeen";

    private static final String TABLE_COMMENT = "comment";
    private static final String COMMENT_COLUMN_ID = "_ID";
    private static final String COMMENT_COLUMN_SENDER_ID = "sender_ID";
    private static final String COMMENT_COLUMN_POST_ID = "post_ID";
    private static final String COMMENT_COLUMN_CONTENT = "content";

    private static final String TABLE_LIKE = "like";
    private static final String LIKE_COLUMN_ID = "_ID";
    private static final String LIKE_COLUMN_SENDER_ID = "sender_ID";
    private static final String LIKE_COLUMN_POST_ID = "post_ID";

    
    private static Connection connection;

    public boolean open() {
        connection = null;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(CONNECTION_STRING);
            return true;
        } catch (SQLException | ClassNotFoundException e) {
                System.out.println(e);
                e.printStackTrace();
            return false;
        }
    }

    public void close() {
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            System.out.println("cannot close the connection" + e);
            e.printStackTrace();
        }
    }

    public void createDB() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_USER + "("
                    + USER_COLUMN_ID + " INTEGER PRIMARY KEY ,"
                    + USER_COLUMN_MAIL + " TEXT ,"
                    + USER_COLUMN_NAME + " TEXT ,"
                    + USER_COLUMN_PASSWORD + " TEXT ,"
                    + USER_COLUMN_USERTYPE + " INTEGER ,"
                    + USER_COLUMN_DESCRIPTION + " TEXT ,"
                    + " UNIQUE (" + USER_COLUMN_MAIL + ")"
                    + ")"
            );

            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_POST + "("
                    + POST_COLUMN_ID + " INTEGER PRIMARY KEY ,"
                    + POST_COLUMN_SENDER_ID + " INTEGER ,"
                    + POST_COLUMN_CONTENT + " TEXT ,"
                    + POST_COLUMN_CATEGORY + " TEXT ,"
                    + POST_COLUMN_TITLE + " TEXT ,"
                    + POST_COLUMN_IMAGE + " TEXT ,"
                    + " FOREIGN KEY (" + POST_COLUMN_SENDER_ID + ") REFERENCES " + TABLE_USER + "(" + USER_COLUMN_ID + ")"
                    + ")"
            );

            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_ANNOUNCEMENT + "("
                    + ANNOUNCEMENT_COLUMN_ID + " INTEGER PRIMARY KEY ,"
                    + ANNOUNCEMENT_COLUMN_SENDER_ID + " INTEGER ,"
                    + ANNOUNCEMENT_COLUMN_CONTENT + " TEXT ,"
                    + ANNOUNCEMENT_COLUMN_TITLE + " TEXT ,"
                    + ANNOUNCEMENT_COLUMN_IMAGE + " TEXT ,"
                    + " FOREIGN KEY (" + ANNOUNCEMENT_COLUMN_SENDER_ID + ") REFERENCES " + TABLE_USER + "(" + USER_COLUMN_ID + ")"
                    + ")"
            );

            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_MESSAGE + "("
                    + MESSAGE_COLUMN_ID + " INTEGER PRIMARY KEY ,"
                    + MESSAGE_COLUMN_SENDER_ID + " INTEGER ,"
                    + MESSAGE_COLUMN_RECEIVER_ID + " INTEGER ,"
                    + MESSAGE_COLUMN_CONTENT + " TEXT ,"
                    + MESSAGE_COLUMN_ISSEEN + " INTEGER ,"
                    + " FOREIGN KEY (" + MESSAGE_COLUMN_SENDER_ID + ") REFERENCES " + TABLE_USER + "(" + USER_COLUMN_ID + "),"
                    + " FOREIGN KEY (" + MESSAGE_COLUMN_RECEIVER_ID + ") REFERENCES " + TABLE_USER + "(" + USER_COLUMN_ID + ")"
                    + ")"
            );

            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_NOTIFICATION + "("
                    + NOTIFICATION_COLUMN_ID + " INTEGER PRIMARY KEY ,"
                    + NOTIFICATION_COLUMN_SENDER_ID + " INTEGER ,"
                    + NOTIFICATION_COLUMN_RECEIVER_ID + " INTEGER ,"
                    + NOTIFICATION_COLUMN_CONTENT + " TEXT ,"
                    + NOTIFICATION_COLUMN_ISSEEN + " INTEGER ,"
                    + " FOREIGN KEY (" + NOTIFICATION_COLUMN_SENDER_ID + ") REFERENCES " + TABLE_USER + "(" + USER_COLUMN_ID + "),"
                    + " FOREIGN KEY (" + NOTIFICATION_COLUMN_RECEIVER_ID + ") REFERENCES " + TABLE_USER + "(" + USER_COLUMN_ID + ")"
                    + ")"
            );

            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_COMMENT + "("
                    + COMMENT_COLUMN_ID + " INTEGER PRIMARY KEY ,"
                    + COMMENT_COLUMN_SENDER_ID + " INTEGER ,"
                    + COMMENT_COLUMN_POST_ID + " INTEGER ,"
                    + COMMENT_COLUMN_CONTENT + " TEXT ,"
                    + " FOREIGN KEY (" + COMMENT_COLUMN_SENDER_ID + ") REFERENCES " + TABLE_USER + "(" + USER_COLUMN_ID + "),"
                    + " FOREIGN KEY (" + COMMENT_COLUMN_POST_ID + ") REFERENCES " + TABLE_POST + "(" + POST_COLUMN_ID + ")"
                    + ")"
            );

            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_LIKE + "("
                    + LIKE_COLUMN_ID + " INTEGER PRIMARY KEY ,"
                    + LIKE_COLUMN_SENDER_ID + " INTEGER ,"
                    + LIKE_COLUMN_POST_ID + " INTEGER ,"
                    + " FOREIGN KEY (" + LIKE_COLUMN_SENDER_ID + ") REFERENCES " + TABLE_USER + "(" + USER_COLUMN_ID + "),"
                    + " FOREIGN KEY (" + LIKE_COLUMN_POST_ID + ") REFERENCES " + TABLE_POST + "(" + POST_COLUMN_ID + ")"
                    + ")"
            );

        } catch (Exception e) {
            System.out.println("cannot create the database");
            e.printStackTrace();
        }
    }

    public int getOwnerID(String ownerMail) {
        String ownerID = "SELECT " + USER_COLUMN_ID + " FROM " + TABLE_USER
                + " WHERE " + USER_COLUMN_MAIL + "=" + '"' + ownerMail + '"';
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(ownerID)) {
            return rs.getInt(USER_COLUMN_ID);
        } catch (Exception e) {
            System.out.println("cannot get owner ID");
            e.printStackTrace();
        }
        return -1;
    }

    public boolean specifyUserType(String mail){
        String temp = mail.substring(mail.indexOf("@")+1);
        int user_type = -1;
        if(temp.contains("ug.bilkent.edu.tr")) {
            user_type = 0;
        }
        else if(temp.contains("alumni.bilkent.edu.tr")) {
            user_type = 1;
        }
        else if(temp.contains("bilkent.edu.tr")) {
            user_type = 2;
        }
        else {
            user_type = -1;
        }
        String choosed = "UPDATE " + TABLE_USER + " SET " + USER_COLUMN_USERTYPE + "=" + user_type +
                " WHERE " + USER_COLUMN_MAIL + "=" + "\"" + mail + "\"";
        try (Statement statement = connection.createStatement()) {
            statement.execute(choosed);
            return true;
        } catch (Exception e) {
            System.out.println("cannot update listing type ");
            e.printStackTrace();
            return false;
        }
    }
    public boolean isBilkentMail(String mail) {
        String temp = mail.substring(mail.indexOf("@")+1);
        if(temp.contains("bilkent.edu.tr")) {
            System.out.println("Mail is valid");
            return true;
        }
        else {
            System.out.println("Mail is not valid (Use bilkent mail)");
            return false;
        }
    }
    public boolean checkMail(String mail){
        String checkMail = "SELECT " + USER_COLUMN_MAIL + " FROM " + TABLE_USER 
                            + " WHERE " + USER_COLUMN_MAIL + "=" + "\"" + mail + "\"";
        try (PreparedStatement statement = connection.prepareStatement(checkMail);
            ResultSet result = statement.executeQuery()) {
            if (result.getString(USER_COLUMN_MAIL).equals(mail)) {
                return isBilkentMail(mail);
            }
        } catch (SQLException e) {
            System.out.println("cannot check the mail");
            e.printStackTrace();
        }
        System.out.println("Enter existing mail!");
        return false;
    }

    public boolean checkPassword(String mail, String password) {
        String checkPass = "SELECT " + USER_COLUMN_PASSWORD + " FROM " + TABLE_USER 
                            + " WHERE " + USER_COLUMN_MAIL + "=" + "\"" + mail + "\"";
        try (PreparedStatement statement = connection.prepareStatement(checkPass);
            ResultSet result = statement.executeQuery()) {
            if (result.getString(USER_COLUMN_PASSWORD).equals(password)) {
                System.out.println("Password is correct.");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("cannot check the mail");
            e.printStackTrace();
        }
        System.out.println("Password is not correct");
        return false;
    }

    public boolean updateDescription(String mail, String description) {
        String updateDescription = "UPDATE " + TABLE_USER + " SET " + USER_COLUMN_DESCRIPTION + "=" + "\"" + description + "\""
                + " WHERE " + USER_COLUMN_MAIL + "=" + "\"" + mail + "\"";
        try (Statement statement = connection.createStatement()) {
            statement.execute(updateDescription);
            return true;
        } catch (Exception e) {
            System.out.println("cannot update description ");
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateName(String mail, String name) {
        String updateName = "UPDATE " + TABLE_USER + " SET " + USER_COLUMN_NAME + "=" + "\"" + name + "\""
                + " WHERE " + USER_COLUMN_MAIL + "=" + "\"" + mail + "\"";
        try (Statement statement = connection.createStatement()) {
            statement.execute(updateName);
            return true;
        } catch (Exception e) {
            System.out.println("cannot update name ");
            e.printStackTrace();
            return false;
        }
    }

    public boolean updatePassword(String mail, String password, String password2) {
        if (!password.equals(password2)) {
            System.out.println("Passwords do not match!");
            return false;
        }
        String updatePassword = "UPDATE " + TABLE_USER + " SET " + USER_COLUMN_PASSWORD + "=" + "\"" + password + "\""
                + " WHERE " + USER_COLUMN_MAIL + "=" + "\"" + mail + "\"";
        try (Statement statement = connection.createStatement()) {
            statement.execute(updatePassword);
            return true;
        } catch (Exception e) {
            System.out.println("cannot update password ");
            e.printStackTrace();
            return false;
        }
    }

        public boolean addUser(String mail, String username, String password, String password2) {
        if (!password.equals(password2)) {
            System.out.println("Passwords do not match!");
            return false;
        }
        if (!isBilkentMail(mail)) {
            return false;
        }
        String INSERT_USER = "INSERT INTO " + TABLE_USER + "("
                + USER_COLUMN_MAIL + ","
                + USER_COLUMN_NAME + ","
                + USER_COLUMN_PASSWORD +  ")"
                + "VALUES(?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(INSERT_USER)) {
            statement.setString(1, mail);
            statement.setString(2, username);
            statement.setString(3, password);
            statement.execute();
            specifyUserType(mail);
            return true;
        } catch (SQLException e) {
            System.out.println("cannot add person");
            e.printStackTrace();
            return false;
        }
    }

    public boolean addPost(String mail, String title, String content, String category, String image) {
        int ownerID = getOwnerID(mail);
        String INSERT_POST = "INSERT INTO " + TABLE_POST + "("
                + POST_COLUMN_SENDER_ID + ","
                + POST_COLUMN_CONTENT + ","
                + POST_COLUMN_CATEGORY + ","
                + POST_COLUMN_TITLE + ","
                + POST_COLUMN_IMAGE +  ")"
                + "VALUES(?,?,?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(INSERT_POST)) {
            statement.setInt(1, ownerID);
            statement.setString(2, content);
            statement.setString(3, category);
            statement.setString(4, title);
            statement.setString(5, image);
            statement.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("cannot add post");
            e.printStackTrace();
            return false;
        }
    }

    public boolean addAnnouncement(String mail, String title, String content, String image) {
        int ownerID = getOwnerID(mail);
        String INSERT_ANNOUNCEMENT = "INSERT INTO " + TABLE_ANNOUNCEMENT + "("
                + ANNOUNCEMENT_COLUMN_SENDER_ID + ","
                + ANNOUNCEMENT_COLUMN_CONTENT + ","
                + ANNOUNCEMENT_COLUMN_TITLE + ","
                + ANNOUNCEMENT_COLUMN_IMAGE +  ")"
                + "VALUES(?,?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(INSERT_ANNOUNCEMENT)) {
            statement.setInt(1, ownerID);
            statement.setString(2, content);
            statement.setString(3, title);
            statement.setString(4, image);
            statement.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("cannot add announcement");
            e.printStackTrace();
            return false;
        }
    }

    public boolean addMessage(String senderMail, String receiverMail, String content) {
        int senderID = getOwnerID(senderMail);
        int receiverID = getOwnerID(receiverMail);
        String INSERT_MESSAGE = "INSERT INTO " + TABLE_MESSAGE + "("
                + MESSAGE_COLUMN_SENDER_ID + ","
                + MESSAGE_COLUMN_RECEIVER_ID + ","
                + MESSAGE_COLUMN_CONTENT + ","
                + MESSAGE_COLUMN_ISSEEN +  ")"
                + "VALUES(?,?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(INSERT_MESSAGE)) {
            statement.setInt(1, senderID);
            statement.setInt(2, receiverID);
            statement.setString(3, content);
            statement.setInt(4, 0);
            statement.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("cannot add message");
            e.printStackTrace();
            return false;
        }
    }

    public boolean addComment(String senderMail, int postID, String content) {
        int senderID = getOwnerID(senderMail);
        String INSERT_COMMENT = "INSERT INTO " + TABLE_COMMENT + "("
                + COMMENT_COLUMN_SENDER_ID + ","
                + COMMENT_COLUMN_POST_ID + ","
                + COMMENT_COLUMN_CONTENT +  ")"
                + "VALUES(?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(INSERT_COMMENT)) {
            statement.setInt(1, senderID);
            statement.setInt(2, postID);
            statement.setString(3, content);
            statement.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("cannot add comment");
            e.printStackTrace();
            return false;
        }
    }
    

    public boolean addLike(String senderMail, int postID) {
        int senderID = getOwnerID(senderMail);
        String INSERT_LIKE = "INSERT INTO " + TABLE_LIKE + "("
                + LIKE_COLUMN_SENDER_ID + ","
                + LIKE_COLUMN_POST_ID +  ")"
                + "VALUES(?,?)";
        try (PreparedStatement statement = connection.prepareStatement(INSERT_LIKE)) {
            statement.setInt(1, senderID);
            statement.setInt(2, postID);
            statement.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("cannot add like");
            e.printStackTrace();
            return false;
        }
    }

    public boolean deletePost(int postID) {
        String DELETE_POST = "DELETE FROM " + TABLE_POST + " WHERE " + POST_COLUMN_ID + "=" + postID;
        try (Statement statement = connection.createStatement()) {
            statement.execute(DELETE_POST);
            return true;
        } catch (Exception e) {
            System.out.println("cannot delete post ");
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteAnnouncement(int announcementID) {
        String DELETE_ANNOUNCEMENT = "DELETE FROM " + TABLE_ANNOUNCEMENT + " WHERE " + ANNOUNCEMENT_COLUMN_ID + "=" + announcementID;
        try (Statement statement = connection.createStatement()) {
            statement.execute(DELETE_ANNOUNCEMENT);
            return true;
        } catch (Exception e) {
            System.out.println("cannot delete announcement ");
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteMessage(int messageID) {
        String DELETE_MESSAGE = "DELETE FROM " + TABLE_MESSAGE + " WHERE " + MESSAGE_COLUMN_ID + "=" + messageID;
        try (Statement statement = connection.createStatement()) {
            statement.execute(DELETE_MESSAGE);
            return true;
        } catch (Exception e) {
            System.out.println("cannot delete message ");
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteComment(int commentID) {
        String DELETE_COMMENT = "DELETE FROM " + TABLE_COMMENT + " WHERE " + COMMENT_COLUMN_ID + "=" + commentID;
        try (Statement statement = connection.createStatement()) {
            statement.execute(DELETE_COMMENT);
            return true;
        } catch (Exception e) {
            System.out.println("cannot delete comment ");
            e.printStackTrace();
            return false;
        }
    }

    public boolean cancelLike(int likeID) {
        String DELETE_LIKE = "DELETE FROM " + TABLE_LIKE + " WHERE " + LIKE_COLUMN_ID + "=" + likeID;
        try (Statement statement = connection.createStatement()) {
            statement.execute(DELETE_LIKE);
            return true;
        } catch (Exception e) {
            System.out.println("cannot delete like ");
            e.printStackTrace();
            return false;
        }
    }

    public boolean updatePost(int postID, String title, String content, String category, String image) {
        String updatePost = "UPDATE " + TABLE_POST + " SET " + POST_COLUMN_TITLE + "=" + "\"" + title + "\"" + ","
                + POST_COLUMN_CONTENT + "=" + "\"" + content + "\"" + ","
                + POST_COLUMN_CATEGORY + "=" + "\"" + category + "\"" + ","
                + POST_COLUMN_IMAGE + "=" + "\"" + image + "\""
                + " WHERE " + POST_COLUMN_ID + "=" + postID;
        try (Statement statement = connection.createStatement()) {
            statement.execute(updatePost);
            return true;
        } catch (Exception e) {
            System.out.println("cannot update post ");
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateAnnouncement(int announcementID, String title, String content, String image) {
        String updateAnnouncement = "UPDATE " + TABLE_ANNOUNCEMENT + " SET " + ANNOUNCEMENT_COLUMN_TITLE + "=" + "\"" + title + "\"" + ","
                + ANNOUNCEMENT_COLUMN_CONTENT + "=" + "\"" + content + "\"" + ","
                + ANNOUNCEMENT_COLUMN_IMAGE + "=" + "\"" + image + "\""
                + " WHERE " + ANNOUNCEMENT_COLUMN_ID + "=" + announcementID;
        try (Statement statement = connection.createStatement()) {
            statement.execute(updateAnnouncement);
            return true;
        } catch (Exception e) {
            System.out.println("cannot update announcement ");
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateMessage(int messageID, String content) {
        String updateMessage = "UPDATE " + TABLE_MESSAGE + " SET " + MESSAGE_COLUMN_CONTENT + "=" + "\"" + content + "\""
                + " WHERE " + MESSAGE_COLUMN_ID + "=" + messageID;
        try (Statement statement = connection.createStatement()) {
            statement.execute(updateMessage);
            return true;
        } catch (Exception e) {
            System.out.println("cannot update message ");
            e.printStackTrace();
            return false;
        }
    }


    public boolean updateComment(int commentID, String content) {
        String updateComment = "UPDATE " + TABLE_COMMENT + " SET " + COMMENT_COLUMN_CONTENT + "=" + "\"" + content + "\""
                + " WHERE " + COMMENT_COLUMN_ID + "=" + commentID;
        try (Statement statement = connection.createStatement()) {
            statement.execute(updateComment);
            return true;
        } catch (Exception e) {
            System.out.println("cannot update comment ");
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Post> getPosts() {
        ArrayList<Post> posts = new ArrayList<>();
        String getPosts = "SELECT * FROM " + TABLE_POST;
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(getPosts)) {
            while (rs.next()) {
                int postID = rs.getInt(POST_COLUMN_ID);
                int senderID = rs.getInt(POST_COLUMN_SENDER_ID);
                String content = rs.getString(POST_COLUMN_CONTENT);
                String category = rs.getString(POST_COLUMN_CATEGORY);
                String title = rs.getString(POST_COLUMN_TITLE);
                String image = rs.getString(POST_COLUMN_IMAGE);
                posts.add(new Post(senderID, postID, title, content, category, image));
            }
            return posts;
        } catch (Exception e) {
            System.out.println("cannot get posts");
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Announcement> getAnnouncements() {
        ArrayList<Announcement> announcements = new ArrayList<>();
        String getAnnouncements = "SELECT * FROM " + TABLE_ANNOUNCEMENT;
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(getAnnouncements)) {
            while (rs.next()) {
                int announcementID = rs.getInt(ANNOUNCEMENT_COLUMN_ID);
                int senderID = rs.getInt(ANNOUNCEMENT_COLUMN_SENDER_ID);
                String content = rs.getString(ANNOUNCEMENT_COLUMN_CONTENT);
                String title = rs.getString(ANNOUNCEMENT_COLUMN_TITLE);
                String image = rs.getString(ANNOUNCEMENT_COLUMN_IMAGE);
                announcements.add(new Announcement(senderID, announcementID, title, content, image));
            }
            return announcements;
        } catch (Exception e) {
            System.out.println("cannot get announcements");
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Message> getMessages(String mail) {
        ArrayList<Message> messages = new ArrayList<>();
        int receiverID = getOwnerID(mail);
        String getMessages = "SELECT * FROM " + TABLE_MESSAGE + " WHERE " + MESSAGE_COLUMN_RECEIVER_ID + "=" + receiverID;
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(getMessages)) {
            while (rs.next()) {
                int messageID = rs.getInt(MESSAGE_COLUMN_ID);
                int senderID = rs.getInt(MESSAGE_COLUMN_SENDER_ID);
                String content = rs.getString(MESSAGE_COLUMN_CONTENT);
                int isSeen = rs.getInt(MESSAGE_COLUMN_ISSEEN);
                messages.add(new Message(senderID, receiverID, messageID, content, isSeen));
            }
            return messages;
        } catch (Exception e) {
            System.out.println("cannot get messages");
            e.printStackTrace();
            return null;
        }
    }

    /** 
    public ArrayList<Like> getLikes(int postID) {
        ArrayList<Like> likes = new ArrayList<>();
        String getLikes = "SELECT * FROM " + TABLE_LIKE + " WHERE " + LIKE_COLUMN_POST_ID + "=" + postID;
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(getLikes)) {
            while (rs.next()) {
                int likeID = rs.getInt(LIKE_COLUMN_ID);
                int senderID = rs.getInt(LIKE_COLUMN_SENDER_ID);
                likes.add(new Like(senderID, postID, likeID));
            }
            return likes;
        } catch (Exception e) {
            System.out.println("cannot get likes");
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Notification> getNotifications(String mail) {
        ArrayList<Notification> notifications = new ArrayList<>();
        int receiverID = getOwnerID(mail);
        String getNotifications = "SELECT * FROM " + TABLE_NOTIFICATION + " WHERE " + NOTIFICATION_COLUMN_RECEIVER_ID + "=" + receiverID;
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(getNotifications)) {
            while (rs.next()) {
                int notificationID = rs.getInt(NOTIFICATION_COLUMN_ID);
                int senderID = rs.getInt(NOTIFICATION_COLUMN_SENDER_ID);
                String content = rs.getString(NOTIFICATION_COLUMN_CONTENT);
                int isSeen = rs.getInt(NOTIFICATION_COLUMN_ISSEEN);
                notifications.add(new Notification(senderID, receiverID, notificationID, content, isSeen));
            }
            return notifications;
        } catch (Exception e) {
            System.out.println("cannot get notifications");
            e.printStackTrace();
            return null;
        }
    }   
    */

    public ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();
        String getUsers = "SELECT * FROM " + TABLE_USER;
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(getUsers)) {
            while (rs.next()) {
                int userID = rs.getInt(USER_COLUMN_ID);
                String mail = rs.getString(USER_COLUMN_MAIL);
                String name = rs.getString(USER_COLUMN_NAME);
                String password = rs.getString(USER_COLUMN_PASSWORD);
                int userType = rs.getInt(USER_COLUMN_USERTYPE);
                String description = rs.getString(USER_COLUMN_DESCRIPTION);
                users.add(new User(userID, mail, name, password, userType, description));
            }
            return users;
        } catch (Exception e) {
            System.out.println("cannot get users");
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<User> getStudents() {
        ArrayList<User> students = new ArrayList<>();
        String getStudents = "SELECT * FROM " + TABLE_USER + " WHERE " + USER_COLUMN_USERTYPE + "=" + 0;
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(getStudents)) {
            while (rs.next()) {
                int userID = rs.getInt(USER_COLUMN_ID);
                String mail = rs.getString(USER_COLUMN_MAIL);
                String name = rs.getString(USER_COLUMN_NAME);
                String password = rs.getString(USER_COLUMN_PASSWORD);
                int userType = rs.getInt(USER_COLUMN_USERTYPE);
                String description = rs.getString(USER_COLUMN_DESCRIPTION);
                students.add(new User(userID, mail, name, password, userType, description));
            }
            return students;
        } catch (Exception e) {
            System.out.println("cannot get students");
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<User> getGraduates() {
        ArrayList<User> graduates = new ArrayList<>();
        String getGraduates = "SELECT * FROM " + TABLE_USER + " WHERE " + USER_COLUMN_USERTYPE + "=" + 1;
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(getGraduates)) {
            while (rs.next()) {
                int userID = rs.getInt(USER_COLUMN_ID);
                String mail = rs.getString(USER_COLUMN_MAIL);
                String name = rs.getString(USER_COLUMN_NAME);
                String password = rs.getString(USER_COLUMN_PASSWORD);
                int userType = rs.getInt(USER_COLUMN_USERTYPE);
                String description = rs.getString(USER_COLUMN_DESCRIPTION);
                graduates.add(new User(userID, mail, name, password, userType, description));
            }
            return graduates;
        } catch (Exception e) {
            System.out.println("cannot get companies");
            e.printStackTrace();
            return null;
        }
    }
