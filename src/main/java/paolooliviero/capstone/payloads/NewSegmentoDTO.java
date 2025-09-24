package paolooliviero.capstone.payloads;

import jakarta.validation.constraints.NotBlank;
import paolooliviero.capstone.enums.TipologiaSegmento;

public record NewSegmentoDTO (

    String nome,

    String criterio,

    TipologiaSegmento tipoSegmento

) {
}
