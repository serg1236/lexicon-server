package com.lexicon.repository;


import com.lexicon.model.Image;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by Sergiy_Dakhniy
 */
public interface ImageRepository extends CrudRepository<Image, Integer> {
    Image findByPublicId(String publicId);
    void deleteByUrl(@Param("url") String url);
}
