package com.week7.simpleVisitor;

import com.week7.Visitor;
import com.week7.simpleVisitor.MyEntry;

import java.io.File;

/**
 * Created by Julia on 08.06.2017.
 */
public class MyVisitor implements Visitor {

    @Override
    public boolean visit(Object o) {
        if(o instanceof MyEntry) System.out.println(((MyEntry)o).o);
        return false;
    }

}
