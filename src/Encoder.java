//author Aritra Dhar
//MT12004
//M.TECH CSE
//INFORMATION SECURITY
//IIIT-Delhi
//Image Encoder

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Encoder {

	public static void main(String[] args) throws Exception 
	{
				//change secret image location (secret message.jpg)
				File in_f=new File("C:\\work\\IIITD\\Monsoon 2012\\Foundation of Computer Security (CSE 545)\\Assignments\\3\\secret message.jpg");
				//change location for the carrier file
				File bin_f=new File("C:\\work\\IIITD\\Monsoon 2012\\Foundation of Computer Security (CSE 545)\\Assignments\\3\\Stegno_New\\Power_comp.txt");
				bin_f.createNewFile();
				FileWriter bwrite=new FileWriter(bin_f);
				
				try 
				{
					BufferedImage img=ImageIO.read(in_f);
					
					int hi=img.getHeight();
					int wd=img.getWidth();
								
					int [][]pixel_data=new int[wd*hi][3];
		
					System.out.println(hi);
					System.out.println(wd);
					
					int i,j,k=0;
					bwrite.append("POWER CONSUMPTION STATISTICS");
					bwrite.append("\n");
					bwrite.append("TOTAL TEST CASES:"+"("+wd+"*"+hi+")"+" many");
					bwrite.append("\n");
					bwrite.append("RATING DONE IN (0:255) GRADING");
					bwrite.append("\n");
					bwrite.append("HOUSE NO.  DAY  NOON  NIGHT");
					for(i=0;i<wd;i++)
					{
						for(j=0;j<hi;j++)
						{
							int clr=  img.getRGB(i,j);
							int  red   = (clr & 0x00ff0000) >> 16;  
						    int  green = (clr & 0x0000ff00) >> 8;  
						    int  blue  =  clr & 0x000000ff; 
						    				    
						    pixel_data[k][0]=red;                           
						    pixel_data[k][1]=green;                        
						    pixel_data[k][2]=blue;		
						    bwrite.append("\n");
							bwrite.append("HOUSE  "+(k+1)+"  "+red+"   "+green+"   "+blue);
							
							k++;
						}
					}
					//bwrite.append(Integer.toBinaryString(wd)+"\n"+Integer.toBinaryString(hi));  // Insert information about the dimention of the image
					
					System.out.println(k);
					bwrite.close();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
				System.out.print("Encoding Complete");
			}

}
