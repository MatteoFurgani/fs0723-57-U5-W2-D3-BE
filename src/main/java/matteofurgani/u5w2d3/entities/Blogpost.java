package matteofurgani.u5w2d3.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name ="blogs")
@Getter
@Setter
@AllArgsConstructor
@ToString
public class Blogpost {
    @Id
    @GeneratedValue
    private int id;
    private String category;
    private String title;
    private String cover;
    private String content;
    private int readingTime;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
}
