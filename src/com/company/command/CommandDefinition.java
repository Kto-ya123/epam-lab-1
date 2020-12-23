package com.company.command;

import com.company.model.dao.Role;

/**
 * CommandDefinition.
 *
 * @author Artsiom Mazhylouski
 */
public class CommandDefinition {
    private String name;
    private String description;
    private Role role;
    private String page;

    public CommandDefinition(String name, String description, Role role, String page) {
        this.name = name;
        this.description = description;
        this.role = role;
        this.page = page;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
}
