/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.webtoolsfinal.repository;

import edu.neu.webtoolsfinal.entity.Task;
import edu.neu.webtoolsfinal.repository.core.BaseRepository;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class TaskRepository extends BaseRepository<Task> {

    public List<Task> viewTasksbyUserName(String username) {
        Criteria criteria = getSession().createCriteria(Task.class);
        criteria.add(Restrictions.eq("username", username));
        criteria.addOrder(Order.desc("dateAdded"));
        final List list = criteria.list();
        return list;
    }

}
