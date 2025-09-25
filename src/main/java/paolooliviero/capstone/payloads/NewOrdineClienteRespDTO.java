package paolooliviero.capstone.payloads;

import paolooliviero.capstone.enums.StatoOrdine;

import java.time.LocalDate;

public record NewOrdineClienteRespDTO (long id,
                                       LocalDate dataOrdine,
                                       StatoOrdine statoOrdine,
                                       String ragioneSociale,
                                       Double importoFattura,
                                       Long segmentoId
)
{
}
