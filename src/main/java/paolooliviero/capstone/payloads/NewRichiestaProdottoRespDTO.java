package paolooliviero.capstone.payloads;

import java.time.LocalDate;

public record NewRichiestaProdottoRespDTO (
        long id,
        int quantitaRichiesta,
        LocalDate dataRichiesta,
        String motivazione,
        long prodottoMagazzinoId,
        long magazzinoId,
        long richiestoDaId

){
}
