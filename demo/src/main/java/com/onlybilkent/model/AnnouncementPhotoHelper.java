package com.onlybilkent.model;

import java.io.IOException;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AnnouncementPhotoHelper {

    @Autowired
    private AnnouncementService announcementService;

    @Autowired
    private PhotoService photoService;

    private PhotoRepository photoRepo;

    public String addPhotoToAnnouncement(String announcementId, String title, MultipartFile file) throws IOException {
        Photo photo = new Photo(title);
        photo.setImage(new Binary(BsonBinarySubType.BINARY, file.getBytes()));
        photo = photoRepo.insert(photo);
        Announcement announcement = announcementService.getAnnouncement(announcementId);
        announcement.setPhotoId(photo.getId());
        announcementService.saveAnnouncement(announcement);
        return photo.getId();
    }

    // Other methods that involve both Announcement and Photo services
}