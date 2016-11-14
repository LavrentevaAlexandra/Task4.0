package com.lavr.fourth.entity;

/**
 * Created by 123 on 01.11.2016.
 */
public class SymbolLeaf implements Component{
    private char symbol;

    public SymbolLeaf(char symbol) {
        this.symbol = symbol;
    }

    public void parse()throws LeafOperationException {
        throw new LeafOperationException("Can't parse symbol");
    }
    public void add(Component c) throws LeafOperationException {
       throw new LeafOperationException("Can't use method add with symbol");
    }
    public void remove(Component c) throws LeafOperationException{
        throw new LeafOperationException("Can't use method remove with symbol");
    }


    @Override
    public String toString() {
        return String.valueOf(symbol);
    }

    @Override
    public void recursiveToString(StringBuilder sb){
        sb.append(symbol);
    }
}


