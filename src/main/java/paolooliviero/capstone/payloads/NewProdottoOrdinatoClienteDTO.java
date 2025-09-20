package paolooliviero.capstone.payloads;

import jakarta.validation.constraints.NotNull;

public record NewProdottoOrdinatoClienteDTO (
        @NotNull Long clienteId,
        Long ordineClienteId,
        double quantita,
        Long prodottoId


        ){
}
