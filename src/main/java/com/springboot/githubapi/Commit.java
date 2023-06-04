package com.springboot.githubapi;

public class Commit {
    private String sha;

    public Commit() {
    }

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }
}