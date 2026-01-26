package model.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import model.entities.Review;
import model.entities.ReviewLike;
import model.entities.Users;
import model.service.ReviewLikeService;
import model.service.ReviewService;
import model.service.UsersService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/likes")

public class ReviewLikeRestController {
	@Autowired
    private ReviewLikeService likeSer;
    
    @Autowired
    private UsersService userSer;
    
    @Autowired
    private ReviewService reviewSer;
    
    @GetMapping("/review/{reviewId}")
    public List<ReviewLike> likes(@PathVariable Integer reviewId) {
        Review review = reviewSer.findById(reviewId);
        if (review != null) {
            return likeSer.findByReview(review);
        }
        return null;
    }
    
    @GetMapping("/user/{username}")
    public List<ReviewLike> likesByUser(@PathVariable String username) {
        Users user = userSer.findByUserName(username);
        if (user != null) {
            return likeSer.findByUser(user);
        }
        return null;
    }
    
    @GetMapping("/count/{reviewId}")
    public long countLikes(@PathVariable Integer reviewId) {
        Review review = reviewSer.findById(reviewId);
        if (review != null) {
            return likeSer.countByReview(review);
        }
        return 0;
    }
    
    @GetMapping("/check")
    public boolean likeados(@RequestParam Integer userId, @RequestParam Integer reviewId) {
        
        Users user = userSer.findById(userId);
        Review review = reviewSer.findById(reviewId);
        
        if (user != null && review != null) {
            return likeSer.likeado(user, review);
        }
        return false;
    }
    
    @PostMapping("/toggle")
    public int toggleLike(@RequestParam Integer userId,@RequestParam Integer reviewId) {
        
        Users user = userSer.findById(userId);
        Review review = reviewSer.findById(reviewId);
        
        if (user == null || review == null) {
            return -2; // Usuario o review no encontrado
        }
        
        return likeSer.toggleLike(user, review);
    }

}
