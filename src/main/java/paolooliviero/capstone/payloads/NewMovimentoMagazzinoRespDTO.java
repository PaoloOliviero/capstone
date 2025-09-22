package paolooliviero.capstone.payloads;

import java.time.LocalDate;

public record NewMovimentoMagazzinoRespDTO
        (Long Id,
         Long prodottoMagazzinoId,
         Long magazzinoId,
         Double quantity,
         String registratoDa,
         LocalDate dataRegistrazione,
         Long storicoPercorrenzaId,
         Long mezzoId,
         Long magazzinoEntrataId,
         Long magazzinoUscitaId,
         Long utenteId
        ) {
}
