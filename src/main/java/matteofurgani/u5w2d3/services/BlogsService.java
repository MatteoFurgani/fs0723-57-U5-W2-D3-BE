package matteofurgani.u5w2d3.services;


import matteofurgani.u5w2d3.entities.Author;
import matteofurgani.u5w2d3.entities.Blogpost;
import matteofurgani.u5w2d3.exceptions.BadRequestException;
import matteofurgani.u5w2d3.exceptions.NotFoundException;
import matteofurgani.u5w2d3.payloads.BlogPostPayload;
import matteofurgani.u5w2d3.payloads.NewBlogPostDTO;
import matteofurgani.u5w2d3.repositories.BlogPostDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

@Service
public class BlogsService {

    @Autowired
    private BlogPostDAO blogDAO;

    @Autowired
    private AuthorsService authorsService;

    //private final List<Blogpost> blogs = new ArrayList<>();

    public Blogpost save(NewBlogPostDTO body) {
        Author author = authorsService.findById(body.authorId());
        Blogpost newBlogPost = new Blogpost();
        newBlogPost.setReadingTime((int) body.readingTime());
        newBlogPost.setContent(body.content());
        newBlogPost.setTitle(body.title());
        newBlogPost.setAuthor(author);
        newBlogPost.setCategory(body.category());
        newBlogPost.setCover("http://picsum.photos/200/300");
        return blogDAO.save(newBlogPost);
    }

    public Page<Blogpost> getBlogs( int page, int size, String sort) {
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));

        return this.blogDAO.findAll(pageable);
    }

    public Blogpost findById(int id) {
        return this.blogDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
       /* Blogpost found = null;

        for (Blogpost blogpost : blogs) {
            if (blogpost.getId() == id)
                found = blogpost;
        }
        if (found == null)
            throw new NotFoundException(id);
        return found;*/
    }

    public void findByIdAndDelete(int id) {
        Blogpost found = this.findById(id);
        this.blogDAO.delete(found);
       /* ListIterator<Blogpost> iterator = this.blogs.listIterator();

        while (iterator.hasNext()) {
            Blogpost currentBlog = iterator.next();
            if (currentBlog.getId() == id) {
                iterator.remove();
            }
        }*/
    }

    public Blogpost findByIdAndUpdate(int id, Blogpost modifierBlog) {
        Blogpost found = this.findById(id);
        found.setCategory(modifierBlog.getCategory());
        found.setContent(modifierBlog.getContent());
        found.setCover(modifierBlog.getCover());
        found.setTitle(modifierBlog.getTitle());
        return this.blogDAO.save(found);
    }

   public List<Blogpost> findBlogsByAuthor(int authorId) {
       Author author = authorsService.findById(authorId);
       return blogDAO.findByAuthor(author);
   }
}
