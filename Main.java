import java.util.Scanner;
import java.io.*;

public class Main {
	int players;
	int[][] schedule;
	int base[];

	public void call(){
		base=new int[players];
		if(players%2!=0){
			schedule=new int[players][players];
			playodd();
		}
		else{
			schedule=new int[players-1][players];
			playeven();
		}
	}
	public void output(){
		if(players>=10){
			try{
				FileWriter writer = new FileWriter("output.txt",true);
				for(int i=0;i<schedule.length;i++){
					int pl=i+1;
					writer.write(Integer.toString(pl));
					for(int j=0;j<schedule[i].length;j++){
						if(schedule[i][j]==0){
							writer.write(":-");
						}
						else
							writer.write(":"+schedule[i][j]);
					}
					writer.write("\n");
				}
				for(int h=0;h<2*players;h++)
					writer.write("#");
				writer.write("\n");
				writer.close();
				System.out.println("schedule stored in output.txt");
			}
			catch(FileNotFoundException e){
				System.out.println("File Not found");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		else{
			for(int i=0;i<schedule.length;i++){
				System.out.print(i+1);
				for(int j=0;j<schedule[i].length;j++){
					if(schedule[i][j]==0){
						System.out.print(":-");
					}
					else
						System.out.print(":"+schedule[i][j]);
				}
				System.out.println();
			}
			System.out.println();

		}
	}
	public void playodd(){
		for(int i=0;i<players;i++){
			base[i]=(i+1);
		}
		for(int days=0;days<players;days++){
			for(int k=0;k<players/2;k++){
				schedule[days][base[k]-1]=base[players-k-1];
				schedule[days][base[players-k-1]-1]=base[k];
			}
			schedule[days][base[players/2]-1]=0;

			int temp=base[players-1];
			for(int i=players-1;i>0;i--){
				base[i]=base[i-1];
			}
			base[0]=temp;	
		}
	}
	public void playeven(){
		for(int i=0;i<players;i++){
			base[i]=(i+1);
		}
		for(int days=0;days<players-1;days++){
			for(int k=0;k<players/2;k++){
				schedule[days][base[k]-1]=base[players-k-1];
				schedule[days][base[players-k-1]-1]=base[k];
			}
			int temp=base[players-2];
			for(int i=players-2;i>0;i--){
				base[i]=base[i-1];
			}
			base[0]=temp;

		}
	}
	public static void main(String args[]){
		Scanner sc=new Scanner(System.in);
		System.out.println("Select an option\n1. Input by File\n2. Input by Console");
		int choice=sc.nextInt();
		String filename;
		Main x;
		try{
			// Taking input options
			if(choice==1){
				System.out.println("Enter the name of the input file");
				filename=sc.next();
				try {
					//filename="C:/Users/rgrac/Desktop/Documents/MS/FOA/PA2/"+filename;
					FileReader fileReader=new FileReader(filename);
					BufferedReader bufferedReader=new BufferedReader(fileReader);
					String line="";
					while((line = bufferedReader.readLine()) != null) {
						x=new Main();
						int xx=Integer.parseInt(line);
						x.players=xx;
						x.call();
						x.output();
					}
					bufferedReader.close();
				}
				catch(FileNotFoundException ex) {
					System.out.println("Unable to open file '" +filename + "'");
				}
				catch(IOException ex) {
					System.out.println( "Error reading file '" + filename + "'");
				}
			}
			else if(choice==2){
				int choice2=1;
				do{
					System.out.println("Enter number of players");
					x=new Main();
					x.players=sc.nextInt();
					x.call();
					x.output();
					System.out.println("select (1/0) to continue");
					choice2=sc.nextInt();
				}while(choice2==1);

			}
		}
		catch(Exception e){	
		}
	}

}
