package com.satanssoft.helix.service.impl;

import com.satanssoft.helix.dao.TagDAO;
import com.satanssoft.helix.hibernate.model.Post;
import com.satanssoft.helix.hibernate.model.Tag;
import com.satanssoft.helix.service.TagService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class TagServiceImpl implements TagService {

    private TagDAO tagDAO;

    public void setTagDAO(TagDAO tagDAO) {
        this.tagDAO = tagDAO;
    }

    @Override
    @Transactional
    public void addTag(Tag tag) {
        this.tagDAO.addTag(tag);
    }

    @Override
    @Transactional
    public void updateTag(Tag tag) {
        this.tagDAO.updateTag(tag);
    }

    @Override
    @Transactional
    public List<Tag> getAllTags() {
        return this.tagDAO.getAllTags();
    }

    @Override
    @Transactional
    public Tag getTagById(int tag_id) {
        return this.tagDAO.getTagById(tag_id);
    }

    @Override
    @Transactional
    public void removeTag(int tag_id) {
        this.tagDAO.removeTag(tag_id);
    }

    @Override
    @Transactional
    public List<Post> getAllPostsForTag(Tag tag) {
        return this.tagDAO.getAllPostsForTag(tag);
    }

}
