package paolooliviero.capstone.payloads;

import jakarta.validation.constraints.NotNull;

public record NewStatoFatturaDTO(
        @NotNull(message = "il nome deve essere obbligatorio")
        String nome
) {
}


