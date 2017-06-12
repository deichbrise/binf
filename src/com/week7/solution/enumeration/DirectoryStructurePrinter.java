package com.week7.solution.enumeration;

import com.common.util.lang.StringUtil;

import java.io.File;

/**
 * @author pascalstammer
 * @version 11.06.17.
 */
public class DirectoryStructurePrinter implements FileVisitor {

    private Integer level;
    private Integer maxLevel;
    private Boolean canVisit;

    public DirectoryStructurePrinter() {
        this(false);
    }

    public DirectoryStructurePrinter( final Integer maxLevel ) {
        this.maxLevel = maxLevel;
        this.level = 0;
        this.canVisit = true;
    }

    public DirectoryStructurePrinter( final boolean recursive) {
        this(recursive ? 10 : 1);
    }

    @Override
    public void descentInDirectory( final File file ) {
        level++;
    }

    @Override
    public void completeDirectory( final File file ) {
        level--;
    }

    @Override
    public boolean visit( final File o ) {
        if(level <= maxLevel && canVisit) {
            if(maxLevel > 0 && level != 0) {
                System.out.println( StringUtil.padLeft( o.getName(), 3 * (level - 1 ) ));
            } else if(maxLevel == 0) {
                System.out.println( StringUtil.padLeft( o.getName(), 0 ));
            }
        }
        return level <= maxLevel;
    }
}
