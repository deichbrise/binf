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

    /**
     * Increases the level of descent
     * @param file
     */
    @Override
    public void descentInDirectory( final File file ) {
        level++;
    }

    /**
     * Decreases the level of descent
     * @param file
     */
    @Override
    public void completeDirectory( final File file ) {
        level--;
    }

    /**
     * Visits given file, prints file name with indention according to current level
     *
     * @param o
     *           the element that has just been visited by
     *           {@link Visitable#accept(Visitor)}
     * @return true if current level of descent is below maximal level
     *         false if maximum level has been reached
     */
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
