/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sorting;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mohamed Wael
 */
public class Sorting {

    int getFileSize(String filename)
    {
        Scanner x = null;
        int i = 0;
        int temp;
        try {
            x = new Scanner(new File(filename));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Sorting.class.getName()).log(Level.SEVERE, null, ex);
        }
        while(x.hasNext())
        {
            temp = x.nextInt();
            i++;
        }
        return i;
    }
    
    void fillArrayFromFile(int[] arr, String filename)
    {
        int i = 0;
        Scanner x = null;
        try {
            x = new Scanner(new File(filename));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Sorting.class.getName()).log(Level.SEVERE, null, ex);
        }
        while(x.hasNext())
        {
            arr[i] = x.nextInt();
            i++;
        }
    }
    
    void printArray(int[] arr)
    {
        int n = arr.length;
        for(int i =0;i<n;i++)
            System.out.print(arr[i]+" "); 
        System.out.println();
    }
    
    void bubblesort(int[] arr)
    {
        int n = arr.length; 
        for (int i = 0; i < n-1; i++) 
            for (int j = 0; j < n-1-i; j++) 
                if (arr[j] > arr[j+1]) 
                {  
                    int temp = arr[j]; 
                    arr[j] = arr[j+1]; 
                    arr[j+1] = temp; 
                } 
    }
    
    void merge(int arr[], int l, int m, int r) 
    { 
        int n1 = m - l + 1; 
        int n2 = r - m; 
        int L[] = new int [n1]; 
        int R[] = new int [n2]; 
        for (int i=0; i<n1; ++i) 
            L[i] = arr[l + i]; 
        for (int j=0; j<n2; ++j) 
            R[j] = arr[m + 1+ j];  
        int i = 0, j = 0;  
        int k = l; 
        while (i < n1 && j < n2) 
        { 
            if (L[i] <= R[j]) 
            { 
                arr[k] = L[i]; 
                i++; 
            } 
            else
            { 
                arr[k] = R[j]; 
                j++; 
            } 
            k++; 
        } 
        while (i < n1) 
        { 
            arr[k] = L[i]; 
            i++; 
            k++; 
        } 
        while (j < n2) 
        { 
            arr[k] = R[j]; 
            j++; 
            k++; 
        } 
    } 
  
    void mergesort(int arr[], int l, int r) 
    { 
        if (l < r) 
        {  
            int m = (l+r)/2;  
            mergesort(arr, l, m); 
            mergesort(arr , m+1, r);  
            merge(arr, l, m, r); 
        } 
    } 
    
    boolean checkIdentical(int[] arr1,int[] arr2)
    {
        if(arr1.length != arr2.length)
            return false;
        for(int i=0;i<arr1.length;i++)
        {
            if(arr1[i] != arr2[i])
                return false;
        }
        return true;
    }
    
    public static void main(String[] args) 
    {
        String filename1 = "numbers1.txt";
        String filename2 = "numbers2.txt";
        Sorting s = new Sorting();
        int filesize1 = s.getFileSize(filename1);
        int[] arr1 = new int[filesize1];
        s.fillArrayFromFile(arr1, filename1);
        int filesize2 = s.getFileSize(filename2);
        int[] arr2 = new int[filesize2];
        s.fillArrayFromFile(arr2, filename2);
        long start1 = System.currentTimeMillis();
        s.bubblesort(arr1);
        long end1 = System.currentTimeMillis();
        System.out.println("First file after Bubble sort:");
        s.printArray(arr1);
        System.out.println("Time Taken for Bubble sort: " +(end1-start1)+"ms");
        long start2 = System.currentTimeMillis();
        s.mergesort(arr2,0,arr2.length-1);
        long end2 = System.currentTimeMillis();
        System.out.println("Second file after Merge sort:");
        s.printArray(arr2);
        System.out.println("Time Taken for Merge sort: " +(end2-start2)+"ms");
        boolean identical = s.checkIdentical(arr1, arr2);
        if(identical)
            System.out.println("Files are identical");
        else
            System.out.println("Files are not identical");
    }
}