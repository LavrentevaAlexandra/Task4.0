package com.lavr.fourth.parse;

import com.lavr.fourth.entity.SymbolLeaf;
import com.lavr.fourth.entity.TextComposite;

/**
 * Created by 123 on 02.11.2016.
 */
public class WordParser extends AbstractParser {

    private SymbolParser reference=new SymbolParser();

    @Override
    public TextComposite parse(TextComposite lexemeList, String lexeme) {
        //parse lexeme to word
        SymbolLeaf firstSymbol;
        Character ch=lexeme.charAt(0);
        if(!Character.isAlphabetic(ch)&& !Character.isDigit(ch)&&ch!='-'){
            firstSymbol= new SymbolLeaf(ch);
            lexemeList.add(firstSymbol);
            lexeme=lexeme.substring(1);
        }
        ch=lexeme.charAt(lexeme.length()-1);
        SymbolLeaf lastSymbol=null;
        if(!Character.isAlphabetic(ch) && !Character.isDigit(ch)&&ch!='-')
        {
            lastSymbol= new SymbolLeaf(ch);
            lexeme=lexeme.substring(0,lexeme.length()-1);
        }
        String word=lexeme;
        TextComposite wordList = new TextComposite(ParsingComponentType.SYMBOL);
        wordList = reference.parse(wordList, word);

        lexemeList.add(wordList);
        if(lastSymbol!=null){
            lexemeList.add(lastSymbol);
        }

        return lexemeList;
    }
}
