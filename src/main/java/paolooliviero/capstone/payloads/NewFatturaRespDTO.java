package paolooliviero.capstone.payloads;

import java.time.LocalDate;

public record NewFatturaRespDTO(
        Long id,
        LocalDate data,
        Double importo,
        String statoFattura,
        Long clienteId
) {}


