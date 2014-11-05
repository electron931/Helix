package com.satanssoft.helix.dao;

import com.satanssoft.helix.hibernate.model.Post;
import com.satanssoft.helix.hibernate.model.Tag;

import java.util.List;

public interface TagDAO {

    public void addTag(Tag tag);
    public void updateTag(Tag tag);
    public List<Tag> getAllTags();
    public Tag getTagById(int tag_id);
    public Tag getTagByUrlSlug(String tagSlug);
    public void removeTag(int tag_id);
    public List<Post> getAllPostsForTag(Tag tag, int pageNumber, int postsPerPage);
    
}
