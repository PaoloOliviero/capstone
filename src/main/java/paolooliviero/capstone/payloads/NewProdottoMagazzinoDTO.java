package paolooliviero.capstone.payloads;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record NewProdottoMagazzinoDTO (
        Long id,
        Double quantitaDisponibile,
        LocalDate dataIngresso,
        Long prodottoId,
        Long magazzinoId

) {
}
