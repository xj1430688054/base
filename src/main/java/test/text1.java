package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import com.couchbase.client.deps.io.netty.util.internal.StringUtil;

public class text1 {
	public static void main(String[] args) {
		File file = new File("D:/workplave/two/file/src/file/company.sql");
		String source = "D:/workplave/two/file/src/file/company.sql";

			try {
				FileReader m=new FileReader(source);
				BufferedReader reader=new BufferedReader(m);
				
				while(true) {
					String nextline=reader.readLine();
					if(nextline==null) break;
//					if(StringUtil.)
					System.out.println("got:"+nextline);
				}
				reader.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
	    
	}

}
