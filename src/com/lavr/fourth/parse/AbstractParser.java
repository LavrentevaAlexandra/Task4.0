package com.lavr.fourth.parse;

import com.lavr.fourth.entity.TextComposite;

/**
 * Created by 123 on 02.11.2016.
 */
public abstract class AbstractParser {
    public abstract TextComposite parse(TextComposite composite, String string);

}
