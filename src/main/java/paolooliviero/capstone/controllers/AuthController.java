package paolooliviero.capstone.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import paolooliviero.capstone.entities.Ruolo;
import paolooliviero.capstone.entities.Utente;
import paolooliviero.capstone.exceptions.ValidationException;
import paolooliviero.capstone.payloads.LoginDTO;
import paolooliviero.capstone.payloads.LoginRespDTO;
import paolooliviero.capstone.payloads.NewUtenteRespDTO;
import paolooliviero.capstone.payloads.UtenteDTO;
import paolooliviero.capstone.service.AuthService;
import paolooliviero.capstone.service.UtenteService;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private UtenteService utenteService;

    @PostMapping("/login")
    public LoginRespDTO login(@RequestBody LoginDTO body) {
        String accessToken = authService.checkCredentialsAndGenerateToken(body);
        Utente found = utenteService.findByEmail(body.email());
        List<String> ruoli = found.getRuoli().stream().map(Ruolo::getNome).toList();
        return new LoginRespDTO(accessToken, ruoli);
    }


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public NewUtenteRespDTO save(@RequestBody @Validated UtenteDTO payload, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            //validationResult.getFieldErrors().forEach(fieldError -> System.out.println(fieldError.getDefaultMessage()));
            throw new ValidationException(validationResult.getFieldErrors()
                    .stream().map(fieldError -> fieldError.getDefaultMessage()).toList());
        } else {
            Utente newUtente = this.utenteService.save(payload);
            return new NewUtenteRespDTO(newUtente.getId());
        }

    }

}

