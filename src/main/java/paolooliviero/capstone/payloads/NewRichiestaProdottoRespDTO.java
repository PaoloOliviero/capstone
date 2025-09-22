package paolooliviero.capstone.payloads;

import java.time.LocalDate;

public record NewRichiestaProdottoRespDTO (
        long id,
        int quantitaRichiesta,
        LocalDate dataRichiesta,
        String motivazione,
        Long prodottoMagazzinoId,
        Long magazzinoId,
        Long richiestoDaId

){
}
