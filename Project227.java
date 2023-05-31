import java.util.*;
import java.io.*;
import static java.lang.Math.*;

public class Project227 {
    static int ch; // the choice selected
    static int timeLine;
     int NOP =0; //  number of processes
     int Current_Process=0 ; // selected process
     int StartTime =0; // strt time of the process
     int DoneProcesses =0; // the finished process
    static Scanner in = new Scanner(System.in);
             // PHASE 2
    static ArrayList<Process> PCB = new ArrayList<>();
    //ArrayList<PCB> readyQ = new ArrayList<>();
    public static void main(String[] args) throws IOException 
    {
        Project227 obj = new Project227();
//        obj.select();
        //select();
        do
        {
            do
            {
                System.out.println("\n------Choose from the following menu:------");

                System.out.println("\n1. Enter process' information.");
                System.out.println("2. Report detailed information about each process and different scheduling criteria.");
                System.out.println("3. Exit the program.");         
                ch = in.nextInt();           
            }while(ch > 3 || ch < 1);

            switch (ch) 
            {
                case 1:
                    PCB.clear();
    //                StartTime=0;
    //                DoneProcesses=0;
    //                Current_Process=0;
                    obj.start();
                    //obj.select();
                break;

                case 2:
                    System.out.print(" \n !! Scheduale ORDER (GANTT CHART)!! \n");
                    obj.PRIORIY();
                    obj.Print();
                    //select();
                break;

                case 3:
                    System.out.print("Thank You !! ");
                break;
                
                //we will never reach here
//                default:
//                System.out.print("Invalid choice :(, Please Choose 1,2 or 3.");
            }
        }while(ch != 3);
    }
    
    //Start Program
    public void start() 
    {
        int temp;
        int pr = 0; 

        System.out.print("Enter the number of processes (P):");
        NOP = in.nextInt(); 

        for (int i = 0; i < NOP; i++) 
        {
            Process process = new Process();
            process.setpID(i);
            
            System.out.printf("Enter the Arrival time of %s (in ms):", process.pID);
            process.setarrivalT(in.nextInt());
            
            do{           
                System.out.printf("Enter %s's Priority (1 to 5):", process.pID);
                pr = in.nextInt(); 
            }while (pr > 5 || pr < 1);
        
            process.setPriority(pr);
            
            System.out.printf("Enter the CPU burst of process %s (in ms):", process.pID);
            temp = in.nextInt();           
            process.burst = temp;
            process.remaningT = temp;
            
            PCB.add(process);
        }
    }
    
    public void PRIORIY() 
    {
        int process_1 = 0;
        String ProcessID_L="";
        int location=0;
        int L_ARRIVAL_TIME ;

        // PREEMPTIVE Prio Q1
        while(DoneProcesses<PCB.size())//!PCB.isEmpty()
        { 
            int min =9;
            Current_Process=0;
            //in_flag=0,
            for (int i = 0; i < PCB.size(); i++) 
            {
                Process temp = PCB.get(i);
                if((temp.arrivalT <= StartTime) && (temp.processPriority < min) && (temp.remaningT > 0))// < to get just first process FCFS
                {
                    min = temp.processPriority;
                    L_ARRIVAL_TIME=PCB.get(i).getarrivalT();
                    Current_Process = i;
                    
                    //FCFS
                    for (int j = 0; j < PCB.size(); j++)
                    {
                        Process temp2 = PCB.get(j);
                        if(min == temp2.processPriority && temp2.arrivalT < L_ARRIVAL_TIME && (temp2.remaningT > 0))
                        {
                            Current_Process=j;
                        }
                    }
                }
            }
            if(Current_Process!=-1)
            {
                if(PCB.get(Current_Process).getStart_time() == -1)
                {
                    StartTime=max(StartTime,PCB.get(Current_Process).arrivalT);
                    if (process_1==0) 
                    {
                        PCB.get(Current_Process).setStart_time(StartTime) ;
                        process_1++;
                    }
                    else if (process_1!=0) 
                    {
                        PCB.get(Current_Process).setStart_time(StartTime+1) ;
                    }
                }
                if(location!=0)
                {
                    if ( ProcessID_L.equalsIgnoreCase(PCB.get(Current_Process).getpID())) 
                    {
                        System.out.print(" | CS |");
                        StartTime++;
                        ProcessID_L=PCB.get(Current_Process).getpID();
                    }
                }
                else if(location==0)
                {
                    location=2;
                    ProcessID_L=PCB.get(Current_Process).getpID();
                }

                PCB.get(Current_Process).setRemainT(PCB.get(Current_Process).remaningT-1);
                StartTime++;
                System.out.print(" | "+ PCB.get(Current_Process).getpID());

                if (PCB.get(Current_Process).remaningT==0 )
                {
                    DoneProcesses++;
                    PCB.get(Current_Process).setEnd_time(StartTime) ;
                }
            }
            else if (Current_Process==-1)
            {
                System.out.print(" | ");
                StartTime++;
            }
        //else
        }//while loop end

        for (int p = 0; p < PCB.size(); p++) //اختصار
        {
            Process temp = PCB.get(p);
            temp.calculateTurnAroundT();
            temp.calculateWaitingTime();
            temp.calculateResponseTime();
            
//            PCB.get(p).TotalTime = PCB.get(p).getEnd_time()-PCB.get(p).getarrivalT();
//            PCB.get(p).setWaiting_T(PCB.get(p).TotalTime -PCB.get(p).getburst());
//            PCB.get(p).setResponse(PCB.get(p).getStart_time()-PCB.get(p).getarrivalT());           
        }
    }
    
