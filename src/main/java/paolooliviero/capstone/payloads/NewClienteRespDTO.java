package paolooliviero.capstone.payloads;

import java.time.LocalDate;

public record NewClienteRespDTO (
        Long id,
        String ragioneSociale,
        String partitaIva,
        String email,
        LocalDate dataInserimento,
        LocalDate dataUltimoContatto,
        Double fatturatoAnnuale,
        String telefonoContatto
) {

}