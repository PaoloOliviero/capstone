package paolooliviero.capstone.payloads;

import java.time.LocalDate;

public record NewProdottoMagazzinoRespDTO (Long id,
                                           Double quantitaDisponibile,
                                           LocalDate dataIngresso,
                                           Long prodottoId,
                                           Long magazzinoId

) {
}
