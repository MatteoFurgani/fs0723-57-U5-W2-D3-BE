package matteofurgani.u5w2d3.services;


import matteofurgani.u5w2d3.entities.Author;
import matteofurgani.u5w2d3.exceptions.BadRequestException;
import matteofurgani.u5w2d3.exceptions.NotFoundException;
import matteofurgani.u5w2d3.repositories.AuthorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class AuthorsService {

    @Autowired
    private AuthorDAO authorDAO;

    public Author save(Author author) {
        this.authorDAO.findByEmail(author.getEmail()).ifPresent(
                author1 -> {
                    throw new BadRequestException("L'email " + author1.getEmail() + " è gia presente");
                }
        );

        author.setAvatar("https://ui-avatars.com/api/?name="+ author.getName() + "+" + author.getSurname());
        return this.authorDAO.save(author);
    }

    public Page<Author> getAuthors(int page, int size, String sortBy) {
        if(size > 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.authorDAO.findAll(pageable);
    }

    public Author findById(int id) {
        return this.authorDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
       /* Author found = null;

        for (Author author : authors) {
            if (author.getId() == id)
                found = author;
        }
        if (found == null)
            throw new NotFoundException(id);
        return found;*/
    }

    public void findByIdAndDelete(int id) {
        Author found = this.findById(id);
        this.authorDAO.delete(found);
        /*ListIterator<Author> iterator = this.authors.listIterator();

        while (iterator.hasNext()) {
            Author currentAuthor = iterator.next();
            if (currentAuthor.getId() == id) {
                iterator.remove();
            }
        }*/
    }

    public Author findByIdAndUpdate(int id, Author modifiedAuthor) {
        Author found = this.findById(id);
        found.setName(modifiedAuthor.getName());
        found.setSurname(modifiedAuthor.getSurname());
        found.setEmail(modifiedAuthor.getEmail());
        found.setAvatar("https://ui-avatars.com/api/?name=" + modifiedAuthor.getName() + "+" + modifiedAuthor.getSurname());
        return this.authorDAO.save(found);
        /*Author found = null;

        for (Author currentAuthor : authors) {
            if (currentAuthor.getId() == id) {
                found = currentAuthor;
                found.setName(author.getName());
                found.setSurname(author.getSurname());
                found.setId(id);
            }
        }
        if (found == null)
            throw new NotFoundException(id);
        return found;*/

    }
}
