package com.chat.service;

import com.chat.modele.Salon;
import com.chat.util.DataException;

/**
 * @author Amaia Nazábal
 * @version 1.0
 * @since 1.0 11/30/16.
 */
public interface GestionSalon {

    void addSalon(String name) throws DataException;

    Salon getSalonByName(String salon) throws DataException;

    void removeSalon(String name) throws DataException;
}
