package paolooliviero.capstone.payloads;

import jakarta.validation.constraints.*;
import paolooliviero.capstone.enums.TipologiaCliente;

import java.time.LocalDate;

public record NewClienteDTO (

        @NotEmpty(message = "obbligatorio!")
        @Size(min = 2, max = 40, message = "Il nome deve essere di lunghezza compresa tra 2 e 40")
        String ragioneSociale,
        @NotEmpty(message = "obbligatoria!")
        @Size(min = 2, max = 40, message = "Il cognome deve essere di lunghezza compresa tra 2 e 40")
        String partitaIva,
        @NotEmpty(message = "Email obbligatoria")
        @Email(message = "Formato email non valido")
        String email,
        @PastOrPresent(message = "dataInserimento non può essere futura")
        LocalDate dataInserimento,
        @PastOrPresent(message = "dataUltimoContatto non può essere futura")
        LocalDate dataUltimoContatto,
        @NotNull(message = "Il fatturato annuale è obbligatorio")
        Double fatturatoAnnuale,
        @NotEmpty(message = "PEC obbligatoria")
        @Email(message = "Formato PEC non valido")
        String pec,
        @NotEmpty(message = "La m è obbligatoria!")
        @Size(min = 4)
        String telefono,
        @NotEmpty(message = "Email contatto obbligatoria")
        @Email(message = "Formato email contatto non valido")
        String emailContatto,
        @NotEmpty(message = "La m è obbligatoria!")
        @Size(min = 4)
        String nomeContatto,
        @NotEmpty(message = "La m è obbligatoria!")
        @Size(min = 4)
        String cognomeContatto,
        @NotEmpty(message = "La m è obbligatoria!")
        @Size(min = 4)
        String telefonoContatto,
        @NotEmpty(message = "La tipologia è obbligatoria!")
        @Size(min = 4)
        String logoAziendale,

        TipologiaCliente tipologiaCliente)
{}

