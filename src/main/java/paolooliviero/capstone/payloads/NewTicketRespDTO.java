package paolooliviero.capstone.payloads;

import java.time.LocalDateTime;

public record NewTicketRespDTO (
        Long id,
        String titolo,
        String descrizione,
        LocalDateTime dataCreazione,
        Long ordineClienteId
){
}
