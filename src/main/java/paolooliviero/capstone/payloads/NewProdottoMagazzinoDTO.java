package paolooliviero.capstone.payloads;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record NewProdottoMagazzinoDTO (
        Long id,
        @NotNull
        Double quantitaDisponibile,
        @NotNull
        LocalDate dataIngresso,
        @NotNull Long prodottoId,
        @NotNull Long magazzinoId

) {
}
