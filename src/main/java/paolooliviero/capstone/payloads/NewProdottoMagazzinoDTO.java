package paolooliviero.capstone.payloads;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record NewProdottoMagazzinoDTO (

        @NotNull
        Integer quantity,
        @NotNull
        LocalDate dataIngresso,
        @Valid
        NewProdottoDTO prodotto,
        @Valid
        NewMagazzinoDTO magazzino,
        @Valid
        NewProdottoMagazzinoDTO prodottoMagazzino
) {
}
