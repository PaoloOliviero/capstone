package paolooliviero.capstone.payloads;

import java.util.List;
import java.util.Set;

public record UtenteDTO(Long id, String email, String nome, String cognome, String password,  List<String> ruoli)

{}


