package paolooliviero.capstone.payloads;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record NewProdottoDTO (
        @NotEmpty(message = "obbligatorio!")
        String nome,
        @NotNull(message = "obbligatoria!")
        Double prezzoUnitario,
        @NotNull(message = "Weight obbligatoria")
        Double weight,
        @NotEmpty(message = "Categoria obbligatoria")
        String categoria,
        @Valid
        @NotEmpty(message = "Almeno un prodottomagazzino è obbligatorio")
        List<NewProdottoMagazzinoDTO> prodottoMagazzino,
        @Valid
        @NotEmpty(message = "Almeno un movimentomagazzino è obbligatorio")
        List<NewMovimentoMagazzinoDTO> movimentoMagazzino,
        @Valid
        NewOrdineClienteDTO ordinecliente
) {
}

