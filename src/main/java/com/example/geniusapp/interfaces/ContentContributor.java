package com.example.geniusapp.interfaces;

public interface ContentContributor {
    void contributeContent(Object content); // Can be Song, Album, etc.
    void editContent(Object content);
    void deleteContent(Object content);
}