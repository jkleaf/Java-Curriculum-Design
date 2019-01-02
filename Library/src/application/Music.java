package application;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
public class Music  {
	private static final Music Instance ;
	private static ArrayList<String> list;
	AudioClip clip =null;
	boolean  flag=true;
	File musicFile;
	URI uri;
	URL url;
	static {
	
			Instance=new Music();
	
	}
	public 	Music()  {
		list=new ArrayList<String>();
		String a;
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader("C:/Users/Lenovo/Desktop/¸èµ¥.txt"));
		
			while((a=br.readLine())!=null) {
				list.add(a);
			}
		
			br.close();}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
			}
	public void play() {
		int i=0;
		Random rand=new Random();
		i=rand.nextInt(list.size());
		musicFile = new File(list.get(i));
		uri = musicFile.toURI();
		try {
			url = uri.toURL();
		} catch (Exception e) {
	}
		clip=Applet.newAudioClip(url);
		if (clip != null)
			
			( (java.applet.AudioClip) clip).loop();
		
}
	public void stop() {
		if (clip != null)
			( (java.applet.AudioClip) clip).stop();
}
	public static Music getInstance()  {
		return Instance;
		
		
	}
}


