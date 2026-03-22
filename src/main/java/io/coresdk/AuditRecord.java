package io.coresdk;

public class AuditRecord {
    private final String eventId;
    private final int sequenceId;
    private final String recordHash;

    public AuditRecord(String eventId, int sequenceId, String recordHash) {
        this.eventId = eventId;
        this.sequenceId = sequenceId;
        this.recordHash = recordHash;
    }

    public String getEventId() { return eventId; }
    public int getSequenceId() { return sequenceId; }
    public String getRecordHash() { return recordHash; }

    @Override
    public String toString() {
        return "AuditRecord{eventId='" + eventId + "', sequenceId=" + sequenceId + ", recordHash='" + recordHash + "'}";
    }
}
