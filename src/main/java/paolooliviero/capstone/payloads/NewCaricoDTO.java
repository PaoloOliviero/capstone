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

        String categoria,
        String descrizione,
        Double volume,
        Long prodottoMagazzinoId,
        Long mezzoId,
        @Valid
        List<NewOrdineClienteDTO> ordineCliente
) {
}