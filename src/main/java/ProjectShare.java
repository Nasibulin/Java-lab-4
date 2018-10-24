public interface ProjectShare {

    public default double getProjectBonus() {
        return 0;
    }

    public default void setProject(Project project) {

    }

    public default Project getProject() {
        return null;
    }

}