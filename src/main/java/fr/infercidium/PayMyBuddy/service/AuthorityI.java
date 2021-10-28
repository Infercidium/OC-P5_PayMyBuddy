package fr.infercidium.PayMyBuddy.service;

import fr.infercidium.PayMyBuddy.model.Authority;

public interface AuthorityI {

    /**
     * find authority with name role.
     * @param name of role.
     * @return authority found.
     */
    Authority getRole(String name);
}
