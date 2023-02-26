package com.EmoHipHop.mz2mo.global.emoji.util;

import java.util.Arrays;

public class EmojiUtil {
    public static String serialize(String str) {
        return Arrays.toString(str.codePoints().toArray());
    }

    public static String deserialize(String str) {
        String[] codepointStrings = str.replaceAll("[\\[\\]\\s]", "").split(",");
        int[] codepoints = new int[codepointStrings.length];
        for (int i = 0; i < codepointStrings.length; i++)
            codepoints[i] = Integer.parseInt(codepointStrings[i]);
        StringBuilder sb = new StringBuilder();
        for (int cp : codepoints)
            sb.appendCodePoint(cp);
        return sb.toString();
    }
}