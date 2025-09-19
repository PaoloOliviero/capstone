package paolooliviero.capstone.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record NewAutistaDTO(

        @NotEmpty(message = "Il nome è obbligatorio")
        String nome,

        @NotEmpty(message = "Il cognome è obbligatorio")
        String cognome,

        @NotEmpty(message = "Il numero di telefono è obbligatorio")
        String telefono

) {
}
