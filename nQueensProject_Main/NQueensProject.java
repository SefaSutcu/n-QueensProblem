package nQueensProject_Main;

import src.NQueensBoard;
import src.NQueensBoard.Config;
import src.NQueensFunctions;
import src.QueenAction;
import src.SearchForActions;
import src.Problem;
import src.GraphSearch;
import src.TreeSearch;

import informedSearchs.GreedyBestFirstSearch;
import informedSearchs.RecursiveBestFirstSearch;
import informedSearchs.AStarSearch;

// import aima.core.search.local.*;
import uninformedSearchs.BreadthFirstSearch;
import uninformedSearchs.DepthFirstSearch;
import uninformedSearchs.DepthLimitedSearch;
import uninformedSearchs.IterativeDeepeningSearch;
import uninformedSearchs.UniformCostSearch;

import java.util.*;

/**
 * Muzaffer Tasiran 181805017
 * Sefa Samet Sutcu 181805038
 */


public class NQueensProject {

	private static final int boardSize = 8;

	public static void main(String[] args) {
		startNQueensProject();
	}

	private static void startNQueensProject() {
		System.out.println("\n--- uninformed searchs ---");
		solveNQueensWithBreadthFirstSearch();
		solveNQueensWithDepthFirstSearch();
		solveNQueensWithRecursiveDLS();
		solveNQueensWithIterativeDeepeningSearch();
		solveNQueensWithUniformCostSearch();
		
		System.out.println("\n--- informed searchs ---");
		solveNQueensWithGreedyBestFirstSearch();
		solveNQueensWithAStarSearch();
		solveNQueensWithRecursiveBestFirstSearch();
	}
	
	private static void QueensInProject() {
		System.out.println("\nFirst Two Queens at (col=0, row=3) and (col=1, row=1) ---");
	}

	private static void solveNQueensWithDepthFirstSearch() {
		System.out.println("\n--- NQueensProject DFS ---");
		
		QueensInProject();
		Problem<NQueensBoard, QueenAction> problem = NQueensFunctions.createProjectFormulationProblem(boardSize, Config.QUEENS_IN_PROJECT);
		SearchForActions<NQueensBoard, QueenAction> search = new DepthFirstSearch<>(new TreeSearch<>());
		Optional<List<QueenAction>> actions = search.findActions(problem);

		actions.ifPresent(qActions -> qActions.forEach(System.out::println));
		System.out.println(search.getMetrics());
		// System.out.println("Final State:\n" + search.getLastState());
	}

	private static void solveNQueensWithBreadthFirstSearch() {
		System.out.println("\n--- NQueensDemo BFS ---");
		
		QueensInProject();
		Problem<NQueensBoard, QueenAction> problem = NQueensFunctions.createProjectFormulationProblem(boardSize, Config.QUEENS_IN_PROJECT);
		SearchForActions<NQueensBoard, QueenAction> search = new BreadthFirstSearch<>(new GraphSearch<>());
		Optional<List<QueenAction>> actions = search.findActions(problem);

		actions.ifPresent(qActions -> qActions.forEach(System.out::println));
		System.out.println(search.getMetrics());
	}
	
	private static void solveNQueensWithGreedyBestFirstSearch() {
		System.out.println("\n--- NQueensProject Greedy First Search ---");
		
		QueensInProject();
		Problem<NQueensBoard, QueenAction> problem = NQueensFunctions.createProjectFormulationProblem(boardSize, Config.QUEENS_IN_PROJECT);
		SearchForActions<NQueensBoard, QueenAction> search = new GreedyBestFirstSearch<>
								(new GraphSearch<>(), NQueensFunctions::getNumberOfAttackingPairs);
		Optional<List<QueenAction>> actions = search.findActions(problem);

		actions.ifPresent(qActions -> qActions.forEach(System.out::println));
		System.out.println(search.getMetrics());
		
	}
	
	private static void solveNQueensWithRecursiveDLS() {
		System.out.println("\n--- NQueensDemo recursive DLS ---");
		
		QueensInProject();
		Problem<NQueensBoard, QueenAction> problem = NQueensFunctions.createProjectFormulationProblem(boardSize, Config.QUEENS_IN_PROJECT);
		SearchForActions<NQueensBoard, QueenAction> search = new DepthLimitedSearch<>(boardSize);
		Optional<List<QueenAction>> actions = search.findActions(problem);

		actions.ifPresent(qActions -> qActions.forEach(System.out::println));
		System.out.println(search.getMetrics());
	}
	
	private static void solveNQueensWithIterativeDeepeningSearch() {
		System.out.println("\n--- NQueensDemo Iterative DS ---");
		
		QueensInProject();
		Problem<NQueensBoard, QueenAction> problem = NQueensFunctions.createProjectFormulationProblem(boardSize, Config.QUEENS_IN_PROJECT);
		SearchForActions<NQueensBoard, QueenAction> search = new IterativeDeepeningSearch<>();
		Optional<List<QueenAction>> actions = search.findActions(problem);

		actions.ifPresent(qActions -> qActions.forEach(System.out::println));
		System.out.println(search.getMetrics());
	}
	
	private static void solveNQueensWithUniformCostSearch() {
		System.out.println("\n--- NQueensDemo UniformCostSearch ---");
		
		QueensInProject();
		Problem<NQueensBoard, QueenAction> problem = NQueensFunctions.createProjectFormulationProblem(boardSize, Config.QUEENS_IN_PROJECT);
		SearchForActions<NQueensBoard, QueenAction> search = new UniformCostSearch<>(new GraphSearch<>());
		Optional<List<QueenAction>> actions = search.findActions(problem);
		
		actions.ifPresent(qActions -> qActions.forEach(System.out::println));
		
		System.out.println(search.getMetrics());
	}
	
	private static void solveNQueensWithAStarSearch() {
		System.out.println("\n--- NQueensDemo A* (complete state formulation, graph search 3e) ---");

		QueensInProject();
		Problem<NQueensBoard, QueenAction> problem = NQueensFunctions.createProjectFormulationProblem(boardSize, Config.QUEENS_IN_PROJECT);
		SearchForActions<NQueensBoard, QueenAction> search = new AStarSearch<>
				(new GraphSearch<>(), NQueensFunctions::getNumberOfAttackingPairs);
		Optional<List<QueenAction>> actions = search.findActions(problem);

		actions.ifPresent(qActions -> qActions.forEach(System.out::println));
		System.out.println(search.getMetrics());
	}

	private static void solveNQueensWithRecursiveBestFirstSearch() {
		System.out.println("\n--- NQueensDemo RecursiveBestFirstSearch ---");

		QueensInProject();
		Problem<NQueensBoard, QueenAction> problem = NQueensFunctions.createProjectFormulationProblem(boardSize, Config.QUEENS_IN_PROJECT);
		SearchForActions<NQueensBoard, QueenAction> search = new RecursiveBestFirstSearch<>(AStarSearch.createEvalFn(NQueensFunctions::getNumberOfAttackingPairs));
		Optional<List<QueenAction>> actions = search.findActions(problem);

		actions.ifPresent(qActions -> qActions.forEach(System.out::println));
		System.out.println(search.getMetrics());
	}

}

