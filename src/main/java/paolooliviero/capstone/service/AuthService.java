package paolooliviero.capstone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import paolooliviero.capstone.entities.Utente;
import paolooliviero.capstone.exceptions.UnauthorizedException;
import paolooliviero.capstone.payloads.LoginDTO;
import paolooliviero.capstone.security.JWTTools;

@Service
public class AuthService {
    @Autowired
    private UtenteService utenteService;

    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private PasswordEncoder bcrypt;

    public String checkCredentialsAndGenerateToken(LoginDTO body) {
        Utente found = this.utenteService.findByEmail(body.email());

        if (bcrypt.matches(body.password(), found.getPassword())) {
            String accessToken = jwtTools.createToken(found);
            return accessToken;
        } else {
            throw new UnauthorizedException("Credenziali errate!");
        }
    }
}
