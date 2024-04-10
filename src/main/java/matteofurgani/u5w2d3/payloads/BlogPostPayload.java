package matteofurgani.u5w2d3.payloads;

import lombok.Getter;

@Getter
public class BlogPostPayload {
    private int authorId;
    private String category;
    private String content;
    private double readingTime;
    private String title;
}
