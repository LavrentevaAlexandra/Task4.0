package com.lavr.fourth.parse;

import com.lavr.fourth.entity.TextComposite;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 123 on 02.11.2016.
 */
public class SentenceParser extends AbstractParser {
    private static final String REGEX_SENTENCE = "([^\\.!\\?]+)(\\.|!|\\?)";

    private LexemeParser reference=new LexemeParser();

    @Override
    public TextComposite parse(TextComposite paragraphList, String paragraph) {
        //parse to sentence
        Pattern patternSentence = Pattern.compile(REGEX_SENTENCE);
        Matcher sentenceMatcher = patternSentence.matcher(paragraph);
        String sentence;
        while (sentenceMatcher.find()) {
            TextComposite sentenceList = new TextComposite(ParsingComponentType.LEXEME);
            sentence = sentenceMatcher.group();
            sentenceList = reference.parse(sentenceList, sentence);

            paragraphList.add(sentenceList);
        }
        return paragraphList;
    }
}
