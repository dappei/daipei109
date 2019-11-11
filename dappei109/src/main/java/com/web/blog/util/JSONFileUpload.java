package com.web.blog.util;

public class JSONFileUpload  {
 
    private String path;
    
    public JSONFileUpload() {
        
    }
    
    public JSONFileUpload(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
