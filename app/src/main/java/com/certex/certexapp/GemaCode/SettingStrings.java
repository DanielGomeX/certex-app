package com.certex.certexapp.GemaCode;

import java.text.Normalizer;

public class SettingStrings {

    public static String removeAccentuation (String str){
        return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }

}
