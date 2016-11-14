package com.lavr.fourth.parse;

import com.lavr.fourth.action.PolskaFormParser;
import com.lavr.fourth.action.PolskaFormException;
import com.lavr.fourth.entity.TextComposite;
import com.lavr.fourth.interpreter.ExpressionClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 123 on 02.11.2016.
 */
 class LexemeParser extends AbstractParser {
    private static final Logger LOG = LogManager.getLogger();


    private static final String REGEX_LEXEME = " +";
    private static final String REGEX_MATH="([0-9\\+\\-\\*/]|\\(|\\)|\\+\\+|--)+";

    private WordParser reference=new WordParser();

    @Override
    public TextComposite parse(TextComposite sentenceList, String sentence) {
        //parse sentence to lexeme
        Pattern expressionPattern=Pattern.compile(REGEX_MATH);
        sentence=sentence.trim();
        String[] lexemes=sentence.split(REGEX_LEXEME);
        for(String  lexeme: lexemes) {
            Matcher expressionMatcher=expressionPattern.matcher(lexeme);
            if(expressionMatcher.matches()){
                try {
                    PolskaFormParser pol = new PolskaFormParser();
                    ExpressionClient interpreter = new ExpressionClient(pol.format(lexeme));
                    lexeme = interpreter.calculate().toString();
                }catch (PolskaFormException ex){
                    LOG.fatal("Wrong mathematical expression", ex);
                    throw  new RuntimeException( "Be sure all brackets are closed and negative number are in brackets. You can use increment operator only with numbers? not with expressions",ex);
                }
            }
            TextComposite lexemeList=new TextComposite(ParsingComponentType.WORD);

            lexemeList = reference.parse(lexemeList, lexeme);

            sentenceList.add(lexemeList);
        }
        return sentenceList;
    }
}
