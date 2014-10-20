package cn.edu.xiaorun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

public class Astar {
	static int  end[][] = new int [3][3]; 
	static int[][]  start=new int[3][3];
	
	//main函数
	public static void main(String[] args){
		inputN();
		Solve(start,end);
		
	}
	
	//用户输入
	public static void inputN(){
		String inputNum;
		int k = 0;
//		int end[][] = new int [3][3]; 
//		int[][] start=new int[3][3];
		
		for(int i=0;i<3;i++)
     	{   	
     		for(int j=0;j<3;j++)
     		{
     			start[i][j] = 0;
     		}    		
        }
		for(int i=0;i<3;i++)
     	{   	
     		for(int j=0;j<3;j++)
     		{
     			end[i][j] = k;
     		   k++;
     		}    		
        }
		try{
			System.out.println("请输入八数码初始值（0~8九个数字的任意序列）,如123045678");			
			BufferedReader br1=new BufferedReader(new InputStreamReader(System.in));
			inputNum=br1.readLine();
			int [] temp = new int[9];
			for(int i=0;i<9;i++){
				temp[i] = 0;
			}
			if(inputNum.length()!=9){
				System.out.println("字数不够！！");
				System.exit(0);
			}
			for(int i=0;i<9;i++){
				if(inputNum.charAt(i)>=48 && inputNum.charAt(i)<=56){
					int a =inputNum.charAt(i)-'0';
					if(temp[a]>=1){
						System.out.println("数字重复！");
						System.exit(0);
					}
					temp[a] +=1;
				}	
				else {
					System.out.println("有字符！");
					System.exit(0);
				}					
			}
			for(int i=0;i<9;i++){
				int a =inputNum.charAt(i)-'0';
				int l = i/3;
				int r = i%3;
				start[l][r] = a;
			}
			System.out.println("输入的初始序列为！");
			for(int i=0;i<3;i++)
	     	{   	
	     		for(int j=0;j<3;j++)
	     		{
	     			System.out.print(start[i][j]+" ");
	     		}    		
	     		System.out.println();
	        }
			
		}catch(IOException o){
			
		}
	}
	
	//A*算法
	public static boolean Solve(int [][]start,int [][]end)
	{
			Vector vc = new Vector(1,1);
	     	int step=0;
	     	Node mynode;
	     	//int source[][] = new int[3][3];
	     	//int dest[][] = new int [3][3]; 
	     	
	     	int i=0;
	     	int j=0;
	     	String space = " ";
	     	
	     	
//		检验算法是否有解		
//		if(!judge_arr(start,end))
//		{
//			System.out.println("该起始状态无法到达目标状态");
//			return false;
//		}
		     	
	     	long   start1   =   System.currentTimeMillis(); //开始时间    
	     	
	     	EightNumber en = new EightNumber();
	     		        	     	
	     	vc = en.process(start,end);//Astar算法，返回解路径上的所有node
	     	 	
	     	long   end1   =   System.currentTimeMillis();  //结束时间
	     	    	
	     	for(int k=0;k<vc.size();k++)
	     	{
	     		System.out.println("=============");
	     		System.out.println("第"+step+"步");
	     		step++;
	     	    mynode = (Node)vc.get((vc.size()-1)-k);

	     		for(i=0;i<3;i++)
	         	{
		       	
		     		for(j=0;j<3;j++)
		     		{
		     		  if(mynode.arr[i][j]!=0)
		     		  {
		     		  	System.out.print(""+mynode.arr[i][j]+" ");	     		  	
		     		  }
		     		  else
		     		  {	     	
		     	     	 System.out.print(""+space+" ");
		     	      } 
		     		}
		     		System.out.println();
	            }
	            
	     		
	     	}	
	     		
	     	
	     	System.out.println("=============");
	     	System.out.println("解答完成 @_@");

	
		return true;
		
	}
	
	
}
