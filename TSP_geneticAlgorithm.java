// // 


// //7A
// import java.util.*;

// public class TSP_BB_GA_2nd_approach {
//     static class Node {
//         int level;  // Current level in the tree (number of visited cities)
//         int cost;   // Cost of the path so far
//         int bound;  // Lower bound for the node
//         int[] path; // Current path of the solution

//         Node(int level, int cost, int bound, int[] path) {
//             this.level = level;
//             this.cost = cost;
//             this.bound = bound;
//             this.path = path.clone();
//         }
//     }

//     public static int tsp(int[][] costMatrix) {
//         int n = costMatrix.length;

//         // Priority queue with custom comparator based on bound value (lowest bound first)
//         PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.bound));

//         // Create initial node
//         int[] initialPath = new int[n + 1];
//         initialPath[0] = 0; // Start from the first city
//         Node root = new Node(0, 0, calculateBound(costMatrix, new HashSet<>(), 0), initialPath);
//         pq.add(root);

//         int minCost = Integer.MAX_VALUE;
//         int[] bestPath = new int[n + 1];

//         while (!pq.isEmpty()) {
//             Node current = pq.poll();

//             // Prune node if its bound is greater than current minCost
//             if (current.bound >= minCost) continue;

//             // If all cities are visited, calculate total cost
//             if (current.level == n - 1) {
//                 int finalCost = current.cost + costMatrix[current.path[current.level]][current.path[0]];
//                 if (finalCost < minCost) {
//                     minCost = finalCost;
//                     bestPath = current.path.clone();
//                 }
//                 continue;
//             }

//             // Branch to generate child nodes
//             for (int i = 0; i < n; i++) {
//                 if (!isVisited(current.path, i, current.level + 1)) {
//                     int[] newPath = current.path.clone();
//                     newPath[current.level + 1] = i;

//                     int newCost = current.cost + costMatrix[current.path[current.level]][i];
//                     HashSet<Integer> visited = new HashSet<>();
//                     for (int j = 0; j <= current.level; j++) {
//                         visited.add(current.path[j]);
//                     }
//                     int newBound = newCost + calculateBound(costMatrix, visited, i);

//                     // Add new node to the priority queue if promising
//                     if (newBound < minCost) {
//                         Node child = new Node(current.level + 1, newCost, newBound, newPath);
//                         pq.add(child);
//                     }
//                 }
//             }
//         }

//         // Print the best path
//         System.out.println("Minimum Cost: " + minCost);
//         System.out.print("Path: ");
//         for (int i = 0; i <= n; i++) {
//             System.out.print(bestPath[i] + " ");
//         }
//         System.out.println();
//         return minCost;
//     }

//     private static boolean isVisited(int[] path, int city, int level) {
//         for (int i = 0; i < level; i++) {
//             if (path[i] == city) return true;
//         }
//         return false;
//     }

//     private static int calculateBound(int[][] costMatrix, Set<Integer> visited, int city) {
//         int n = costMatrix.length;
//         int bound = 0;

//         for (int i = 0; i < n; i++) {
//             if (!visited.contains(i)) {
//                 int minEdge = Integer.MAX_VALUE;
//                 for (int j = 0; j < n; j++) {
//                     if (j != i && !visited.contains(j)) {
//                         minEdge = Math.min(minEdge, costMatrix[i][j]);
//                     }
//                 }
//                 bound += minEdge;
//             }
//         }
//         return bound;
//     }

//     public static void main(String[] args) {
//         // Example cost matrix
//         int[][] costMatrix = {
//             {0, 10, 15, 20},
//             {10, 0, 35, 25},
//             {15, 35, 0, 30},
//             {20, 25, 30, 0}
//         };

//         tsp(costMatrix);
//     }
// }


import java.util.*;

public class TSP_geneticAlgorithm {
//7B
    static class City {
        int x, y;

        City(int x, int y) {
            this.x = x;
            this.y = y;
        }

        double distanceTo(City other) {
            return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
        }
    }

    static class Tour {
        List<City> cities;
        double fitness;

