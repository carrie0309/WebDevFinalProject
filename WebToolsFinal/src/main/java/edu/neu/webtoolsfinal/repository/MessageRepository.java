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
import org.springframework.transaction.annotation.Transactional;

@Repository
public class MessageRepository extends BaseRepository<Task> {

    @Transactional(readOnly = true)
    public List<Task> findByUsername(String username) {
        Criteria criteria = getSession().createCriteria(Task.class);
        criteria.add(Restrictions.eq("username", username));
        criteria.addOrder(Order.desc("messageDate"));
        final List list = criteria.list();
        return list;
    }

    @Transactional
    public void delete(List<Integer> messageIds) {
        if (!messageIds.isEmpty()) {
            getSession().createQuery("DELETE FROM Message m WHERE m.messageId IN (:ids)")
                    .setParameterList("ids", messageIds).executeUpdate();

        }
    }
}
