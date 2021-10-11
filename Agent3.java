package Sample.AgentSystem;

import jade.core.*;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.*;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.*;

import java.io.*;  
import java.util.*;

public class Agent3 extends Agent
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
					String fileName = msg.getContent().toString();
					
					try
					{
						Scanner sc = new Scanner(new File(fileName));  
						sc.useDelimiter(",");   
						
						while (sc.hasNext())  
						{  
							System.out.print(sc.next()+" ");  
						}  
						
						sc.close();  
					}
					catch(IOException e)
					{
						e.printStackTrace();
					}
				}
			}
		}
		
	}
}
