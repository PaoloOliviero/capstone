package paolooliviero.capstone.controllers;

import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import paolooliviero.capstone.entities.Carico;
import paolooliviero.capstone.entities.ProdottoMagazzino;
import paolooliviero.capstone.payloads.NewCaricoDTO;
import paolooliviero.capstone.payloads.NewCaricoRespDTO;
import paolooliviero.capstone.service.CaricoService;

import java.util.List;


@RestController
@RequestMapping("/carichi")
public class CaricoController {

    @Autowired
    private CaricoService caricoService;

    @GetMapping
//    @PreAuthorize("hasAuthority('')")
    public Page<Carico> findAll(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "10") int size,
                                @RequestParam(defaultValue = "id") String sortBy) {
        return caricoService.findAll(page, size, sortBy);
    }

    @PostMapping("/creacarico")
    @ResponseStatus(HttpStatus.CREATED)
    public NewCaricoRespDTO save(@RequestBody @Validated NewCaricoDTO payload, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            throw new ValidationException("Validazione fallita");
        }

        Carico newCarico = caricoService.save(payload);

        List<Long> prodottiIds = newCarico.getProdottoMagazzino() == null
                ? List.of()
                : newCarico.getProdottoMagazzino().stream()
                .map(ProdottoMagazzino::getId)
                .toList();

        return new NewCaricoRespDTO(
                newCarico.getId(),
                newCarico.getCategoria(),
                newCarico.getDescrizione(),
                newCarico.getVolume(),
                newCarico.getMezzoDiTrasporto() != null ? newCarico.getMezzoDiTrasporto().getId() : null,
                prodottiIds
        );
    }

    @GetMapping("/{caricoId}")
//    @PreAuthorize("hasAuthority('')")
    public Carico getById(@PathVariable long caricoId) {
        return caricoService.findById(caricoId);
    }

    @PutMapping("/{caricoId}")
//    @PreAuthorize("hasAuthority('ADMIN')")
    public Carico getByIdAndUpdate(@PathVariable long caricoId, @RequestBody NewCaricoDTO payload) {
        return this.caricoService.findByIdAndUpdate(caricoId, payload);
    }

    @DeleteMapping("/{caricoId}")
//    @PreAuthorize("hasAuthority('')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void getByIdAndDelete(@PathVariable long caricoId) {
        this.caricoService.findByIdAndDelete(caricoId);
    }

    @GetMapping("/filtracategoria")
    public List<Carico> filtroCategoria(@RequestParam String categoria) {
        return caricoService.filtroCategoria(categoria);
    }

    @GetMapping("/filtradescrizione")
    public List<Carico> filtroDescrizione(@RequestParam String descrizione) {
        return caricoService.filtroDescrizione(descrizione);
    }

    @GetMapping("/filtramezzo")
    public List<Carico> filtroMezzo(@RequestParam Long mezzoId) {
        return caricoService.filtroMezzo(mezzoId);
    }

    @GetMapping("/con-relazioni")
    public List<Carico> getAllConRelazioni() {
        return caricoService.findAllConRelazioni();
    }

    @GetMapping("/con-relazionidto")
    public List<NewCaricoRespDTO> getAllConRelazioniDTO() {
        return caricoService.findAllConRelazioniDTO();
    }

}
