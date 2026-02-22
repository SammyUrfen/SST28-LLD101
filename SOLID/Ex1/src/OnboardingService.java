import java.util.*;

public class OnboardingService {
    private final StudentRepository repo;

    public OnboardingService(StudentRepository repo) { this.repo = repo; }

    private Printer printer = new Printer();
    private StudentParser parser = new StudentParser();
    private StudentValidator validator = new StudentValidator();

    // Intentionally violates SRP: parses + validates + creates ID + saves + prints.
    public void registerFromRawInput(String raw) {
        printer.printInput(raw);

        Map<String,String> data = parser.parse(raw);

        String name = data.getOrDefault("name", "");
        String email = data.getOrDefault("email", "");
        String phone = data.getOrDefault("phone", "");
        String program = data.getOrDefault("program", "");

        List<String> errors = validator.validate(name, email, phone, program);

        if (!errors.isEmpty()) {
            printer.printErrors(errors);
            return;
        }

        String id = IdUtil.nextStudentId(repo.count());
        StudentRecord rec = new StudentRecord(id, name, email, phone, program);

        repo.save(rec);

        printer.printSuccess(rec, repo);
    }
}
