package my.nvinz;

import com.google.common.collect.Table;
import com.google.common.collect.TreeBasedTable;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import javax.swing.JFileChooser;
import java.io.File;
import java.util.regex.Pattern;

public class Solver {

    Table<String, String, Integer> table;

    public Solver() throws IOException {
        table = TreeBasedTable.create();

        openAndParse();
        print();

        if (checkRows() != -1)
            System.out.println("Incorrect data in row " + checkRows());
        else if (checkCols() != -1)
            System.out.println("Incorrect data in col " + checkCols());
        else
            System.out.println("Table Correct");
    }

    void openAndParse() throws IOException {
        String csvFile = "";
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("C:\\Users\\user\\Documents\\Solv3\\out\\artifacts\\Solv3_jar"));
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            csvFile = selectedFile.getAbsolutePath();
        }

        Scanner scanner = new Scanner(new File(csvFile));
        scanner.useDelimiter(Pattern.compile("([\n;]|(\r\n))+"));

        int i = 0, j = 0;
        while (scanner.hasNext())
        {
            for (i = 0; i < 9; i++) {
                table.put("row"+j, "col"+i, Integer.parseInt(scanner.next()));
            }
            i = 0; j++;
        }

        scanner.close();
    }

    /*  Check rows for duplicates
     *   -1 (true) = correct
     *   other (false) = contains
     */
    public int checkRows(){
        for (int i = 0; i < 9; i++) {
            Map<String, Integer> rowMap = table.row("row"+i);
            List<Integer> rowValues = new ArrayList();

            for (Map.Entry<String, Integer> cell : rowMap.entrySet()) {
                if (cell.getValue() != 0)
                    rowValues.add(cell.getValue());
            }

            Set<Integer> set = new HashSet<Integer>(rowValues);

            if(set.size() < rowValues.size()){
                return i;
            }
        }
        return -1;
    }

    /*  Check columns for duplicates
    *   -1 (true) = correct
    *   other (false) = contains
    */
    public int checkCols(){
        for (int i = 0; i < 9; i++) {
            Map<String, Integer> rowMap = table.column("col"+i);
            List<Integer> colValues = new ArrayList();

            for (Map.Entry<String, Integer> cell : rowMap.entrySet()) {
                if (cell.getValue() != 0)
                    colValues.add(cell.getValue());
            }

            Set<Integer> set = new HashSet<Integer>(colValues);

            if(set.size() < colValues.size()){
                return i;
            }
        }
        return -1;
    }

    /*
     * If a possible number is already in a row
     */
    public boolean isInRow(int row, int num){
        for (int i = 0; i < 9; i++){
            if (table.get("row"+row, "col"+i) == num){
                return true;
            }
        }
        return false;
    }

    /*
     * If a possible number is already in a column
     */
    public boolean isInCol(int col, int num){
        for (int i = 0; i < 9; i++){
            if (table.get("row"+i, "col"+col) == num){
                return true;
            }
        }
        return false;
    }

    /*
     * If a possible number is already in 3x3 box
     */
    public boolean isInBox(int row, int col, int num){
        int r = row - row % 3;
        int c = col - col % 3;
        for (int i = r; i < r+3; i++) {
            for (int j = c; j < c+3; j++) {
                if (table.get("row"+i, "col"+j) == num){
                    return true;
                }
            }
        }
        return false;
    }

    /*
     *  Check if a number possible to a row, col & box position is ok
     */
    private boolean isOk(int row, int col, int num){
        return !isInRow(row, num)  &&  !isInCol(col, num)  &&  !isInBox(row, col, num);
    }

    /*
     *  Recursive BackTracking algorithm
     */
    public boolean solve(){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (table.get("row"+i, "col"+j) == 0){
                    for (int num = 1; num <= 9; num++) {
                        if (isOk(i, j, num)) {
                            table.put("row"+i, "col"+j, num);
                            if (solve()){
                                return true;
                            }
                            else{
                                table.put("row"+i, "col"+j, 0);
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public void print(){
        int j, c0 = 1, c1 = 1;
        System.out.println(" col | 0 1 2 | 3 4 5 | 6 7 8 |");
        System.out.println("-----+-------+-------+-------+");
        for (int i = 0; i < 9; i++) {
            System.out.print("row"+i + " | ");
            for (j = 0; j < 9; j++) {
                System.out.print(table.get("row"+i, "col"+j) + " ");
                if (c0%3==0) {
                    System.out.print("| ");
                } c0++;
            }
            if (c0==28 || c0==55) {
                System.out.print("\n-----+-------+-------+-------+");
            }
            j = 0;
            System.out.println("");
        }
        System.out.println("-----+-------+-------+-------+");
    }
}
