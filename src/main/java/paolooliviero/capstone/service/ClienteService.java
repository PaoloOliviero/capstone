package paolooliviero.capstone.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import paolooliviero.capstone.entities.Cliente;
import paolooliviero.capstone.exceptions.NotFoundException;
import paolooliviero.capstone.payloads.NewClienteDTO;
import paolooliviero.capstone.repositories.ClienteRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente save (NewClienteDTO payload) {

        Cliente newCliente= new Cliente();
        newCliente.setRagioneSociale(payload.ragioneSociale());
        newCliente.setPartitaIva(payload.partitaIva());
        newCliente.setEmail(payload.email());
        newCliente.setDataInserimento(payload.dataInserimento());
        newCliente.setDataUltimoContatto(payload.dataUltimoContatto());
        newCliente.setFatturatoAnnuale(payload.fatturatoAnnuale());
        newCliente.setTelefonoContatto(payload.telefonoContatto());

        return clienteRepository.save(newCliente);
    }

    public Page<Cliente> findAll(int pageNumber, int pageSize, String sortBy) {
        if (pageSize > 50) pageSize = 50;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());
        return this.clienteRepository.findAll(pageable);
    }

    public Cliente findById(long Id) {
        return this.clienteRepository.findById(Id).orElseThrow(() -> new NotFoundException(Id));
    }

    public void findByIdAndDelete(long clienteId) {
        Cliente found = this.findById(clienteId);
        this.clienteRepository.delete(found);
    }

    public Cliente findByIdAndUpdate(long clienteId, NewClienteDTO payload) {
        Cliente found = this.findById(clienteId);

        found.setRagioneSociale(payload.ragioneSociale());
        found.setPartitaIva(payload.partitaIva());
        found.setEmail(payload.email());
        found.setDataInserimento(payload.dataInserimento());
        found.setDataUltimoContatto(payload.dataUltimoContatto());
        found.setFatturatoAnnuale(payload.fatturatoAnnuale());
        found.setTelefonoContatto(payload.telefonoContatto());
        return clienteRepository.save(found);
    }

    public List<Cliente> filterToFatturato(int fatturatoAnnuale) {
        List<Cliente> clientiFiltrati = clienteRepository.filterToFatturatoAnnuale(fatturatoAnnuale);
        return clientiFiltrati;
    }

    public List<Cliente> filterToDataInserimento(LocalDate dataInserimento) {
        List<Cliente> clientiFiltrati = clienteRepository.filterToDataInserimento(dataInserimento);
        return clientiFiltrati;
    }

    public List<Cliente> filterToDataUltimoContatto(LocalDate dataUltimoContatto) {
        List<Cliente> clientiFiltrati = clienteRepository.filterToDataUltimoContatto(dataUltimoContatto);
        return clientiFiltrati;
    }

    public List<Cliente> filterToRagioneSociale (String ragioneSociale) {
        List<Cliente> clientiFiltrati = clienteRepository.filterToRagioneSociale(ragioneSociale);
        return clientiFiltrati;
    }

}
