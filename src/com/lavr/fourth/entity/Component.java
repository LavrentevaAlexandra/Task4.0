package com.lavr.fourth.entity;

/**
 * Created by 123 on 01.11.2016.
 */
public interface Component {
    void add(Component c) throws LeafOperationException;
    void remove(Component c) throws LeafOperationException;
    void recursiveToString(StringBuilder sb);
}