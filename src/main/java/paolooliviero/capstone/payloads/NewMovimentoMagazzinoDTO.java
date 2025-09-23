package paolooliviero.capstone.payloads;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record NewMovimentoMagazzinoDTO(
        Double quantity,
        Long magazzinoId,
        Long prodottoMagazzinoId,
        Long utenteId,
        LocalDate dataRegistrazione
) {
}