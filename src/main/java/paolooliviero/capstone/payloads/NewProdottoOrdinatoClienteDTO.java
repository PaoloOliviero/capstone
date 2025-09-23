package paolooliviero.capstone.payloads;

import jakarta.validation.constraints.NotNull;

public record NewProdottoOrdinatoClienteDTO (
        Long clienteId,
        Long ordineClienteId,
        Double quantita,
        Long prodottoId


        ){
}
