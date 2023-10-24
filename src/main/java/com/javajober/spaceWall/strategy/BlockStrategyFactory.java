package com.javajober.spaceWall.strategy;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

@Component
public class BlockStrategyFactory {

	private final Map<String, MoveBlockStrategy> moveBlockStrategies;
	private final Map<String, FixBlockStrategy> fixBlockStrategies;


	public BlockStrategyFactory(Set<MoveBlockStrategy> moveBlockStrategySet, Set<FixBlockStrategy> fixBlockStrategySet) {
		initializeMoveBlockStrategies(moveBlockStrategySet);
		this.moveBlockStrategies = initializeMoveBlockStrategies(moveBlockStrategySet);
		this.fixBlockStrategies = initializeFixBlockStrategies(fixBlockStrategySet);
	}

	public MoveBlockStrategy findMoveBlockStrategy(String moveBlockStrategyName) {
		return moveBlockStrategies.get(moveBlockStrategyName);
	}

	public FixBlockStrategy findFixBlockStrategy(String fixBlockStrategyName) {
		return fixBlockStrategies.get(fixBlockStrategyName);
	}


	private Map<String, MoveBlockStrategy> initializeMoveBlockStrategies(Set<MoveBlockStrategy> StrategySet) {

		Map<String, MoveBlockStrategy>  strategies = new HashMap<>();

		StrategySet.forEach(
			strategy -> strategies.put(strategy.getStrategyName(), strategy));

		return strategies;
	}

	private Map<String, FixBlockStrategy> initializeFixBlockStrategies(Set<FixBlockStrategy> StrategySet) {

		Map<String, FixBlockStrategy>  strategies = new HashMap<>();

		StrategySet.forEach(
			strategy -> strategies.put(strategy.getStrategyName(), strategy));

		return strategies;
	}
}
