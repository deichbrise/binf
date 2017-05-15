package com.week4.solution.fraction.manager;

import com.week4.solution.fraction.strategy.CalculationStrategy;
import com.week4.solution.fraction.strategy.impl.FloatingPointCalculationStrategy;
import com.week4.solution.fraction.strategy.impl.FractionCalculationStrategy;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

/**
 * Verwaltet CalculationStrategies
 *
 * @author pascalstammer
 * @version 15.05.17.
 */
public class CalculationStrategyManager {
    private List<CalculationStrategy> calculationStrategies = new ArrayList<>();
    private static CalculationStrategyManager instance;

    private CalculationStrategyManager(){
        calculationStrategies.add( new FractionCalculationStrategy() );
        calculationStrategies.add( new FloatingPointCalculationStrategy() );
    }

    public static CalculationStrategyManager getInstance() {
        if(instance == null) {
            instance = new CalculationStrategyManager();
        }
        return instance;
    }

    public void addCalculationStrategy(final CalculationStrategy calculationStrategy) {
        calculationStrategies.add( calculationStrategy );
    }

    public List<CalculationStrategy> getCalculationStrategies() {
        return calculationStrategies;
    }
}
