package paolooliviero.capstone.payloads;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record NewProdottoDTO (
        Long id,
        String nome,
        Double prezzoUnitario,
        String categoria,
        Long ordineClienteId
) {
}