    public void Print() throws IOException
    {
        //PRINT CONSOLE
        
        System.out.println("\n \n Display Console Information : ");
        System.out.printf( "Process ID   "  +"Priority   " +"Arrival time   " +"CPU burst   " +"Start   "  +"End   " +"TotalTime   "  +"Waiting time   " +"Response time   "+ "\n");
        PCB.forEach((n) -> {
        System.out.printf("%s" + " "
        + "%d      " + "%d      " + "%d      " + "%d      "  + "%d      "  + "%d      " + "%d      "  + "%d      " 
        + "\n",
        n.getpID(),
        n.getPriority(),
        n.getarrivalT(),
        n.getburst(),
        
        n.getStart_time(),
        n.getEnd_time(),
        
        n.getTotalTime(),
        n.getWaiting_T(),
        n.getResponse()
        );
        });
        ///WRITE
        float  Ttotaltime=0, Twating=0,Tresponsetime=0;
        float averageWaitng=0, averageResponse_T=0 ,avgTotalTime=0;

        // Process Proc=new Process();
        BufferedWriter outputWriter;
        outputWriter = new BufferedWriter(new FileWriter("C:\\os1\\report.txt"));
        outputWriter.write(
        "Process ID  "  +
        "Priority  "  +
        "Arrival time  " +
        "CPU burst  "  +
     
        "Start time  "  +
        "End time  "  +
        
        "Turn around time  "  +
        "Waiting time  "  +
        "Response time  "
        );
        for (Process n:PCB) {
        Twating+=n.getWaiting_T();
        Ttotaltime+=n.getTotalTime();
        Tresponsetime+=n.getResponse();
        outputWriter.newLine();
        outputWriter.write(
        n.getpID() + "      " +
        n.getPriority()+ "      " +
        n.getarrivalT()+ "      " +
        n.getburst()+ "      " +
        
        n.getStart_time() + "      " +
        n.getEnd_time() + "      " +
        
        n.getTotalTime() + "      "+
        n.getWaiting_T() + "      "+
        n.getResponse()
        );
        outputWriter.newLine();
        }
        System.out.printf("\nAverage Waiting Time: %.2f" ,averageWaitng );
        System.out.printf("\nAverage Total Time: %.2f " , avgTotalTime );
        System.out.printf("\nAverage Response Time: %.2f " , averageResponse_T );

        averageWaitng=Twating/PCB.size();
        avgTotalTime=Ttotaltime/PCB.size();
        averageResponse_T=Tresponsetime/PCB.size();
        outputWriter.newLine();
        outputWriter.write("Average Waiting Time: " +averageWaitng );
        outputWriter.write("Average Total Time: " +avgTotalTime );
        outputWriter.write("Average Response Time: " +averageResponse_T );
        outputWriter.flush();
        outputWriter.close();
        
    }
}

