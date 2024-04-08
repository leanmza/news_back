package com.lean.news.service.interfaces;

import com.lean.news.model.entity.Image;

public interface IImageService {
    Image save(Image image);

    void delete(String id);

}
