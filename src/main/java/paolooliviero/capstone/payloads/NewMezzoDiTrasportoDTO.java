package paolooliviero.capstone.payloads;

import jakarta.validation.constraints.NotNull;
import paolooliviero.capstone.enums.StatoMezzo;

import java.util.List;

public record NewMezzoDiTrasportoDTO(

        @NotNull(message = "La capienza massima è obbligatoria")
        Integer capienzaMassima,

        @NotNull(message = "Lo stato del mezzo è obbligatorio")
        StatoMezzo statoMezzo,

        @NotNull(message = "L'autista è obbligatorio")
        Long autistaId,

        @NotNull(message = "Il carico associato è obbligatorio")
        Long caricoId,

        List<Long> movimentoMagazzinoIds

) {
}
