package com.onlybilkent.model;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class AnnouncementService {

    @Autowired
    private AnnouncementRepository announcementRepo;
    
    @Autowired
    public UserService userService;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Announcement> allPosts() {
        return announcementRepo.findAll();
    }

    public Optional<Announcement> singlePost(String title) {
        return announcementRepo.findPostByTitle(title);
    }

    public String getSenderId(String id) {
        Optional<Announcement> a = announcementRepo.findById(id);
        return a.get().getSenderId();
    }

    public boolean existsById(String id) {
        return announcementRepo.existsById(id);
    }

    public Optional<Announcement> findByAnnouncementId(String id) {
        return announcementRepo.findById(id);
    }

    public Optional<Announcement> findBySenderId(String senderId) {
        return announcementRepo.findBySenderId(senderId);
    }

    public Optional<Announcement> findByTitle(String str) {
        return announcementRepo.findByTitleRegex(str);
    }

    public Optional<Announcement> findByContent(String str) {
        return announcementRepo.findByContentRegex(str);
    }

    public void savePost(Announcement a) {
        announcementRepo.save(a);
    }

	public List<Announcement> allAnnouncements() {
		return announcementRepo.findAll();
	}

    public Announcement announce(String userId, String title, String content) {
        Announcement announcement = new Announcement(userId, title, content);
        Query query = new Query(Criteria.where("_id").is(announcement.getSenderId()));
        Update update = new Update().push("announcements", announcement);
        mongoTemplate.updateFirst(query, update, User.class);
        return announcementRepo.save(announcement);
    }

    public Announcement getAnnouncement(String announcementId) {
        return announcementRepo.findById(announcementId).get();
    }

    public void saveAnnouncement(Announcement announcement) {
        announcementRepo.save(announcement);
    }

}
