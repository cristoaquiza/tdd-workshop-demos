import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AssignmentMarkerSolutionTest {

    class NeverPlagiarised extends PlagiarismService {
        boolean assignmentIsPlagiarised(Assignment assignment) {
            return false;
        }
    }

    class AlwaysPlagiarised extends PlagiarismService {
        boolean assignmentIsPlagiarised(Assignment assignment) {
            return true;
        }
    }

    @Test
    public void itScores5WhenNotPlagiarisedAndHalfCorrect() {
        Assignment assignment = new Assignment(false, true);
        PlagiarismService neverPlagiarised = new NeverPlagiarised();
        FakeStudentEmailService fakeStudentEmailService = new FakeStudentEmailService();

        int mark = new AssignmentMarker().markAssignment(assignment, neverPlagiarised, fakeStudentEmailService);

        assertEquals(fakeStudentEmailService.wasCalledWith(assignment, mark), true);
        assertEquals(5, mark);
    }

    @Test
    public void itScores10WhenNotPlagiarisedAndTotallyCorrect() {
        Assignment assignment = new Assignment(true, false);
        PlagiarismService neverPlagiarised = new NeverPlagiarised();
        FakeStudentEmailService fakeStudentEmailService = new FakeStudentEmailService();

        int mark = new AssignmentMarker().markAssignment(assignment, neverPlagiarised, fakeStudentEmailService);

        assertEquals(fakeStudentEmailService.wasCalledWith(assignment, mark), true);
        assertEquals(10, mark);
    }

    @Test
    public void itScores0WhenPlagiarised() {
        Assignment assignment = new Assignment(false, true);
        PlagiarismService neverPlagiarised = new AlwaysPlagiarised();
        FakeStudentEmailService fakeStudentEmailService = new FakeStudentEmailService();

        int mark = new AssignmentMarker().markAssignment(assignment, neverPlagiarised, fakeStudentEmailService);

        assertEquals(fakeStudentEmailService.wasCalledWith(assignment, mark), true);
        assertEquals(0, mark);
    }

    class FakeStudentEmailService extends StudentEmailService {

        private Assignment realAssignment = null;
        private int realMark;

        boolean wasCalledWith(Assignment expectedAssignment, int expectedMark) {
            return expectedAssignment.equals(realAssignment) && realMark == expectedMark;
        }

        @Override
        void emailStudentTheirResults(Assignment assignment, int mark) {
            this.realAssignment = assignment;
            this.realMark = mark;
        }
    }
}