package org.af.jhlir.call;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang.ArrayUtils;

/*
  A syntactically valid name consists of letters, numbers and the
  dot or underline characters and starts with a letter or the dot
  not followed by a number.
  The definition of a _letter_ depends on the current locale, but
  only ASCII digits are considered to be digits.

  The reserved words in R's parser are

  'if' 'else' 'repeat' 'while' 'function' 'for' 'in' 'next' 'break'
  'TRUE' 'FALSE' 'NULL' 'Inf' 'NaN' 'NA' 'NA_integer_' 'NA_real_'
  'NA_complex_' 'NA_character_'

  '...' and '..1', '..2' etc, which are used to refer to arguments
  passed down from an enclosing function.
*/
public class RLegalName {
    private final String name;

    public static final String[] reserved = {"if", "else", "repeat", "while", "function", "for", "in", "next", "break",
            "TRUE", "FALSE", "NULL", "Inf", "NaN", "NA", "NA_integer_", "NA_real_", "NA_complex_", "NA_character_"};

    public RLegalName(String name) throws RIllegalNameException {
        if (isPassedArg(name))
            throw new RIllegalNameException(name + " is a reserved R expression. You are not allowed to use '...' or '..' followed by a number!");
        if (isReserved(name))
            throw new RIllegalNameException(name + " is a reserved R word! ");
        if (!isLegal(name))
            throw new RIllegalNameException(name + " is an illegal R name. A valid name consists of letters, numbers and the" +
                    "  dot or underline characters and starts with a letter or the dot" +
                    "  not followed by a number.");
        this.name = name;
    }

    public static RLegalName makeRLegalNameUnchecked(String name) {
        try {
            return new RLegalName(name);
        } catch (RIllegalNameException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static List<RLegalName> makeRLegalNamesUnchecked(List<String> names) {
        List<RLegalName> result = new ArrayList<RLegalName>();
        for (String s : names)
            result.add(RLegalName.makeRLegalNameUnchecked(s));
        return result;
    }

    public static List<RLegalName> makeRLegalNamesUnchecked(String[] names) {
        return makeRLegalNamesUnchecked(Arrays.asList(names));
    }


    public String getName() {
        return name;
    }

    public static boolean isLegal(String name) {
        Pattern pattern = Pattern.compile("([a-zA-Z]|\\.[a-zA-Z])[\\w\\.]*");
        return !isReserved(name) && pattern.matcher(name).matches();
    }

    public static boolean isReserved(String name) {
        return ArrayUtils.contains(reserved, name) || isPassedArg(name);
    }

    public static boolean isPassedArg(String name) {
        Pattern pattern = Pattern.compile("\\.\\.[\\d+|\\.]");
        return pattern.matcher(name).matches();
    }

    @Override
    public String toString() {
        return name;
    }
}
