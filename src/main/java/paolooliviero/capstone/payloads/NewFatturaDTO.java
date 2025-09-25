package paolooliviero.capstone.payloads;

import jakarta.validation.constraints.*;
import paolooliviero.capstone.entities.StatoFattura;

import java.time.LocalDate;

public record NewFatturaDTO(
        Double importo,
        LocalDate dataEmissione,
        Long id_cliente,
        Long id_statoFattura
) {}



