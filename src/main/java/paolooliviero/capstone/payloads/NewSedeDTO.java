package paolooliviero.capstone.payloads;

import jakarta.validation.constraints.NotEmpty;
import paolooliviero.capstone.enums.TipoSede;

public record NewSedeDTO (
        @NotEmpty(message = "La sede deve essere obbligatoria.Scegliere tra: LEGALE,OPERATIVA")
        TipoSede tipoSede
) {
}


