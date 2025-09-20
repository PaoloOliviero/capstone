package paolooliviero.capstone.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import paolooliviero.capstone.enums.StatoOrdine;

import java.time.LocalDate;
import java.util.List;

public record NewOrdineClienteDTO(
        @NotNull(message = "Lo stato dell'ordine è obbligatorio")
        StatoOrdine statoOrdine,

        @PastOrPresent(message = "La data dell'ordine non può essere futura")
        LocalDate dataOrdine,

        @NotEmpty(message = "L'indirizzo di spedizione è obbligatorio")
        @Size(min = 5, max = 100, message = "L'indirizzo deve essere tra 5 e 100 caratteri")
        String indirizzoSpedizione,

        @NotEmpty(message = "Il metodo di pagamento è obbligatorio")
        @Size(min = 3, max = 30, message = "Il metodo di pagamento deve essere tra 3 e 30 caratteri")
        String metodopagamento,

        @NotNull(message = "La fattura è obbligatoria")
        NewFatturaDTO fattura,

        @NotNull(message = "La spedizione è obbligatoria")
        NewSpedizioneDTO spedizione,

        @NotNull(message = "Il cliente è obbligatorio")
        NewClienteDTO cliente,

        @NotNull(message = "Il carico è obbligatorio")
        NewCaricoDTO carico,

        @NotEmpty(message = "La lista dei prodotti non può essere vuota")
        List<Long> prodottoIds,

        NewRichiestaProdottoDTO richiestaProdotto
) {
}
