package paolooliviero.capstone.payloads;

public record NewProdottoRespDTO (long id,
                                  String nome,
                                  double prezzoUnitario,
                                  String categoria,
                                  Long ordineClienteId

){
}
