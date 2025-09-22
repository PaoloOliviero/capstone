package paolooliviero.capstone.payloads;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import paolooliviero.capstone.entities.MezzoDiTrasporto;
import paolooliviero.capstone.entities.OrdineCliente;

import java.time.LocalDate;
import java.util.List;

public record NewCaricoDTO(

        @NotEmpty(message = "obbligatoria!")
        String categoria,
        @NotEmpty(message = "obbligatorio!")
        String descrizione,
        @NotNull(message = "Il fatturato annuale è obbligatorio")
        Double volume,
        @NotEmpty(message = "Almeno un prodottomagazzino è obbligatorio")
        Long prodottoMagazzinoId,
        @Valid
        Long mezzoId,
        @Valid
        @NotEmpty
        List<NewOrdineClienteDTO> ordineCliente
) {
}