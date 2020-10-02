package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.Category;

import java.util.List;

public interface AdsCategories {
    long joinAdsToCategories(long adId, long categoryId);
    List<Category> getCategoriesByAdId(long adId);
    List<Ad> getAdsByCategoryId(long categoryId);
}
