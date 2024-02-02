package com.ExpenseApp.webApp.controller;

import com.ExpenseApp.webApp.model.Expense;
import com.ExpenseApp.webApp.model.Group;
import com.ExpenseApp.webApp.model.Member;
import com.ExpenseApp.webApp.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
@CrossOrigin(origins = "http://localhost:8081")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @GetMapping
    public ResponseEntity<List<Group>> getAllGroups() {
        List<Group> groups = groupService.getAllGroups();
        return ResponseEntity.ok(groups);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Group> getGroupById(@PathVariable Long id) {
        Group group = groupService.getGroupById(id);
        return ResponseEntity.ok(group);
    }

    @PostMapping
    public ResponseEntity<Group> createGroup(@RequestBody Group group) {
        try {
            Group createdGroup = groupService.createGroup(group);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdGroup);
        } catch (Exception e) {
            // Log the exception
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/{id}/members")
    public ResponseEntity<Group> addMemberToGroup(@PathVariable Long id, @RequestBody Member member) {
        try {
            Group updatedGroup = groupService.addMemberToGroup(id, member);
            return ResponseEntity.ok(updatedGroup);
        } catch (Exception e) {
            // Log the exception
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{id}/members/{memberId}")
    public ResponseEntity<Group> removeMemberFromGroup(@PathVariable Long id, @PathVariable Long memberId) {
        try {
            Group updatedGroup = groupService.removeMemberFromGroup(id, memberId);
            return ResponseEntity.ok(updatedGroup);
        } catch (Exception e) {
            // Log the exception
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/{id}/expenses")
    public ResponseEntity<Group> addExpenseToGroup(@PathVariable Long id, @RequestBody Expense expense) {
        try {
            Group updatedGroup = groupService.addExpenseToGroup(id, expense);
            return ResponseEntity.ok(updatedGroup);
        } catch (Exception e) {
            // Log the exception
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }
}
