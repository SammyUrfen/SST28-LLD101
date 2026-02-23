public class CgrRule implements EligibilityRule {
    @Override
    public String check(StudentProfile student) {
        if (student.cgr < 7.0) {
            return "CGR below 7.0";
        }
        return null;
    }
}
