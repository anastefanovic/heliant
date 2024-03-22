package heliant.controller;


import heliant.dto.AuthenticationRequestDto;
import heliant.dto.AuthenticationResponseDto;
import heliant.dto.KorisnikDto;
import heliant.entity.Korisnik;
import heliant.security.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public AuthenticationResponseDto register(@RequestBody KorisnikDto korisnikDto) {
        Korisnik korisnik = new Korisnik(
                korisnikDto.getKorisnickoIme(),
                korisnikDto.getLozinka(),
                korisnikDto.getRola()
        );
        return authenticationService.register(korisnik);
    }

    @PostMapping("/authenticate")
    public AuthenticationResponseDto authenticate(@RequestBody AuthenticationRequestDto authenticationRequest) {
        return authenticationService.authenticate(authenticationRequest);
    }
}
