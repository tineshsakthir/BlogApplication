package com.tinesh_blogs.BlogApplication;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
public class Blog {

  @Id
  @GeneratedValue
  private int id ;
  @Size(min=5 , message = "THe Size of the Heading should be atleast 5 character!!!")
  private String title ;
  private String username ;

  @Size(min=10 , message = "THe Size of the Heading should be atleast 10 character!!!")
  private String content ;
  @Lob
  @Column(columnDefinition = "MEDIUMBLOB")
  private String image ;
  private LocalDateTime dateOfCreation ;
  private LocalDateTime lastEdited ;

    public Blog() {
    }

    public Blog(int id, String title, String username, String content, String image, LocalDateTime dateOfCreation, LocalDateTime lastEdited) {
      this.id = id;
      this.title = title;
      this.username = username;
      this.content = content;
      this.image = image;
      this.dateOfCreation = dateOfCreation;
      this.lastEdited = lastEdited;
    }

    public int getId() {
      return id;
    }

    public void setId(int id) {
      this.id = id;
    }

    public String getTitle() {
      return title;
    }

    public void setTitle(String title) {
      this.title = title;
    }

    public String getUsername() {
      return username;
    }

    public void setUsername(String username) {
      this.username = username;
    }

    public String getContent() {
      return content;
    }

    public void setContent(String content) {
      this.content = content;
    }

    public String getImage() {
      return image;
    }

    public void setImage(String image) {
      this.image = image;
    }

    public LocalDateTime getDateOfCreation() {
      return dateOfCreation;
    }

    public void setDateOfCreation(LocalDateTime dateOfCreation) {
      this.dateOfCreation = dateOfCreation;
    }

    public LocalDateTime getLastEdited() {
      return lastEdited;
    }

    public void setLastEdited(LocalDateTime lastEdited) {
      this.lastEdited = lastEdited;
    }
}
   
