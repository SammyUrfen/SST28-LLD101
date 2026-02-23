public abstract class Exporter {
    // implied "contract" but not enforced (smell)
    public final ExportResult export(ExportRequest req) {
        if (req == null) {
            throw new IllegalArgumentException("ExportRequest must not be null");
        }
        if (req.title == null) {
            throw new IllegalArgumentException("title must not be null");
        }
        ExportResult result = doExport(req);
        if (result == null || result.bytes == null || result.contentType == null) {
            throw new IllegalStateException("Exporter produced invalid result");
        }
        return result;
    }

    protected abstract ExportResult doExport(ExportRequest req);
}
