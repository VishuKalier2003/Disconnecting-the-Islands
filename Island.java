/* You are given an m x n binary grid grid where 1 represents land and 0 represents water... An island is a maximal 4-directionally (horizontal or vertical) connected group of 1's... The grid is said to be connected if we have exactly one island, otherwise is said disconnected... In one day, we are allowed to change any single land cell (1) into a water cell (0)... Return the minimum number of days to disconnect the grid...
 * Eg 1: grid = [[0,1,1,0],[0,1,1,0],[0,0,0,0]]                Output = 2
 * Eg 2: grid = [[1],[1]]                                      Output = 2
 * Eg 3: grid = [[0,0]]                                        Output = 0
 */
import java.util.*;
public class Island
{
    public int MinimumDaysToSink(int[][] grid)
    {
        int count = 0;
        if((grid.length == 1) && (grid[0].length == 1) || (grid.length == 0) || (grid[0].length == 0))
            return 0;    // Base Condition of a single cell...
        if((grid.length == 2) && (grid[0].length == 1))   // Base condition ternary...
            return grid[0][0] == 1 && grid[1][0] == 1 ? 2 : grid[0][0] == 0 && grid[1][0] == 0 ? 0 : 1; 
        if((grid.length == 1) && (grid[0].length == 2))   // Base condition ternary...
            return grid[0][0] == 1 && grid[0][1] == 1 ? 2 : grid[0][0] == 0 && grid[0][1] == 0 ? 0 : 1; 
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
        for(int i = 0; i < grid.length; i++)
        {
            for(int j = 0; j < grid[0].length; j++)
            {
                if(grid[i][j] == 1)    // If the current cell is land...
                {    // Checking the adjacency of each cell...
                    if((i - 1 >= 0) && (grid[i-1][j] == 1))
                        queue.add(++count);
                    if((i + 1 != grid.length) && (grid[i+1][j] == 1))
                        queue.add(++count);
                    if((j - 1 >= 0) && (grid[i][j-1] == 1))
                        queue.add(++count);
                    if((j + 1 != grid[0].length) && (grid[i][j+1] == 1))
                        queue.add(++count);
                    count = 0;
                }
            }
        }
        return queue.peek()+1;    // returning the cell with minimum adjacency...
    }
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        int m, n;
        System.out.print("Enter the number of rows (n) : ");
        n = sc.nextInt();
        System.out.print("Enter the number of columns (m) : ");
        m = sc.nextInt();
        int matrix[][] = new int[n][m];
        for(int i = 0; i < matrix.length; i++)
        {
            for(int j = 0; j < matrix[0].length; j++)
            {
                System.out.print("Enter state of "+(i+1)+" row and "+(j+1)+" column : ");
                matrix[i][j] = sc.nextInt();
            }
        }
        Island island = new Island();     // Object creation...
        System.out.println("The Minimum days to make Island disconnected : "+island.MinimumDaysToSink(matrix));
        sc.close();
    }
}

// Time Complexity  - O(n^2) time...
// Space Complexity - O(n) space...

/* DEDUCTIONS :- 
 * 1. Since to disconnect the grid we need tw Islands, the best way would be to remove the cell with minimum adjacent land cells and make it an independent island...
 * 2. We need at most two days to disconnect a grid...
 * 3. We can use a Breadth-First-Search technique to check the adjacent land cells of the current cell for a depth of one cell and return the minimum adjacent cell...
*/