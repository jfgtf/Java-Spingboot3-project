package com.springboot.githubapi;

import java.util.List;

public class Repository {
    private String name;
    private String ownerLogin;
    private List<Branch> branches;
    private Boolean fork;

    public Repository() {
    }

    public String getName() {
        return name;
    }

    public Boolean getFork() {
        return fork;
    }

    public void setFork(Boolean fork) {
        this.fork = fork;
    }

    public String getOwnerLogin() {
        return ownerLogin;
    }

    public List<Branch> getBranches() {
        return branches;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOwnerLogin(String ownerLogin) {
        this.ownerLogin = ownerLogin;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }
}