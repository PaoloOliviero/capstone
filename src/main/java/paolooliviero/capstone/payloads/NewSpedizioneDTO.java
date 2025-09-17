package paolooliviero.capstone.payloads;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record NewSpedizioneDTO (
        @NotEmpty(message = "obbligatorio!")
        String partenza,
        @NotEmpty(message = "obbligatorio!")
        String arrivo,
        @NotNull(message = "obbligatoria!")
        Double tempoPrevisto,
        @NotNull(message = "Weight obbligatoria")
        Double tempoEffettivo,
        @Valid
        NewMagazzinoDTO magazzino
) {
}

