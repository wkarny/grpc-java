package io.grpc.examples.helloworld;

import java.util.Scanner;

class RecursiveClient extends Thread 
{ 
    public void run()
    { 
        HelloWorldClient client = new HelloWorldClient("localhost", 50051);
        try {
          /* Access a service running on the local machine on port 50051 */
          String user = "world";
          client.greet(user);
        } 
        finally {
            try{
                client.shutdown();
            }
            catch (Exception e){
                System.out.println("Exception");
            }
        }
    } 

    public static void main(String[] args) throws Exception
    { 
        int n = 8; // Number of threads 
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the number of threads to be created : ");
        n = in.nextInt();
        RecursiveClient[] obj = new RecursiveClient[n];
        long start = System.currentTimeMillis();
        for (int i=0; i<n; i++) 
        { 
            obj[i] = new RecursiveClient(); 
            obj[i].start(); 
        } 

        for (int i=0; i<n; i++) 
            obj[i].join();
        //Thread.join();
        System.out.println("**********************\nFinished\n**********************");
        System.out.println("The time : "+(System.currentTimeMillis()-start)+" milliseconds");
    } 
} 
