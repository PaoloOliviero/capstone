package paolooliviero.capstone.controllers;

import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import paolooliviero.capstone.entities.Segmento;
import paolooliviero.capstone.enums.TipologiaSegmento;
import paolooliviero.capstone.payloads.NewSegmentoDTO;
import paolooliviero.capstone.payloads.NewSegmentoRespDTO;
import paolooliviero.capstone.service.SegmentoService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/segmenti")
public class SegmentoController {

    @Autowired
    private SegmentoService segmentoService;

    @GetMapping
    public Map<String, Object> findAll(@RequestParam int page,
                                       @RequestParam int size,
                                       @RequestParam String sortBy) {
        Page<Segmento> pagina = segmentoService.findAll(page, size, sortBy);

        Map<String, Object> response = new HashMap<>();
        response.put("content", pagina.getContent());
        response.put("totalElements", pagina.getTotalElements());
        response.put("totalPages", pagina.getTotalPages());
        response.put("pageNumber", pagina.getNumber());
        response.put("pageSize", pagina.getSize());

        return response;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NewSegmentoRespDTO save(@RequestBody @Validated NewSegmentoDTO payload, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            throw new ValidationException("Errore validazione segmento");
        } else {
            Segmento newSegmento = segmentoService.save(payload);
            return new NewSegmentoRespDTO(newSegmento.getId(), newSegmento.getNome(), newSegmento.getTipologiaSegmento());
        }
    }

    @GetMapping("/{segmentoId}")
    public Segmento getById(@PathVariable long segmentoId) {
        return segmentoService.findById(segmentoId);
    }

    @PutMapping("/{segmentoId}")
    public Segmento getByIdAndUpdate(@PathVariable long segmentoId, @RequestBody NewSegmentoDTO payload) {
        return segmentoService.findByIdAndUpdate(segmentoId, payload);
    }

    @DeleteMapping("/{segmentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void getByIdAndDelete(@PathVariable long segmentoId) {
        segmentoService.findByIdAndDelete(segmentoId);
    }

    @GetMapping("/filtro/tipologia")
    public List<Segmento> filterByTipologia(@RequestParam TipologiaSegmento tipoSegmento) {
        return segmentoService.filterByTipologia(tipoSegmento);
    }
}