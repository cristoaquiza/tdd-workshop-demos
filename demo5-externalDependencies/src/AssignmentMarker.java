public class AssignmentMarker {
    int markAssignment(Assignment assignment, PlagiarismService plagiarismService, StudentEmailService emailService) {
        int mark = -1;

        if (plagiarismService.assignmentIsPlagiarised(assignment)) {
            mark = 0;
        } else if(assignment.isHalfCorrect()) {
            mark = 5;
        } else if (assignment.isTotallyCorrect()) {
            mark = 10;
        }

        emailService.emailStudentTheirResults(assignment, mark);

        return mark;
    }
}
