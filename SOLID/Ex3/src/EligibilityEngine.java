import java.util.*;

public class EligibilityEngine {
    private final FakeEligibilityStore store = new FakeEligibilityStore();;
    private final List<EligibilityRule> rules;


    public EligibilityEngine() {
        this.rules = new ArrayList<>();
        this.rules.add(new CgrRule());
        this.rules.add(new AttendanceRule());
        this.rules.add(new CreditsRule());
        this.rules.add(new DisciplineRule());
    }
    public void runAndPrint(StudentProfile s) {
        EligibilityEngineResult r = evaluate(s); // giant conditional inside
        ReportPrinter.print(s, r);
        store.save(s.rollNo, r.status);
    }

    public EligibilityEngineResult evaluate(StudentProfile student) {
        List<String> reasons = new ArrayList<>();
        for (EligibilityRule rule : rules) {
            String failure = rule.check(student);
            if (failure != null) {
                reasons.add(failure);
            }
        }

        boolean isEligible = reasons.isEmpty();
        String result = isEligible ? "ELIGIBLE" : "NOT_ELIGIBLE";

        return new EligibilityEngineResult(result, reasons);
    }
}

class EligibilityEngineResult {
    public final String status;
    public final List<String> reasons;
    public EligibilityEngineResult(String status, List<String> reasons) {
        this.status = status;
        this.reasons = reasons;
    }
}
