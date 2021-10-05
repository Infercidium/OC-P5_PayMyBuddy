package fr.infercidium.PayMyBuddy.service;

import fr.infercidium.PayMyBuddy.model.Authority;
import fr.infercidium.PayMyBuddy.repository.AuthorityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AuthorityService implements AuthorityI {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorityService.class);

    private AuthorityRepository authorityR;

    public AuthorityService(final AuthorityRepository authorityRe) {
        this.authorityR = authorityRe;
    }


    @Override
    public Authority getUser() {
        return authorityR.findByNameIgnoreCase("user");
    }
}
