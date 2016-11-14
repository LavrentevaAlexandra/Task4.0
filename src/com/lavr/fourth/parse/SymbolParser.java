package com.lavr.fourth.parse;

import com.lavr.fourth.entity.SymbolLeaf;
import com.lavr.fourth.entity.TextComposite;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 123 on 02.11.2016.
 */
public class SymbolParser extends AbstractParser {

    @Override
    public TextComposite parse(TextComposite wordList, String word) {
        for(int i=0;i<word.length();i++){
            Character ch=word.charAt(i);
            SymbolLeaf symbol=new SymbolLeaf(ch);
            wordList.add(symbol);
        }

        return wordList;
    }
}
