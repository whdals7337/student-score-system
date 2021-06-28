package grade;

public class GradeStrategyFactory {
    public GradeStrategy getGradeStrategy(int strategyType) {
        switch (strategyType) {
            case 0:
                return new BasicGradeStrategy();
            case 1:
                return new MajorGradeStrategy();
            case 2:
                return new PassFailGradeStrategy();
            default:
                return null;
        }
    }
}
