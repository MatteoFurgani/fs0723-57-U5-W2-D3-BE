package matteofurgani.u5w2d3.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record NewAuthortDTO(
        @NotEmpty(message = "Il nome non può essere vuoto")
        @Size(min=3, max=15, message = "Il nome deve essere compreso tra i 3 e i 30caratteri ")
        String name,
        @NotEmpty(message = "Il cognome non può essere vuoto")
        @Size(min=3, max=15, message = "Il nome deve essere compreso tra i 3 e i 30caratteri ")
        String surname,
        @NotEmpty(message = "L'email non può essere vuota")
        @Email(message = "L'email inserita non è valida")
        String email,
        @NotEmpty(message = "La data di nascita non può essere vuota")
        String dateOfBirth,
        String avatar) {

}
