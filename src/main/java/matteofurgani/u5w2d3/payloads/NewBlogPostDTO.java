package matteofurgani.u5w2d3.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record NewBlogPostDTO(
        @NotEmpty(message = "Il titolo non può essere vuoto")
        @Size(min=3, max=30, message = "Il titolo deve essere compreso tra i 3 e i 30caratteri ")
        String title,
        @NotEmpty(message = "Il contunuto non può essere vuoto")
        @Size(min=3, message = "Il contenuto deve essere compreso tra i 3 e i 30caratteri ")
        String content,
        @NotEmpty(message = "La categoria non può essere vuoto")
        @Size(min=3, max=20, message = "La categoria deve essere compreso tra i 3 e i 30caratteri ")
        String category,
        @NotEmpty(message = "Il tempo di lettura non può essere vuoto")
        double readingTime,

        @NotEmpty(message = "L'id dell'autore non può essere vuoto")
        int authorId) {

}
