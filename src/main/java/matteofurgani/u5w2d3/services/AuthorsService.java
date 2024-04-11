package matteofurgani.u5w2d3.services;


import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import matteofurgani.u5w2d3.entities.Author;
import matteofurgani.u5w2d3.exceptions.BadRequestException;
import matteofurgani.u5w2d3.exceptions.NotFoundException;
import matteofurgani.u5w2d3.payloads.NewAuthortDTO;
import matteofurgani.u5w2d3.repositories.AuthorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class AuthorsService {

    @Autowired
    private AuthorDAO authorDAO;

    @Autowired
    private Cloudinary cloudinaryUploader;

    public Author save(NewAuthortDTO body) {
        this.authorDAO.findByEmail(body.email()).ifPresent(
                author1 -> {
                    throw new BadRequestException("L'email " + author1.getEmail() + " è gia presente");
                }
        );

       Author newAuthor = new Author(body.name(),body.surname(),body.email(),body.dateOfBirth(),
               "https://ui-avatars.com/api/?name="+ body.name() + "+" + body.surname());
       return this.authorDAO.save(newAuthor);
    }

    public Page<Author> getAuthors(int page, int size, String sortBy) {
        if(size > 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.authorDAO.findAll(pageable);
    }

    public Author findById(int id) {
        return this.authorDAO.findById(id).orElseThrow(() -> new NotFoundException(id));

    }

    public void findByIdAndDelete(int id) {
        Author found = this.findById(id);
        this.authorDAO.delete(found);

    }

    public Author findByIdAndUpdate(int id, Author modifiedAuthor) {
        Author found = this.findById(id);
        found.setName(modifiedAuthor.getName());
        found.setSurname(modifiedAuthor.getSurname());
        found.setEmail(modifiedAuthor.getEmail());
        found.setAvatar("https://ui-avatars.com/api/?name=" + modifiedAuthor.getName() + "+" + modifiedAuthor.getSurname());
        return this.authorDAO.save(found);

    }

    public String uploadImage(MultipartFile image) throws IOException {
        String url = (String) cloudinaryUploader.uploader().upload(image.getBytes(), ObjectUtils.emptyMap()).get("url");
        return url;
    }
}
