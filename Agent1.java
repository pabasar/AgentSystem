package Sample.AgentSystem;

import jade.core.*;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.*;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.*;

import java.io.*;  

public class Agent1 extends Agent
{
	protected void setup()
	{
		ServiceDescription sd = new ServiceDescription();
		DFAgentDescription dfd = new DFAgentDescription();
		
		sd.setName(getLocalName());
		sd.setType("SendingMsg");
		dfd.setName(getAID());
		dfd.addServices(sd);
		
		try
		{
			DFService.register(this,dfd);
			
			try
			{
				File f=new File("C:/Users/Pabasar/Agent Technology/New Jade/jade/JADE-all-4.5.0/JADE-src-4.5.0/jade/src/Sample/t1/data.txt");    
				FileReader fr=new FileReader(f);   
				BufferedReader br=new BufferedReader(fr);  
				
				StringBuffer sb=new StringBuffer();    
				String line; 
				
				while((line=br.readLine())!=null)  
				{  
					sb.append(line);      
					sb.append("\n");     
				} 
				
				fr.close(); 
				
				String dat = sb.toString();
				//System.out.println(dat);
				
				sendMsg("Agent2",dat,ACLMessage.REQUEST);
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}	
		}
		catch(FIPAException e)
		{
			System.out.println(e);
		}
	}
	
	public void sendMsg(String rec,String msg,int p)
	{
		ACLMessage acl = new ACLMessage();
		
		AID r = new AID(rec,AID.ISLOCALNAME);
		acl.addReceiver(r);
		acl.setContent(msg);
		acl.setPerformative(p);
		send(acl);
	}
}
