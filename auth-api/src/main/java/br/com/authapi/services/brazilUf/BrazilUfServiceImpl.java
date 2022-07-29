package br.com.authapi.services.brazilUf;

import br.com.authapi.entities.brazilUf.BrazilUf;
import br.com.authapi.exceptions.business.ObjectNotFoundException;
import br.com.authapi.repository.brazilUf.BrazilUfRepository;
import org.springframework.stereotype.Service;

@Service
public class BrazilUfServiceImpl implements BrazilUfService {

    private final BrazilUfRepository brazilUfRepository;

    public BrazilUfServiceImpl(BrazilUfRepository brazilUfRepository) {
        this.brazilUfRepository = brazilUfRepository;
    }

    @Override
    public BrazilUf findBrazilUfById(Long id) {
        return brazilUfRepository
                .findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(String.format("Uf for the given id %s was not found", id)));
    }
}
