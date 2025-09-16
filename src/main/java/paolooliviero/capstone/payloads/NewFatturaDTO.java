package paolooliviero.capstone.payloads;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record NewFatturaDTO(

        @PastOrPresent(message = "data non può essere futura")
        LocalDate data,
        @Positive(message = "L'importo deve essere positivo")
        double importo,
        @NotNull(message = "Il cliente è obbligatorio")
        long id_cliente,
        @NotNull(message = "Lo stato della fattura è obbligatorio")
        long id_statoFattura
)
{}