package paolooliviero.capstone.payloads;

import jakarta.validation.constraints.NotBlank;

public record NewTicketDTO(
        @NotBlank(message = "Il titolo è obbligatorio")
        String titolo,

        @NotBlank(message = "La descrizione è obbligatoria")
        String descrizione
) {}


