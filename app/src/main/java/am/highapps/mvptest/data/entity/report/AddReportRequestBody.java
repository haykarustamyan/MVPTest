package am.highapps.mvptest.data.entity.report;

public class AddReportRequestBody {

    public static final int FORUM_COMMENT = 2;
    public static final int FORUM_COMMENT_REPLY = 3;

    private String reasonMessage;
    private int reportType;
    private int typeId;

    public AddReportRequestBody(String reasonMessage, int reportType, int typeId) {
        this.reasonMessage = reasonMessage;
        this.reportType = reportType;
        this.typeId = typeId;
    }

    public String getReasonMessage() {
        return reasonMessage;
    }

    public void setReasonMessage(String reasonMessage) {
        this.reasonMessage = reasonMessage;
    }

    public int getReportType() {
        return reportType;
    }

    public void setReportType(int reportType) {
        this.reportType = reportType;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }
}
