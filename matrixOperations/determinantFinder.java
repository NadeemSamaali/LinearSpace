package matrixOperations;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * This class contains all the necessary methods to calculate the determinant of any nxn matrix
 * 
 * @author Nadeem Samaali
 * @version 1.0.0 - First stable release
 */


public class determinantFinder 
{
    /*
    //Setting up the scanner
    Scanner input = new Scanner(System.in);
    */
    static DecimalFormat df = new DecimalFormat("0.00");
    static DecimalFormat d0 = new DecimalFormat("0.000000000");

    /**
     * This method allows for printing the entries of the user
     * 
     * @param N Size of the matrix to be printed
     * @param ansArray Array of the entries entred by the user
     */


    public static void printEntries(int N, String[] ansArray1)
    {
        int sCounter1 = -1;

        //Outputting the matrix as well as the data entries
        System.out.print("[");
        for(int a = 0; a<=N; a++)
        {
            System.out.print("[");
            for(int b = 0; b<N; b++)
            {
                sCounter1 += 1;
                System.out.print(ansArray1[sCounter1]);
                System.out.print(",");
            }
            sCounter1 += 1;
            System.out.print(ansArray1[sCounter1]);

            if(sCounter1 == Math.pow(N+1,2)-1)
            System.out.print("]");

            else
            System.out.print("];");
        }
        System.out.println("]");
    }

    /***
     * This method will input the values of the entires in their designated
     * location withing the matrix array
     * 
     * @param N Size of the matrix array
     * @param ansArray2 Array of the entires of the user
     */
    public static void setMatrixFromString(double[][] M,int N, String[] ansArray2)
    {
        int sCounter2 = -1;

        for(int i = 0; i<=N; i++)
        {
            for(int j = 0; j<=N; j++)
            {
                sCounter2 += 1;
                M[i][j] = Double.valueOf(ansArray2[sCounter2]);
            }
        }
    }

    /**
     * This method allows for the printing of any nXn matrix | To use for testing purposes
     * 
     * @param M matrix to print
     * @param N size of the metrix
     */
    public static void printMatrix(double[][] M, int N)
    {
        //Outputting the matrix as well as the data entries
        System.out.print("[");
        for(int a = 0; a<=N; a++)
        {
            System.out.print("[");
            for(int b = 0; b<N; b++)
            {
                System.out.print(Double.valueOf(df.format(M[a][b])));
                System.out.print(",");
            }
            System.out.print(Double.valueOf(df.format(M[a][N])));

            if(a==N)
            System.out.print("]");

            else
            System.out.print("];");
        }
        System.out.println("]");
    }

    /**
     * This method will allow for the sorting of the rows of a square matrix in ascending order
     * 
     * @param M matrix to sort
     * @param N size of the square matrix
     * @return K amount of rows that were swtiched in the sorting
     */
    public static double sortMatrix(double[][] M, int N, double K)
    {
        //temporary erray where rows to be switched will be temporarily stored 
        double[] tempM = new double[N+1];
        int amountOf0;

        //Matrix sorting algorithm -- Sorts by ascending leading zeros
        for(int i = 0; i<=N; i++)
        {
            amountOf0 = 0;

            for(int j = 0; j<=N; j++)
            {
                if(M[i][j] == 0)
                {
                    amountOf0 += 1;
                }
                else
                    break;  
            }

            if(amountOf0 != i)
            {
                for(int k = 0; k<=N; k++)
                {
                    tempM[k] = M[i][k];

                    //Keeps track of how many times rows are swtiched for the determinant calculations
                    K += (1/(double)(N+1));
                }

                for(int l = 0; l<=N; l++)
                {
                    M[i][l] = M[amountOf0][l];
                }

                for(int m = 0; m<=N; m++)
                {
                    M[amountOf0][m] = tempM[m];
                }
            }   
        }
        //Returns how many rows were switched during the sorting
        return K;
    }

    

