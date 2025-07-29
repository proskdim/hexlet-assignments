package exercise.controller.users;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import exercise.model.Post;
import exercise.Data;

@RestController
@RequestMapping("/api")
public class PostsController {
    private static  List<Post> posts = Data.getPosts();

    @GetMapping("/users/{id}/posts")
    public ResponseEntity<List<Post>> index(@PathVariable Integer id) {
        var page = posts.stream()
                .filter(p -> p.getUserId() == id)
                .toList();

        return ResponseEntity.ok(page);
    }

    @PostMapping("/users/{id}/posts")
    public ResponseEntity<Post> create(@PathVariable Integer id, @RequestBody Post page) {
        var post = new Post();

        post.setUserId(id);
        post.setSlug(page.getSlug());
        post.setTitle(page.getTitle());
        post.setBody(page.getBody());

        posts.add(post);

        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }
}
