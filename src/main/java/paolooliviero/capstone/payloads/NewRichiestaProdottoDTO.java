package paolooliviero.capstone.payloads;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record NewRichiestaProdottoDTO(

        long prodottoMagazzinoId,

        int quantitaRichiesta,

        LocalDate dataRichiesta,

        String motivazione,

        long richiestoDaId,

        long magazzinoId
) {
}
