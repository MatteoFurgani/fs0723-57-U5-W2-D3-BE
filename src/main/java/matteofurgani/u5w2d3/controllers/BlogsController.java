package matteofurgani.u5w2d3.controllers;


import matteofurgani.u5w2d3.entities.Blogpost;
import matteofurgani.u5w2d3.payloads.BlogPostPayload;
import matteofurgani.u5w2d3.services.BlogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blogs")
public class BlogsController {
    @Autowired
    BlogsService blogsService;

    // 1. - POST http://localhost:3001/blogs (+ req.body)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // <-- 201
    public Blogpost saveBlog(@RequestBody BlogPostPayload body) {

        return this.blogsService.save(body);
    }

    // 2. - GET http://localhost:3001/blogs
    @GetMapping("")
    public Page<Blogpost> getBlogs(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10")int size,
                                   @RequestParam(defaultValue = "id") String sort){
        return this.blogsService.getBlogs(page, size, sort);


    }

    // 3. - GET http://localhost:3001/blogs/{id}
    @GetMapping("/{blogId}")
    public Blogpost findById(@PathVariable int blogId) {
        return this.blogsService.findById(blogId);
    }

    // 4. - PUT http://localhost:3001/blogs/{id} (+ req.body)
    @PutMapping("/{blogId}")
    public Blogpost findAndUpdate(@PathVariable int blogId, @RequestBody Blogpost body) {
        return this.blogsService.findByIdAndUpdate(blogId, body);
    }

    // 5. - DELETE http://localhost:3001/blogs/{id
    @DeleteMapping("/{blogId}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // <-- 204 NO CONTENT
    public void findAndDelete(@PathVariable int blogId) {
        blogsService.findByIdAndDelete(blogId);
    }
}
