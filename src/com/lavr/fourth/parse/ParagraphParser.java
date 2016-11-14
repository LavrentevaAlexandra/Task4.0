package com.lavr.fourth.parse;

import com.lavr.fourth.entity.TextComposite;


/**
 * Created by 123 on 02.11.2016.
 */
public class ParagraphParser extends AbstractParser {
    private final String REGEX_PARAGRAPH = "\\n\\t";

    private SentenceParser reference=new SentenceParser();

    @Override
    public TextComposite parse(TextComposite wholeText, String text) {
        // parse to paragraph
        String paragraphs[]=text.split(REGEX_PARAGRAPH);
        String paragraph;
        for(int i=0;i<paragraphs.length;i++) {
            paragraph = paragraphs[i];
            TextComposite paragraphList = new TextComposite(ParsingComponentType.SENTENCE);
            paragraphList = reference.parse(paragraphList, paragraph);
            wholeText.add(paragraphList);
        }
        return wholeText;
    }
}
