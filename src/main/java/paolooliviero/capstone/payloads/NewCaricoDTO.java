package paolooliviero.capstone.payloads;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.List;

public record NewCaricoDTO(

        String categoria,
        String descrizione,
        Double volume,

        @NotEmpty(message = "Almeno un prodotto deve essere associato al carico")
        List<@NotNull Long> prodottoMagazzinoIds,

        @NotNull(message = "Il mezzo di trasporto è obbligatorio")
        Long mezzoId,

        @Valid
        List<NewOrdineClienteDTO> ordineCliente

) {}