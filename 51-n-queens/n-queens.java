import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        
        // Initialize an empty board
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        
        // Boolean arrays to track attack zones
        boolean[] cols = new boolean[n];
        boolean[] posDiag = new boolean[2 * n - 1]; // row + col
        boolean[] negDiag = new boolean[2 * n - 1]; // row - col + (n - 1)
        
        backtrack(0, n, board, result, cols, posDiag, negDiag);
        return result;
    }
    
    private void backtrack(int row, int n, char[][] board, List<List<String>> result, 
                           boolean[] cols, boolean[] posDiag, boolean[] negDiag) {
        // Base case: We successfully placed n queens
        if (row == n) {
            result.add(constructBoard(board));
            return;
        }
        
        // Try placing a queen in each column of the current row
        for (int col = 0; col < n; col++) {
            int pDiag = row + col;
            int nDiag = row - col + n - 1; 
            
            // If the position is under attack, skip it
            if (cols[col] || posDiag[pDiag] || negDiag[nDiag]) {
                continue;
            }
            
            // Place the queen
            board[row][col] = 'Q';
            cols[col] = true;
            posDiag[pDiag] = true;
            negDiag[nDiag] = true;
            
            // Move to the next row
            backtrack(row + 1, n, board, result, cols, posDiag, negDiag);
            
            // Backtrack: Remove the queen and its attack zones
            board[row][col] = '.';
            cols[col] = false;
            posDiag[pDiag] = false;
            negDiag[nDiag] = false;
        }
    }
    
    // Helper method to convert the char 2D array into a List of Strings
    private List<String> constructBoard(char[][] board) {
        List<String> currentBoard = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            currentBoard.add(new String(board[i]));
        }
        return currentBoard;
    }
}