package paolooliviero.capstone.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record NewMagazzinoDTO (

        @NotNull(message = "La capacità occupata deve essere obbligatoria")
        Double capacitaOccupata,
        @NotNull(message = "La capacità occupata deve essere obbligatoria")
        Double capacitaTotale,
        @NotEmpty
        List<NewProdottoMagazzinoDTO> prodottoMagazzino,
        @NotEmpty
        List<NewMovimentoMagazzinoDTO> movimentoMagazzino

){
}

