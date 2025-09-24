package paolooliviero.capstone.payloads;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import paolooliviero.capstone.enums.StatoOrdine;

import java.time.LocalDate;
import java.util.List;

public record NewOrdineClienteDTO(

        StatoOrdine statoOrdine,

        @PastOrPresent(message = "La data dell'ordine non può essere futura")
        LocalDate dataOrdine,

        @Size(min = 5, max = 100, message = "L'indirizzo deve essere tra 5 e 100 caratteri")
        String indirizzoSpedizione,

        @Size(min = 3, max = 30, message = "Il metodo di pagamento deve essere tra 3 e 30 caratteri")
        String metodopagamento,

        @Valid
        NewFatturaDTO fattura,

        @Valid
        NewSpedizioneDTO spedizione,

        @Valid
        NewClienteDTO cliente,

        @Valid
        NewCaricoDTO carico,

        @Valid
        List<Long> prodottoIds,

        NewRichiestaProdottoDTO richiestaProdotto
) {
}
