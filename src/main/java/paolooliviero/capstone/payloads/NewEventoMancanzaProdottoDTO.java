package paolooliviero.capstone.payloads;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record NewEventoMancanzaProdottoDTO (

        @PastOrPresent(message = "obbligatorio!")
        LocalDate periodoIniziale,
        @PastOrPresent(message = "obbligatoria!")
        LocalDate periodoFinale,
        @NotNull(message = "L'ID dell'utente è obbligatorio")
        Long scopertodaId,
        @NotNull(message = "L'ID del prodotto in magazzino è obbligatorio")
        Long prodottoMagazzinoId

){
}