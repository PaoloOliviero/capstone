package paolooliviero.capstone.payloads;

public record NewStoricoPercorrenzaDTO(
        Long id,
        Long spedizioneId,
        Long mezzoId,
        String nomeAutista,
        Double tempoEffettivoTratta,
        Long magazzinoEntrataId,
        Long magazzinoUscitaId
) {}
