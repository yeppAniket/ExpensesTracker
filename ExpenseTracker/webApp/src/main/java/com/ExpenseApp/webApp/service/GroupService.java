// GroupService.java
package com.ExpenseApp.webApp.service;

import com.ExpenseApp.webApp.exception.ResourceNotFoundException;
import com.ExpenseApp.webApp.model.Group;
import com.ExpenseApp.webApp.model.Member;
import com.ExpenseApp.webApp.model.Expense;
import com.ExpenseApp.webApp.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    public Group getGroupById(Long id) {
        return groupRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Group not found with id " + id));
    }

    public Group createGroup(Group group) {
        if (group.getName() == null || group.getName().isEmpty()) {
            throw new IllegalArgumentException("Group name cannot be null or empty");
        }

        // Implement validation if needed
        return groupRepository.save(group);
    }

    public Group addMemberToGroup(Long groupId, Member member) {
        Group group = getGroupById(groupId);

        if (member == null) {
            throw new IllegalArgumentException("Member cannot be null");
        }

        // Implement validation if needed
        member.setGroup(group);
        group.getMembers().add(member);
        return groupRepository.save(group);
    }

    public Group removeMemberFromGroup(Long groupId, Long memberId) {
        Group group = getGroupById(groupId);
        // Implement validation if needed
        group.getMembers().removeIf(member -> member.getId().equals(memberId));
        return groupRepository.save(group);
    }

    public Group addExpenseToGroup(Long groupId, Expense expense) {
        Group group = getGroupById(groupId);

        if (expense == null) {
            throw new IllegalArgumentException("Expense cannot be null");
        }

        // Implement validation if needed
        expense.setGroup(group);
        group.getExpenses().add(expense);
        return groupRepository.save(group);
    }
}