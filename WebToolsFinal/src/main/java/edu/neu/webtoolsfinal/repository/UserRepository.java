/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.webtoolsfinal.repository;

import edu.neu.webtoolsfinal.entity.User;
import edu.neu.webtoolsfinal.repository.core.BaseRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository extends BaseRepository<User> {

    @Transactional
    public User login(String username, String password) {
        Criteria criteria = getSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("username", username));
        criteria.add(Restrictions.eq("password", password));

        User user = (User) criteria.uniqueResult();

        return user;
    }

    public User findByUsername(String username) {
        Criteria criteria = getSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("username", username));
        User user = (User) criteria.uniqueResult();
        return user;
    }

    public List<User> quickSearch(String gender, String seekingGender, Integer minAge, Integer maxAge, String country, String state, String city) {
        Criteria criteria = getSession().createCriteria(User.class);
        if (gender != null && !"Any".equals(gender)) {
            criteria.add(Restrictions.eq("gender", gender));
        }

        if (seekingGender != null && !"Any".equals(seekingGender)) {
            criteria.add(Restrictions.eq("seekingGender", seekingGender));
        }

        if (minAge != null && maxAge != null) {
            criteria.add(Restrictions.between("age", minAge, maxAge));
        }

        if (country != null && !"Any".equals(country)) {
            criteria.add(Restrictions.eq("country", country));
        }

        if (state != null && !"".equals(state)) {
            criteria.add(Restrictions.eq("state", state));
        }

        if (city != null && !"".equals(city)) {
            criteria.add(Restrictions.eq("city", city));
        }

        return criteria.list();
    }

    public User findByUserId(int userId) {
        Criteria criteria = getSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("userId", userId));
        User user = (User) criteria.uniqueResult();
        return user;
    }
    
    
     

   
}
