package paolooliviero.capstone.payloads;

import java.time.LocalDateTime;

public record NewNotificaRespDTO(
        Long id,
        String titolo,
        String descrizione,
        boolean visualizzata,
        LocalDateTime data,
        String cliente,
        String utenteDestinatario
) {}

