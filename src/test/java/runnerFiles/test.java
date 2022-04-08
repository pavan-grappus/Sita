package runnerFiles;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections4.map.HashedMap;

import resource.EnvironmentDetails;

public class test {

	public test() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {

		List<String> actualarrayText = new ArrayList<String>();
		
		actualarrayText.add("a");
		actualarrayText.add("a");
		actualarrayText.add("a");
		actualarrayText.add("a");
		
		
		if(actualarrayText.stream().allMatch(s->s.contentEquals("a")))
		{
			System.out.println("pass");
		}
		else
		{
			System.out.println("fail");
		}
		
		
		

}
}
