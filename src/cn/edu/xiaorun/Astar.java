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
		String inputEnd;
		int k = 1;
//		int end[][] = new int [3][3]; 
//		int[][] start=new int[3][3];
		
		for(int i=0;i<3;i++)
     	{   	
     		for(int j=0;j<3;j++)
     		{
     			start[i][j] = 0;
     		}    		
        }
		

		try{
			System.out.println("请输入八数码初始值（0~8九个数字的任意序列）,如283164705");			
			BufferedReader br1=new BufferedReader(new InputStreamReader(System.in));
			inputNum=br1.readLine();
			
			System.out.println("请输入八数码目标值（0~8九个数字的任意序列）,如123804765");	
			inputEnd=br1.readLine();
			int [] temp = new int[9];
			int [] temp2 = new int[9];
			for(int i=0;i<9;i++){
				temp[i] = 0;
				temp2[i] = 0;
			}
			if(inputNum.length()!=9){
				System.out.println("字数不够！！");
				System.exit(0);
			}
			
			//输入初始序列校验
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
			//输入目标序列校验
			for(int i=0;i<9;i++){
				if(inputEnd.charAt(i)>=48 && inputEnd.charAt(i)<=56){
					int a =inputEnd.charAt(i)-'0';
					if(temp2[a]>=1){
						System.out.println("数字重复！");
						System.exit(0);
					}
					temp2[a] +=1;
				}	
				else {
					System.out.println("有字符！");
					System.exit(0);
				}					
			}
			
			//初始序列
			for(int i=0;i<9;i++){
				int a =inputNum.charAt(i)-'0';
				int l = i/3;
				int r = i%3;
				start[l][r] = a;
			}
			
			//目标序列
			for(int i=0;i<9;i++){
				int a =inputEnd.charAt(i)-'0';
				int l = i/3;
				int r = i%3;
				end[l][r] = a;
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
			
			System.out.println("输入的目标序列为！");
			for(int i=0;i<3;i++)
	     	{   	
	     		for(int j=0;j<3;j++)
	     		{
	     			System.out.print(end[i][j]+" ");
	     		}    		
	     		System.out.println();
	        }
			
			//目标序列，默认为123456780
//			for(int i=0;i<3;i++)
//	     	{   	
//	     		for(int j=0;j<3;j++)
//	     		{
//	     			end[i][j] = k;
//	     		    k++;
//	     		}    		
//	        }
//			end[2][2] = 0;
			
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
	     	
	     	
		//检验算法是否有解		
		if(!judge_arr(start,end))
		{
			System.out.println("该起始状态无法到达目标状态");
			return false;
		}
		     	
	     	long   start1   =   System.currentTimeMillis(); //开始时间    
	     	
	     	EightNumber en = new EightNumber();
	     		        	     	
	     	vc = en.process(start,end);//Astar算法，返回解路径上的所有node
	     	 	
	     	long   end1   =   System.currentTimeMillis();  //结束时间
	     	long length = end1-start1;
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
	     	System.out.println("处理时间为"+length+"毫秒");
	
		return true;
		
	}
	
	  public static boolean judge_arr(int start[][],int end[][])
		{
			int count1 = 0;
			int count2 = 0;
			int m = 0;
			int starts[] = new int[9];
			int ends[] = new int[9];
			
			for(int i=0;i<3;i++)
			{
				for(int j=0;j<3;j++)
				{
					starts[m] = start[i][j];
					ends[m] = end[i][j];
					m++;
				}
				
			}
			
			
			for(int i = 0;i<9;i++)
			{
				for(int j = 0;j<i;j++)
	 			    if(starts[i]<starts[j]&&starts[i]!=0) 			    
	   				count1++;
			}
			
			
			for(int i = 0;i<9;i++)
			{
				for(int j = 0;j<i;j++)
	 			    if(ends[i]<ends[j]&&ends[i]!=0)
	   				count2++;
	   		}
	   		
			System.out.println("START="+count1);
			System.out.println("END="+count2);
			
			if(count1%2==count2%2)
			{
				System.out.println("------------有解------------");
				return true;
			}
			else
			{
				System.out.println("-----------无解--------------");	
				return false;
			}
		}
}
