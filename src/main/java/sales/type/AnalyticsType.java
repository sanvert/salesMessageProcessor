package sales.type;

/**
 * Created by sanver.
 */
public enum AnalyticsType {
    INTERIM_REPORT(10),
    GENERAL_REPORT(50),
    EMPTY_REPORT(-1);

    private int messageFrequency;

    AnalyticsType(int frequency) {
        messageFrequency = frequency;
    }

    public int getMessageFrequency() {
        return messageFrequency;
    }
}
