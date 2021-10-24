package fr.infercidium.PayMyBuddy.service;

import fr.infercidium.PayMyBuddy.model.Authority;

public interface AuthorityI {
    /**
     * find authority with name role.
     * @return authority found.
     */
    Authority getUser();
}
