package paolooliviero.capstone.payloads;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record NewProdottoDTO (
        String nome,
        Double prezzoUnitario,
        Double volume,
        String categoria,
        List<NewProdottoMagazzinoDTO> prodottoMagazzino,
        List<NewMovimentoMagazzinoDTO> movimentoMagazzino,
        NewOrdineClienteDTO ordinecliente
) {
}

