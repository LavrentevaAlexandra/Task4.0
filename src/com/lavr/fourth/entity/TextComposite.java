package com.lavr.fourth.entity;

import com.lavr.fourth.parse.ParsingComponentType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

/**
 * Created by 123 on 01.11.2016.
 */

public class TextComposite implements Component {
    private static final Logger LOG = LogManager.getLogger();

    private ArrayList<Component> components = new ArrayList<>();
    private ParsingComponentType componentType;     //type of components.

    public TextComposite(ParsingComponentType type) {
        this.componentType = type;
    }

    public void add(Component component) {
        components.add(component);
    }

    @Override
    public void remove(Component component) throws LeafOperationException {
        if(((TextComposite)component).getType().equals(((TextComposite)components.get(0)).getType())) {
            components.remove(component);
        }else for (Component comp:components) {
            comp.remove(component);
        }
    }

    public ParsingComponentType getType() {
        return componentType;
    }
    public int size(){
        int size=0;
        for(Component o1Component: getComponents()){
            size++;
        }
        return size;
    }

    public ArrayList<Component> getComponents() {
        return components;
    }

    @Override
    public String toString() {
        StringBuilder sb= new StringBuilder();
        recursiveToString(sb);
        return sb.toString();
    }

    @Override
    public void recursiveToString(StringBuilder sb){
        for (Component component:components) {
            component.recursiveToString(sb);
            switch (getType()){                    //without default, because do nothing in other situations
                case PARAGRAPH:
                    sb.append("\n");
                    break;
                case LEXEME:
                    sb.append(" ");
                    break;
            }
        }
    }
}

