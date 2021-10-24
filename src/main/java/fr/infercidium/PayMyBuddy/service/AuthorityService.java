package fr.infercidium.PayMyBuddy.service;

import fr.infercidium.PayMyBuddy.model.Authority;
import fr.infercidium.PayMyBuddy.repository.AuthorityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthorityService implements AuthorityI {

    /**
     * Instantiation of LOGGER in order to inform in console.
     */
    private static final Logger LOGGER
            = LoggerFactory.getLogger(AuthorityService.class);

    /**
     * Instantiation of AuthorityRepository.
     */
    @Autowired
    private AuthorityRepository authorityR;

    /**
     * find authority with name role.
     * @return authority found.
     */
    @Override
    public Authority getUser() {
        LOGGER.debug("'User' role found");
        return authorityR.findByNameIgnoreCase("user");
    }
}
