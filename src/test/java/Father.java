import net.sf.ehcache.pool.sizeof.AgentSizeOf;

public class Father {
    public static void main(String[] args) {
        AgentSizeOf agentSizeOf = new AgentSizeOf();
        Father father = new Father();
        System.out.println("father size :"+agentSizeOf.sizeOf(father));
    }
}
