package io.coresdk;

import java.util.List;
import java.util.ArrayList;

/**
 * Short-lived scoped JWT for agent-to-agent delegation.
 */
public class AgentToken {
    private String token = "";
    private int expiresInSeconds = 300;
    private List<String> agentChain = new ArrayList<>();

    public AgentToken() {}

    public AgentToken(String token, int expiresInSeconds, List<String> agentChain) {
        this.token = token;
        this.expiresInSeconds = expiresInSeconds;
        this.agentChain = agentChain;
    }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    public int getExpiresInSeconds() { return expiresInSeconds; }
    public void setExpiresInSeconds(int expiresInSeconds) { this.expiresInSeconds = expiresInSeconds; }
    public List<String> getAgentChain() { return agentChain; }
    public void setAgentChain(List<String> agentChain) { this.agentChain = agentChain; }

    @Override
    public String toString() {
        return "AgentToken{expiresInSeconds=" + expiresInSeconds + ", chain=" + agentChain + "}";
    }
}
