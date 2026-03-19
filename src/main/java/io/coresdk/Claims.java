package io.coresdk;

import java.util.List;

public class Claims {
    private String sub;
    private String tenantId;
    private List<String> roles;
    private long exp;

    public Claims() {}

    public Claims(String sub, String tenantId, List<String> roles, long exp) {
        this.sub = sub;
        this.tenantId = tenantId;
        this.roles = roles;
        this.exp = exp;
    }

    public String getSub() { return sub; }
    public void setSub(String sub) { this.sub = sub; }

    public String getTenantId() { return tenantId; }
    public void setTenantId(String tenantId) { this.tenantId = tenantId; }

    public List<String> getRoles() { return roles; }
    public void setRoles(List<String> roles) { this.roles = roles; }

    public long getExp() { return exp; }
    public void setExp(long exp) { this.exp = exp; }
}