        Tour(List<City> cities) {
            this.cities = new ArrayList<>(cities);
            Collections.shuffle(this.cities); // Random initial tour
            calculateFitness();
        }

        void calculateFitness() {
            double totalDistance = 0;
            for (int i = 0; i < cities.size() - 1; i++) {
                totalDistance += cities.get(i).distanceTo(cities.get(i + 1));
            }
            totalDistance += cities.get(cities.size() - 1).distanceTo(cities.get(0)); // Return to start
            fitness = 1 / totalDistance; // Higher fitness for shorter tours
        }

        double getDistance() {
            return 1 / fitness; // Inverse of fitness
        }
    }

    public static void main(String[] args) {
        int cityCount = 5;
        int populationSize = 10;
        int generations = 30;
        double mutationRate = 0.15;

        // 1. Create random cities
        List<City> cities = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < cityCount; i++) {
            cities.add(new City(rand.nextInt(200), rand.nextInt(200)));
        }

        // 2. Initialize population
        List<Tour> population = new ArrayList<>();
        for (int i = 0; i < populationSize; i++) {
            population.add(new Tour(cities));
        }

        for (int gen = 0; gen < generations; gen++) {
            // 3.  Evaluate fitness
            population.forEach(Tour::calculateFitness);
            population.sort(Comparator.comparingDouble(t -> -t.fitness)); // Sort by fitness (descending)

            // 4. Selection: Elitism (keep top 10%)
            List<Tour> newPopulation = new ArrayList<>(population.subList(0, populationSize / 10));

            // Crossover
            Random random = new Random();
            while (newPopulation.size() < populationSize) {
                Tour parent1 = selectParent(population);
                Tour parent2 = selectParent(population);
                Tour child = crossover(parent1, parent2);
                newPopulation.add(child);
            }

            // Mutation
            for (Tour tour : newPopulation) {
                if (random.nextDouble() < mutationRate) {
                    mutate(tour);
                }
            }

            // Replace old population
            population = newPopulation;

            // Print best tour of this generation
            Tour bestTour = population.get(0);
            System.out.println("Generation " + gen + ": Best distance = " + bestTour.getDistance());
        }

        // Final result
        Tour bestTour = population.get(0);
        System.out.println("Final Best Tour Distance: " + bestTour.getDistance());
        System.out.println("Cities Order: ");
        bestTour.cities.forEach(city -> System.out.println("(" + city.x + ", " + city.y + ")"));
    }

    // Parent selection using roulette wheel
    private static Tour selectParent(List<Tour> population) {
        double totalFitness = population.stream().mapToDouble(t -> t.fitness).sum();
        double rand = Math.random() * totalFitness;
        double cumulativeFitness = 0;
        for (Tour tour : population) {
            cumulativeFitness += tour.fitness;
            if (cumulativeFitness >= rand) {
                return tour;
            }
        }
        return population.get(0); // Fallback
    }

    // Crossover using ordered crossover (OX)
    private static Tour crossover(Tour parent1, Tour parent2) {
        int n = parent1.cities.size();
        List<City> childCities = new ArrayList<>(Collections.nCopies(n, null));

        Random rand = new Random();
        int start = rand.nextInt(n);
        int end = rand.nextInt(n);

        if (start > end) {
            int temp = start;
            start = end;
            end = temp;
        }

        // Copy segment from Parent 1
        for (int i = start; i <= end; i++) {
            childCities.set(i, parent1.cities.get(i));
        }

        // Fill remaining from Parent 2
        int currentIndex = 0;
        for (City city : parent2.cities) {
            if (!childCities.contains(city)) {
                while (childCities.get(currentIndex) != null) {
                    currentIndex++;
                }
                childCities.set(currentIndex, city);
            }
        }

        return new Tour(childCities);
    }

    // Mutation: Swap two cities
    private static void mutate(Tour tour) {
        Random rand = new Random();
        int i = rand.nextInt(tour.cities.size());
        int j = rand.nextInt(tour.cities.size());
        Collections.swap(tour.cities, i, j);
        tour.calculateFitness();
    }
}