package paolooliviero.capstone.payloads;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record NewRichiestaProdottoDTO(

        @NotNull(message = "Il prodotto magazzino è obbligatorio")
        Long prodottoMagazzinoId,

        @Min(value = 1, message = "La quantità richiesta deve essere almeno 1")
        int quantitaRichiesta,

        @PastOrPresent(message = "La data di richiesta non può essere futura")
        LocalDate dataRichiesta,

        @NotEmpty(message = "La motivazione è obbligatoria")
        @Size(min = 5, max = 200, message = "La motivazione deve essere tra 5 e 200 caratteri")
        String motivazione,

        @NotNull(message = "Il richiedente è obbligatorio")
        Long richiestoDaId,

        NewMovimentoMagazzinoDTO movimentoMagazzino,

        Long mezzoDiTrasportoId,

        Long magazzinoId
) {
}
