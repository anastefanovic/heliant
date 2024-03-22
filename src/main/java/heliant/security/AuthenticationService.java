package heliant.security;

import heliant.dto.AuthenticationRequestDto;
import heliant.dto.AuthenticationResponseDto;
import heliant.entity.Korisnik;
import heliant.repository.KorisnikRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final KorisnikRepository korisnikRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationResponseDto register(Korisnik newKorisnik){
        Korisnik korisnik = new Korisnik(
                newKorisnik.getKorisnickoIme(),
                passwordEncoder.encode(newKorisnik.getLozinka()),
                newKorisnik.getRola()
        );
        korisnikRepository.save(korisnik);
        String token = jwtService.generateToken(korisnik, generateExtraClaims(korisnik));
        return  new AuthenticationResponseDto(token);
    }

    public AuthenticationResponseDto authenticate(AuthenticationRequestDto authenticationRequest){
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                authenticationRequest.getKorisnickoIme(), authenticationRequest.getLozinka()
        );
        authenticationManager.authenticate(authToken);

        Korisnik korisnik = korisnikRepository.findByKorisnickoIme(authenticationRequest.getKorisnickoIme()).get();
        String token = jwtService.generateToken(korisnik, generateExtraClaims(korisnik));
        return new AuthenticationResponseDto(token);
    }

    private Map<String, Object> generateExtraClaims(Korisnik korisnik) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("role", korisnik.getRola().name());
        return extraClaims;
    }
}