    /**
     * This method is an operator that serves part in the process of the reduction and cofactor
     * expansion of a square matrix in order to reduce into an upper triangular matrix.
     * 
     * The the metod will calculate the determinant
     * 
     * @param M matrix to operate
     * @param N size of the matrix
     */    
    public static void getDeterminant(double[][] M, int N)
    {
        //calculating determinant
        double num1 = 1.0;
        int amountOfK = 0;
            for(int v = 0; v<=N; v++)
                amountOfK += v;
                        double[] k = new double[amountOfK];

        //System.out.println(amountOfK);

        for(int m = 0; m<amountOfK; m++)
        {
            k[m] = 1.0;
        }
        int num0 = -1;

        double kFactor = sortMatrix(M,N,0);

        System.out.println("\nSorted the matrix : ");
        printMatrix(M,N);
        System.out.println("Amount of Rows switched : " + Double.valueOf(df.format(kFactor)));

        for(int d = 0; d<=N; d++)
        {  
            /*This loop will multiply each row R>R1 by a factor k, where k is the factor needed to tranforms
            *the entry of the first row into the entry located at [0][0] as well was multiplying the remainer
            *of the entires of this row by that same scalar k
            */
            for(int x = 0; x<=N-d; x++)
            {  
                if(M[x][x] != 0)
                {
                    for(int y = 1; y <= N-(x)-d; y++)
                    {
                        if(M[x+y][x] != 0)
                        {
                            num0 += 1;

                            k[num0] = (M[x][x]/M[x+y][x]);
                            System.out.print("\nK = M(" + x + "," + x + ")" + "/M(" + (d+y) + "," + x + ")");
                            System.out.print(" --> " + df.format(k[num0]) + "*R" + (x+y+1) +"\n");

                            for(int i = 0; i<=N; i++)
                                M[x+y][i] = k[num0]*M[x+y][i];

                            printMatrix(M,N);
    
                            for(int l = 0; l<=N; l++)
                                M[x+y][l] -= M[x][l];


                            System.out.println("\nR" + (x+y+1) + " - R" + (x+1) + " --> R" +(x+y+1) );
                            printMatrix(M,N);
                        }                        
                    }
                }   
            }
        }
       
        for(int n0 = 0; n0<amountOfK; n0++)
        {
            //System.out.println("\nRecorded values of k : ");
            if(1/k[n0] == 0) 
                k[n0] = 1.0;
            else
                k[n0] = k[n0];

            //System.out.println(k[n0]);
        }
        
        System.out.println();
        System.out.print("det(M) = ");

        //System.out.println("1/k :");
        for(int u = 0; u<amountOfK; u++)
        {
            num1 *=(1/k[u]);
            System.out.print(df.format(1/k[u]) + " * ");
        }
        
        for(int r = 0; r<=N; r++)
            {
                System.out.print(df.format(M[r][r]) + " * ");
                num1 = M[r][r]*num1;
            }

        num1 *= Math.pow(-1,Double.valueOf(df.format(kFactor)));

        System.out.print(Math.pow(-1,Double.valueOf(df.format(kFactor))));
        //num1 = num1*negFactor;

        System.out.println("\n\n>> The determinant of this matrix is : " + df.format(num1));      
    }

    public static void main(String[] args)
    {

        Scanner input = new Scanner(System.in);
        String ans;

        System.out.println("\n>> Please insert square matrix size (1,2,3...,n) :\n");
        ans = "";
        ans = input.nextLine();

        //Setting the value n as the size of the matrix
        int n = Integer.valueOf(ans)-1;
        ans = "";

        //Adding the entries of the matrix into an array by splitting
        System.out.println("\n>> Insert the values of the entries sparated with a comma\n   respecting this form : a11,...,a1n,...,am1,...,amn\n");
        ans = input.nextLine();
        String[] entries = ans.split(",");

        //Outputting the matrix
        System.out.print("\n>> Here is the inputted matrix : ");
        printEntries(n,entries);
        System.out.println();

        double[][] matrix = new double[n+1][n+1];

        //Set the entires into the designated placements in the matrix Array
        setMatrixFromString(matrix,n,entries);

        //Reduce the matrix into upper-triangular form
        getDeterminant(matrix, n);
    }
    
    
}