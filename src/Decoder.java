//author Aritra Dhar
//MT12004
//M.TECH CSE
//INFORMATION SECURITY
//IIIT-Delhi
//Image Decoder

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;


public class Decoder
{
	public static void main(String[] args) throws Exception 
	{
		
		
		BufferedReader wd_b=new BufferedReader(new InputStreamReader(System.in));
		BufferedReader hi_b=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter weidth and height of the image retrived from Power_comp.txt :: ");
		String wd_s=wd_b.readLine();
		String hi_s=hi_b.readLine();
		int hi=Integer.parseInt(hi_s);
		int wd=Integer.parseInt(wd_s);              
	
		//provide image location where the image will be created
		File img_ret=new File("C:\\work\\IIITD\\Monsoon 2012\\Foundation of Computer Security (CSE 545)\\Assignments\\3\\Stegno_New\\image_ret.jpg");
		img_ret.createNewFile();
		
		BufferedImage img_copy=new BufferedImage(wd, hi, BufferedImage.TYPE_INT_RGB);
		
		//provide location for the carrier file
		FileInputStream in_bin_img=new FileInputStream("C:\\work\\IIITD\\Monsoon 2012\\Foundation of Computer Security (CSE 545)\\Assignments\\3\\Stegno_New\\Power_comp.txt");
		BufferedReader b_img=new BufferedReader(new InputStreamReader(new DataInputStream(in_bin_img)));
		
		int i=0,j=0,count=0;
		String input;
		//char a;
		int []pix_data=new int[wd*hi];
		for(int k=0;k<wd*hi;k++)
		{
			pix_data[i]=0x00000000;
		}
		@SuppressWarnings("unused")
		String a1,a2,a3,a4,a5;
		int red,green,blue;
		int internal=0;
		
		while((input=b_img.readLine())!=null)
		{
			
			if(count>=4)
			{
				StringTokenizer st = new StringTokenizer(input, " ");
				//int temp=0x000000;
				while(st.hasMoreElements())
				{
					a1=st.nextToken();
					a2=st.nextToken();
					a3=st.nextToken();
					a4=st.nextToken();
					a5=st.nextToken();
					
					red=(0x00000000|Integer.parseInt(a3))<<16;
					green=(0x00000000|Integer.parseInt(a4))<<8;
					blue=(0x00000000|Integer.parseInt(a5));
					
					//System.out.println(green);
					
					pix_data[internal]=(pix_data[internal] | red | green | blue);
					//System.out.print(pix_data[internal]+" "+internal+"\n");
					//System.out.println(a3);
					//System.out.println(a4);
					//System.out.println(a5);
					internal++;
				}
				
			}
			count++;
		}
		int t=0;
		
		for(i=0;i<wd;i++)
		{
			for(j=0;j<hi;j++)
			{	
				img_copy.setRGB(i, j, pix_data[t]);
				t++;
			}
		}
		//System.out.print(t);
		
		ImageIO.write(img_copy,"jpg",img_ret);
		
		in_bin_img.close();
		b_img.close();
		
		System.out.print("Secret message Recoverded");
	}

}

