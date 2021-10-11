package Sample.AgentSystem;

import jade.core.*;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.*;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.*;

import java.io.*;  

public class Agent2 extends Agent
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
			addBehaviour(new myBehaviour(this));
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
	
	class myBehaviour extends CyclicBehaviour
	{
		myBehaviour(Agent a)
		{
			super(a);
		}
		
		public void action()
		{
			ACLMessage msg = myAgent.receive();
			
			if(msg!=null)
			{
				if(msg.getPerformative()==ACLMessage.REQUEST)
				{
					String dat = msg.getContent().toString();
					
					try (PrintWriter pw = new PrintWriter(new File("C:/Users/Pabasar/Agent Technology/New Jade/jade/JADE-all-4.5.0/JADE-src-4.5.0/jade/src/Sample/t1/dat.csv"))) 
					{
						pw.write(dat);
						sendMsg("Agent3","C:/Users/Pabasar/Agent Technology/New Jade/jade/JADE-all-4.5.0/JADE-src-4.5.0/jade/src/Sample/t1/dat.csv",ACLMessage.REQUEST);
					} 
					catch(FileNotFoundException e) 
					{
						e.printStackTrace();
					}
				}
			}
		}
		
	}
}
