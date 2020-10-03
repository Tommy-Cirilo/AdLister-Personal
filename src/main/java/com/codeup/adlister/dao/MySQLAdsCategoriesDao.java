package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.Category;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class MySQLAdsCategoriesDao extends MySQLDao implements AdsCategories {
    public MySQLAdsCategoriesDao(Config config) {
        super(config);
    }

    @Override
    public long joinAdsToCategories (long adId, long catId) {
        try {
            String insertQuery = "INSERT INTO ads_categories(ad_id, category_id) VALUES (?, ?)";
            PreparedStatement stmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, adId);
            stmt.setLong(2, catId);
            return (long) stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error in adding to ad_categories", e);
        }
    }

    @Override
    public List<Category> getCategoriesByAdId(long adId) {
        return null;
    }

    @Override
    public List<Ad> getAdsByCategoryId(long categoryId) {
        return null;
    }
}
