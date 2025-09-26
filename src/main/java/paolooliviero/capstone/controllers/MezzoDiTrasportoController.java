package paolooliviero.capstone.controllers;

import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import paolooliviero.capstone.entities.MezzoDiTrasporto;
import paolooliviero.capstone.payloads.NewMezzoDiTrasportoDTO;
import paolooliviero.capstone.payloads.NewMezzoDiTrasportoRespDTO;
import paolooliviero.capstone.service.MezzoDiTrasportoService;

public class MezzoDiTrasportoController {

    @Autowired
    private MezzoDiTrasportoService mezzoDiTrasportoService;

    @GetMapping
    @PreAuthorize("hasAnyRole('USER_ADMIN', 'USER_OPERATORE', 'USER_COMMERCIALE')")
    public Page<MezzoDiTrasporto> findAll(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size,
                                          @RequestParam(defaultValue = "id") String sortBy) {
        return mezzoDiTrasportoService.findAll(page, size, sortBy);
    }

    @PostMapping("/creamezzoditrasporto")
    @PreAuthorize("hasAnyRole('USER_ADMIN', 'USER_OPERATORE', 'USER_COMMERCIALE')")
    @ResponseStatus(HttpStatus.CREATED)
    public NewMezzoDiTrasportoRespDTO save(@RequestBody @Validated NewMezzoDiTrasportoDTO payload, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            throw new ValidationException("Validazione fallita");
        }
        MezzoDiTrasporto newMezzoDiTrasporto = mezzoDiTrasportoService.save(payload);
        return new NewMezzoDiTrasportoRespDTO(newMezzoDiTrasporto.getId());
    }

    @GetMapping("/{mezzoitrasportoId}")
    @PreAuthorize("hasAnyRole('USER_ADMIN', 'USER_OPERATORE', 'USER_COMMERCIALE')")
    public MezzoDiTrasporto getById(@PathVariable long mezzoDiTrasportoId) {
        return mezzoDiTrasportoService.findById(mezzoDiTrasportoId);
    }

    @PutMapping("/{mezzoditrasportoId}")
    @PreAuthorize("hasAnyRole('USER_ADMIN', 'USER_OPERATORE', 'USER_COMMERCIALE')")
    public MezzoDiTrasporto getByIdAndUpdate(@PathVariable long mezzoDiTrasportoId, @RequestBody NewMezzoDiTrasportoDTO payload) {
        return this.mezzoDiTrasportoService.findByIdAndUpdate(mezzoDiTrasportoId, payload);
    }

    @DeleteMapping("/{clienteId}")
    @PreAuthorize("hasAnyRole('USER_ADMIN', 'USER_OPERATORE', 'USER_COMMERCIALE')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void getByIdAndDelete(@PathVariable long clienteId) {
        this.mezzoDiTrasportoService.findByIdAndDelete(clienteId);
    }
}

