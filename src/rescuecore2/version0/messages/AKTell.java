package rescuecore2.version0.messages;

import rescuecore2.worldmodel.EntityID;

/**
   An agent TELL command.
 */
public class AKTell extends AgentCommand {
    private RawDataComponent data;

    /**
       Create an empty AKTell command.
     */
    AKTell() {
        super("AK_TELL", MessageConstants.AK_TELL);
        init();
    }

    /**
       Construct a tell command.
       @param agent The ID of the agent issuing the command.
       @param data The content of the command.
     */
    public AKTell(EntityID agent, byte[] data) {
        super("AK_TELL", MessageConstants.AK_TELL, agent);
        init();
        this.data.setData(data);
    }

    /**
       Get the content of the message.
       @return The message content.
     */
    public byte[] getContent() {
        return data.getData();
    }

    private void init() {
        data = new RawDataComponent("Message");
        addMessageComponent(data);
    }
}