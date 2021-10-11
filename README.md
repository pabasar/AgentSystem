# Agents
## Agent system using Java Agent Development Framework (JADE)

Contains 3 Agents. Agent 1 read data on data.txt file and send data to Agent 2. Then Agent 2 write the received data on a CSV file with comma delimiter. Then Agent 2 send CSV file name to Agent 3. Then Agent 3 read data on CSV file and displays that data.

Output as follows.

![agent](https://user-images.githubusercontent.com/49782156/136830504-224ced25-bdf5-41f8-b94b-618f21bee143.PNG)

To compile : javac -classpath lib\jade.jar -d classes src\Sample\AgentSystem\*.java
To Run : java -cp lib\jade.jar;classes jade.Boot -gui
