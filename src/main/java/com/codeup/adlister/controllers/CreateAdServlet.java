package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.Category;
import com.codeup.adlister.models.User;
import com.codeup.adlister.util.Validate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@WebServlet(name = "controllers.CreateAdServlet", urlPatterns = "/ads/create")
public class CreateAdServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null) {
            request.getSession().setAttribute("redirect", "/ads/create");
            response.sendRedirect("/login");
            return;
        }
        List<Category> categories = DaoFactory.getCategoriesDao().getAllCategories();
        request.getSession().setAttribute("categories",categories);
        request.getRequestDispatcher("/WEB-INF/ads/create.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = (User) request.getSession().getAttribute("user");
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        List<Category> categoryList = (List<Category>) request.getSession().getAttribute("categories");
        String[] categorySelects = request.getParameterValues("categories");
        ArrayList<Category> categories = new ArrayList<>();
        for(String select : categorySelects) {
            for(Category category : categoryList) {
                if(category.getId() == Long.parseLong(select)) {
                    categories.add(category);
                }
            }
        }

        HashMap<String, Boolean> errorList = Validate.getErrorList(title, description);

        if(!Validate.checkForErrors(errorList)) {
            request.getSession().setAttribute("title",title);
            request.getSession().setAttribute("description",description);
            if(!errorList.get("titleNotEmpty"))
                request.getSession().removeAttribute("title");
            if(!errorList.get("descriptionNotEmpty"))
                request.getSession().removeAttribute("description");
            response.sendRedirect("/ads/create");
            return;
        }
        request.getSession().removeAttribute("title");
        request.getSession().removeAttribute("description");

        Ad ad = new Ad(
                user.getId(),
                request.getParameter("title"),
                request.getParameter("description"),
                categories
        );
        long adId = DaoFactory.getAdsDao().insert(ad);
        for (Category category : categories) {
            DaoFactory.getAdsCategoriesDao().joinAdsToCategories(adId, category.getId());
        }
        response.sendRedirect("/ads");
    }
}
