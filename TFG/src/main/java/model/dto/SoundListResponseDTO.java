package model.dto;

import model.entities.SoundList;
import java.time.LocalDateTime;

public class SoundListResponseDTO {
    private Integer id;
    private String username;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    
    public SoundListResponseDTO() {
    }
    
    public SoundListResponseDTO(Integer id, String username, String name, String description, LocalDateTime createdAt) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
    }
    
    public SoundListResponseDTO(SoundList soundList) {
        this.id = soundList.getId();
        this.username = soundList.getUser().getUsername();
        this.name = soundList.getName();
        this.description = soundList.getDescription();
        this.createdAt = soundList.getCreatedAt();
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
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
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}