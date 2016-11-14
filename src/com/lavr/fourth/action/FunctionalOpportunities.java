package com.lavr.fourth.action;

import com.lavr.fourth.entity.Component;
import com.lavr.fourth.entity.LeafOperationException;
import com.lavr.fourth.entity.SymbolLeaf;
import com.lavr.fourth.entity.TextComposite;
import com.lavr.fourth.parse.ParsingComponentType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by 123 on 14.11.2016.
 */
public class FunctionalOpportunities {
    private static final Logger LOG = LogManager.getLogger();

    public void  findLayer(TextComposite text,ArrayList<TextComposite> layerList,ParsingComponentType type){
        for (Component component:text.getComponents()){
            if(((TextComposite)component).getType().equals(type)){
                layerList.add((TextComposite) component);
            }else findLayer ((TextComposite)component,layerList,type);
        }
    }


    public ArrayList<TextComposite> sortAccordingToLexemes(TextComposite text){
        ArrayList<TextComposite>sentences=new ArrayList<>();
        findLayer(text,sentences,ParsingComponentType.LEXEME);
        Collections.sort(sentences, new Comparator<TextComposite>() {
            @Override
            public int compare(TextComposite o1, TextComposite o2) {
                return o1.size()-o2.size();
            }
        });
        return sentences;
    }

    public String sortedText(TextComposite text){
        StringBuilder sortedText=new StringBuilder();
        for (TextComposite string: sortAccordingToLexemes(text)) {
            sortedText.append(string);
            sortedText.append("\n");
        }
        return sortedText.toString();
    }

    public void deleteLexemes (TextComposite text,int length, char firstLetter){
        ArrayList<TextComposite>lexemes=new ArrayList<>();
        findLayer(text,lexemes,ParsingComponentType.WORD);
        String currentLexeme;
        String currentChar;
        String firstChar=String.valueOf(firstLetter).toLowerCase();
        for(TextComposite lexeme: lexemes){
            currentLexeme=lexeme.toString();
            currentChar=String.valueOf(currentLexeme.charAt(0)).toLowerCase();
            if(currentLexeme.length()==length && currentChar.equals(firstChar)){
                try {
                    text.remove(lexeme);
                } catch (LeafOperationException e) {
                    LOG.error("Can't use remove operation with symbol leaf");
                }
            }
        }
    }
    public void exchangeLexeme(TextComposite text){
        ArrayList<Component> list = text.getComponents();
        for (Component component : list) {
            if (((TextComposite) component).getType().equals(ParsingComponentType.WORD)) {
                ArrayList<Component> lexemes = ((TextComposite) component).getComponents();
                Component first = lexemes.get(0);
                Component last=lexemes.get(lexemes.size()-1);
                lexemes.set(0, last);
                lexemes.set(lexemes.size() - 1, first);
            } else {
                exchangeLexeme(((TextComposite) component));
            }
        }
    }
}
