package com.lavr.fourth.runner;

import com.lavr.fourth.action.FunctionalOpportunities;
import com.lavr.fourth.action.TextReader;
import com.lavr.fourth.action.TextWriter;
import com.lavr.fourth.entity.TextComposite;
import com.lavr.fourth.parse.*;

/**
 * Created by 123 on 01.11.2016.
 */
public class RunComposite {
    private static final String PATH="files\\text.txt";

    public static void main(String[] args) {
        TextReader textReader=new TextReader();
        TextWriter textWriter=new TextWriter();
        FunctionalOpportunities functions=new FunctionalOpportunities();
        String text=textReader.loadText(PATH);
        ParagraphParser p=new ParagraphParser();
        TextComposite wholeText=new TextComposite(ParsingComponentType.PARAGRAPH);
        p.parse(wholeText,text);
        textWriter.writeText("files\\Composite_To_String.txt",wholeText.toString());
        textWriter.writeText("files\\Sorted_Text(2_task).txt",functions.sortedText(wholeText));
        functions.deleteLexemes(wholeText,2,'i');
        textWriter.writeText("files\\With_Deleted_Lexemes(6_task).txt",wholeText.toString());
        functions.exchangeLexeme(wholeText);
        textWriter.writeText("files\\With_Exchanged_Lexemes(3_task).txt",wholeText.toString());
    }
}
