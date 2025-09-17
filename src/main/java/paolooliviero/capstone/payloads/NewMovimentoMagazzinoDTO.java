package paolooliviero.capstone.payloads;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record NewMovimentoMagazzinoDTO(
        @NotNull(message = "La capacità occupata deve essere obbligatoria")
        Double quantity,
        @Valid
        NewProdottoMagazzinoDTO prodottoMagazzino,
        @Valid
        NewMagazzinoDTO magazzino,
        @Valid
        NewProdottoDTO prodotto,
        @Valid
        UtenteDTO utente
) {
}