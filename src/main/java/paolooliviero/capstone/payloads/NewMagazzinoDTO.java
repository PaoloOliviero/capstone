package paolooliviero.capstone.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record NewMagazzinoDTO (

        @NotNull(message = "La capacità occupata deve essere obbligatoria")
        Double capacitàOccupata,
        @NotEmpty(message = "Almeno un prodottomagazzino è obbligatorio")
        List<NewProdottoMagazzinoDTO> prodottoMagazzino,
        @NotEmpty(message = "Almeno un prodottomagazzino è obbligatorio")
        List<NewMovimentoMagazzinoDTO> movimentoMagazzino
){
}

