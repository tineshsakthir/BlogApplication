package com.tinesh_blogs.BlogApplication;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
public class BlogController {


  private BlogRepository blogRepository ;
  @Autowired
  public BlogController(BlogRepository blogRepository) {
    this.blogRepository = blogRepository;
  }

  @GetMapping("/")
  public String home(ModelMap modelMap){
    List<Blog> blogList = blogRepository.findAll() ;
    modelMap.put("blogList",blogList) ;
    return "home" ;
  }

  @GetMapping("/profile")
  public String profil(ModelMap modelMap){
    modelMap.put("name",getLoggedInUsername()) ;
    modelMap.put("count" , (long) blogRepository.findByUsername(getLoggedInUsername()).size()) ;
    return "profile" ;
  }

  @GetMapping("/edit-blogs")
  public String showMyBlogsToEdit(ModelMap modelMap){
    List<Blog> currentUserBlogs = blogRepository.findByUsername(getLoggedInUsername()) ;
    modelMap.put("currentUserBlogs",currentUserBlogs) ;
    return "myBlogsPage" ;
  }

  @GetMapping("/add-blog")
  public String redirectToAddNewBlogPage(ModelMap model){
    Blog blog = new Blog(0,"",getLoggedInUsername(),"","",null,null) ;
    model.put("blog", blog) ; 
    return "add" ; 
  }

  @PostMapping("/add-blog")
  public String addNewBlogToDb(@Valid Blog blog , @RequestParam("imageFile") MultipartFile imageFile) throws IOException {

    String filename = StringUtils.cleanPath(Objects.requireNonNull(imageFile.getOriginalFilename())) ;
    if(filename.contains("..")){
      System.out.println("Not Valid");
    }else{
      System.out.println(Base64.getEncoder().getClass());
      blog.setImage(Base64.getEncoder().encodeToString(imageFile.getBytes()));
    }
    blog.setUsername(getLoggedInUsername());
    blog.setDateOfCreation(LocalDateTime.now());
    blog.setLastEdited(LocalDateTime.now());
    blogRepository.save(blog) ;
    return "redirect:/";
  }


  @GetMapping("update-blog")
  public String toUpdatePage(@RequestParam("id") int blogId , ModelMap modelMap) {
    Optional<Blog> blog = blogRepository.findById(blogId) ;
    if(blog.isPresent()){
      modelMap.put("blog",blog.get()) ;
      return "update" ;
    }
    return "redirect:edit-blogs" ;
  }

  @PostMapping("update-blog")
  public String updateTheBlogInDb(@Valid Blog blog, @RequestParam("imageFile") MultipartFile imageFile) throws IOException {
    Optional<Blog> dbBlog = blogRepository.findById(blog.getId()) ;
    boolean edited = false ;
    if(dbBlog.isPresent()){
     Blog curBlog = dbBlog.get() ;
     curBlog.setUsername(getLoggedInUsername());
     if(!blog.getTitle().isEmpty() && !blog.getTitle().equals(curBlog.getTitle())){
       edited = true ;
       curBlog.setTitle(blog.getTitle());
     }
     if(!blog.getContent().isEmpty() && !blog.getContent().equals(curBlog.getContent()) ) {
       edited = true ;
         curBlog.setContent(blog.getContent());
     }
      if(!imageFile.isEmpty() ){
        edited = true ;
        String filename = StringUtils.cleanPath(Objects.requireNonNull(imageFile.getOriginalFilename())) ;
        if(filename.contains("..")){
          System.out.println("Not Valid");
        }else{
          curBlog.setImage(Base64.getEncoder().encodeToString(imageFile.getBytes()));
        }
      }
      if(edited) curBlog.setLastEdited(LocalDateTime.now());
     blogRepository.save(curBlog) ;
    }
    return "redirect:/edit-blogs" ;
  }


  @GetMapping("/delete-blog")
  public String deleteBlogById(@RequestParam("id") int id){
    blogRepository.deleteById(id);
    return "redirect:/edit-blogs" ;
  }

  public String getLoggedInUsername(){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication() ;
    return authentication.getName() ;
  }
}


