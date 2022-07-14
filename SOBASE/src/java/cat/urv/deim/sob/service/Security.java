/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.service;

import java.util.regex.Pattern;

/**
 *
 * @author Gerard
 */
public class Security {
    public static String stripEvilChars(String evilInput) {
        Pattern evilChars = Pattern.compile("[^a-zA-Z0-9 ]");
        return evilChars.matcher(evilInput).replaceAll("");
    }
}
